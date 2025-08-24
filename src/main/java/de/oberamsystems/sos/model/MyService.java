package de.oberamsystems.sos.model;

import java.time.Duration;

import jakarta.persistence.*;

@Entity
public class MyService implements IWatchable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String runtime2;
	private Duration runtime;
	private boolean isRunning;

	protected MyService() {
	}
	
	public MyService(String name) {
		this.name = name;
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
