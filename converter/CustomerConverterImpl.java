package com.fabianocruz.customerapi.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fabianocruz.customerapi.dto.CustomerDTO;
import com.fabianocruz.customerapi.model.Customer;

@Component
public class CustomerConverterImpl implements CustomerConverter {

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
    	return modelMapper.map(customerDTO, Customer.class);
    }
    
	@Override
	public CustomerDTO toDto(Customer customer) {
		return modelMapper.map(customer, CustomerDTO.class);
	}

	@Override
	public List<CustomerDTO> toDto(List<Customer> customer) {
		return customer.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public void updateModel(CustomerDTO customerDTO, Customer customer) {
		Customer updated = toEntity(customerDTO);
		customer.setName(updated.getName());
	}

}
