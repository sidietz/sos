package de.oberamsystems.sos.watchdogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oberamsystems.sos.model.MyHttpService;
import de.oberamsystems.sos.model.MyHttpServiceRepository;
import de.oberamsystems.sos.model.NotRunner;

public class PingWatchdogController implements IWatchdogController {
	
	private static final Logger log = LoggerFactory.getLogger(PsWatchdogController.class);

	private MyHttpServiceRepository repo;
	private List<PingWatchdog> pWdgs;

	public PingWatchdogController() {
	}

	public PingWatchdogController(MyHttpServiceRepository repo) {
		this.repo = repo;
	}

	@Override
	public void check() {
		pWdgs = new ArrayList<PingWatchdog>();
		for (MyHttpService myproc : repo.findAll()) {
			
			PingWatchdog ps = new PingWatchdog(myproc);
			
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

			repo.save(myproc);
		}
	}

	@Override
	public List<NotRunner> getNotRunners() {
		List<NotRunner> notRunners = Collections.synchronizedList(new ArrayList<NotRunner>());
		for (MyHttpService service : repo.findAll()) {
			if(!service.isRunning()) {
				log.debug("not runner: " + service.getName());
				notRunners.add(new NotRunner(service, null, null));
			}
		}
		return notRunners;
	}

}
