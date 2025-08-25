package de.oberamsystems.sos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oberamsystems.sos.model.Customer;
import de.oberamsystems.sos.model.MyProcess;
import de.oberamsystems.sos.model.CustomerRepository;
import de.oberamsystems.sos.model.DbObject;
import de.oberamsystems.sos.model.DbObjectRepository;
import de.oberamsystems.sos.model.MyProcessRepository;
import de.oberamsystems.sos.model.MyService;
import de.oberamsystems.sos.model.MyServiceRepository;

@SpringBootApplication
@EnableScheduling
//@EnableConfigurationProperties(Mailer.class)
//@SpringBootApplication(scanBasePackages = "de.oberamsystems.sos.*")
public class SosApplication {

	private static final Logger log = LoggerFactory.getLogger(SosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SosApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			/*
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				// System.out.println(beanName);
			}
			*/

		};
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			/*
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(customer -> {
				log.info(customer.toString());
			});
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
			*/
		};
	}
	
	@Bean
	public CommandLineRunner initProcs(MyProcessRepository repository) {
		return (args) -> {
			// save a few customers
			
			log.info("Initialize procs");
			repository.save(new MyProcess("all_sink", "all_sink.py"));
			repository.save(new MyProcess("immich", "immich"));
			repository.save(new MyProcess("habittrove", "habittrove"));
		};
	}
	
	@Bean
	public CommandLineRunner initSvcs(MyServiceRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new MyService("sshd"));
			repository.save(new MyService("samba"));
		};
	}
	
	@Bean
	public CommandLineRunner initDbObjs(DbObjectRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new DbObject("sensors2"));
			repository.save(new DbObject("price"));
		};
	}
	
	
	/*
	 @Scheduled(fixedRate = 15 * 1000)
	 
	//@PostConstruct
	public int scheduleTest() {
		System.out.println("Ran scheduled!");
		log.warn("Scheduled Test");
		PsWatchdogController pwc = new PsWatchdogController();
		pwc.check();
		
		return 0;
	}
	*/

}
