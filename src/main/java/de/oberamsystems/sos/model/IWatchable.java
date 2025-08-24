package de.oberamsystems.sos.model;

import java.time.Duration;

public interface IWatchable {

	Duration getRuntime();

	void setRuntime(Duration runtime);

	boolean isRunning();

	void setRunning(boolean isRunning);

}