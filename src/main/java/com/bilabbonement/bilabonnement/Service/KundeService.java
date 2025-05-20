package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.Kunde;
import com.bilabbonement.bilabonnement.Model.Lejekontrakt;
import com.bilabbonement.bilabonnement.Repository.KundeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KundeService {

    @Autowired
    KundeRepository kundeRepository;

    public List<Kunde> selectLejerID() {
        return kundeRepository.selectLejerID();
    }

    public void opretKunde(Kunde kunde) {kundeRepository.opretKunde(kunde);}

}
