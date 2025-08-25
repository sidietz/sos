package de.oberamsystems.sos.model2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sensor2 extends DbEntry {

	private int id;
	private LocalDateTime date;
	private String place;
	private String sensor;
	private float temperature;
	private float humidity;
	private float co2;

	public Sensor2() {
	}

	public Sensor2(int id, LocalDateTime date, String place, String sensor, float temperature, float humidity,
			float co2) {
		this.id = id;
		this.date = date;
		this.place = place;
		this.sensor = sensor;
		this.temperature = temperature;
		this.humidity = humidity;
		this.co2 = co2;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getCo2() {
		return co2;
	}

	public void setCo2(float co2) {
		this.co2 = co2;
	}
	
	@Override
	public String toString() {
		return String.format("Sensor2[id=%d, date='%s', sensor='%s', co2='%s']", id, date, sensor, co2);
	}

	public List<DbEntry> build(ResultSet rs) {

		List<DbEntry> prices = new ArrayList<DbEntry>();
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
				prices.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return prices;

	}

}
