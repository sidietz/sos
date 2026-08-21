package de.oberamsystems.sos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootApplication
@Configuration
@EnableScheduling
public class SosApplication  {

	private static final Logger log = LoggerFactory.getLogger(SosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SosApplication.class, args);
	}
	
}
