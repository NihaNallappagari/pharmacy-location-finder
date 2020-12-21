package com.pharmacy.locationfinder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Creates custom exception for invalid parameters.
 * @author Niharika, Nallappagari
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomInvalidParamsException extends RuntimeException 
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for {@link CustomInvalidParamsException}.
     * @param exception - thrown exception
     */
    public CustomInvalidParamsException(String exception) {
        super(exception);
    }
}
