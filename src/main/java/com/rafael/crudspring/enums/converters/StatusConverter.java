package com.rafael.crudspring.enums.converters;

import java.util.stream.Stream;

import com.rafael.crudspring.enums.Status;

import jakarta.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if(status == null){
            return null;
        }
        return status.getValue();
    }


    @Override
    public Status convertToEntityAttribute(String value) {
        return Stream.of(Status.values()).filter(s -> s.getValue().equals(value)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
