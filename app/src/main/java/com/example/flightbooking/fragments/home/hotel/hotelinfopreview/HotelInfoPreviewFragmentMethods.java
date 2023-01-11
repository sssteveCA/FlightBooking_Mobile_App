package com.example.flightbooking.fragments.home.hotel.hotelinfopreview;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.HashMap;
import java.util.Map;

public class HotelInfoPreviewFragmentMethods {

    public static void setTable(Context context, HotelInfoPreviewFragmentModel hipfm, HotelInfoPreviewFragmentView hipfv){
        hipfv.setTvTableCaption("Preventivo");
        HotelInfoPreviewFragmentMethods.setTableLayout(hipfm.getTableData(), hipfm, hipfv);
    }

    /**
     * Set the TableLayout and its children
     * @param hotelData
     * @param hipfm
     * @param hipfv
     */
    public static void setTableLayout(HashMap<String, Object> hotelData, HotelInfoPreviewFragmentModel hipfm, HotelInfoPreviewFragmentView hipfv){
        LinearLayout.LayoutParams linear_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linear_lp.setMargins(0,10,0,0);
        hipfv.getTable().setLayoutParams(linear_lp);
        for(Map.Entry<String, Object> td_set: hotelData.entrySet()){
            TableRow tr = hipfv.hotelInfoTableRow(td_set.getKey(), td_set.getValue());
            hipfv.getTable().addView(tr);
            hipfm.setHiptvTableValues(td_set.getKey(), td_set.getValue());
        }//for(Map.Entry<String, Object> td_set: hotelData.entrySet()){
        hipfv.getLlTable().addView(hipfv.getTable());
    }
}
