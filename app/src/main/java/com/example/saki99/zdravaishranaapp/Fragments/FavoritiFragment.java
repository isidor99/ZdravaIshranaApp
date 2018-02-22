package com.example.saki99.zdravaishranaapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.saki99.zdravaishranaapp.Constants;
import com.example.saki99.zdravaishranaapp.DBHelper;
import com.example.saki99.zdravaishranaapp.R;
import com.example.saki99.zdravaishranaapp.POJO.Recept;
import com.example.saki99.zdravaishranaapp.Adapters.ReceptiRecyclerViewAdapter;

import java.util.ArrayList;

public class FavoritiFragment extends Fragment {

    RecyclerView listaOmiljneih;
    ArrayList<Recept> recepti;
    DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoriti, container, false);

        dbHelper = new DBHelper(getContext());

        listaOmiljneih = view.findViewById(R.id.favoriti_recycler);

        showAll();

        return view;
    }

    private void showAll() {

        recepti = (ArrayList<Recept>) dbHelper.getOmiljeniRecepti();

        try {

            ReceptiRecyclerViewAdapter adapter = new ReceptiRecyclerViewAdapter(recepti, getContext(), Constants.FRAGMENT_FAVORITES);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            listaOmiljneih.setLayoutManager(layoutManager);
            listaOmiljneih.setItemAnimator(new DefaultItemAnimator());
            listaOmiljneih.setAdapter(adapter);

        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
