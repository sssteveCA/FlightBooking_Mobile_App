package com.example.flightbooking.fragments.home.hotel.images;

import com.example.flightbooking.fragments.home.hotel.HotelFragmentModel;

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
}
