package com.pharmacy.locationfinder.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.locationfinder.exceptions.CustomFileNotFoundException;
import com.pharmacy.locationfinder.exceptions.CustomInvalidParamsException;
import com.pharmacy.locationfinder.models.PharmacyLocationResult;
import com.pharmacy.locationfinder.service.PharmacyService;

/**
 * REST API providing service to pharmacy location.
 * @author Niharika, Nallappagari
 */
@RestController
public class PharmacyLocationController
{
    @Autowired
    private PharmacyService pharmaService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PharmacyLocationController.class);

    /**
     * If user enter valid information and pharmacies exists, then the service returns list of
     * nearest pharmacies. If exceptions are thrown they service will send appropriate message and
     * error details.
     * @param userLatitude - provided user latitude that is used for distance calculation.
     * @param userLongitude - provided user longitude that is used for distance calculation.
     * @return HTTP Response Entity which contains the HTTP status code and list of nearest
     *         pharmacies.
     */
    @GetMapping(path = "/pharmacy/latitude/{userLatitude}/longitude/{userLongitude}")
    public ResponseEntity<List<PharmacyLocationResult>> getNearestPharmacy(@PathVariable final String userLatitude, @PathVariable final String userLongitude)
    {
        if(userLatitude == null)
        {
            LOGGER.error("Exception occured in " + PharmacyLocationController.class.getName()
                    + "getNearestPharmacy. Exception: Missing user latitude");
            throw new CustomInvalidParamsException("Missing user latitude");
        }
        if(userLongitude == null)
        {
            LOGGER.error("Exception occured in " + PharmacyLocationController.class.getName()
                    + "getNearestPharmacy. Exception: Missing user longitude");
            throw new CustomInvalidParamsException("Missing user longitude");
        }
        double latitudeDouble = 0.0;
        try
        {
            latitudeDouble = Double.parseDouble(userLatitude);
        }
        catch(NumberFormatException e)
        {
            LOGGER.error("Exception occured in " + PharmacyLocationController.class.getName()
                    + "getNearestPharmacy. Exception: Invalid user latitude", e);
            throw new CustomInvalidParamsException("Invalid user latitude");
        }
        double longitudeDouble = 0.0;
        try
        {
            longitudeDouble = Double.parseDouble(userLongitude);
        }
        catch(NumberFormatException e)
        {
            LOGGER.error("Exception occured in " + PharmacyLocationController.class.getName()
                    + "getNearestPharmacy. Exception: Invalid user longitude", e);
            throw new CustomInvalidParamsException("Invalid user longitude");
        }
        try
        {
            List<PharmacyLocationResult> pharmacies = pharmaService.getNearestPharmacies(latitudeDouble, longitudeDouble);
            return new ResponseEntity<List<PharmacyLocationResult>>(pharmacies, HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
            LOGGER.error("Exception occured in " + PharmacyLocationController.class.getName()
                    + "getNearestPharmacy. Exception: Failed to retrieve csv file location", e);
            throw new CustomFileNotFoundException("Failed to retrieve csv file location");
        }
        catch(FileNotFoundException e)
        {
            LOGGER.error("Exception occured in " + PharmacyLocationController.class.getName()
                    + "getNearestPharmacy. Exception: Failed to retrieve csv file", e);
            throw new CustomFileNotFoundException("Failed to retrieve csv file");
        }
        catch(IOException e)
        {
            LOGGER.error("Exception occured in " + PharmacyLocationController.class.getName()
                    + "getNearestPharmacy. Exception: Failed to read data from csv file", e);
            throw new CustomFileNotFoundException("Failed to read data from csv file");
        }
    }
}