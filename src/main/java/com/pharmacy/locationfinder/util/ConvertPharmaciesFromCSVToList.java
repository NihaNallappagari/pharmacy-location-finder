package com.pharmacy.locationfinder.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.pharmacy.locationfinder.models.Pharmacy;

/**
 * Convert pharmacy csv file data to pharmacy objects list.
 * @author Niharika, Nallappagari
 */
public class ConvertPharmaciesFromCSVToList
{

    /**
     * Converts pharmacy csv file rows to pharmacy objects and adds to list.
     * @return List of pharmacy objects that has pharmacy information from csv file.
     * @throws IOException if stream to file cannot be written to, or closed.
     * @throws NumberFormatException if file does not exist.
     */
    public static List<Pharmacy> listOfPharmacies() throws NumberFormatException, IOException
    {
        List<Pharmacy> pharmacies = new ArrayList<>();
        URL pharmaciesCSVPath = ConvertPharmaciesFromCSVToList.class.getResource("pharmacies.csv");
        if(pharmaciesCSVPath == null)
            throw new NullPointerException();
        else
        {
            String fileLocation = pharmaciesCSVPath.getPath();
            BufferedReader readFileData = new BufferedReader(new FileReader(fileLocation));
            String line = "";
            // Remove label line as we don't need it to be saved.
            readFileData.readLine();
            // Iterate through each row create a pharmacy class and add it to a list.
            while((line = readFileData.readLine()) != null)
            {
                String[] pharmaciesDataFromCSV = line.split(",");
                Pharmacy pharmacy = new Pharmacy();
                pharmacy.setName(pharmaciesDataFromCSV[0].substring(1, pharmaciesDataFromCSV[0].length() - 1));
                pharmacy.setAddress(pharmaciesDataFromCSV[1].substring(1, pharmaciesDataFromCSV[1].length() - 1));
                pharmacy.setCity(pharmaciesDataFromCSV[2].substring(1, pharmaciesDataFromCSV[2].length() - 1));
                pharmacy.setState(pharmaciesDataFromCSV[3].substring(1, pharmaciesDataFromCSV[3].length() - 1));
                pharmacy.setZipCode(Integer.parseInt(pharmaciesDataFromCSV[4]));
                pharmacy.setLatitude(Double.parseDouble(pharmaciesDataFromCSV[5]));
                pharmacy.setLongitude(Double.parseDouble(pharmaciesDataFromCSV[6]));
                pharmacies.add(pharmacy);
            }
            readFileData.close();
        }
        return pharmacies;
    }
}
