package com.bilabbonement.bilabonnement.Controller;

import com.bilabbonement.bilabonnement.Model.bruger;
import com.bilabbonement.bilabonnement.Service.BrugerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
                case "admin":
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

    @GetMapping("/skadesrapporter")
    public String showSkadesrapporter()
    {
        return "home/skadesrapporter";
    }
}
