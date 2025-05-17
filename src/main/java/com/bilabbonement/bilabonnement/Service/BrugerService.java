package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.Bruger;
import com.bilabbonement.bilabonnement.Repository.BrugerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrugerService
{
    @Autowired
    private BrugerRepository brugerRepository;

    // Her tjekker vi om brugernavn og adgangskode matcher noget i databasen
    public Bruger validerLogin(String brugernavn, String adgangskode)
    {
        Bruger bruger = brugerRepository.findBrugerByBrugernavn(brugernavn);

        // Hvis brugeren findes og adgangskoden passer, så returnér brugeren
        if (bruger != null && bruger.getAdgangskode().equals(adgangskode))
        {
            return bruger;
        }

        // Ellers returnér null = login mislykkedes
        return null;
    }

    public boolean opretBruger(Bruger bruger)
    {
        // Kun opret hvis brugernavn ikke allerede findes
        if (brugerRepository.findBrugerByBrugernavn(bruger.getBrugernavn()) != null)
        {
            return false;
        }
        return brugerRepository.opretBruger(bruger);
    }

    public List<Bruger> findAlleBrugere() {
        return brugerRepository.findAlleBrugere();
    }

    public boolean sletBruger(String brugernavn) {
        return brugerRepository.sletBruger(brugernavn);
    }
}