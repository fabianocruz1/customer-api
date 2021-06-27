package com.fabianocruz.customerapi.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fabianocruz.customerapi.model.Customer;
import com.fabianocruz.customerapi.repository.CustomerRepository;
import com.fabianocruz.customerapi.util.RestException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Page<Customer> readAllCustomers(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}

	@Override
	public Customer readCustomer(String cpf) {
		Customer customer = customerRepository.getByCpf(cpf);
		if (Objects.isNull(customer)) throw new RestException("Exception.notFound", new Object[]{"Customer", cpf}) ;
		return customer;
	}

	@Override
	public void saveCustomer(Customer customer) {
		try {
			customerRepository.save(customer);		
		} catch (Exception e) {
			throw new RestException("Exception.sql", new Object[]{"Customer", customer});
		}
	}

	@Override
	public void updateCustomer(Customer customer, long idCustomer) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteCustomer(long idCustomer) {
		// TODO Auto-generated method stub
		
	}

}
