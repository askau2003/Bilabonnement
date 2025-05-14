package com.bilabbonement.bilabonnement.Model;

public class Kunde {

    private int lejer_id;
    private String navn;
    private String email;
    private String cpr_nummer; // varchar(11)
    private int koerekortnummer;
    private int adresse_id;

    public Kunde() {
    }

    public Kunde(int lejer_id, String navn, String email, String cpr_nummer, int koerekortnummer, int adresse_id) {
        this.lejer_id = lejer_id;
        this.navn = navn;
        this.email = email;
        this.cpr_nummer = cpr_nummer;
        this.koerekortnummer = koerekortnummer;
        this.adresse_id = adresse_id;
    }

    public int getLejer_id() {
        return lejer_id;
    }

    public void setLejer_id(int lejer_id) {
        this.lejer_id = lejer_id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpr_nummer() {
        return cpr_nummer;
    }

    public void setCpr_nummer(String cpr_nummer) {
        this.cpr_nummer = cpr_nummer;
    }

    public int getKoerekortnummer() {
        return koerekortnummer;
    }

    public void setKoerekortnummer(int koerekortnummer) {
        this.koerekortnummer = koerekortnummer;
    }

    public int getAdresse_id() {
        return adresse_id;
    }

    public void setAdresse_id(int adresse_id) {
        this.adresse_id = adresse_id;
    }
}
