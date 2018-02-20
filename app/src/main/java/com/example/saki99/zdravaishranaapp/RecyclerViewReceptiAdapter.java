package com.example.saki99.zdravaishranaapp;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Saki99 on 11.2.2018..
 */

public class RecyclerViewReceptiAdapter extends RecyclerView.Adapter<RecyclerViewReceptiAdapter.ReceptiViewHolder> {

    private ArrayList<Recept> recepti;

    public RecyclerViewReceptiAdapter(ArrayList<Recept> recepti) {
        this.recepti = recepti;
    }

    public class ReceptiViewHolder extends RecyclerView.ViewHolder {

        private HashMap <String, TextView> textViewHashMap = new HashMap<>();
        private ImageView slika;
        private LikeButton omiljeni;

        public  ReceptiViewHolder(View view) {
            super(view);

            textViewHashMap.put(Constants.RECEPT_NASLOV, (TextView) view.findViewById(R.id.card_naslov));
            textViewHashMap.put(Constants.RECEPT_OPIS, (TextView) view.findViewById(R.id.card_tekst));
            textViewHashMap.put(Constants.RECEPT_BROJ_PROTEINA, (TextView) view.findViewById(R.id.card_view_proteini));
            textViewHashMap.put(Constants.RECEPT_MASTI, (TextView) view.findViewById(R.id.card_view_masti));
            textViewHashMap.put(Constants.RECEPT_UGLJENI_HIDRATI, (TextView) view.findViewById(R.id.card_view_ugljeni_hidrati));

            slika = view.findViewById(R.id.card_slika);
            omiljeni = view.findViewById(R.id.card_favourite);
        }

    }

    @Override
    public void onBindViewHolder(ReceptiViewHolder holder, int position) {

        holder.textViewHashMap.get(Constants.RECEPT_NASLOV).setText(recepti.get(position).getNaziv());
        holder.textViewHashMap.get(Constants.RECEPT_OPIS).setText(recepti.get(position).getOpis());
        holder.textViewHashMap.get(Constants.RECEPT_BROJ_PROTEINA).setText(recepti.get(position).getProteini());
        holder.textViewHashMap.get(Constants.RECEPT_MASTI).setText(recepti.get(position).getMasti());
        holder.textViewHashMap.get(Constants.RECEPT_UGLJENI_HIDRATI).setText(recepti.get(position).getUgljenHidrati());
        holder.slika.setBackground(new BitmapDrawable(recepti.get(position).getSlika()));

        //ovde stao
        if (recepti.get(position).isOmiljeni())
            holder.omiljeni.setLiked(true);
        else
            holder.omiljeni.setLiked(false);

        holder.omiljeni.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {

            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });
    }

    @Override
    public ReceptiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_recepti, parent,false);

        return new ReceptiViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return recepti.size();
    }
}
