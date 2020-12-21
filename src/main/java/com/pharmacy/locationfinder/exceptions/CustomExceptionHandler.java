package com.pharmacy.locationfinder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Default to class for custom exceptions.
 * @author Niharika, Nallappagari
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    /**
     * Creates custom file not found exception with appropriate details.
     * @param exception - thrown exception
     * @param request - request that made
     * @return response entity with appropriate error details and status.
     */
    @ExceptionHandler(CustomFileNotFoundException.class)
    public final ResponseEntity<Object> handleFilerNotFoundException(CustomFileNotFoundException exception, WebRequest request)
    {
        ErrorResponse error = new ErrorResponse("Resource Not Found", exception.getLocalizedMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Creates custom invalid params exception with appropriate details.
     * @param exception - thrown exception
     * @param request - request that made
     * @return response entity with appropriate error details and status.
     */
    @ExceptionHandler(CustomInvalidParamsException.class)
    public final ResponseEntity<Object> userArgumentNotValid(CustomInvalidParamsException exception, WebRequest request)
    {
        ErrorResponse errorResponse = new ErrorResponse("Validation Failed", exception.getLocalizedMessage());
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
