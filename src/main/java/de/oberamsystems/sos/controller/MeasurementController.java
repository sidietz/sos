package de.oberamsystems.sos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.oberamsystems.sos.mqtt.Measurement;
import de.oberamsystems.sos.mqtt.MeasurementService;

@Controller
public class MeasurementController {
	
	@Autowired
	private MeasurementService procService;
	
	@ModelAttribute("allMeasurements")
	public List<Measurement> populateMeasurements() {
	    return this.procService.getAllMeasurements();
	}
	
	@GetMapping(value = "/measurements")	
	public String index() {

		return "measurements";
	}

}
