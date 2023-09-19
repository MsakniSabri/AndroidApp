package com.example.projectfnackiller;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Comparator;
import java.util.Objects;

import com.example.projectfnackiller.API.Book;
import com.example.projectfnackiller.API.LibrariesList;
import com.example.projectfnackiller.API.Library;
import com.example.projectfnackiller.libraryview.LibraryViewAdapter;
import com.example.projectfnackiller.phisicallibrary.LibrariesViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class BookActivity extends AppCompatActivity implements LocationListener {

    private String bookTitle;
    private RecyclerView recyclerView;
    private static LibrariesViewAdapter mAdapter;
    protected LocationManager locationManager;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private double lat;
    private double longitude;
    ArrayList<Library> allLibraries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Bundle b = getIntent().getExtras();
        this.bookTitle = b.getString("bookTitle");
        Book book = (Book)getIntent().getSerializableExtra("BOOK");



        checkLocationPermission();
        //ShowMap("geo:37.7749,-122.4194");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);


        TextView t = findViewById(R.id.textTitle);
        TextView t2 = findViewById(R.id.textAuthor);
        TextView t3 = findViewById(R.id.textRealease);
        TextView t4 = findViewById(R.id.textLanguage);
        TextView t5 = findViewById(R.id.textCategory);

        String s = "Auteur : ";
        if (book.getAuthors().isEmpty()) {
            s+= "N/A\n";
        } else {
            for(int i=0;i<book.getAuthors().size();i++){
                s+=book.getAuthors().get(i) + "\n";
            }
        }
        t2.setText(s);

        t3.setText("Date de sortie : "+book.getDate());
        t4.setText("Langue : " + book.getLanguage());

        String s2 = "Genre : ";
        for(int i=0;i<book.getCategories().size();i++){
            s2+=book.getCategories().get(i) + "\n";
        }
        t5.setText(s2);

        t.setText(book.getTitle());





        ArrayList<Library> res = new ArrayList<>();
        LibrariesList lis = LibrariesList.getInstance();
        this.allLibraries = lis.getLibraries();


        for (Library library: book.getLibraries().keySet()) {
            Log.d("BOOFQ",library.getName() + " | ");
            res.add(library);
        }


        this.allLibraries = res;



        this.recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



        //this.mAdapter = new LibrariesViewAdapter(getApplicationContext(),book);

        mAdapter = new LibrariesViewAdapter(getApplicationContext(),allLibraries,book);
        recyclerView.setAdapter(mAdapter);
        //recyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("LOC","Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        this.lat = location.getLatitude();
        this.longitude = location.getLongitude();
        if(Objects.nonNull(mAdapter) && Objects.nonNull(location.getLatitude()) && Objects.nonNull(location.getLongitude())){

            this.allLibraries.sort(new Comparator<Library>() {
                @Override
                public int compare(Library o1, Library o2) {
                    return Double.compare(calculateDistance(o1.getLongitude(),longitude, o1.getLatitude(), lat), calculateDistance(o2.getLongitude(),longitude, o2.getLatitude(), lat));
                }
            });

            mAdapter.changeLocation(location.getLatitude(),location.getLongitude());

            refreshRecyclerView(allLibraries);




        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(BookActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
                    }

                } else {

                }
                return;
            }

        }
    }




    public void ShowMap(String loca){
        Uri gmmIntentUri = Uri.parse(loca);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private static void refreshRecyclerView(ArrayList<Library> library){
        mAdapter.changeLibrary(library);
        mAdapter.notifyDataSetChanged();
    }


    public double calculateDistance(double lon1,double lon2,double lat1,double lat2){


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
        return weight;
    }





}