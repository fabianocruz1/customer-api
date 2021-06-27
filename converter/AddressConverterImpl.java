package com.fabianocruz.customerapi.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fabianocruz.customerapi.dto.AddressDTO;
import com.fabianocruz.customerapi.model.Address;

@Component
public class AddressConverterImpl implements AddressConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Address toEntity(AddressDTO addressDTO) {
    	return modelMapper.map(addressDTO, Address.class);
    }
    
	@Override
	public AddressDTO toDto(Address address) {
		return modelMapper.map(address, AddressDTO.class);
	}

	@Override
	public List<AddressDTO> toDto(List<Address> addresses) {
		return addresses.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public void updateModel(AddressDTO addressDTO, Address address) {
		Address updated = toEntity(addressDTO);
		modelMapper.map(updated, address);
	}

}
