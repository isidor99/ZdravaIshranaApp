package com.example.saki99.zdravaishranaapp.Activityes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;

import com.example.saki99.zdravaishranaapp.Constants;
import com.example.saki99.zdravaishranaapp.Fragments.ArticlesFragment;
import com.example.saki99.zdravaishranaapp.Fragments.FavoritiFragment;
import com.example.saki99.zdravaishranaapp.Fragments.ShopingFragment;
import com.example.saki99.zdravaishranaapp.R;

public class FavSopClanciActivity extends AppCompatActivity {

    public static final String TIP = "tip";
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_sop_clanci);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        fragmentManager = getSupportFragmentManager();

        int tip = -1;
        try {

            tip = getIntent().getExtras().getInt(TIP);

        } catch (NullPointerException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (tip == Constants.FAVORITES) {

            fragmentManager.beginTransaction().add(R.id.okvir, new FavoritiFragment()).commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.label_favorites));

        } else if (tip == Constants.SOPING) {

            fragmentManager.beginTransaction().add(R.id.okvir, new ShopingFragment()).commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.label_shoping));

        } else if (tip == Constants.ARTIKLI) {

            fragmentManager.beginTransaction().add(R.id.okvir, new ArticlesFragment()).commit();
            getSupportActionBar().setTitle(getResources().getString(R.string.label_articles));

        }
    }
}
