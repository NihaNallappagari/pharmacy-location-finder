package com.pharmacy.locationfinder.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pharmacy.locationfinder.models.PharmacyLocationResult;

/**
 * Interface describing a service used for retrieving pharmacy information.
 * @author Niharika, Nallappagari
 */
@Service
public interface PharmacyService
{
    /**
     * Service returns nearest pharamcies list for valid latitude and longitude else throw
     * appropriate exceptions.
     * @return list of nearest Pharmacies.
     * @throws IOException if stream to file cannot be written to, or closed.
     * @throws NumberFormatException if file does not exist.
     */
    public List<PharmacyLocationResult> getNearestPharmacies(double userLatitude, double userLongitude) throws FileNotFoundException, IOException;
}
