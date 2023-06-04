package ru.java.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;

public class ModelMapperException extends ResponseStatusException {

    public ModelMapperException(String type, String shortMaessage) {

        super(
                HttpStatus.INTERNAL_SERVER_ERROR,
                format("'%s' exception '%s' ", type, shortMaessage));
    }

}
