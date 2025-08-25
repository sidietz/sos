package de.oberamsystems.sos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oberamsystems.sos.model.MyProcess;
import de.oberamsystems.sos.model.DbObject;
import de.oberamsystems.sos.model.DbObjectRepository;
import de.oberamsystems.sos.model.MyProcessRepository;
import de.oberamsystems.sos.model.MyService;
import de.oberamsystems.sos.model.MyServiceRepository;

@SpringBootApplication
@Configuration
@EnableScheduling
public class SosApplication  {

	private static final Logger log = LoggerFactory.getLogger(SosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SosApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initProcs(MyProcessRepository repository) {
		return (args) -> {
			log.debug("Initialize processes");
			repository.save(new MyProcess("all_sink", "all_sink.py"));
			repository.save(new MyProcess("immich", "immich"));
			repository.save(new MyProcess("habittrove", "habittrove"));
		};
	}
	
	@Bean
	public CommandLineRunner initSvcs(MyServiceRepository repository) {
		return (args) -> {
			log.debug("Initialize services");
			repository.save(new MyService("sshd"));
			repository.save(new MyService("samba"));
		};
	}
	
	@Bean
	public CommandLineRunner initDbObjs(DbObjectRepository repository) {
		return (args) -> {
			log.debug("Initialize dbObjects");
			repository.save(new DbObject("sensors2"));
			repository.save(new DbObject("price"));
		};
	}
}
