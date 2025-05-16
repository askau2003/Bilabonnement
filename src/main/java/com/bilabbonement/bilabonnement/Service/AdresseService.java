package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.Adresse;
import com.bilabbonement.bilabonnement.Repository.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdresseService {

    @Autowired
    AdresseRepository adresseRepository;

    public List<Adresse> selectAll() {
        return adresseRepository.selectAll();
    }
}
