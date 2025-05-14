package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.Skaderapport;
import com.bilabbonement.bilabonnement.Repository.SkaderapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkaderapportService {

    @Autowired
    private SkaderapportRepository repository;

    public List<Skaderapport> hentAlleRapporter() {
        return repository.findAll();
    }

    public void gemNyRapport(Skaderapport rapport) {
        repository.save(rapport);
    }

    public Skaderapport hentRapportVedId(int id) {
        return repository.findById(id);
    }

    public void sletRapport(int id) {
        repository.deleteById(id);
    }

    public void opdaterRapport(Skaderapport rapport) {
        repository.update(rapport);
    }
}