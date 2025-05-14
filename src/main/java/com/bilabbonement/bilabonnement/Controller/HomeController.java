package com.bilabbonement.bilabonnement.Controller;

import com.bilabbonement.bilabonnement.Model.status;
import com.bilabbonement.bilabonnement.Service.BilService;
import com.bilabbonement.bilabonnement.Service.LejekontraktService;
import com.bilabbonement.bilabonnement.Service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final LejekontraktService lejekontraktService;
    private final TransportService transportService;
    private final BilService bilService;


    // Constructor-injection er nemmere at læse + nemmere at teste
    @Autowired
    public HomeController(LejekontraktService lejekontraktService, TransportService transportService, BilService bilService) {
        this.lejekontraktService = lejekontraktService;
        this.transportService = transportService;
        this.bilService = bilService;
    }

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
