package com.fabianocruz.customerapi.converter;

import java.util.List;

import com.fabianocruz.customerapi.dto.AddressDTO;
import com.fabianocruz.customerapi.model.Address;

public interface AddressConverter {
	AddressDTO toDto(Address address);
	List<AddressDTO> toDto(List<Address> address);
	Address toEntity(AddressDTO addressDTO);
	void updateModel(AddressDTO addressDTO, Address address);
}
