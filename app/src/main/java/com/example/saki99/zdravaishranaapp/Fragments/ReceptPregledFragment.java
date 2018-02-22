package com.example.saki99.zdravaishranaapp.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.TextKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saki99.zdravaishranaapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_MASTI;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_NASLOV;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_PROTEINI;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_SLIKA_ADRESA;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_SLIKA_IME;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_TEKST;
import static com.example.saki99.zdravaishranaapp.Activityes.PregledActivity.INTENT_UGLJENI;

public class ReceptPregledFragment extends Fragment {

    ImageView slika;
    TextView proteini,ugljeni,masti,naslov,tekst;
    Bundle podaci;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        podaci = getActivity().getIntent().getExtras();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_recept_pregled, container, false);

        slika = view.findViewById(R.id.slika_pregled_recept);
        proteini = view.findViewById(R.id.proteini_recept_pregled);
        ugljeni = view.findViewById(R.id.ugljeni_recept_pregled);
        masti = view.findViewById(R.id.masti_recept_pregled);
        naslov = view.findViewById(R.id.recepti_pregled_naslov);
        tekst = view.findViewById(R.id.recepti_pregled_tekst);

        Bitmap pic = loadImageFromStorage(podaci.getString(INTENT_SLIKA_ADRESA), podaci.getString(INTENT_SLIKA_IME));

        slika.setBackground(new BitmapDrawable(pic));
        proteini.setText(podaci.getString(INTENT_PROTEINI));
        ugljeni.setText(podaci.getString(INTENT_UGLJENI));
        masti.setText(podaci.getString(INTENT_MASTI));
        naslov.setText(podaci.getString(INTENT_NASLOV));
        tekst.setText(podaci.getString(INTENT_TEKST));

        return view;
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
