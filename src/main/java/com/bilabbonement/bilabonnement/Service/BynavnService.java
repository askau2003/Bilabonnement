package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.Bil;
import com.bilabbonement.bilabonnement.Model.Bynavn;
import com.bilabbonement.bilabonnement.Repository.BilRepository;
import com.bilabbonement.bilabonnement.Repository.BynavnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BynavnService {

    @Autowired
    private BynavnRepository bynavnRepository;

    public List<Bynavn> selectAll() {
        return bynavnRepository.selectAll();
    }
    public void opretBy(Bynavn bynavn) {
        bynavnRepository.opretBy(bynavn);
    }
}
