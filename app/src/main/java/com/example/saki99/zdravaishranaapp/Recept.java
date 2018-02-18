package com.example.saki99.zdravaishranaapp;

import android.graphics.Bitmap;

/**
 * Created by Saki99 on 11.2.2018..
 */

public class Recept {
    private String naziv;
    private String opis;
    private String proteini;
    private String masti;
    private String ugljenHidrati;
    private Bitmap slika;
    private boolean omiljeni;

    public Recept() {}

    public Recept(String naziv, String opis, String proteini, String masti, String ugljenHidrati, Bitmap slika,boolean omiljeni) {
        this.naziv = naziv;
        this.opis = opis;
        this.proteini = proteini;
        this.masti = masti;
        this.ugljenHidrati = ugljenHidrati;
        this.slika = slika;
        this.omiljeni = omiljeni;
    }

    public Recept(String naziv, String opis, String proteini, String masti, String ugljenHidrati) {
        this.naziv = naziv;
        this.opis = opis;
        this.proteini = proteini;
        this.masti = masti;
        this.ugljenHidrati = ugljenHidrati;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getProteini() {
        return proteini;
    }

    public void setProteini(String proteini) {
        this.proteini = proteini;
    }

    public String getMasti() {
        return masti;
    }

    public void setMasti(String masti) {
        this.masti = masti;
    }

    public String getUgljenHidrati() {
        return ugljenHidrati;
    }

    public void setUgljenHidrati(String ugljenHidrati) {
        this.ugljenHidrati = ugljenHidrati;
    }

    public Bitmap getSlika() {
        return slika;
    }

    public void setSlika(Bitmap slika) {
        this.slika = slika;
    }

    public boolean isOmiljeni() {
        return omiljeni;
    }

    public void setOmiljeni(boolean omiljeni) {
        this.omiljeni = omiljeni;
    }
}
