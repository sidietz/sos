package de.oberamsystems.sos.mqtt;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "sensors2")
public class Measurement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "date", columnDefinition = "TIMESTAMP")
	private LocalDateTime date;
	String place;
	String sensor;
	float temperature;
	float humidity;
	float co2;
	
	public Measurement() {
	}
	
	public Measurement(int id, LocalDateTime date, String place, String sensor, float temperature, float humidity, float co2) {
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

	public int getId() {
		return id;
	}

}
