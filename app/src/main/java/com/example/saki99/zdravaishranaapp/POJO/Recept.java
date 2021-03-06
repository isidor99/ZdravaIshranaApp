package com.example.saki99.zdravaishranaapp.POJO;

/**
 * Created by Saki99 on 11.2.2018..
 */

public class Recept {
    private int id;
    private String naziv;
    private String opis;
    private String proteini;
    private String masti;
    private String ugljenHidrati;
    private String adresaSlike;
    private String imeSlike;
    private boolean omiljeni;

    public Recept() {}

    public Recept(String naziv, String opis, String proteini, String masti, String ugljenHidrati, String imeSlike, String adresaSlike, boolean omiljeni) {
        this.naziv = naziv;
        this.opis = opis;
        this.proteini = proteini;
        this.masti = masti;
        this.ugljenHidrati = ugljenHidrati;
        this.imeSlike = imeSlike;
        this.adresaSlike = adresaSlike;
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


    public void setImeSlike(String imeSlike) { this.imeSlike = imeSlike; }
    public String getImeSlike() { return this.imeSlike; }


    public String getAdresaSlika() {
        return adresaSlike;
    }
    public void setAdresaSlika(String adresaSlike) {
        this.adresaSlike = adresaSlike;
    }


    public boolean isOmiljeni() {
        return omiljeni;
    }
    public void setOmiljeni(boolean omiljeni) {
        this.omiljeni = omiljeni;
    }


    public void setId(int id) {
        this.id = id;
    }
    public int getId() { return this.id; }
}
