package com.bilabbonement.bilabonnement.Controller;


import com.bilabbonement.bilabonnement.Model.lejekontrakt;
import com.bilabbonement.bilabonnement.Repository.lejekontraktRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import java.time.LocalDate;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String index()
    {
        return "home/logind";
    }

    @GetMapping("/logindhome")
       public String showLogindHome()
    {
        return "home/logindhome";
    }
    @Autowired
    private lejekontraktRepository repository;

    // side til og oprette kontrakt
    @GetMapping("/opretkontrakt")
    public String visOpretKontraktForm(Model model) {
        model.addAttribute("kontrakt", new lejekontrakt());
        return "home/opretkontrakt";
    }

    // opretter kontrakten
    @PostMapping("/opretkontrakt")
    public String opretKontraktSubmit(@ModelAttribute lejekontrakt kontrakt, Model model) {
        kontrakt.setOprettelsesdato(LocalDate.now());
        kontrakt.setBetalingsdato(LocalDate.now());

        // Gemmer i databasen
        repository.insert(kontrakt);

        model.addAttribute("kontrakt", kontrakt);// vis kontrakt på side vis nødvendigt

        return "home/kontraktoprettet";//vis at kontrakten er blevet oprettet
    }
}