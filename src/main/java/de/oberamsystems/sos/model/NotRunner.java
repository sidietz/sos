package de.oberamsystems.sos.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotRunner {

	private Watchable watchable;
	private LocalDateTime notRunningSince;
	private String notRunningSince2;
	private Duration duration;

	public NotRunner() {
	}

	public NotRunner(Watchable watchable, LocalDateTime notRunningSince, Duration duration) {
		this.watchable = watchable;
		this.notRunningSince = notRunningSince;
		this.notRunningSince2 = localDateTimeToString(notRunningSince);
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
		this.notRunningSince2 = localDateTimeToString(notRunningSince);
	}

	public String getNotRunningSince2() {
		return notRunningSince2;
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
	
	private static String localDateTimeToString(LocalDateTime ldt) {
		if (ldt == null) {
			return "";
		} else {
			return ldt.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));
		}
	}

}
