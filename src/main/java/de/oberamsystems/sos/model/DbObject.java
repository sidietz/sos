package de.oberamsystems.sos.model;

import jakarta.persistence.*;

@Entity
public class DbObject extends Watchable {
	
	public String dbName;
	
	public DbObject() {
	}
	
	public DbObject(String dbName) {
		super(dbName, "dbobject");
		this.dbName = dbName;
	}
	
	@Override
	public String toString() {
		return String.format("DbObject[id=%d, name='%s', isRunning='%s']", id, dbName, isRunning);
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
