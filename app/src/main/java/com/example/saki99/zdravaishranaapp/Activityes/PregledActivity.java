package com.example.saki99.zdravaishranaapp.Activityes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.saki99.zdravaishranaapp.Constants;
import com.example.saki99.zdravaishranaapp.Fragments.ClanakPregledFragment;
import com.example.saki99.zdravaishranaapp.Fragments.ProdavnicaPregledFragment;
import com.example.saki99.zdravaishranaapp.Fragments.ReceptPregledFragment;
import com.example.saki99.zdravaishranaapp.R;

public class PregledActivity extends AppCompatActivity {

    public static final String INTENT_TIP = "tip";
    public static final String INTENT_NASLOV = "naslov";
    public static final String INTENT_SLIKA_IME = "slika_ime";
    public static final String INTENT_SLIKA_ADRESA = "slika_adresa";
    public static final String INTENT_PROTEINI = "proteini";
    public static final String INTENT_MASTI = "masti";
    public static final String INTENT_UGLJENI = "ugljeni";
    public static final String INTENT_TEKST = "tekst";
    int tip;
    String naslov;
    android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled);

        Toolbar toolbar = findViewById(R.id.toolbar_pregled);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tip = getIntent().getExtras().getInt(INTENT_TIP);;
        fragmentManager = getSupportFragmentManager();
        naslov = getResources().getString(R.string.pregled_activity_naslov);

        if(tip == Constants.FRAGMENT_PREGLED_RECEPT){
            fragmentManager.beginTransaction().add(R.id.okvir_pregled, new ReceptPregledFragment()).commit();
            getSupportActionBar().setTitle(naslov);
        }
        else if(tip == Constants.FRAGMENT_PREGLED_PRODAVNICA){
            fragmentManager.beginTransaction().add(R.id.okvir_pregled, new ProdavnicaPregledFragment()).commit();
            getSupportActionBar().setTitle(naslov);
        }
        else if(tip == Constants.FRAGMENT_PREGLED_CLANAK){
            fragmentManager.beginTransaction().add(R.id.okvir_pregled, new ClanakPregledFragment()).commit();
            getSupportActionBar().setTitle(naslov);
        }
    }
}
