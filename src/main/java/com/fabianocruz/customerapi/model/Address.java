package com.fabianocruz.customerapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "addresses")
public class Address {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false, length=100)
	private String street;

	@Column(nullable=false, length=30)
	private String state;
	
	@Column(nullable=false, length=30)
	private String city;

	@Column(nullable=false, length=10)
	private String postalCode;

	@Column(nullable=false, length=30)
	private String country;
	
}
