package de.oberamsystems.sos.model2;

import java.time.LocalDateTime;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceBuilder {

	public static List<Price> build(ResultSet rs) {
		
		List<Price> prices = new ArrayList<Price>();
		try {
			while (rs.next()) {

				//int id = rs.getInt("id");
				String stationId = rs.getString("station_id");
				LocalDateTime date = rs.getTimestamp("changed_at").toLocalDateTime();
				float diesel = rs.getFloat("diesel");
				float e5 = rs.getFloat("e5");
				float e10 = rs.getFloat("e10");
				
				Price p = new Price(stationId, date, diesel, e5, e10);
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
