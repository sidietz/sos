package de.oberamsystems.sos.watchdogs;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.net.ssl.SSLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oberamsystems.sos.model.MyHttpService;


public class PingWatchdog implements IWatchdog {
	
	private static final Logger log = LoggerFactory.getLogger(PingWatchdog.class);
	
	private MyHttpService service;

	public PingWatchdog(MyHttpService svc) {
		this.service = svc;
	}
	

	@Override
	public void check() {
		
		try {
			HttpRequest request = HttpRequest.newBuilder(new URI(service.getHttp() + service.getHostname() + ":" + service.getPort() + "/")).GET()
			//.timeout(Duration.ofSeconds(25))
			.build();
			HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			int code = response.statusCode();
			log.debug("Code for: " + service + " is: " + code);
			if (code == 200 || code == 302 || code == 308) {
				Duration myd = service.getRuntime();
				if (myd == null) {
					myd = Duration.ofSeconds(5);
				}
				Instant i = Instant.now().plus(myd);
				Instant currentTime = Instant.now();
				LocalDateTime currentDate = LocalDateTime.now();
				Duration d = Duration.between(currentTime, i);
				String s = Instant.now().toString();
				
				DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		        String datetime = currentDate.format(targetFormat);
				
				service.setRuntime(d);
				service.setRuntime2(datetime);
				service.setRunning(true);
			} else {
				service.setRunning(false);
				log.warn(String.format("HttpService '%s' not running!", service));
			}
		} catch (HttpTimeoutException e) {
			service.setRunning(false);
			log.warn(String.format("HttpService '%s' not running!", service));
		} catch (SSLException e) {
			service.setRunning(false);
			log.warn(String.format("HttpService '%s' not running!", service));
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			log.info(sStackTrace);
		} catch (URISyntaxException | IOException | InterruptedException e) {
			service.setRunning(false);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			log.info(sStackTrace);
		} 
	}
}
