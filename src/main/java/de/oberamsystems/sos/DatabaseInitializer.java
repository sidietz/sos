package de.oberamsystems.sos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.oberamsystems.sos.model.DbObject;
import de.oberamsystems.sos.model.DbObjectRepository;
import de.oberamsystems.sos.model.MyHttpService;
import de.oberamsystems.sos.model.MyHttpServiceRepository;
import de.oberamsystems.sos.model.MyProcess;
import de.oberamsystems.sos.model.MyProcessRepository;
import de.oberamsystems.sos.model.MyService;
import de.oberamsystems.sos.model.MyServiceRepository;

@Configuration
public class DatabaseInitializer {

    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Bean
    public CommandLineRunner initProcs(MyProcessRepository repository) {
        return (args) -> {
            log.debug("Initialize processes");
            repository.save(new MyProcess("all_sink", "all_sink.py"));
        };
    }
    
    @Bean
    public CommandLineRunner initSvcs(MyServiceRepository repository) {
        return (args) -> {
            log.debug("Initialize services");
            repository.save(new MyService("ssh"));
            repository.save(new MyService("smbd"));
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
    
    @Bean
    public CommandLineRunner initHttpSvcs(MyHttpServiceRepository repository) {
        return (args) -> {
            log.debug("Initialize http services");
            repository.save(new MyHttpService(8001, "paperless-ngx", "docker"));
            repository.save(new MyHttpService(3000, "habittrove", "docker"));
            repository.save(new MyHttpService(2283, "immich", "docker"));
            repository.save(new MyHttpService(7070, "evcc", "baremetal"));
            repository.save(new MyHttpService(3003, "grafana", "baremetal"));
            repository.save(new MyHttpService(8000, "slm", "baremetal"));
            repository.save(new MyHttpService(30001, "sos-botadapter", "baremetal"));
            repository.save(new MyHttpService("plantwatch.de", 443, "plantwatch", "uberspace", "https://"));
            repository.save(new MyHttpService("fuelsentinel.de", 443, "fuelsentinel", "uberspace", "https://"));
        };
    }
}
