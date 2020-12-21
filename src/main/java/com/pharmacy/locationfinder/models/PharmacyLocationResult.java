package com.pharmacy.locationfinder.models;

/**
 * POJO for Pharmacy Location Result.
 * @author Niharika, Nallappagari
 */
public class PharmacyLocationResult
{
    private double distance;
    private String pharmacyName;
    private String street;
    private String city;
    private String state;
    private int zipCode;

    /**
     * @return the distance
     */
    public double getDistance()
    {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    /**
     * @return the pharmacyName
     */
    public String getPharmacyName()
    {
        return pharmacyName;
    }

    /**
     * @param pharmacyName the pharmacy name to set
     */
    public void setName(String pharmacyName)
    {
        this.pharmacyName = pharmacyName;
    }

    /**
     * @return the street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * @return the zipCode
     */
    public int getZipCode()
    {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }
}
