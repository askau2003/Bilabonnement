package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.Forhaandsaftale;
import com.bilabbonement.bilabonnement.Repository.ForhaandsaftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForhaandsaftaleService {

    @Autowired
    ForhaandsaftaleRepository forhaandsaftaleRepository;

    public void opretForhaandsaftale(Forhaandsaftale forhaandsaftale) {
        if(forhaandsaftale.isBetalt() == null) {
            forhaandsaftale.setBetalt(false);
        }
        forhaandsaftaleRepository.opretForhaandsaftale(forhaandsaftale);
    }
}
