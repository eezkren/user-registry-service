package com.isilona.registry.domain.service.validation;

public interface ValidationService<T> {

    boolean isValid(T validatedObject);
}
