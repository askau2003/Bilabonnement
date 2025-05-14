package com.bilabbonement.bilabonnement.Model;

public class Bynavn {

    private int postnummer;
    private String bynavn;

    public Bynavn() {
    }

    public Bynavn(int postnummer, String bynavn) {
        this.postnummer = postnummer;
        this.bynavn = bynavn;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }

    public String getBynavn() {
        return bynavn;
    }

    public void setBynavn(String bynavn) {
        this.bynavn = bynavn;
    }
}
