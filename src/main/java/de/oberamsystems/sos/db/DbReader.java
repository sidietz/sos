package de.oberamsystems.sos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbReader {
	
	private static final Logger log = LoggerFactory.getLogger(DbReader.class);

	private String connectionString;
	private String driver;
	private String user;
	private String password;

	public DbReader(String driver, String connectionString, String user, String password) {
		this.connectionString = connectionString;
		this.driver = driver;
		this.user = user;
		this.password = password;
	}
	
	public ResultSet execute(String query, List<String> params) {
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(connectionString, user, password);
			if (con != null) {
				
				PreparedStatement stmt = con.prepareStatement(query);
				for (int i=0; i<params.size(); i++) {
					stmt.setString(i+1, params.get(i));
				}
				
				ResultSet resultSet = stmt.executeQuery();
				con.close();
				
				return resultSet;				
			} else {
				log.warn(String.format("DbReader could not connect to Database\n%s with\n%s", connectionString, query));
				return null;
			}
		} catch (Exception e) {
			log.warn(String.format("DbReader failed with error message %s", e.getMessage()));
			e.printStackTrace();
			return null;
		}
	}

}
