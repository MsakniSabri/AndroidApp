package com.example.projectfnackiller.phisicallibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfnackiller.API.Library;
import com.example.projectfnackiller.BookActivity;
import com.example.projectfnackiller.R;



public class LibrariesViewHolderLibrary extends RecyclerView.ViewHolder implements View.OnClickListener{



    TextView view;
    TextView view2;
    TextView locaview;
    ImageView imageView;
    String bookTitle;

    String loca;
    Library currentLib;
    int gender;

    public LibrariesViewHolderLibrary(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        view = itemView.findViewById(R.id.locationText);
        view2 = itemView.findViewById(R.id.quatityText);
        locaview = itemView.findViewById(R.id.distanceText);
        ImageView imageView = itemView.findViewById(R.id.imageView);





        // int resourceId = R.drawable.class.getField("fr_200_133").getInt(null);


        //Log.i("COMPARE",gender + " : to : " + R.drawable.sword);


    }

    @Override
    public void onClick(View view) {

        Context context = view.getContext();

        Uri gmmIntentUri = Uri.parse(loca);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);

    }



    public TextView getView(){
        return view;
    }
    public TextView getView2(){
        return view2;
    }

    public void setLib(Library lib){
        this.currentLib = lib;
        this.loca = "geo:0,0?q=" + lib.getLatitude()+ ","+ lib.getLongitude()+"(foo)";
    }


    public String calculateDistance(double lon1,double lon2,double lat1,double lat2){
        double weight;

        // Haversine formula
        double dlon = Math.toRadians(lon2) - Math.toRadians(lon1);
        double dlat = Math.toRadians(lat2) - Math.toRadians(lat1);
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        weight = (c*r);
        return "" + weight;
    }

    public void setTitle(String s){
        this.bookTitle = s;
    }

    public void setDistance(double lat, double longitude){
        locaview.setText(getSafeSubstring(calculateDistance(longitude,currentLib.getLongitude(),lat,currentLib.getLatitude()),5));
    }

    public void setGender(int s){
        this.gender= s;
    }

    public String getSafeSubstring(String s, int maxLength){
        if(!TextUtils.isEmpty(s)){
            if(s.length() >= maxLength){
                return s.substring(0, maxLength);
            }
        }
        return s;
    }

}