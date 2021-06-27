package com.fabianocruz.customerapi.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.fabianocruz.customerapi.dto.AddressDTO;
import com.fabianocruz.customerapi.dto.CustomerDTO;

class CustomerTest {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Test
	public void testCustomerMapping() {
		AddressDTO addressDto = new AddressDTO();
		addressDto.setCity("São Paulo");
		addressDto.setState("São Paulo");
		addressDto.setCountry("Brazil");
		addressDto.setPostalCode("02040-020");
		addressDto.setStreet("Rua Atalanta, 115 - Jd São Paulo");

		CustomerDTO dto = new CustomerDTO();
		dto.setName("John Doe");
		dto.setCpf("11122233399");
		dto.setAddress(addressDto);

		Customer customer = modelMapper.map(dto, Customer.class);
		assertEquals(customer.getName(), dto.getName());
		assertEquals(customer.getCpf(), dto.getCpf());
		assertEquals(customer.getAddress().getCity(), dto.getAddress().getCity());

		AddressDTO updatedAddressDto = new AddressDTO();
		updatedAddressDto.setStreet("Av Paulista 2002 - 22o Andar - Centro");

		CustomerDTO updatedDto = new CustomerDTO();
		updatedDto.setName("John Doe Jr");
		updatedDto.setAddress(addressDto);

		modelMapper.map(updatedDto, customer);
		assertEquals(updatedDto.getName(), customer.getName());
		assertEquals(updatedDto.getAddress().getStreet(), customer.getAddress().getStreet());

	}
}
