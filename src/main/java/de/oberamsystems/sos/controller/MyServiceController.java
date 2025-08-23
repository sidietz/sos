package de.oberamsystems.sos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.oberamsystems.sos.model.MyService;
import de.oberamsystems.sos.model.MyServiceService;

@Controller
public class MyServiceController {
	
	@Autowired
	private MyServiceService serviceService;
	
	@ModelAttribute("allServices")
	public List<MyService> populateServices() {
	    return this.serviceService.getAllServices();
	}

	@GetMapping("/services")
	public String services() {
		return "services";
	}

}
