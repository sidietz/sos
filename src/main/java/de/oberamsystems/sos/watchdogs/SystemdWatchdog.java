package de.oberamsystems.sos.watchdogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oberamsystems.sos.model.MyService;

public class SystemdWatchdog {
	
	private static final Logger log = LoggerFactory.getLogger(SystemdWatchdog.class);

	
	private String shellCommand;
	private MyService service;
	
	public SystemdWatchdog(MyService svc) {
		this.service = svc;
		this.shellCommand = svc.getName();
	}
	
	public void check() {
		try {

			List<ProcessBuilder> builders = Arrays.asList(new ProcessBuilder("systemctl", "status", shellCommand),
					new ProcessBuilder("grep", "running"));

			List<Process> processes = ProcessBuilder.startPipeline(builders);
			Process last = processes.get(processes.size() - 1);

			BufferedReader r = new BufferedReader(new InputStreamReader(last.getInputStream()));
			List<String> lines = r.lines().collect(Collectors.toList());

			String s = lines.getFirst().trim().replaceAll("\\s+", " ");
			List<String> elems = Arrays.asList(s.split(" "));

			try {
				String date = elems.get(5);
				String time = elems.get(6);
				service.setRunning(true);
				String datetime = date + "T" + time + "Z";				
				Instant currentTime = Instant.now();
				Instant instant2 = Instant.parse(datetime);
				Duration d = Duration.between(currentTime, instant2);
				service.setRuntime(d);
				service.setRuntime2(datetime);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				service.setRunning(false);
				log.info(String.format("Service '%s' not running!", service));
			}
		} catch (IOException | NoSuchElementException e) {
			service.setRunning(false);
		}

		return;
	}

}
