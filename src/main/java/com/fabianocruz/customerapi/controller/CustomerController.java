package com.fabianocruz.customerapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabianocruz.customerapi.dto.CustomerDTO;
import com.fabianocruz.customerapi.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
    @GetMapping
	public Page<CustomerDTO> readAllCustomers(@RequestParam(value="page", defaultValue = "0") int pageIndex, 
            @RequestParam(value="size", defaultValue = "10") int pageSize) {
    	return customerService.readAllCustomers(PageRequest.of(pageIndex, pageSize));
	}

    @GetMapping(value = "/{cpf}" )
	public CustomerDTO readCustomer(@PathVariable String cpf) {
    	System.out.println("readCustomer "+ cpf);
    	return customerService.readCustomer(cpf);
	}

	@PostMapping
	public void saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		customerService.saveCustomer(customerDTO);
	}
	
	@DeleteMapping("/{idCustomer}")
	public void deleteCustomer(@PathVariable Long idCustomer) {
		customerService.deleteCustomer(idCustomer);
	}	
	
	@PutMapping("/{cpf}")
	public void updateCustomer(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable String cpf) {
		customerService.updateCustomer(customerDTO, cpf);
	}
}
