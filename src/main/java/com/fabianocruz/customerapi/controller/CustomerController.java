package com.fabianocruz.customerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabianocruz.customerapi.dto.CustomerDTO;
import com.fabianocruz.customerapi.dto.CustomerUpdateDTO;
import com.fabianocruz.customerapi.model.Customer;
import com.fabianocruz.customerapi.service.CustomerService;
import com.fabianocruz.customerapi.util.DTO;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
    @GetMapping
	public Page<Customer> readAllCustomers(@RequestParam("page") int pageIndex, 
            @RequestParam("size") int pageSize) {
    	return customerService.readAllCustomers(PageRequest.of(pageIndex, pageSize));
	}

    @GetMapping(value = "/{cpf}" )
	public Customer readCustomer(@PathVariable String cpf) {
    	System.out.println("readCustomer "+ cpf);
    	return customerService.readCustomer(cpf);
	}

	@PostMapping
	public void saveCustomer(@DTO(CustomerDTO.class) Customer customer) {
		customerService.saveCustomer(customer);
	}
	
	@PutMapping("/{idCustomer}")
	public void updateCustomer(@DTO(CustomerUpdateDTO.class) Customer customer) {
		customerService.saveCustomer(customer);
	}
}
