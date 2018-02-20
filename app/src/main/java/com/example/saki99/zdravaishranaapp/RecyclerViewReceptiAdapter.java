package com.example.saki99.zdravaishranaapp;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Saki99 on 11.2.2018..
 */

public class RecyclerViewReceptiAdapter extends RecyclerView.Adapter<RecyclerViewReceptiAdapter.ReceptiViewHolder> {

    private ArrayList<Recept> recepti;
    private DBHelper dbHelper;
    private int kod;
    private Context context;

    public RecyclerViewReceptiAdapter(ArrayList<Recept> recepti, Context context) {

        this.recepti = recepti;
        this.dbHelper = new DBHelper(context);
        this.context = context;
        this.kod = -1; // ako se adapter formira u ReceptiFragment onda je ovaj kod nebitan
    }

    public RecyclerViewReceptiAdapter(ArrayList<Recept> recepti, Context context, int kod) {

        this.recepti = recepti;
        this.dbHelper = new DBHelper(context);
        this.context = context;
        this.kod = kod;
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
    public void onBindViewHolder(ReceptiViewHolder holder, final int position) {

        holder.textViewHashMap.get(Constants.RECEPT_NASLOV).setText(recepti.get(position).getNaziv());
        holder.textViewHashMap.get(Constants.RECEPT_OPIS).setText(recepti.get(position).getOpis());
        holder.textViewHashMap.get(Constants.RECEPT_BROJ_PROTEINA).setText(recepti.get(position).getProteini());
        holder.textViewHashMap.get(Constants.RECEPT_MASTI).setText(recepti.get(position).getMasti());
        holder.textViewHashMap.get(Constants.RECEPT_UGLJENI_HIDRATI).setText(recepti.get(position).getUgljenHidrati());
        holder.slika.setBackground(new BitmapDrawable(recepti.get(position).getSlika()));

        if (recepti.get(position).isOmiljeni())
            holder.omiljeni.setLiked(true);
        else
            holder.omiljeni.setLiked(false);

        holder.omiljeni.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                dbHelper.addOmiljnei(recepti.get(position).getId());
                if (kod == -1) {
                    Toast.makeText(context, "Dodato u omiljene recepte!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                dbHelper.removeOmiljeni(recepti.get(position).getId());

                // ako se nalazimo na omiljenim receptima i odcekiramo neki recept on treba da se ukloni
                if (kod == Constants.FAVORITES) {
                    recepti.remove(position);
                    notifyItemRemoved(position);
                }

                Toast.makeText(context, "Uklonjeno iz omiljenih recepata!", Toast.LENGTH_LONG).show();
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
