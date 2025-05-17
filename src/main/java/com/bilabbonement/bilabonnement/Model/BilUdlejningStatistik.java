package com.bilabbonement.bilabonnement.Model;

public class BilUdlejningStatistik
{
        private int vognnummer;
        private String maerke;
        private String model;
        private String nummerplade;
        private int antalUdlejninger;

        // Getters og setters
        public int getVognnummer() {
            return vognnummer;
        }
        public void setVognnummer(int vognnummer) {
            this.vognnummer = vognnummer;
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
        public String getNummerplade() {
            return nummerplade;
        }
        public void setNummerplade(String nummerplade) {
            this.nummerplade = nummerplade;
        }
        public int getAntalUdlejninger() {
            return antalUdlejninger;
        }
        public void setAntalUdlejninger(int antalUdlejninger) {
            this.antalUdlejninger = antalUdlejninger;
        }
    }
