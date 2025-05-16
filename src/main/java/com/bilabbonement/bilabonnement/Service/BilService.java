package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.Bil;
import com.bilabbonement.bilabonnement.Model.BilUdlejningStatistik;
import com.bilabbonement.bilabonnement.Model.Status;
import com.bilabbonement.bilabonnement.Repository.BilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilService {
    @Autowired
    BilRepository bilRepository;

    // Henter alle biler fra databasen
    public List<Bil> findAll() {
        return bilRepository.findAll();
    }

    public List<Status> antalIStatus() {
        return bilRepository.antalIStatus();
    }

    public List<Bil> reserveredeBiler() {
        return bilRepository.reserveredeBiler();
    }

    public void opdaterBilStatus(int vognnummer, String nyStatus) {
        bilRepository.opdaterBilStatus(vognnummer, nyStatus);
    }

    public List<Bil> findUdleveresBiler() {
        return bilRepository.findUdleveresBiler();
    }

    public List<Bil> selectVognnummer() {
        return bilRepository.selectVognnummer();
    }
    public List<BilUdlejningStatistik> findAntalUdlejningerPrBil() {
        return bilRepository.findAntalUdlejningerPrBil();
    }
}