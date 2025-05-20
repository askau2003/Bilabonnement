package com.bilabbonement.bilabonnement.Model;

public class UdlejningPris {

    private String valuta;
    private int udlejningPris;

    public UdlejningPris() {
    }

    public UdlejningPris(String valuta, int udlejningPris) {
        this.valuta = valuta;
        this.udlejningPris = udlejningPris;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public int getUdlejningPris() {
        return udlejningPris;
    }

    public void setUdlejningPris(int udlejningPris) {
        this.udlejningPris = udlejningPris;
    }
}
