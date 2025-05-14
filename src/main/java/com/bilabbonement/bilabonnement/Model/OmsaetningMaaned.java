package com.bilabbonement.bilabonnement.Model;

public class OmsaetningMaaned {

    private String valuta;
    private int omsaetning;

    public OmsaetningMaaned() {
    }

    public OmsaetningMaaned(String valuta, int omsaetning) {
        this.valuta = valuta;
        this.omsaetning = omsaetning;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public int getOmsaetning() {
        return omsaetning;
    }

    public void setOmsaetning(int omsaetning) {
        this.omsaetning = omsaetning;
    }
}
