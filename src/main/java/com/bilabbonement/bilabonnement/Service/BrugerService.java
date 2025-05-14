package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.bruger;
import com.bilabbonement.bilabonnement.Repository.BrugerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrugerService
{
    @Autowired
    private BrugerRepository brugerRepository;

    // Her tjekker vi om brugernavn og adgangskode matcher noget i databasen
    public bruger validerLogin(String brugernavn, String adgangskode)
    {
        bruger bruger = brugerRepository.findbrugerByBrugernavn(brugernavn);

        // Hvis brugeren findes og adgangskoden passer, så returnér brugeren
        if (bruger != null && bruger.getAdgangskode().equals(adgangskode))
        {
            return bruger;
        }

        // Ellers returnér null = login mislykkedes
        return null;
    }
}