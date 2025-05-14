package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Repository.LejekontraktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LejekontraktService {
    @Autowired
    LejekontraktRepository lejekontraktRepository;

    public Double gennemsnitligBetalingstid() {
        return lejekontraktRepository.gennemsnitligBetalingstid();
    }

    public Double omsaetningMaaned() {
        return lejekontraktRepository.omsaetningMaaned();
    }
}
