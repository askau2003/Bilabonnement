package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.skaderapport;
import com.bilabbonement.bilabonnement.Repository.skaderapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class skaderapportService {

    @Autowired
    private skaderapportRepository repository;

    public List<skaderapport> hentAlleRapporter() {
        return repository.findAll();
    }

    public void gemNyRapport(skaderapport rapport) {
        repository.save(rapport);
    }

    public skaderapport hentRapportVedId(int id) {
        return repository.findById(id);
    }

    public void sletRapport(int id) {
        repository.deleteById(id);
    }

    public void opdaterRapport(skaderapport rapport) {
        repository.update(rapport);
    }
}