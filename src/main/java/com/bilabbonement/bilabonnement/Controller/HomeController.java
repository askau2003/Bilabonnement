package com.bilabbonement.bilabonnement.Controller;

import com.bilabbonement.bilabonnement.Model.Bruger;
import com.bilabbonement.bilabonnement.Model.Lejekontrakt;
import com.bilabbonement.bilabonnement.Model.Status;
import com.bilabbonement.bilabonnement.Model.OmsaetningMaaned;
import com.bilabbonement.bilabonnement.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    private final BilService bilService;
    private final BrugerService brugerService;
    private final LejekontraktService lejekontraktService;
    private final SkaderapportService skaderapportService;
    private final TransportService transportService;

    @Autowired
    public HomeController(BilService bilService, BrugerService brugerService, LejekontraktService lejekontraktService, SkaderapportService skaderapportService, TransportService transportService) {
        this.bilService = bilService;
        this.brugerService = brugerService;
        this.lejekontraktService = lejekontraktService;
        this.skaderapportService = skaderapportService;
        this.transportService = transportService;
    }


    @GetMapping("/")
    public String index() {
        return "home/logind";
    }


    // Her håndterer vi login-knappen fra HTML'en
    @PostMapping("/logind")
    public String logind(@RequestParam String brugernavn,
                         @RequestParam String adgangskode,
                         HttpSession session) {
        // Tjekker om brugeren findes i databasen og at adgangskoden matcher
        Bruger bruger = brugerService.validerLogin(brugernavn, adgangskode);

        if (bruger != null) {
            // Gemmer brugeren i session, så vi kan huske hvem der er logget ind
            session.setAttribute("bruger", bruger);

            // Tjekker hvilken rolle brugeren har, og sender videre til den rigtige side
            switch (bruger.getRolle().toLowerCase()) {
                case "administrator":
                    return "redirect:/logindhome"; // sender admin-brugere til admin-siden
                case "forretningsudvikler":
                    return "redirect:/forretninghome"; // sender forretningsudviklere til deres side
                default:
                    // Hvis brugeren har en ukendt rolle, kan vi evt. vise en fejlside eller logge ud
                    return "redirect:/";
            }
        } else {
            // Hvis login fejler, så bliver man bare på login-siden igen
            return "home/logind";
        }
    }


    @GetMapping("/logindhome")
    public String showLogindHome() {
        return "home/logindhome";
    }


    // side til og oprette kontrakt
    @GetMapping("/opretkontrakt")
    public String visOpretKontraktForm(Model model) {
        model.addAttribute("kontrakt", new Lejekontrakt());
        return "home/opretkontrakt";
    }


    // opretter kontrakten
    @PostMapping("/opretkontrakt")
    public String opretKontraktSubmit(@ModelAttribute Lejekontrakt kontrakt, Model model) {
        kontrakt.setOprettelsesdato(LocalDate.now());
        kontrakt.setBetalingsdato(LocalDate.now());

        // Gemmer i databasen
        lejekontraktService.opretLejeKontrakt(kontrakt);

        model.addAttribute("kontrakt", kontrakt);// vis kontrakt på side vis nødvendigt

        return "home/kontraktoprettet";//vis at kontrakten er blevet oprettet
    }


    @GetMapping("/skadesrapporter")
    public String visskaderapport() {
        return "home/skadesrapporter";
    }


    // Overfører attributes til View (html)
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Double gennemsnitlig_betalingstid = lejekontraktService.gennemsnitligBetalingstid();
        model.addAttribute("gennemsnitlig_betalingstid", gennemsnitlig_betalingstid);

        Double gennemsnitlig_transporttid = transportService.gennemsnitligTransporttid();
        model.addAttribute("gennemsnitlig_transporttid", gennemsnitlig_transporttid);

        Double totalOmsaetning = lejekontraktService.omsaetningMaaned();
        model.addAttribute("totalOmsaetning", totalOmsaetning);

        List<Status> statusList = bilService.antalIStatus();
        model.addAttribute("statusList", statusList);

        return "home/dashboard";
    }

    // Viser bil-lager siden med alle biler
    @GetMapping("/billager")
    public String visBilLager(Model model) {
        model.addAttribute("biler", bilService.findAll());
        return "home/billager";
    }
}