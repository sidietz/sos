package de.oberamsystems.sos.watchdogs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oberamsystems.sos.model.MyService;
import de.oberamsystems.sos.model.MyServiceService;


//@Component
public class SystemdWatchdogController implements IWatchdogController {
	
	private static final Logger log = LoggerFactory.getLogger(PsWatchdogController.class);


	/*
	 * @Autowired private SingletonBean singletonBean;
	 */
	// @Autowired
	private MyServiceService serviceService;

	public SystemdWatchdogController() {
	}

	public SystemdWatchdogController(MyServiceService serviceService) {
		this.serviceService = serviceService;
	}

	private List<SystemdWatchdog> pWdgs;// = new ArrayList<PsWatchdog>();

	// @PostConstruct
	public void check() {
		pWdgs = new ArrayList<SystemdWatchdog>();
		for (MyService myproc : serviceService.getAllServices()) {
			//System.out.println(myproc.getName());
			SystemdWatchdog ps = new SystemdWatchdog(myproc);
			
			boolean before  = myproc.isRunning();			
			MyService proc2 = ps.check();
			boolean after  = proc2.isRunning();
			
			if (before == true && after == true) {
				;
			} else if (before == true && after == false) {
				log.warn(String.format("Service '%s' died!", proc2.getName()));
			} else if (before == false && after == true) {
				
			} else {
				;
			}
			pWdgs.add(ps);
			
			serviceService.saveService(proc2);
			//System.out.println(proc2.isRunning());
		}
		
		/*
		for (PsWatchdog pswdg : pWdgs) {
			pswdg.check();
		}
		*/
	}
}
