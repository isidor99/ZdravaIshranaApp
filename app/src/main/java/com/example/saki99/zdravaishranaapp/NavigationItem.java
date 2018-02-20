package com.example.saki99.zdravaishranaapp;

import android.graphics.Bitmap;

/**
 * Created by PC on 2/18/2018.
 */

public class NavigationItem {

    private String naziv;
    private Bitmap slika;

    public NavigationItem () {}

    public NavigationItem(String naziv, Bitmap slika) {
        this.naziv = naziv;
        this.slika = slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Bitmap getSlika() {
        return slika;
    }

    public void setSlika(Bitmap slika) {
        this.slika = slika;
    }
}
