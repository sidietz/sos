package de.oberamsystems.sos.model;

import java.time.Duration;

import jakarta.persistence.*;

@Entity
public class DbObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String dbName;
	private String runtime2;
	private Duration runtime;
	private boolean isRunning;
	
	DbObject() {
	}
	
	public DbObject(String dbName) {
		super();
		this.dbName = dbName;
		this.runtime = null;
		this.runtime2 = "0";
		this.isRunning = true;
	}
	
	@Override
	public String toString() {
		return String.format("DbObject[id=%d, name='%s', isRunning='%s']", id, dbName, isRunning);
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

	public String getDbName() {
		return dbName;
	}
	
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
}
