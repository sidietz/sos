package de.oberamsystems.sos.model;

import jakarta.persistence.*;

@Entity
public class MyProcess extends Watchable {

	private String command;
	
	public MyProcess() {
	}

	public MyProcess(String name, String command) {
		super(name, "process");
		this.setCommand(command);
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
