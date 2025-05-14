package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportService {
    @Autowired
    TransportRepository transportRepository;

    public Double gennemsnitligTransporttid() {
        return transportRepository.gennemsnitligTransporttid();
    }
}
