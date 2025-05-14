package com.bilabbonement.bilabonnement.Controller;

import com.bilabbonement.bilabonnement.Model.bruger;
import com.bilabbonement.bilabonnement.Model.lejekontrakt;
import com.bilabbonement.bilabonnement.Repository.lejekontraktRepository;
import com.bilabbonement.bilabonnement.Service.BrugerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private BrugerService brugerService;
    @GetMapping("/")
    public String index()
    {
        return "home/logind";
    }
    // Her håndterer vi login-knappen fra HTML'en
    @PostMapping("/logind")
    public String logind(@RequestParam String brugernavn,
                         @RequestParam String adgangskode,
                         HttpSession session)
    {
        // Tjekker om brugeren findes i databasen og at adgangskoden matcher
        bruger bruger = brugerService.validerLogin(brugernavn, adgangskode);

        if (bruger != null)
        {
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
        }
        else {
            // Hvis login fejler, så bliver man bare på login-siden igen
            return "home/logind";
        }
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

    @GetMapping("/skadesrapporter")
    public String visskaderapport()
    {
        return "home/skadesrapporter";
    }

    // Overfører attributes til View (html)
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Double gennemsnitlig_betalingstid = lejekontraktService.gennemsnitligBetalingstid();
        model.addAttribute("gennemsnitlig_betalingstid",gennemsnitlig_betalingstid);

        Double gennemsnitlig_transporttid = transportService.gennemsnitligTransporttid();
        model.addAttribute("gennemsnitlig_transporttid",gennemsnitlig_transporttid);

        Double omsaetning_maaned = lejekontraktService.omsaetningMaaned();
        model.addAttribute("omsaetning_maaned",omsaetning_maaned);

        List<status> statusList = bilService.antalIStatus();
        model.addAttribute("statusList",statusList);

        return "home/dashboard";
    }
}