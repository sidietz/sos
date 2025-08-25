package de.oberamsystems.sos.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NotRunnerManager {
	
	List<NotRunner> notRunners = Collections.synchronizedList(new ArrayList<NotRunner>());
	
	public NotRunnerManager() {
	}

	public List<NotRunner> getNotRunners() {
		return notRunners;
	}

	public void setNotRunners(List<NotRunner> notRunners) {
		this.notRunners = notRunners;
	}

	public void deleteNotRunnerById(NotRunner notRunner) {
		notRunners.remove(notRunner);
	}

	public void addNotRunner(NotRunner nr) {
		notRunners.add(nr);
	}

	public void clear() {
		notRunners.clear();
	}
	
	
	
	
	
	

}
