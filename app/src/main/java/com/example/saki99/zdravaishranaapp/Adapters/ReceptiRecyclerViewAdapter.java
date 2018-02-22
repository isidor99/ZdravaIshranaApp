package com.example.saki99.zdravaishranaapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saki99.zdravaishranaapp.Activityes.PregledActivity;
import com.example.saki99.zdravaishranaapp.Constants;
import com.example.saki99.zdravaishranaapp.DBHelper;
import com.example.saki99.zdravaishranaapp.R;
import com.example.saki99.zdravaishranaapp.POJO.Recept;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_MASTI;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_NASLOV;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_PROTEINI;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_SLIKA_ADRESA;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_SLIKA_IME;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_TEKST;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_TIP;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_UGLJENI;

/**
 * Created by Saki99 on 11.2.2018..
 */

public class ReceptiRecyclerViewAdapter extends RecyclerView.Adapter<ReceptiRecyclerViewAdapter.ReceptiViewHolder> {

    private ArrayList<Recept> recepti;
    private DBHelper dbHelper;
    private int kod;
    private Context context;
   // AdapterView.OnItemClickListener onItemClickListener;

    public ReceptiRecyclerViewAdapter(ArrayList<Recept> recepti, Context context) {

        this.recepti = recepti;
        this.dbHelper = new DBHelper(context);
        this.context = context;
        this.kod = -1; // ako se adapter formira u ReceptiFragment onda je ovaj kod nebitan
    }

    public ReceptiRecyclerViewAdapter(ArrayList<Recept> recepti, Context context, int kod) {

        this.recepti = recepti;
        this.dbHelper = new DBHelper(context);
        this.context = context;
        this.kod = kod;
    }

    public class ReceptiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private HashMap <String, TextView> textViewHashMap = new HashMap<>();
        private ImageView slika;
        private LikeButton omiljeni;
        private Context contextViewHolder;

        public  ReceptiViewHolder(View view) {
            super(view);

            contextViewHolder=view.getContext();
            textViewHashMap.put(Constants.RECEPT_NASLOV, (TextView) view.findViewById(R.id.card_naslov));
            textViewHashMap.put(Constants.RECEPT_OPIS, (TextView) view.findViewById(R.id.card_tekst));
            textViewHashMap.put(Constants.RECEPT_BROJ_PROTEINA, (TextView) view.findViewById(R.id.card_view_proteini));
            textViewHashMap.put(Constants.RECEPT_MASTI, (TextView) view.findViewById(R.id.card_view_masti));
            textViewHashMap.put(Constants.RECEPT_UGLJENI_HIDRATI, (TextView) view.findViewById(R.id.card_view_ugljeni_hidrati));

            slika = view.findViewById(R.id.card_slika);
            omiljeni = view.findViewById(R.id.card_favourite);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(contextViewHolder, PregledActivity.class);
            intent.putExtra(INTENT_TIP,Constants.FRAGMENT_PREGLED_RECEPT);
            intent.putExtra(INTENT_NASLOV,recepti.get(getAdapterPosition()).getNaziv());
            intent.putExtra(INTENT_SLIKA_ADRESA,recepti.get(getAdapterPosition()).getAdresaSlika());
            intent.putExtra(INTENT_SLIKA_IME,recepti.get(getAdapterPosition()).getImeSlike());
            intent.putExtra(INTENT_PROTEINI,recepti.get(getAdapterPosition()).getProteini());
            intent.putExtra(INTENT_MASTI,recepti.get(getAdapterPosition()).getMasti());
            intent.putExtra(INTENT_UGLJENI,recepti.get(getAdapterPosition()).getUgljenHidrati());
            intent.putExtra(INTENT_TEKST,recepti.get(getAdapterPosition()).getOpis());

            contextViewHolder.startActivity(intent);
        }
    }

    @Override
    public void onBindViewHolder(ReceptiViewHolder holder, final int position) {

        Bitmap slika = loadImageFromStorage(recepti.get(position).getAdresaSlika(), recepti.get(position).getImeSlike());

        holder.textViewHashMap.get(Constants.RECEPT_NASLOV).setText(recepti.get(position).getNaziv());
        holder.textViewHashMap.get(Constants.RECEPT_OPIS).setText(recepti.get(position).getOpis());
        holder.textViewHashMap.get(Constants.RECEPT_BROJ_PROTEINA).setText(recepti.get(position).getProteini());
        holder.textViewHashMap.get(Constants.RECEPT_MASTI).setText(recepti.get(position).getMasti());
        holder.textViewHashMap.get(Constants.RECEPT_UGLJENI_HIDRATI).setText(recepti.get(position).getUgljenHidrati());
        holder.slika.setBackground(new BitmapDrawable(slika));

        if (recepti.get(position).isOmiljeni())
            holder.omiljeni.setLiked(true);
        else
            holder.omiljeni.setLiked(false);

        holder.omiljeni.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                dbHelper.addOmiljeni(recepti.get(position).getId());
                if (kod == -1) {
                    Toast.makeText(context, "Dodato u omiljene recepte!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                dbHelper.removeOmiljeni(recepti.get(position).getId());

                // ako se nalazimo na omiljenim receptima i odcekiramo neki recept on treba da se ukloni
                if (kod == Constants.FRAGMENT_FAVORITES) {
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

    private Bitmap loadImageFromStorage(String path, String name) {

        Bitmap b = null;

        try {
            File f = new File(path, name + ".jpg");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return b;
    }

}
