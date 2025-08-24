package de.oberamsystems.sos.watchdogs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oberamsystems.sos.model.MyProcess;
import de.oberamsystems.sos.model.MyProcessService;

//@Component
public class PsWatchdogController implements IWatchdogController {
	
	private static final Logger log = LoggerFactory.getLogger(PsWatchdogController.class);


	/*
	 * @Autowired private SingletonBean singletonBean;
	 */
	// @Autowired
	private MyProcessService procService;

	public PsWatchdogController() {
	}

	public PsWatchdogController(MyProcessService procService) {
		this.procService = procService;
	}

	private List<PsWatchdog> pWdgs;// = new ArrayList<PsWatchdog>();

	// @PostConstruct
	public void check() {
		pWdgs = new ArrayList<PsWatchdog>();
		for (MyProcess myproc : procService.getAllProcesses()) {
			//System.out.println(myproc.getName());
			PsWatchdog ps = new PsWatchdog(myproc);
			
			boolean before  = myproc.isRunning();			
			MyProcess proc2 = ps.check();
			boolean after  = proc2.isRunning();
			
			if (before == true && after == true) {
				;
			} else if (before == true && after == false) {
				log.warn(String.format("Process '%s' died!", proc2.getName()));
			} else if (before == false && after == true) {
				
			} else {
				;
			}
			pWdgs.add(ps);
			
			procService.saveProcess(proc2);
			//System.out.println(proc2.isRunning());
		}
		
		/*
		for (PsWatchdog pswdg : pWdgs) {
			pswdg.check();
		}
		*/
	}
}
