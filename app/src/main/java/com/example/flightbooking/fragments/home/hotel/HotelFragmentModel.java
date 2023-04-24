package com.example.flightbooking.fragments.home.hotel;

import android.content.Context;

import com.example.flightbooking.common.Connection;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.requests.hotel.HotelSearch;
import com.example.flightbooking.models.response.hotel.HotelInfo;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelFragmentModel {

    public interface GetHotelsInfo{
        public void hotelInfoResponse(JsonObject hotels);
        public void hotelInfoError(String message);
    }

    public interface GetHotelPreviewInfo{
        public void hotelPreviewResponse(HotelInfo hi);
        public void hotelPreviewError(String message);
    }

    private Context context;
    private HotelFragmentClient hfc;
    private JsonObject hotelsInfo = null;

    public enum EditTextsDate{
        CKECK_IN,CHECK_OUT
    }

    public HotelFragmentModel(Context ctx){
        this.context = ctx;
        this.hfc = new HotelFragmentClient();
    }

    /**
     *  Check if phone is connected to internet
     * @return boolean
     */
    public boolean getConnectionStatus(){
        return Connection.checkInternet(this.context);
    }
    public JsonObject getHotelsInfo(){return this.hotelsInfo;}

    /**
     * Get a list of cities in a country that have bookable hotels
     * @param country the country to search the cities
     * @return
     */
    public LinkedList<String> getHotelCitiesOfCountry(String country){
        LinkedList<String> cities = new LinkedList<>();
        if(this.hotelsInfo != null){
            if(this.hotelsInfo.has(country)){
                JsonObject joCities = this.hotelsInfo.getAsJsonObject(country);
                Set<Map.Entry<String, JsonElement>> entries = joCities.entrySet();
                for(Map.Entry<String, JsonElement> entry: entries){
                    cities.add(entry.getKey());
                }
            }//if(this.hotelsInfo.has(country)){
        }//if(this.hotelsInfo != null){

        return cities;
    }

    /**
     * Get the information of a specific hotel
     * @param country the coutnry where the hotel is located
     * @param city the city where the hotel is located
     * @param hotel the hotel to search
     * @return An HashMap with the hotel information
     */
    public HashMap<String, Object> getHotelInfo(String country, String city, String hotel){
        HashMap<String, Object> info = new HashMap<>();
        if(this.hotelsInfo != null){
            if(this.hotelsInfo.has(country)){
                JsonObject joCities = this.hotelsInfo.getAsJsonObject(country);
                if(joCities.has(city)){
                    JsonObject joHotels = joCities.getAsJsonObject(city);
                    if(joHotels.has(hotel)){
                        JsonObject joInfo = joHotels.getAsJsonObject(hotel);
                        info.put("max_people", joInfo.get("max_people").getAsInt());
                        info.put("price",joInfo.get("price").getAsFloat());
                        info.put("rooms",joInfo.get("rooms").getAsInt());
                        info.put("score",joInfo.get("score").getAsInt());
                        info.put("stars",joInfo.get("stars").getAsInt());
                        info.put("images",joInfo.get("images").getAsInt());
                    }//if(joHotels.has(hotel)){
                }//if(joCities.has(hotel)){
            }//if(this.hotelsInfo.has(country)){
        }//if(this.hotelsInfo != null){
        return info;
    }

    /**
     * Get a list with the countries that contain bookable hotels
     * @return
     */
    public LinkedList<String> getHotelsCountries(){
        LinkedList<String> countries = new LinkedList<>();
        if(this.hotelsInfo != null){
            Set<Map.Entry<String, JsonElement>> entries = this.hotelsInfo.entrySet();
            for(Map.Entry<String, JsonElement> entry: entries){
                countries.add(entry.getKey());
            }
        }//if(this.hotelsInfo != null){
        return countries;
    }

    /**
     * Get a list of bookable hotels that are in a particular city
     * @param country the country to search the hotels
     * @param city the city to search the hotels
     * @return
     */
    public LinkedList<String> getHotelsOfCity(String country, String city){
        LinkedList<String> hotels = new LinkedList<>();
        if(this.hotelsInfo != null){
            if(this.hotelsInfo.has(country)){
                JsonObject joCities = this.hotelsInfo.getAsJsonObject(country);
                if(joCities.has(city)){
                    JsonObject joHotels = joCities.getAsJsonObject(city);
                    Set<Map.Entry<String, JsonElement>> entries = joHotels.entrySet();
                    for(Map.Entry<String, JsonElement> entry: entries){
                        hotels.add(entry.getKey());
                    }
                }//if(joCities.has(city)){
            }//if(this.hotelsInfo.has(country)){
        }//if(this.hotelsInfo != null){
        return hotels;
    }

    /**
     * Perform the HTTP request to get all available hotels details
     * @param ghi
     */
    public void getHotelsInfoRequest(GetHotelsInfo ghi){
        HotelFragmentModel this_hfm = this;
        this.hfc.getHfi().hotels().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    this_hfm.hotelsInfo = response.body();
                    ghi.hotelInfoResponse(this_hfm.hotelsInfo);
                }//if(response.isSuccessful()){
                else{
                    try {
                        String errorBody = response.errorBody().string();
                        ghi.hotelInfoError(errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ghi.hotelInfoError(t.getMessage());
            }
        });
    }

    /**
     * Perform the HTTP request to get a price preview of booked hotel
     * @param hs the request body
     * @param ghpi the listener called when the request ends
     */
    public void hotelPreviewRequest(HotelSearch hs, GetHotelPreviewInfo ghpi){
        this.hfc.getHfi().hotelPrice(hs).enqueue(new Callback<HotelInfo>() {
            @Override
            public void onResponse(Call<HotelInfo> call, Response<HotelInfo> response) {
                if(response.isSuccessful()){
                    ghpi.hotelPreviewResponse(response.body());
                }
                else{
                    String message = "";
                    try {
                        String jsonString = response.errorBody().string();
                        JsonElement je = JsonParser.parseString(jsonString);
                        JsonObject jo = je.getAsJsonObject();
                        message = jo.get(Globals.KEY_MESSAGE).getAsString();
                        if(message == null) message = Globals.ERR_REQUEST;
                    } catch (IOException e) {
                        e.printStackTrace();
                        message = Globals.ERR_REQUEST;
                    } finally {
                        ghpi.hotelPreviewError(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<HotelInfo> call, Throwable t) {
                ghpi.hotelPreviewError(Globals.ERR_REQUEST);
            }
        });
    }
}
