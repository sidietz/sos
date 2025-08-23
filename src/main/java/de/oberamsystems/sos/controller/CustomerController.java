package de.oberamsystems.sos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.oberamsystems.sos.model.Customer;
import de.oberamsystems.sos.model.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public Customer createUser(@RequestBody Customer user) {
        return service.saveCustomer(user);
    }

    @GetMapping
    public Iterable<Customer> getAllUsers() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getUserById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteCustomerById(id);
    }
}