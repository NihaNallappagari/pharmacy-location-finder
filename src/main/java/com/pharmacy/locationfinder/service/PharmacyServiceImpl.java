package com.pharmacy.locationfinder.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.pharmacy.locationfinder.models.Pharmacy;
import com.pharmacy.locationfinder.models.PharmacyLocationResult;
import com.pharmacy.locationfinder.util.ConvertPharmaciesFromCSVToList;

/**
 * Implementation of {@link PharmacyService}
 * @author Niharika, Nallappagari
 */
@Service
public class PharmacyServiceImpl implements PharmacyService
{
    @Override
    public List<PharmacyLocationResult> getNearestPharmacies(double userLatitude, double userLongitude) throws FileNotFoundException, IOException
    {
        List<Pharmacy> pharmacies = ConvertPharmaciesFromCSVToList.listOfPharmacies();
        Map<Double, List<Pharmacy>> nearestPharmacies = new TreeMap<>();
        pharmacies.stream().forEach(eachPharmacy -> {
            double currentPharmaciesDistance = this.calculateDistance(userLatitude, userLongitude, eachPharmacy.getLatitude(),
                    eachPharmacy.getLongitude());
            if(nearestPharmacies.containsKey(currentPharmaciesDistance))
            {
                nearestPharmacies.get(currentPharmaciesDistance).add(eachPharmacy);
            }
            else
            {
                List<Pharmacy> listOfPharmacies = new ArrayList<>();
                listOfPharmacies.add(eachPharmacy);
                nearestPharmacies.put(currentPharmaciesDistance, listOfPharmacies);
            }
        });
        List<PharmacyLocationResult> pharmaLocationResults = new ArrayList<>();
        if(nearestPharmacies.size() > 0)
        {
            // Create a result class and add data thats need to send to front end and return the
            // list.
            Map.Entry<Double, List<Pharmacy>> nearestPharmacyWithLocation = ((TreeMap<Double, List<Pharmacy>>) nearestPharmacies)
                    .firstEntry();
            nearestPharmacyWithLocation.getValue().forEach(nearestPharm -> {
                PharmacyLocationResult nearestPharmacyResult = new PharmacyLocationResult();
                nearestPharmacyResult.setName(nearestPharm.getName().trim());
                nearestPharmacyResult.setStreet(nearestPharm.getAddress().trim());
                nearestPharmacyResult.setCity(nearestPharm.getCity().trim());
                nearestPharmacyResult.setState(nearestPharm.getState().trim());
                nearestPharmacyResult.setZipCode(nearestPharm.getZipCode());
                // Assuming we can round miles to 2 decimals.
                nearestPharmacyResult.setDistance(Math.round(nearestPharmacyWithLocation.getKey() * 100.0) / 100.0);
                pharmaLocationResults.add(nearestPharmacyResult);
            });
        }
        return pharmaLocationResults;
    }

    // As private methods are not exposed, javadoc is not needed.
    private double calculateDistance(double userLatitude, double userLongitude, double pharmacyLatitude, double pharmacyLongitude)
    {
        double longitudeDifference = userLongitude - pharmacyLongitude;
        double dist = Math.sin(Math.toRadians(userLatitude)) * Math.sin(Math.toRadians(pharmacyLatitude))
                + Math.cos(Math.toRadians(userLatitude)) * Math.cos(Math.toRadians(pharmacyLatitude))
                        * Math.cos(Math.toRadians(longitudeDifference));
        return Math.toDegrees(Math.acos(dist)) * 60 * 1.1515;
    }
}
