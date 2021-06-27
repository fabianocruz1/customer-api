package com.fabianocruz.customerapi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
    private Object[] args;
}