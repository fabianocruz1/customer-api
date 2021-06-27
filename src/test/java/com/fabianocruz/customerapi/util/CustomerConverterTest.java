package com.fabianocruz.customerapi.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.fabianocruz.customerapi.dto.AddressDTO;
import com.fabianocruz.customerapi.dto.CustomerDTO;
import com.fabianocruz.customerapi.model.Address;
import com.fabianocruz.customerapi.model.Customer;

@SpringBootTest
class CustomerConverterTest {

	static CustomerConverter customerConverter;
	
	@BeforeAll
	static void setup() {
		ModelMapper modelMapper = new ModelMapper();
		customerConverter = new CustomerConverter(modelMapper );
	}	

	@Test
	void testConvertToDto() {
		Address address = new Address();
		address.setCity("city 1");
		address.setCountry("country 1");
		address.setPostalCode("postalCode");
		address.setState("state 1");
		address.setStreet("street 1");
		Customer customer = new Customer();
		customer.setAddress(address);
		customer.setCpf("80003508668");
		customer.setName("Fabiano");
		customer.setId(1l);
		
		CustomerDTO dto = customerConverter.convertToDto(customer);
		assertEquals(customer.getName(), dto.getName());
		assertEquals(customer.getCpf(), dto.getCpf());
		assertEquals(customer.getId(), dto.getId());
		AddressDTO addressDto = dto.getAddress();
		assertNotNull(addressDto);
		assertEquals(address.getCity(), addressDto.getCity());
		assertEquals(address.getCountry(), addressDto.getCountry());
		assertEquals(address.getId(), addressDto.getId());
		assertEquals(address.getPostalCode(), addressDto.getPostalCode());
		assertEquals(address.getState(), addressDto.getState());
		assertEquals(address.getStreet(), addressDto.getStreet());

		
	}

	@Test
	void testConvertToEntity() {
		AddressDTO addressDto = new AddressDTO();
		addressDto.setCity("city 1");
		addressDto.setCountry("country 1");
		addressDto.setPostalCode("postalCode");
		addressDto.setState("state 1");
		addressDto.setStreet("street 1");
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setAddress(addressDto);
		customerDto.setCpf("80003508668");
		customerDto.setName("Fabiano");
		customerDto.setId(1l);
		
		Customer customer = customerConverter.convertToEntity(customerDto);
		assertEquals(customerDto.getName(), customer.getName());
		assertEquals(customerDto.getCpf(), customer.getCpf());
		assertEquals(customerDto.getId(), customer.getId());
		Address address = customer.getAddress();
		assertNotNull(address);
		assertEquals(addressDto.getCity(), address.getCity());
		assertEquals(addressDto.getCountry(), address.getCountry());
		assertEquals(addressDto.getId(), address.getId());
		assertEquals(addressDto.getPostalCode(), address.getPostalCode());
		assertEquals(addressDto.getState(), address.getState());
		assertEquals(addressDto.getStreet(), address.getStreet());
	}

	@Test
	void testCopyToEntity() {
		AddressDTO addressDto = new AddressDTO();
		addressDto.setCity("city 1");
		addressDto.setCountry("country 1");
		addressDto.setPostalCode("postalCode");
		addressDto.setState("state 1");
		addressDto.setStreet("street 1");
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setAddress(addressDto);
		customerDto.setCpf("80003508668");
		customerDto.setName("Fabiano");
		customerDto.setId(1l);

		Address address = new Address();
		address.setCity("city new");
		address.setCountry("country new");
		address.setPostalCode("postalCode");
		address.setState("state new");
		address.setStreet("street new");
		Customer customer = new Customer();
		customer.setAddress(address);
		customer.setCpf("80003508668");
		customer.setName("Fabiano");
		customer.setId(1l);
		
		customerConverter.copyToEntity(customerDto, customer);
		
		assertEquals(customerDto.getName(), customer.getName());
		assertEquals(customerDto.getCpf(), customer.getCpf());
		assertEquals(customerDto.getId(), customer.getId());
		Address addressUpdated = customer.getAddress();
		assertNotNull(addressUpdated);
		assertEquals(addressDto.getCity(), addressUpdated.getCity());
		assertEquals(addressDto.getCountry(), addressUpdated.getCountry());
		assertEquals(addressDto.getId(), addressUpdated.getId());
		assertEquals(addressDto.getPostalCode(), addressUpdated.getPostalCode());
		assertEquals(addressDto.getState(), addressUpdated.getState());
		assertEquals(addressDto.getStreet(), addressUpdated.getStreet());

	}

}
