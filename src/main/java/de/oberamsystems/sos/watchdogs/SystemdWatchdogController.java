package de.oberamsystems.sos.watchdogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oberamsystems.sos.model.MyService;
import de.oberamsystems.sos.model.MyServiceService;
import de.oberamsystems.sos.model.NotRunner;

public class SystemdWatchdogController implements IWatchdogController {
	
	private static final Logger log = LoggerFactory.getLogger(SystemdWatchdogController.class);

	private MyServiceService serviceService;

	public SystemdWatchdogController() {
	}

	public SystemdWatchdogController(MyServiceService serviceService) {
		this.serviceService = serviceService;
	}

	private List<SystemdWatchdog> pWdgs;

	public void check() {
		pWdgs = new ArrayList<SystemdWatchdog>();
		for (MyService myproc : serviceService.getAllServices()) {
			
			SystemdWatchdog ps = new SystemdWatchdog(myproc);
			
			boolean before  = myproc.isRunning();
			ps.check();
			boolean after = myproc.isRunning();
			
			if (before == true && after == true) {
				;
			} else if (before == true && after == false) {
				log.info(String.format("Service '%s' died!", myproc.getName()));
			} else if (before == false && after == true) {
				log.info(String.format("Service '%s' recovered!", myproc.getName()));
			} else {
				;
			}
			pWdgs.add(ps);
			
			serviceService.saveService(myproc);
		}
	}

	@Override
	public List<NotRunner> getNotRunners() {
		List<NotRunner> notRunners = Collections.synchronizedList(new ArrayList<NotRunner>());
		for (MyService service : serviceService.getAllServices()) {
			if(!service.isRunning()) {
				log.debug("not runner: " + service.getName());
				notRunners.add(new NotRunner(service, null, null));
			}
		}
		return notRunners;
	}
}
