package com.fabianocruz.customerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabianocruz.customerapi.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer getByCpf(String cpf);

}
