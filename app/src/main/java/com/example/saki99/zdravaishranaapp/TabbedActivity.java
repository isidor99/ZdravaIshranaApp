package com.example.saki99.zdravaishranaapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class TabbedActivity extends AppCompatActivity {

    ViewPager viewPager;
    RecyclerView listView;
    private NavigationDrawerRecyclerViewAdapter nAdapter;
    private ArrayList<NavigationItem> nItems;

    private static final int MY_PERMISSION_REQUEST_LOCATION = 1;

    LocationManager locationManager;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        viewPager = findViewById(R.id.container);
        listView = findViewById(R.id.navList);

        nItems = new ArrayList<>();

        dbHelper = new DBHelper(TabbedActivity.this);
        writeToLocalDatabase();

        SimpleFragmentPagerAdapter pagerAdapter = new SimpleFragmentPagerAdapter(TabbedActivity.this, getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        addDrawerItems();



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

    private void addDrawerItems() {

        nItems.add(new NavigationItem("Favourites", BitmapFactory.decodeResource(getResources(), R.drawable.heart_outline)));
        nItems.add(new NavigationItem("Shoping", BitmapFactory.decodeResource(getResources(), R.drawable.cart)));
        nItems.add(new NavigationItem("Articles", BitmapFactory.decodeResource(getResources(), R.drawable.library)));

        nAdapter = new NavigationDrawerRecyclerViewAdapter(nItems, TabbedActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        listView.setLayoutManager(layoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(nAdapter);
        nAdapter.notifyDataSetChanged();
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

    private void writeToLocalDatabase() {
        Bitmap bit1 = BitmapFactory.decodeResource(getResources(), R.drawable.sunka);
        Bitmap bit2 = BitmapFactory.decodeResource(getResources(), R.drawable.jagoda);
        Bitmap bit3 = BitmapFactory.decodeResource(getResources(), R.drawable.banana);
        Bitmap bit4 = BitmapFactory.decodeResource(getResources(), R.drawable.riba);
        Bitmap bit5 = BitmapFactory.decodeResource(getResources(), R.drawable.musaka);

        dbHelper.addRecept(new Recept(
                "Sunka",
                "Socna, ukusna i mnogo mesnata!",
                "123",
                "879",
                "20",
                bit1,
                true));

        dbHelper.addRecept(new Recept(
                "Soothie od jagode",
                "Jako zdravo i osvjezavajuce pice",
                "88",
                "0",
                "133",
                bit2,
                false
        ));

        dbHelper.addRecept(new Recept(
                "Smoothie od banane",
                "Osvjezavajuce bice od banane za sve uzraste",
                "150",
                "11",
                "167",
                bit3,
                true
        ));

        dbHelper.addRecept(new Recept(
                "Riblji fileti",
                "Zdrava riba bogata sa omega 3",
                "788",
                "240",
                "19",
                bit4,
                false
        ));

        dbHelper.addRecept(new Recept(
                "Musaka",
                "Mocna rucak sa krompirom i mesom",
                "470",
                "1200",
                "99",
                bit5,
                false
        ));
    }
}
