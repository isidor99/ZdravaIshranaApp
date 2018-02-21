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
import com.example.saki99.zdravaishranaapp.POJO.Proizvod;

import com.example.saki99.zdravaishranaapp.Adapters.ProizvodiRecyclerViewAdapter;
import com.example.saki99.zdravaishranaapp.R;

import java.util.ArrayList;

/**
 * Created by Saki99 on 9.2.2018..
 */

public class ProizvodiFragment extends Fragment {

    RecyclerView listaProizvodi;
    ArrayList<Proizvod> proizvodi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_proizvodi, container, false);

        listaProizvodi = view.findViewById(R.id.lista_proizvodi);

        proizvodi = new ArrayList<>();

        proizvodi.add(new Proizvod("Mladi koziji sir", BitmapFactory.decodeResource(getResources(),R.drawable.koziji_sir)));
        proizvodi.add(new Proizvod("Polutvrdi koziji sir \n \"Suho voce\"", BitmapFactory.decodeResource(getResources(),R.drawable.koziji_sir)));
        proizvodi.add(new Proizvod("Mladi koziji sir \n \"Biber\"", BitmapFactory.decodeResource(getResources(),R.drawable.koziji_sir)));
        proizvodi.add(new Proizvod("Mladi koziji sir \n \"Cili\"", BitmapFactory.decodeResource(getResources(),R.drawable.koziji_sir)));
        proizvodi.add(new Proizvod("Sok kruska 100% \nVrhovac", BitmapFactory.decodeResource(getResources(),R.drawable.kruska_sok)));
        proizvodi.add(new Proizvod("Polutvrdi koziji sir", BitmapFactory.decodeResource(getResources(),R.drawable.koziji_sir)));
        proizvodi.add(new Proizvod("Med bagrem \nGagic", BitmapFactory.decodeResource(getResources(),R.drawable.kruska_sok)));
        proizvodi.add(new Proizvod("Tjestenina od jaja", BitmapFactory.decodeResource(getResources(),R.drawable.tjestenina_jaja)));
        proizvodi.add(new Proizvod("Tjestenina od kopriva", BitmapFactory.decodeResource(getResources(),R.drawable.tjestenina_kopriva)));
        proizvodi.add(new Proizvod("Tjestenina sa gljivama", BitmapFactory.decodeResource(getResources(),R.drawable.tjestenina_gljive)));
        proizvodi.add(new Proizvod("Imunitet preparat \nGagic", BitmapFactory.decodeResource(getResources(),R.drawable.imunitet_gagic)));
        proizvodi.add(new Proizvod("Med livadski \nGagic", BitmapFactory.decodeResource(getResources(),R.drawable.livada_gagic)));
        proizvodi.add(new Proizvod("Med sumski \nGagic", BitmapFactory.decodeResource(getResources(),R.drawable.sumski_med_gagic)));
        proizvodi.add(new Proizvod("Med za prostatu \nGagic", BitmapFactory.decodeResource(getResources(),R.drawable.med_za_prostatu)));
        proizvodi.add(new Proizvod("Med sa suhim vocem \nGagic", BitmapFactory.decodeResource(getResources(),R.drawable.med_sa_suvim_vocem)));
        proizvodi.add(new Proizvod("Bronhi med \nGagic", BitmapFactory.decodeResource(getResources(),R.drawable.bronhi)));


        try {
            ProizvodiRecyclerViewAdapter adapter = new ProizvodiRecyclerViewAdapter(proizvodi);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            listaProizvodi.setLayoutManager(layoutManager);
            listaProizvodi.setAdapter(adapter);
            listaProizvodi.setItemAnimator(new DefaultItemAnimator());

            adapter.notifyDataSetChanged();
        } catch (Exception e){

            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        proizvodi = null;
        listaProizvodi = null;
        Runtime.getRuntime().gc();
    }
}
