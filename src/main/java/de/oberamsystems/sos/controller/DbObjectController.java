package de.oberamsystems.sos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.oberamsystems.sos.model.DbObject;
import de.oberamsystems.sos.model.DbObjectService;
import de.oberamsystems.sos.model.IWatchable;

@Controller
public class DbObjectController {
	
	@Autowired
	private DbObjectService procService;
	
	@ModelAttribute("allDbObjects")
	public List<DbObject> populateDbObjects() {
		for (IWatchable obj : this.procService.getAllDbObjects()) {
			System.out.println(obj);
		}
	    return this.procService.getAllDbObjects();
	}
	
	@GetMapping(value = "/dbobjects")	
	public String index() {

		return "dbobjects";
	}

}
