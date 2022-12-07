package com.example.flightbooking.fragments.home.hotel.images;

import com.example.flightbooking.fragments.home.hotel.HotelFragmentModel;
import com.example.flightbooking.interfaces.Globals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotelImagesModel {
    private String country;
    private String city;
    private String hotel;
    /**
     * Number of hotel images
     */
    private int images;

    public HotelImagesModel(String country, String city, String hotel, int images){
        this.country = country;
        this.city = city;
        this.hotel = hotel;
        this.images = images;
    }

    public String getCountry(){ return this.country; }
    public String getCity(){ return this.city; }
    public String getHotel(){ return this.hotel; }
    public int getImages(){ return this.images; }

    /**
     * Get the URL images prefix to use for requests
     * @return
     */
    private String urlImagesPrefix(){
        String prefix = "/"+this.country+"/"+this.city+"/"+this.hotel+"_";
        prefix = prefix.replaceAll("[\\s\\-&]","_");
        prefix = prefix.replaceAll("[+()]","");
        return prefix;
    }
}
