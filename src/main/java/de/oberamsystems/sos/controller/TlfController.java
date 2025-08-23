package de.oberamsystems.sos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.oberamsystems.sos.model.Customer;
import de.oberamsystems.sos.model.CustomerService;

@Controller
public class TlfController {
	
	@Autowired
	private CustomerService customerService;
	
	@ModelAttribute("allCustomers")
	public List<Customer> populateCustomers() {
	    return this.customerService.getAllCustomers();
	}
	
	@GetMapping(value = "/tlf")	
	public String index() {

		return "tlf";
	}
}
