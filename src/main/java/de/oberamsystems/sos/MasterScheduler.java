package de.oberamsystems.sos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.oberamsystems.sos.mail.EmailService;
import de.oberamsystems.sos.model.DbObjectService;
import de.oberamsystems.sos.model.MyProcessService;
import de.oberamsystems.sos.model.MyServiceService;
import de.oberamsystems.sos.model.NotRunner;
import de.oberamsystems.sos.watchdogs.IWatchdogController;
import de.oberamsystems.sos.watchdogs.PriceWatchdogController;
import de.oberamsystems.sos.watchdogs.PsWatchdogController;
import de.oberamsystems.sos.watchdogs.SensorWatchdogController;
import de.oberamsystems.sos.watchdogs.SystemdWatchdogController;
@Component
@EnableScheduling
public class MasterScheduler {

	private static final Logger log = LoggerFactory.getLogger(MasterScheduler.class);

	@Autowired
	private MyProcessService procService;

	@Autowired
	private MyServiceService serviceService;

	@Autowired
	private DbObjectService dbObjectService;
	
	@Autowired
	private EmailService mailer;

	@Scheduled(fixedRate = 15 * 1000)
	public int scheduleTest() {
		// System.out.println("Ran scheduled!");
		log.debug("run scheduled tasks");
		PsWatchdogController pwc = new PsWatchdogController(procService);
		pwc.check();

		SystemdWatchdogController swc = new SystemdWatchdogController(serviceService);
		swc.check();

		SensorWatchdogController dbc = new SensorWatchdogController(dbObjectService);
		dbc.check();
		IWatchdogController pc = new PriceWatchdogController(dbObjectService, mailer);
		pc.check();
		return 0;
	}
	


}
