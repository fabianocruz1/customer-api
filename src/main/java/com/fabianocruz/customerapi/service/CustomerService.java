package com.fabianocruz.customerapi.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fabianocruz.customerapi.dto.CustomerDTO;

public interface CustomerService {
	Page<CustomerDTO> readAllCustomers(Pageable pageable);
	CustomerDTO readCustomer(String cpf);
	void saveCustomer(CustomerDTO customerDto);
	void deleteCustomer(Long idCustomer);
	void updateCustomer(CustomerDTO customerDto, Long idCustomer);
	void updateCustomer(@Valid CustomerDTO customerDTO, String cpf);
}
