package de.oberamsystems.sos.model2;

import java.sql.ResultSet;
import java.util.List;

public abstract class DbEntry {

	public DbEntry() {
	}

	public abstract <T extends DbEntry> List<T> build(ResultSet rs);
	
}