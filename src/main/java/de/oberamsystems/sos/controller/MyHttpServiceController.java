package de.oberamsystems.sos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.oberamsystems.sos.model.MyHttpServiceRepository;

@Controller
public class MyHttpServiceController {
	
	@Autowired
	private MyHttpServiceRepository repo;

	@GetMapping("/httpservices")
	public String services(Model model) {
		model.addAttribute("allHttpServices", repo.findAll());
		return "httpservices";
	}

}
