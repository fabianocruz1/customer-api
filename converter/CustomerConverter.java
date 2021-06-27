package com.fabianocruz.customerapi.converter;

import java.util.List;

import com.fabianocruz.customerapi.dto.CustomerDTO;
import com.fabianocruz.customerapi.model.Customer;

public interface CustomerConverter {
	CustomerDTO toDto(Customer customer);
	List<CustomerDTO> toDto(List<Customer> customer);
	Customer toEntity(CustomerDTO customerDTO);
	void updateModel(CustomerDTO customerDTO, Customer customer);
}
