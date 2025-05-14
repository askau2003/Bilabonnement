package com.bilabbonement.bilabonnement.Model;

public class Adresse {

    private int adresse_id;
    private String vej_navn;
    private int vej_nummer;
    private int postnummer;

    public Adresse() {
    }

    public Adresse(int adresse_id, String vej_navn, int vej_nummer, int postnummer) {
        this.adresse_id = adresse_id;
        this.vej_navn = vej_navn;
        this.vej_nummer = vej_nummer;
        this.postnummer = postnummer;
    }

    public int getAdresse_id() {
        return adresse_id;
    }

    public void setAdresse_id(int adresse_id) {
        this.adresse_id = adresse_id;
    }

    public String getVej_navn() {
        return vej_navn;
    }

    public void setVej_navn(String vej_navn) {
        this.vej_navn = vej_navn;
    }

    public int getVej_nummer() {
        return vej_nummer;
    }

    public void setVej_nummer(int vej_nummer) {
        this.vej_nummer = vej_nummer;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }
}
