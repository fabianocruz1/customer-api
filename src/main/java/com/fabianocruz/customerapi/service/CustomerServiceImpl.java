package com.fabianocruz.customerapi.service;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabianocruz.customerapi.dto.CustomerDTO;
import com.fabianocruz.customerapi.model.Customer;
import com.fabianocruz.customerapi.repository.CustomerRepository;
import com.fabianocruz.customerapi.util.CustomerConverter;
import com.fabianocruz.customerapi.util.RestException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerConverter customerConverter;
	
	@Override
	@Transactional(readOnly = true)
	public Page<CustomerDTO> readAllCustomers(Pageable pageable) {
		Page<Customer> customers = customerRepository.findAll(pageable);
		return customers.map(customerConverter::convertToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerDTO readCustomer(String cpf) {
		Customer customer = getCustomer(cpf);
		return customerConverter.convertToDto(customer);
	}

	@Override
	@Transactional
	public void saveCustomer(CustomerDTO customerDto) {
		Customer customer = customerConverter.convertToEntity(customerDto);
		saveCustomer(customer);
	}

	@Override
	@Transactional
	public void updateCustomer(@Valid CustomerDTO customerDto, String cpf) {
		Customer customer = getCustomer(cpf);
		customerConverter.copyToEntity(customerDto, customer);
		saveCustomer(customer);
	}

	private Customer getCustomer(String cpf) {
		Customer customer = customerRepository.getByCpf(cpf);
		if (Objects.isNull(customer)) throw new RestException("Exception.notFound", new Object[]{"Customer", cpf}) ;
		return customer;
	}

	private void saveCustomer(Customer saved) {
		try {
			customerRepository.save(saved);		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RestException("Exception.sql", new Object[]{"Customer"});
		}
	}

	@Override
	@Transactional
	public void updateCustomer(CustomerDTO customerDto, Long idCustomer) {
		try {
			Customer saved = customerRepository.getById(idCustomer);
			customerConverter.copyToEntity(customerDto, saved);
			customerRepository.save(saved);		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RestException("Exception.sql", new Object[]{"Customer", customerDto});
		}
	}

	@Override
	@Transactional
	public void deleteCustomer(Long idCustomer) {
		try {
			customerRepository.deleteById(idCustomer);
		} catch (Exception e) {
			throw new RestException("Exception.notFound", new Object[]{"Customer", idCustomer});
		}

	}

}
