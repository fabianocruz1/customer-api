package com.fabianocruz.customerapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.fabianocruz.customerapi.model.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{

}
