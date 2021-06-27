package com.fabianocruz.customerapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fabianocruz.customerapi.model.Customer;

public interface CustomerService {
	Page<Customer> readAllCustomers(Pageable pageable);
	Customer readCustomer(String cpf);
	void saveCustomer(Customer customer);
	void updateCustomer(Customer customer, long idCustomer);
	void deleteCustomer(long idCustomer);
}
