package com.bilabbonement.bilabonnement.Model;

public class forhaandsaftale {

    private int aftale_id;
    private int pris;
    private boolean betalt;
    private int afhentnings_adresse;
    private int lejer_id;
    private int vognnummer;

    public forhaandsaftale() {
    }

    public forhaandsaftale(int aftale_id, int pris, boolean betalt, int afhentnings_adresse, int lejer_id, int vognnummer) {
        this.aftale_id = aftale_id;
        this.pris = pris;
        this.betalt = betalt;
        this.afhentnings_adresse = afhentnings_adresse;
        this.lejer_id = lejer_id;
        this.vognnummer = vognnummer;
    }

    public int getAftale_id() {
        return aftale_id;
    }

    public void setAftale_id(int aftale_id) {
        this.aftale_id = aftale_id;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public boolean isBetalt() {
        return betalt;
    }

    public void setBetalt(boolean betalt) {
        this.betalt = betalt;
    }

    public int getAfhentnings_adresse() {
        return afhentnings_adresse;
    }

    public void setAfhentnings_adresse(int afhentnings_adresse) {
        this.afhentnings_adresse = afhentnings_adresse;
    }

    public int getLejer_id() {
        return lejer_id;
    }

    public void setLejer_id(int lejer_id) {
        this.lejer_id = lejer_id;
    }

    public int getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(int vognnummer) {
        this.vognnummer = vognnummer;
    }
}
