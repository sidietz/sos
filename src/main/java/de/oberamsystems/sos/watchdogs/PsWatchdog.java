package de.oberamsystems.sos.watchdogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oberamsystems.sos.model.MyProcess;

public class PsWatchdog {
	
	private static final Logger log = LoggerFactory.getLogger(PsWatchdog.class);

	private String shellCommand;
	private MyProcess proc;

	public PsWatchdog(String shellCommand) {
		this.shellCommand = shellCommand;
	}

	public PsWatchdog(MyProcess proc) {
		this.proc = proc;
		this.shellCommand = proc.getName();
	}

	public MyProcess check() {
		try {
			// process = Runtime.getRuntime().exec("/bin/bash ps aux");
			// process = Runtime.getRuntime().exec("ping 8.8.8.8");
			//ps -eo pid,lstart,etime,command | grep thunderbird
			/**/
			List<ProcessBuilder> builders = Arrays.asList(new ProcessBuilder("ps", "aux"),
					new ProcessBuilder("grep", shellCommand));

			List<Process> processes = ProcessBuilder.startPipeline(builders);
			Process last = processes.get(processes.size() - 1);

			// List<String> output = last.getInputStream();
			BufferedReader r = new BufferedReader(new InputStreamReader(last.getInputStream()));
			List<String> lines = r.lines().collect(Collectors.toList());
			lines.removeLast(); // remove ps aux command

			
			/*System.out.println("ps aux output");
			System.out.println(s);
			*/
			
			// System.out.println(elems);
			try {
				
				String s = lines.getFirst().trim().replaceAll("\\s+", " ");
				List<String> elems = Arrays.asList(s.split(" "));
				String started = elems.get(8);
				String cmd = elems.get(10);
				
				Instant currentTime = Instant.now();
				Instant instant2 = Instant.parse("2019-04-21T05:25:00Z");
				
				proc.setRunning(true);
			} catch (NoSuchElementException e) {
				proc.setRunning(false);
				log.warn(String.format("Process '%s' not running!", shellCommand));
				//System.out.println("not running");
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
			proc.setRunning(false);
			//System.out.println("not running");
		}

		return proc;
	}

}
