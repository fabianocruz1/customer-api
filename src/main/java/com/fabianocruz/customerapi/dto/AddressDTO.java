package com.fabianocruz.customerapi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class AddressDTO {

	@Id
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String street;

	@NotNull
	@Size(min = 1, max = 30)
	private String state;

	@NotNull
	@Size(min = 1, max = 30)
	private String city;

	@NotNull
	@Size(min = 1, max = 10)
	private String postalCode;

	@NotNull
	@Size(min = 1, max = 30)
	private String country;

}
