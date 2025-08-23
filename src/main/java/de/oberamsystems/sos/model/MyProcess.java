package de.oberamsystems.sos.model;

import java.time.Duration;

import jakarta.persistence.*;

@Entity
public class MyProcess {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String command;
	private String runtime2;
	private Duration runtime;
	private boolean isRunning;

	protected MyProcess() {
	}

	public MyProcess(String name, String command) {
		this.name = name;
		this.command = command;
		this.runtime = null;
		this.runtime2 = "0";
		this.isRunning = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getRuntime2() {
		return runtime2;
	}

	public void setRuntime2(String runtime2) {
		this.runtime2 = runtime2;
	}

	public Duration getRuntime() {
		return runtime;
	}

	public void setRuntime(Duration runtime) {
		this.runtime = runtime;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

}
