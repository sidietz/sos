package de.oberamsystems.sos.model;

import java.time.Duration;
import java.time.LocalDateTime;

//import jakarta.persistence.*;

//@Entity
public class NotRunner {
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	*/
	//@OneToOne//(cascade = CascadeType.PERSIST)
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
	
	@Override
	public String toString() {
		return String.format("NotRunner[Watchable=%s, name='%s', kind='%s']", watchable.toString(), "", "");
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
	public boolean equals(Object other){
	    boolean result;
	    if((other == null) || (getClass() != other.getClass())){
	        result = false;
		}
	    else {
	        result = watchable.equals(((NotRunner) other).watchable); //&&  age.equals(other.age);
	    }

	    return result;
	}
	


}
