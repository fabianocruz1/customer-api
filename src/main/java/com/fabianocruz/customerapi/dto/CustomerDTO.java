package com.fabianocruz.customerapi.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class CustomerDTO {

    @NotNull
    @Size(min=2, max=100)
    private String name;
    
    @NotNull
    @Size(min=11, max=11)
    @CPF
    private String cpf;
    
    @NotNull
    @Valid
    private AddressDTO address;

}
