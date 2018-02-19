package com.example.saki99.zdravaishranaapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by Saki99 on 9.2.2018..
 */

public class ReceptiFragment extends Fragment {

    ArrayList<Recept> recepti = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recepti, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recepti_recycler);

        Bitmap bit1 = BitmapFactory.decodeResource(getResources(), R.drawable.sunka);
        Bitmap bit2 = BitmapFactory.decodeResource(getResources(), R.drawable.jagoda);
        Bitmap bit3 = BitmapFactory.decodeResource(getResources(), R.drawable.banana);
        Bitmap bit4 = BitmapFactory.decodeResource(getResources(), R.drawable.riba);
        Bitmap bit5 = BitmapFactory.decodeResource(getResources(), R.drawable.musaka);

        recepti.add(new Recept(
                "Sunka",
                "Socna, ukusna i mnogo mesnata!",
                "123",
                "879",
                "20",
                bit1,
                true));

        recepti.add(new Recept(
                "Soothie od jagode",
                "Jako zdravo i osvjezavajuce pice",
                "88",
                "0",
                "133",
                bit2,
                false
        ));

        recepti.add(new Recept(
                "Smoothie od banane",
                "Osvjezavajuce bice od banane za sve uzraste",
                "150",
                "11",
                "167",
                bit3,
                true
        ));

        recepti.add(new Recept(
                "Riblji fileti",
                "Zdrava riba bogata sa omega 3",
                "788",
                "240",
                "19",
                bit4,
                false
        ));

        recepti.add(new Recept(
                "Musaka",
                "Mocna rucak sa krompirom i mesom",
                "470",
                "1200",
                "99",
                bit5,
                false
        ));

        try {

            RecyclerViewReceptiAdapter adapter = new RecyclerViewReceptiAdapter(recepti);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
