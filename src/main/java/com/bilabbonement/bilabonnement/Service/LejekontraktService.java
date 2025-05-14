package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.Lejekontrakt;
import com.bilabbonement.bilabonnement.Model.OmsaetningMaaned;
import com.bilabbonement.bilabonnement.Repository.LejekontraktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LejekontraktService {
    @Autowired
    LejekontraktRepository lejekontraktRepository;

    public Double gennemsnitligBetalingstid() {
        return lejekontraktRepository.gennemsnitligBetalingstid();
    }

    public Double omsaetningMaaned() {

        // tilføj data fra repository til en List
        List<OmsaetningMaaned> omsaetningList = lejekontraktRepository.omsaetningMaaned();
        // ny variabel
        Double totalOmsaetning = 0.0;

        // kører listen igennem
        for (OmsaetningMaaned omsaetning : omsaetningList) {
            //hvis valuta er euro skal omsætningen ganges med 7,5 for at få det i DKK derefter ligges oven i variabel
            if ("EURO".equals(omsaetning.getValuta())) {
                totalOmsaetning += omsaetning.getOmsaetning() * 7.5;
            }
            // hvis valuta er DKK skal omsætningen bare ligges oven i variabel
            if ("DKK".equals(omsaetning.getValuta())) {
                totalOmsaetning += omsaetning.getOmsaetning();
            }
        }

        // returnerer omsætningen
        return totalOmsaetning;
    }

    public void opretLejeKontrakt(Lejekontrakt kontrakt) {
        lejekontraktRepository.opretLejeKontrakt(kontrakt);
    }

}