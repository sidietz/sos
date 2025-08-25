package de.oberamsystems.sos.model2;

import java.sql.ResultSet;
import java.util.List;

public abstract class DbEntry {

	public DbEntry() {
	}
	
	//public List<DbEntry> build(ResultSet rs);

	public abstract <T extends DbEntry> List<T> build(ResultSet rs);
	
}