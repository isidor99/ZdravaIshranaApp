package com.example.saki99.zdravaishranaapp.Adapters;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saki99.zdravaishranaapp.POJO.Prodavnica;
import com.example.saki99.zdravaishranaapp.R;

import java.util.ArrayList;

/**
 * Created by PC on 2/19/2018.
 */

public class ProdavniceRecyclerViewAdapter extends RecyclerView.Adapter<ProdavniceRecyclerViewAdapter.MyViewHolder> {

    ArrayList<Prodavnica> prodavnice;

    public ProdavniceRecyclerViewAdapter(ArrayList<Prodavnica> prodavnice){this.prodavnice=prodavnice;}

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView slika;
        TextView ime,adresa;

        MyViewHolder(View view){
            super(view);

            slika=view.findViewById(R.id.image_view_prodavnice);
            ime=view.findViewById(R.id.text_view_prodavnice);
            adresa=view.findViewById(R.id.text_view_prodavnice_adresa);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_prodavnice,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.ime.setText(prodavnice.get(position).getIme());
            holder.adresa.setText(prodavnice.get(position).getAdresa());
            holder.slika.setBackground(new BitmapDrawable(prodavnice.get(position).getIkonica()));
    }

    @Override
    public int getItemCount() {
        return prodavnice.size();
    }
}
