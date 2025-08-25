package de.oberamsystems.sos.watchdogs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oberamsystems.sos.model.MyProcess;
import de.oberamsystems.sos.model.MyProcessService;
import de.oberamsystems.sos.model.NotRunner;

public class PsWatchdogController implements IWatchdogController {
	
	private static final Logger log = LoggerFactory.getLogger(PsWatchdogController.class);

	private MyProcessService procService;

	public PsWatchdogController() {
	}

	public PsWatchdogController(MyProcessService procService) {
		this.procService = procService;
	}

	private List<PsWatchdog> pWdgs;
	
	public void check() {
		pWdgs = new ArrayList<PsWatchdog>();
		for (MyProcess myproc : procService.getAllProcesses()) {
			PsWatchdog ps = new PsWatchdog(myproc);
			
			boolean before  = myproc.isRunning();			
			ps.check();
			boolean after  = myproc.isRunning();
			
			if (before == true && after == true) {
				;
			} else if (before == true && after == false) {
				log.info(String.format("Process '%s' died!", myproc.getName()));
			} else if (before == false && after == true) {
				log.info(String.format("Service '%s' recovered!", myproc.getName()));
			} else {
				;
			}
			pWdgs.add(ps);
			
			procService.saveProcess(myproc);
		}
	}

	@Override
	public List<NotRunner> getNotRunners() {
		List<NotRunner> notRunners = new ArrayList<NotRunner>();
		for (MyProcess service : procService.getAllProcesses()) {
			if(!service.isRunning()) {
				log.debug("not runner: " + service.getName());
				notRunners.add(new NotRunner(service, null, null));
			}
		}
		return notRunners;
	}
}
