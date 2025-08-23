package de.oberamsystems.sos.model2;

import java.time.LocalDateTime;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sensor2Builder {

	public static List<Sensor2> build(ResultSet rs) {
		
		List<Sensor2> prices = new ArrayList<Sensor2>();
		try {
			while (rs.next()) {

				int id = rs.getInt("id");
				LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
				String place = rs.getString("place");
				String sensor = rs.getString("sensor");
				float temperature = rs.getFloat("temperature");
				float humidity = rs.getFloat("humidity");
				float co2 = rs.getFloat("co2");
				
				Sensor2 p = new Sensor2(id, date, place, sensor, temperature, humidity, co2);
				//System.out.println(e10);
				prices.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return prices;

	}

}
