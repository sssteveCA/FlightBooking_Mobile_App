package com.example.flightbooking.fragments.home.hotel.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.flightbooking.fragments.home.hotel.HotelFragmentModel;
import com.example.flightbooking.interfaces.Globals;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotelImagesModel {

    /**
     * When all requests finish (successful or not)
     */
    public interface RequestsFinish{
        public void onFinish(ArrayList<Bitmap> images);
    }

    private Context context;
    private String country;
    private String city;
    private String hotel;
    /**
     * Number of hotel images
     */
    private int images;
    /**
     * Number of images successfully fetched
     */
    private int fetched;
    private HotelImagesClient hic;
    private ArrayList<Bitmap> fetchedImages = new ArrayList<>();

    public HotelImagesModel(Context context,String country, String city, String hotel, int images){
        Log.d("HotelImagesModel","constructor");
        this.context = context;
        this.country = country;
        this.city = city;
        this.hotel = hotel;
        this.images = images;
        this.hic = new HotelImagesClient(this.context);
    }

    public String getCountry(){ return this.country; }
    public String getCity(){ return this.city; }
    public String getHotel(){ return this.hotel; }
    public int getImages(){ return this.images; }
    public ArrayList<Bitmap> getFetchedImages(){ return this.fetchedImages; }

    /**
     * Get the URL images prefix to use for requests
     * @return
     */
    private String urlImagesPrefix(){
        String prefix = "/"+this.country+"/"+this.city+"/"+this.hotel+"/"+this.hotel+"_";
        prefix = prefix.replaceAll("[\\s\\-&]","_");
        prefix = prefix.replaceAll("[+()]","");
        return prefix;
    }

    /**
     * Execute all the requests to get the images
     */
    public void executeRequests(RequestsFinish rf){
        Log.d("HotelImagesModel","executeRequests");
        this.fetched = 0;
        String prefix = this.urlImagesPrefix();
        prefix = Globals.BASE_URL+Globals.HOTEL_IMG_FOLDER+prefix;
        Log.d("HotelImagesModel","executeRequests prefix => "+prefix);
        HotelImagesModel this_him = this;
        for(int i = 1; i <= this.images; i++){
            String url = prefix+i+".jpg";
            this.hic.addRequest(url,new HotelImagesClient.HotelImagesListener() {
                @Override
                public void imageResponse(Bitmap image) {
                    Log.d("HotelImagesModel","addRequest listener "+url+" complete");
                    this_him.fetchedImages.add(image);
                    this_him.fetched++;
                    if(this_him.fetched >= this_him.images)
                        rf.onFinish(this_him.fetchedImages);
                }
                @Override
                public void imageError(String message) {
                    Log.e("HotelImagesModel","addRequest listener "+url+" error");
                    Log.e("HotelImagesModel","executeRequests errore => "+message);
                    this_him.fetched++;
                    if(this_him.fetched >= this_him.images)
                        rf.onFinish(this_him.fetchedImages);
                }
            });
        }
    }
}
