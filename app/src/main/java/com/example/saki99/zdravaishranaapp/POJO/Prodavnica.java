package com.example.saki99.zdravaishranaapp.POJO;

import android.graphics.Bitmap;

/**
 * Created by PC on 2/19/2018.
 */

public class Prodavnica {

    private String ime;
    private String adresa;
    private Bitmap ikonica;

    public Prodavnica(String ime, String adresa, Bitmap ikonica) {
        this.ime = ime;
        this.adresa = adresa;
        this.ikonica = ikonica;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Bitmap getIkonica() {
        return ikonica;
    }

    public void setIkonica(Bitmap ikonica) {
        this.ikonica = ikonica;
    }
}
