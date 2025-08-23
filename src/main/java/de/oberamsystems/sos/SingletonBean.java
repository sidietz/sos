package de.oberamsystems.sos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.oberamsystems.sos.model.MyProcess;
import de.oberamsystems.sos.model.MyProcessService;

@Component
public class SingletonBean {
	
	@Autowired
	private MyProcessService procService;
	
	private volatile List<String> test;
	private volatile List<MyProcess> procs;
	
	public SingletonBean() {
	}
	
	public synchronized void setTest(List<String> s) {
		this.test = s;
	}
	
	public List<String> getTest() {
		return test;
	}

	public MyProcessService getProcService() {
		return procService;
	}

	public List<MyProcess> getProcs() {
		return procs;
	}

	public void setProcs(List<MyProcess> procs) {
		this.procs = procs;
	}

}
