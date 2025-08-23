package de.oberamsystems.sos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DbReader {

	private String connectionString;
	private String driver;
	private String user;
	private String password;

	public DbReader(String driver, String conS, String user, String password) {
		this.connectionString = conS;
		this.driver = driver;
		this.user = user;
		this.password = password;
	}
	
	public ResultSet execute(String query, List<String> params) {
		try {
			// load the MySQL JDBC driver
			Class.forName(driver);

			// establish connection with the database
			Connection con = DriverManager.getConnection(connectionString, user, password);
			if (con != null) {
				// SQL query to retrieve data from the 'book' table
				
				PreparedStatement stmt = con.prepareStatement(query);

				for (int i=0; i<params.size(); i++) {
					stmt.setString(i+1, params.get(i));
				}
				
				// execute the query and get the result set
				ResultSet resultSet = stmt.executeQuery();
				
				
				con.close();
				return resultSet;
				/*
				System.out.println("The Available Data\n");

				// iterate through the result set and print the data
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String author_name = resultSet.getString("author");
					String book_name = resultSet.getString("name");
					String book_price = resultSet.getString("price");

					// print the retrieved data
					System.out.println("ID: " + id + ", Author_Name: " + author_name + ", Book_Name: " + book_name
							+ ", Book_Price " + book_price);
				}
				*/
				
			} else {
				System.out.println("Not Connected...");
				return null;
			}
		} catch (Exception e) {
			// handle any exceptions that occur
			System.out.println("Exception is " + e.getMessage());
			return null;
		}
	}

}
