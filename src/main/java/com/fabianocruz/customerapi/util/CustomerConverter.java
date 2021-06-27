package com.fabianocruz.customerapi.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fabianocruz.customerapi.dto.CustomerDTO;
import com.fabianocruz.customerapi.model.Customer;

@Component
public class CustomerConverter {

	private ModelMapper modelMapper;

	@Autowired
	public CustomerConverter(ModelMapper modelMapper) {
		this.modelMapper= modelMapper;
	}
	
	public CustomerDTO  convertToDto(Customer customer) {
		return modelMapper.map(customer, CustomerDTO.class);
	}

	public Customer convertToEntity(CustomerDTO customerDto) {
		return modelMapper.map(customerDto, Customer.class);
	}

	public void copyToEntity(CustomerDTO customerDto, Customer customer) {
		modelMapper.map(customerDto, customer);
	}
}
