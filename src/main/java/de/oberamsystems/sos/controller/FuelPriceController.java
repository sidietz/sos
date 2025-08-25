package de.oberamsystems.sos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.oberamsystems.sos.spritdisplay.FuelPrice;
import de.oberamsystems.sos.spritdisplay.FuelPriceService;

@Controller
public class FuelPriceController {
	
	@Autowired
	private FuelPriceService service;
	
	@ModelAttribute("allFuelPrices")
	public List<FuelPrice> populateFuelPrices() {
	    return this.service.getAllFuelPrices();
	}
	
	@GetMapping(value = "/fuelprices")	
	public String index() {

		return "fuelprices";
	}

}
