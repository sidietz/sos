package de.oberamsystems.sos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.oberamsystems.sos.model.MyProcess;
import de.oberamsystems.sos.model.MyProcessService;

@Controller
public class MyProcessController {
	
	@Autowired
	private MyProcessService procService;
	
	@ModelAttribute("allProcesses")
	public List<MyProcess> populateProcesses() {
	    return this.procService.getAllProcesses();
	}
	
	@GetMapping(value = "/processes")	
	public String index() {

		return "processes";
	}

}
