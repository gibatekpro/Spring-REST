package com.gibatekpro.customer_tracker_rest.controller;


import com.gibatekpro.customer_tracker_rest.entity.CustomerEntity;
import com.gibatekpro.customer_tracker_rest.exception.CustomerNotFoundException;
import com.gibatekpro.customer_tracker_rest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerEntity> customersList() {

        //get customers from the dao
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public CustomerEntity getCustomer(@PathVariable int customerId) {

        CustomerEntity customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("CUSTOMER NOT FOUND!!!");
        }
        //get the customer from the service
        return customer;
    }

    @PostMapping("/customers")
    public CustomerEntity saveCustomer(@RequestBody CustomerEntity customer) {

        //This will force MySQL to set a new Id and save this as new data
        customer.setId(0);

        customerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/customers/{customerId}")
    public CustomerEntity updateCustomer(@PathVariable int customerId, @RequestBody CustomerEntity customer) {

        //Good practice to set Id first
        customer.setId(customerId);

        customerService.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {

        CustomerEntity customer = customerService.getCustomer(customerId);

        if ( customer == null) {

            throw new CustomerNotFoundException("CUSTOMER NOT FOUND");

        }

        //get the customer from the service
        customerService.deleteCustomer(customerId);

        return "Customer deleted " + customerId;
    }

}
