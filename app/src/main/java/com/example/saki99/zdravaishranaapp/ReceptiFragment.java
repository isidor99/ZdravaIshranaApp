package com.example.saki99.zdravaishranaapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Saki99 on 9.2.2018..
 */

public class ReceptiFragment extends Fragment {

    ArrayList<Recept> recepti = new ArrayList<>();
    FloatingActionButton fab;
    DBHelper dbHelper;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recepti, container, false);
        recyclerView = view.findViewById(R.id.recepti_recycler);
        fab = view.findViewById(R.id.fab);

        dbHelper = new DBHelper(getContext());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        showAll();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showAll() {

        recepti = (ArrayList<Recept>) dbHelper.getAllRecepti();

        try {

            RecyclerViewReceptiAdapter adapter = new RecyclerViewReceptiAdapter(recepti, getContext());

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
