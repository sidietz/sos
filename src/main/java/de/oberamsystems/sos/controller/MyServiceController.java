package de.oberamsystems.sos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.oberamsystems.sos.model.MyServiceRepository;

@Controller
public class MyServiceController {
	
	@Autowired
	private MyServiceRepository repo;

	@GetMapping("/services")
	public String services(Model model) {
		model.addAttribute("allServices", repo.findAll());
		return "services";
	}

}
