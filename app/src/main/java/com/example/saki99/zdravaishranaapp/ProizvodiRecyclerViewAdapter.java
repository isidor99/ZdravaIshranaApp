package com.example.saki99.zdravaishranaapp;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2/19/2018.
 */

public class ProizvodiRecyclerViewAdapter extends RecyclerView.Adapter<ProizvodiRecyclerViewAdapter.MyViewHolder> {

    ArrayList<Proizvod> proizvodi;

    ProizvodiRecyclerViewAdapter(ArrayList<Proizvod> proizvodi){ this.proizvodi=proizvodi;}

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView slika;
        TextView naziv;

        MyViewHolder(View view){
            super(view);

            slika=view.findViewById(R.id.image_view_proizvodi);
            naziv=view.findViewById(R.id.text_view_proizvodi);

        }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.naziv.setText(proizvodi.get(position).getNaziv().toString());
        holder.slika.setBackground(new BitmapDrawable(proizvodi.get(position).getSlika()));

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_proizvodi,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return proizvodi.size();
    }
}
