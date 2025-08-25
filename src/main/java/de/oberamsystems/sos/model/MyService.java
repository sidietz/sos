package de.oberamsystems.sos.model;


import jakarta.persistence.*;

@Entity
public class MyService extends Watchable {

	public MyService() {
	}
	
	public MyService(String name) {
		super(name, "service");
	}
}
