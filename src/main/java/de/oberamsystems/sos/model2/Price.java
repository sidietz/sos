package de.oberamsystems.sos.model2;

import java.time.LocalDateTime;


public class Price {

	private String stationId;
	private LocalDateTime date;
	private float diesel;
	private float e5;
	private float e10;
	
	public Price(String stationId, LocalDateTime date, float diesel, float e5, float e10) {
		this.stationId = stationId;
		this.date = date;
		this.diesel = diesel;
		this.e5 = e5;
		this.e10 = e10;
	}
	
	@Override
	public String toString() {
		return String.format("Price[date='%s', stationId='%s', e10='%s']", date, stationId, e10);
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public float getDiesel() {
		return diesel;
	}

	public void setDiesel(float diesel) {
		this.diesel = diesel;
	}

	public float getE5() {
		return e5;
	}

	public void setE5(float e5) {
		this.e5 = e5;
	}

	public float getE10() {
		return e10;
	}

	public void setE10(float e10) {
		this.e10 = e10;
	}

}
