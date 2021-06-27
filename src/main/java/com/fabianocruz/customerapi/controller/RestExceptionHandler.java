package com.fabianocruz.customerapi.controller;


import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fabianocruz.customerapi.util.RestException;
import com.fabianocruz.customerapi.util.RestMessage;

@ControllerAdvice
public class RestExceptionHandler {
    private static final String UNEXPECTED_ERROR = "Exception.unexpected";
    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<RestMessage> handleIllegalArgument(RestException ex, Locale locale) {
    	System.out.println(".....handleIllegalArgument....");

        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);
        return new ResponseEntity<>(new RestMessage(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestMessage> handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
    	System.out.println(".....handleArgumentNotValidException....");
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getAllErrors()
                .stream()
                .map(objectError -> messageSource.getMessage(objectError, locale))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new RestMessage(errorMessages), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestMessage> handleExceptions(Exception ex, Locale locale) {
    	System.out.println(".....handleExceptions....");
        String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, locale);
        ex.printStackTrace();
        return new ResponseEntity<>(new RestMessage(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}