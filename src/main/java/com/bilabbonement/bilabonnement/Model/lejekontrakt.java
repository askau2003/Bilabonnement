package com.bilabbonement.bilabonnement.Model;

import java.time.LocalDate;

public class lejekontrakt {

    private int kontrakt_id;
    private LocalDate startdato;
    private LocalDate slutdato;
    private int pris;
    private int depositum;
    private String valuta; // EURO, DKK
    private LocalDate oprettelsesdato;
    private LocalDate betalingsdato;
    private int afhentnings_adresse;
    private int afleverings_adresse;
    private int vognnummer;
    private int rapport_id;
    private int lejer_id;

    public lejekontrakt() {
    }

    public lejekontrakt(int kontrakt_id, LocalDate startdato, LocalDate slutdato, int pris, int depositum, String valuta, int afhentnings_adresse, int afleverings_adresse, int vognnummer, int rapport_id, int lejer_id) {
        this.kontrakt_id = kontrakt_id;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.pris = pris;
        this.depositum = depositum;
        this.valuta = valuta;
        this.afhentnings_adresse = afhentnings_adresse;
        this.afleverings_adresse = afleverings_adresse;
        this.vognnummer = vognnummer;
        this.rapport_id = rapport_id;
        this.lejer_id = lejer_id;
    }

    public int getKontrakt_id() {
        return kontrakt_id;
    }

    public void setKontrakt_id(int kontrakt_id) {
        this.kontrakt_id = kontrakt_id;
    }

    public LocalDate getStartdato() {
        return startdato;
    }

    public void setStartdato(LocalDate startdato) {
        this.startdato = startdato;
    }

    public LocalDate getSlutdato() {
        return slutdato;
    }

    public void setSlutdato(LocalDate slutdato) {
        this.slutdato = slutdato;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public int getDepositum() {
        return depositum;
    }

    public void setDepositum(int depositum) {
        this.depositum = depositum;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public int getAfhentnings_adresse() {
        return afhentnings_adresse;
    }

    public void setAfhentnings_adresse(int afhentnings_adresse) {
        this.afhentnings_adresse = afhentnings_adresse;
    }

    public int getAfleverings_adresse() {
        return afleverings_adresse;
    }

    public void setAfleverings_adresse(int afleverings_adresse) {
        this.afleverings_adresse = afleverings_adresse;
    }

    public int getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(int vognnummer) {
        this.vognnummer = vognnummer;
    }

    public int getRapport_id() {
        return rapport_id;
    }

    public void setRapport_id(int rapport_id) {
        this.rapport_id = rapport_id;
    }

    public int getLejer_id() {
        return lejer_id;
    }

    public void setLejer_id(int lejer_id) {
        this.lejer_id = lejer_id;
    }
}
