package de.oberamsystems.sos.watchdogs;

import java.util.ArrayList;
import java.util.List;

import de.oberamsystems.sos.model.MyProcess;
import de.oberamsystems.sos.model.MyProcessService;

//@Component
public class PsWatchdogController {

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
			System.out.println(myproc.getName());
			PsWatchdog ps = new PsWatchdog(myproc);
			
			boolean before  = myproc.isRunning();			
			MyProcess proc2 = ps.check();
			boolean after  = proc2.isRunning();
			
			if (before == true && after == true) {
				;
			} else if (before == true && after == false) {
				System.out.println("Process " + proc2.getName() + " died!");
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
