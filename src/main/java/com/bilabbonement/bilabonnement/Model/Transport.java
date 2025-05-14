package com.bilabbonement.bilabonnement.Model;

import java.time.LocalDate;

public class Transport {

    private int transport_id;
    private LocalDate lastbil_afhentnings_dato;
    private LocalDate lastbil_afleverings_dato;
    private int vognnummer;

    public Transport() {
    }

    public Transport(int transport_id, LocalDate lastbil_afhentnings_dato, LocalDate lastbil_afleverings_dato, int vognnummer) {
        this.transport_id = transport_id;
        this.lastbil_afhentnings_dato = lastbil_afhentnings_dato;
        this.lastbil_afleverings_dato = lastbil_afleverings_dato;
        this.vognnummer = vognnummer;
    }

    public int getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(int transport_id) {
        this.transport_id = transport_id;
    }

    public LocalDate getLastbil_afhentnings_dato() {
        return lastbil_afhentnings_dato;
    }

    public void setLastbil_afhentnings_dato(LocalDate lastbil_afhentnings_dato) {
        this.lastbil_afhentnings_dato = lastbil_afhentnings_dato;
    }

    public LocalDate getLastbil_afleverings_dato() {
        return lastbil_afleverings_dato;
    }

    public void setLastbil_afleverings_dato(LocalDate lastbil_afleverings_dato) {
        this.lastbil_afleverings_dato = lastbil_afleverings_dato;
    }

    public int getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(int vognnummer) {
        this.vognnummer = vognnummer;
    }
}
