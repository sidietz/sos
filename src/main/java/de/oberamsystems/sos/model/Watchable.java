package de.oberamsystems.sos.model;

import java.time.Duration;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass
public abstract class Watchable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	protected String name;
	protected String kind;
	protected String runtime2;
	protected Duration runtime;
	protected boolean isRunning;
	
	public Watchable() {
	}
	
	@Override
	public String toString() {
		return String.format("Watchable[id=%d, name='%s', kind='%s']", id, name, kind);
	}
	
	public Watchable(String name, String kind) {
		this.name = name;
		this.kind = kind;
		this.runtime = null;
		this.runtime2 = "0";
		this.isRunning = true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getKind() {
		return this.kind;
	}
	
	@Override
	public boolean equals(Object other){
	    boolean result;
	    if((other == null) || (getClass() != other.getClass())){
	        result = false;
		}
	    else{
	        result = (id == ((Watchable) other).id); //&&  age.equals(other.age);
	    }

	    return result;
	}
}
