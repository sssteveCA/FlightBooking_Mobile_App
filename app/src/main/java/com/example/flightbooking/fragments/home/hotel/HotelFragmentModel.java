package com.example.flightbooking.fragments.home.hotel;

import android.content.Context;

import com.example.flightbooking.common.Connection;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelFragmentModel {

    public interface GetCountries{
        public void countriesResponse(List<String> countries);
        public void countriesError(String message);
    }

    public interface GetCities{
        public void citiesResponse(List<String> cities);
        public void citiesError(String message);
    }

    public interface GetHotels{
        public void hotelsResponse(List<String> hotels);
        public void hotelsError(String message);
    }

    private Context context;
    private HotelFragmentClient hfc;

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

    /**
     * Perform the HTTP request to get the hotel countries list
     */
    public void getCountries(GetCountries gc){
        this.hfc.getHfi().countries().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()){
                    List<String> countries = response.body();
                    gc.countriesResponse(countries);
                }
                else{
                    String errorBody = "";
                    try {
                        errorBody = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        gc.countriesError(errorBody);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                gc.countriesError(t.getMessage());
            }
        });
    }

    /**
     * Perform the HTTP request to get the hotel cities list of a particular country
     */
    public void getCities(String country, GetCities gc){
        this.hfc.getHfi().cities(country).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()){
                    List<String> cities = response.body();
                    gc.citiesResponse(cities);
                }
                else{
                    String errorBody = "";
                    try {
                        errorBody = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        gc.citiesError(errorBody);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                gc.citiesError(t.getMessage());
            }
        });
    }

    /**
     * Perform the HTTP request to get the available hotels of a country in a particular city
     */
    public void getHotels(String country, String city, GetHotels gh){
        this.hfc.getHfi().hotels(country,city).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()){
                    List<String> hotels = response.body();
                    gh.hotelsResponse(hotels);
                }
                else{
                    String errorBody = "";
                    try {
                        errorBody = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        gh.hotelsError(errorBody);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                gh.hotelsError(t.getMessage());
            }
        });
    }
}
