package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.Bil;
import com.bilabbonement.bilabonnement.Model.BilUdlejningStatistik;
import com.bilabbonement.bilabonnement.Model.Status;
import com.bilabbonement.bilabonnement.Repository.BilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<String, List<Bil>> findAllGroupedByStatus() {
        // Hent alle biler fra databasen
        List<Bil> alleBiler = bilRepository.findAll();

        // Grupper bilerne i en Map, hvor nøgle er status og værdi er liste af biler med den status
        Map<String, List<Bil>> grupperet = alleBiler.stream()
                .collect(Collectors.groupingBy(Bil::getStatus));

        // Opret en ny LinkedHashMap for at bevare en bestemt rækkefølge på statusserne
        LinkedHashMap<String, List<Bil>> sorteretMap = new LinkedHashMap<>();

        // Definer den ønskede rækkefølge på statusser
        List<String> statusRækkefølge = List.of("Ledig", "Udlejet", "Udleveres", "Reserveret", "Vaerksted", "Rengoeres");

        // Tilføj grupperne til sorteretMap i den ønskede rækkefølge, hvis de findes
        for (String status : statusRækkefølge) {
            if (grupperet.containsKey(status)) {
                sorteretMap.put(status, grupperet.get(status));
            }
        }

        // Returnér den sorterede Map med biler grupperet efter status
        return sorteretMap;
    }

    public void opretBil(Bil bil) {
        bilRepository.opretBil(bil);
    }
}