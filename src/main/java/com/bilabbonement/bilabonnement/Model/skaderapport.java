package com.bilabbonement.bilabonnement.Model;

public class skaderapport {

    private int rapport_id;
    private String beskrivelse;
    private int pris;
    private int vognnummer;

    public skaderapport() {
    }

    public skaderapport(int rapport_id, String beskrivelse, int pris, int vognnummer) {
        this.rapport_id = rapport_id;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.vognnummer = vognnummer;
    }

    public int getRapport_id() {
        return rapport_id;
    }

    public void setRapport_id(int rapport_id) {
        this.rapport_id = rapport_id;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public int getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(int vognnummer) {
        this.vognnummer = vognnummer;
    }
}
