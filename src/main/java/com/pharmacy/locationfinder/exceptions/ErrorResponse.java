package com.pharmacy.locationfinder.exceptions;

/**
 * Response containing additional context for failure reason.
 * @author Niharika, Nallappagari
 */
public class ErrorResponse
{
    private String message;
    private String details;

    /**
     * Constructor for {@link ErrorResponse}.
     * @param message - thrown exception error message
     * @param details - thrown exception error details
     */
    public ErrorResponse(String message, String details)
    {
        super();
        this.setMessage(message);
        this.setDetails(details);
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * @return the details
     */
    public String getDetails()
    {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details)
    {
        this.details = details;
    }
}
