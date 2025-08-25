package de.oberamsystems.sos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.oberamsystems.sos.model.DbObjectRepository;

@Controller
public class DbObjectController {
	
	@Autowired
	private DbObjectRepository repo;
	
	/*
	@ModelAttribute("allDbObjects")
	public List<DbObject> populateDbObjects() {
	    return this.procService.getAllDbObjects();
	}*/
	
	@GetMapping(value = "/dbobjects")	
	public String index(Model model) {
		model.addAttribute("allDbObjects", repo.findAll());
		return "dbobjects";
	}

}
