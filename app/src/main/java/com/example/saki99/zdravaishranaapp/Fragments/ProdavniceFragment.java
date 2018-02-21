package com.example.saki99.zdravaishranaapp.Fragments;

import android.graphics.BitmapFactory;
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

import com.example.saki99.zdravaishranaapp.POJO.Prodavnica;
import com.example.saki99.zdravaishranaapp.Adapters.ProdavniceRecyclerViewAdapter;
import com.example.saki99.zdravaishranaapp.R;

import java.util.ArrayList;

/**
 * Created by Saki99 on 9.2.2018..
 */

public class ProdavniceFragment extends Fragment {

    RecyclerView prodavniceLista;
    ArrayList<Prodavnica> prodavnice;

    @Override
    public void onStart() {
        super.onStart();
        prodavnice = new ArrayList<>();

        prodavnice.add(new Prodavnica("Bio Shop Jana","Veljka Bulajica 13b", BitmapFactory.decodeResource(getResources(), R.drawable.jana_bio_shop)));

        try {

            ProdavniceRecyclerViewAdapter adapter = new ProdavniceRecyclerViewAdapter(prodavnice);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            prodavniceLista.setItemAnimator(new DefaultItemAnimator());
            prodavniceLista.setAdapter(adapter);
            prodavniceLista.setLayoutManager(layoutManager);

            adapter.notifyDataSetChanged();
        } catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_prodavnice, container, false);

        prodavniceLista = view.findViewById(R.id.lista_prodavnice);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        prodavniceLista = null;
        prodavnice = null;
        Runtime.getRuntime().gc();
    }
}
