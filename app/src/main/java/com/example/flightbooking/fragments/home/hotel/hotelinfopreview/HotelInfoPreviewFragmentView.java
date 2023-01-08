package com.example.flightbooking.fragments.home.hotel.hotelinfopreview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HotelInfoPreviewFragmentView {

    private Context context;
    private LinearLayout ll_table;
    private TableLayout table;
    private Button bt_book;
    private Button bt_back;

    public HotelInfoPreviewFragmentView(Context context,LinearLayout ll_table, Button bt_book, Button bt_back){
        this.context = context;
        this.ll_table = ll_table;
        this.table = new TableLayout(this.context);
        this.bt_book = bt_book;
        this.bt_back = bt_back;
    }

    public LinearLayout getLlTable(){return this.ll_table;}
    public TableLayout getTable(){return this.table;}
    public Button getBtBook(){return this.bt_book;}
    public Button getBtBack(){return this.bt_back;}

    /**
     * Create a TableRow to add to TableLayout
     * @param key the label of the row
     * @param val the value of the row
     * @return TableRow view
     */
    public TableRow hotelInfoTableRow(String key, Object val){
        TableRow tr = new TableRow(this.context);
        TextView tv_row_name = new TextView(this.context);
        tv_row_name.setText(key);
        tv_row_name.setTypeface(null, Typeface.BOLD);
        tv_row_name.setPadding(10,20,10,20);
        tv_row_name.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        tr.addView(tv_row_name);
        TextView tv_row_val = new TextView(this.context);
        tv_row_val.setText(val.toString());
        tv_row_val.setPadding(10,20,10,20);
        tv_row_val.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        tr.addView(tv_row_val);
        return tr;
    }

    /**
     * Add a TextView caption to TableLayout
     * @param caption
     */
    public void setTvTableCaption(String caption){
        TextView table_caption = new TextView(this.context);
        LinearLayout.LayoutParams linear_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linear_lp.setMargins(0,40,0,0);
        table_caption.setLayoutParams(linear_lp);
        table_caption.setText(caption.toUpperCase());
        table_caption.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        table_caption.setGravity(Gravity.CENTER);
        this.getLlTable().addView(table_caption);
    }
}
