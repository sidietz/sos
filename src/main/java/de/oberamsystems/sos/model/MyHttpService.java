package de.oberamsystems.sos.model;

import jakarta.persistence.*;

@Entity
public class MyHttpService extends Watchable {
	
	private String hostname;
	private int port;
	private String hostingType;
	private String http;

	private final static String LOCALHOST = "127.0.0.1";
	private final static String HTTP = "http://";
	
	public MyHttpService() {
	}
	
	public MyHttpService(int port, String name) {
		super(name, "httpservice");
		this.hostname = LOCALHOST;
		this.port = port;
		this.http = HTTP;
	}
	
	public MyHttpService(int port, String name, String hostingType) {
		super(name, "httpservice");
		this.hostname = LOCALHOST;
		this.port = port;
		this.hostingType = hostingType;
		this.http = HTTP;
	}
	
	public MyHttpService(String hostname, int port, String name, String hostingType) {
		super(name, "httpservice");
		this.hostname = hostname;
		this.port = port;
		this.hostingType = hostingType;
		this.http = HTTP;
	}
	
	public MyHttpService(String hostname, int port, String name, String hostingType, String http) {
		super(name, "httpservice");
		this.hostname = hostname;
		this.port = port;
		this.hostingType = hostingType;
		this.http = http;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHostingType() {
		return hostingType;
	}

	public void setHostingType(String hostingType) {
		this.hostingType = hostingType;
	}
	
	
	public String getHttp() {
		return http;
	}

	public void setHttp(String http) {
		this.http = http;
	}
}
