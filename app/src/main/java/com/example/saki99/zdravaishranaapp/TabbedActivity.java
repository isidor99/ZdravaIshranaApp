package com.example.saki99.zdravaishranaapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;


public class TabbedActivity extends AppCompatActivity {

    ViewPager viewPager;

    private static final int MY_PERMISSION_REQUEST_LOCATION = 1;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        viewPager = findViewById(R.id.container);

        SimpleFragmentPagerAdapter pagerAdapter = new SimpleFragmentPagerAdapter(TabbedActivity.this, getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(TabbedActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(TabbedActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                ActivityCompat.requestPermissions(TabbedActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);

            } else {

                ActivityCompat.requestPermissions(TabbedActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);

            }

        } else {

            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case MY_PERMISSION_REQUEST_LOCATION:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(TabbedActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    } else {

                        Toast.makeText(TabbedActivity.this, getResources().getString(R.string.location_toast), Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }

    private String getCity(double lat, double lon) {
        String city = "";

        Geocoder geocoder = new Geocoder(TabbedActivity.this, Locale.getDefault());
        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(lat, lon, 1);

            if (addresses.size() > 0) {
                city = addresses.get(0).getLocality();
            }

        } catch (Exception e) {
            Toast.makeText(TabbedActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return city;
    }
}
