package de.oberamsystems.sos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.oberamsystems.sos.model.MyProcessRepository;

@Controller
public class MyProcessController {
	
	@Autowired
	private MyProcessRepository repo;
	
	@GetMapping(value = "/processes")	
	public String index(Model model) {
		model.addAttribute("allProcesses", repo.findAll());

		return "processes";
	}

}
