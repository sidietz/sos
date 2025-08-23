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

import de.oberamsystems.sos.model.MyService;

public class SystemdWatchdog {
	
	private String shellCommand;
	private MyService service;
	
	public SystemdWatchdog(MyService svc) {
		this.service = svc;
		this.shellCommand = svc.getName();
	}
	
	public MyService check() {
		try {
			// process = Runtime.getRuntime().exec("/bin/bash ps aux");
			// process = Runtime.getRuntime().exec("ping 8.8.8.8");
			/**/
			List<ProcessBuilder> builders = Arrays.asList(new ProcessBuilder("systemctl", "status", shellCommand),
					new ProcessBuilder("grep", "running"));

			List<Process> processes = ProcessBuilder.startPipeline(builders);
			Process last = processes.get(processes.size() - 1);

			// List<String> output = last.getInputStream();
			BufferedReader r = new BufferedReader(new InputStreamReader(last.getInputStream()));
			List<String> lines = r.lines().collect(Collectors.toList());
			lines.removeLast(); // remove ps aux command

			String s = lines.getFirst().trim().replaceAll("\\s+", " ");
			/*System.out.println("ps aux output");
			System.out.println(s);
			*/
			List<String> elems = Arrays.asList(s.split(" "));
			// System.out.println(elems);
			try {
				String date = elems.get(5);
				String time = elems.get(6);
				String datetime = date + "T" + time + "Z";
				service.setRunning(true);
				Instant currentTime = Instant.now();
				Instant instant2 = Instant.parse(datetime);
				Duration d = Duration.between(currentTime, instant2);
				service.setRuntime(d);
				service.setRuntime2(datetime);
			} catch (NoSuchElementException e) {
				service.setRunning(false);
				System.out.println("not running");
			}

			/*
			 * builder.command("ps", "aux", "\\|", "grep", shellCommand); Process process =
			 * builder.start(); ExecutorService executor =
			 * Executors.newSingleThreadExecutor();
			 * 
			 * StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(),
			 * System.out::println); Future<?> future = executor.submit(streamGobbler); int
			 * exitCode = process.waitFor(); future.get(100, TimeUnit.SECONDS);
			 * System.out.println(exitCode); executor.close();
			 */

		} catch (IOException | NoSuchElementException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			service.setRunning(false);
			//System.out.println("not running");
		}

		return service;
	}

}
