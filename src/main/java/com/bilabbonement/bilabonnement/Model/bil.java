package com.bilabbonement.bilabonnement.Model;

public class bil {

    private int vognnummer;
    private String stelnummer;
    private String nummerplade;
    private String maerke;
    private String model;
    private String braendstof; // Benzin, Diesel, Elektrisk, Hybrid
    private String farve;
    private String status; // Ledig, Udleveres, Udlejet, Vaerksted, Rengoeres, Reservet
    private boolean anhaengertraek;
    private int odometer;
    private boolean automatgear;

    public bil() {
    }

    public bil(int vognnummer, String stelnummer, String nummerplade, String maerke, String model, String braendstof, String farve, String status, boolean anhaengertraek, int odometer, boolean automatgear) {
        this.vognnummer = vognnummer;
        this.stelnummer = stelnummer;
        this.nummerplade = nummerplade;
        this.maerke = maerke;
        this.model = model;
        this.braendstof = braendstof;
        this.farve = farve;
        this.status = status;
        this.anhaengertraek = anhaengertraek;
        this.odometer = odometer;
        this.automatgear = automatgear;
    }

    public int getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(int vognnummer) {
        this.vognnummer = vognnummer;
    }

    public String getStelnummer() {
        return stelnummer;
    }

    public void setStelnummer(String stelnummer) {
        this.stelnummer = stelnummer;
    }

    public String getNummerplade() {
        return nummerplade;
    }

    public void setNummerplade(String nummerplade) {
        this.nummerplade = nummerplade;
    }

    public String getMaerke() {
        return maerke;
    }

    public void setMaerke(String maerke) {
        this.maerke = maerke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBraendstof() {
        return braendstof;
    }

    public void setBraendstof(String braendstof) {
        this.braendstof = braendstof;
    }

    public String getFarve() {
        return farve;
    }

    public void setFarve(String farve) {
        this.farve = farve;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAnhaengertraek() {
        return anhaengertraek;
    }

    public void setAnhaengertraek(boolean anhaengertraek) {
        this.anhaengertraek = anhaengertraek;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public boolean isAutomatgear() {
        return automatgear;
    }

    public void setAutomatgear(boolean automatgear) {
        this.automatgear = automatgear;
    }
}
