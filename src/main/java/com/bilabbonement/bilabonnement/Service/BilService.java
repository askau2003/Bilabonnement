package com.bilabbonement.bilabonnement.Service;

import com.bilabbonement.bilabonnement.Model.Status;
import com.bilabbonement.bilabonnement.Repository.BilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilService {
    @Autowired
    BilRepository bilRepository;

    public List<Status> antalIStatus() {
        return bilRepository.antalIStatus();
    }
}