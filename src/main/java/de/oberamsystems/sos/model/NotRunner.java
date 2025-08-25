package de.oberamsystems.sos.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class NotRunner {

	private Watchable watchable;
	private LocalDateTime notRunningSince;
	private Duration duration;

	public NotRunner() {
	}

	public NotRunner(Watchable watchable, LocalDateTime notRunningSince, Duration duration) {
		this.watchable = watchable;
		this.notRunningSince = notRunningSince;
		this.duration = duration;
	}

	public Watchable getWatchable() {
		return watchable;
	}

	public void setWatchable(Watchable watchable) {
		this.watchable = watchable;
	}

	public LocalDateTime getNotRunningSince() {
		return notRunningSince;
	}

	public void setNotRunningSince(LocalDateTime notRunningSince) {
		this.notRunningSince = notRunningSince;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	@Override
	public boolean equals(Object other) {
		if ((other == null) || (getClass() != other.getClass())) {
			return false;
		} else {
			return watchable.equals(((NotRunner) other).watchable); // && age.equals(other.age);
		}
	}

	@Override
	public String toString() {
		return String.format("NotRunner[Watchable=%s, name='%s', kind='%s']", watchable.toString(), "", "");
	}

}
