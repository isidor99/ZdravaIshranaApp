package com.example.saki99.zdravaishranaapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by PC on 2/18/2018.
 */

public class NavigationDrawerRecyclerViewAdapter extends RecyclerView.Adapter<NavigationDrawerRecyclerViewAdapter.MyViewHolder>{

    private final ArrayList<NavigationItem> opcije;
    private Context context;

    NavigationDrawerRecyclerViewAdapter(ArrayList<NavigationItem> opcije, Context context){
        this.opcije = opcije;
        this.context = context; }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView naziv;
        private ImageView slicica;

        public MyViewHolder(View view) {
            super(view);
            naziv = view.findViewById(R.id.navigation_drawer_tekst);
            slicica = view.findViewById(R.id.navigation_drawer_slicica);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.naziv.setText(opcije.get(position).getNaziv());
        holder.slicica.setBackground(new BitmapDrawable(opcije.get(position).getSlika()));

        //klik na jedan view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, FavSopClanciActivity.class);
                intent.putExtra(FavSopClanciActivity.TIP, position);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_drawer_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return opcije.size();
    }
}
