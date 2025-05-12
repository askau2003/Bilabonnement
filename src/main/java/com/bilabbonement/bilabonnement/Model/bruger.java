package com.bilabbonement.bilabonnement.Model;

public class bruger {

    private String brugernavn;
    private String adgangskode;
    private String rolle;

    public bruger() {
    }

    public bruger(String brugernavn, String adgangskode, String rolle) {
        this.brugernavn = brugernavn;
        this.adgangskode = adgangskode;
        this.rolle = rolle;
    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getAdgangskode() {
        return adgangskode;
    }

    public void setAdgangskode(String adgangskode) {
        this.adgangskode = adgangskode;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
}
