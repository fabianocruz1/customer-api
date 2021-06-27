package com.fabianocruz.customerapi.util;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.fabianocruz.customerapi.dto.CustomerDTO;
import com.fabianocruz.customerapi.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DTOModelMapper extends RequestResponseBodyMethodProcessor {
    private ModelMapper modelMapper = null;

    private EntityManager entityManager;

    public ModelMapper modelMapper() {
    	ModelMapper modelMapper = new ModelMapper();
    	TypeMap<Customer, CustomerDTO> typeMap = modelMapper.createTypeMap(Customer.class, CustomerDTO.class);
    	typeMap.addMappings(mapper -> {
    	  mapper.map(Customer::getAddress, CustomerDTO::setAddress);
    	});
    	return modelMapper;
    }
    
    public DTOModelMapper(ObjectMapper objectMapper, EntityManager entityManager) {
        super(Collections.singletonList(new MappingJackson2HttpMessageConverter(objectMapper)));
        this.entityManager = entityManager;
        this.modelMapper = modelMapper();
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(DTO.class);
    }

    @Override
    protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
        binder.validate();
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object dto = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        Object id = getEntityId(dto);
        if (id == null) {
            return modelMapper.map(dto, parameter.getParameterType());
        } else {
            Object persistedObject = entityManager.find(parameter.getParameterType(), id);
            Check.notNull(persistedObject, "Exception.notFound",
                    parameter.getParameterType().getSimpleName(), id);
            modelMapper.map(dto, persistedObject);
            return persistedObject;
        }
    }

    @Override
    protected Object readWithMessageConverters(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {
        for (Annotation ann : parameter.getParameterAnnotations()) {
            DTO dtoType = AnnotationUtils.getAnnotation(ann, DTO.class);
            if (dtoType != null) {
                return super.readWithMessageConverters(inputMessage, parameter, dtoType.value());
            }
        }
        throw new RuntimeException();
    }

    private Object getEntityId(@NotNull Object dto) {
        for (Field field : dto.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Id.class) != null) {
                try {
                    field.setAccessible(true);
                    return field.get(dto);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }
}