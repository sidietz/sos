package de.oberamsystems.sos.model;

import java.time.Duration;

public abstract class Watchable {

	private String runtime2;
	private Duration runtime;
	private boolean isRunning;
	
	Watchable() {
		super();
		this.runtime = null;
		this.runtime2 = "0";
		this.isRunning = true;
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
