package com.pharmacy.locationfinder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Creates custom exception for resources not found.
 * @author Niharika, Nallappagari
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomFileNotFoundException extends RuntimeException 
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for {@link CustomFileNotFoundException}.
     * @param exception - thrown exception
     */
    public CustomFileNotFoundException(String exception) {
        super(exception);
    }
}