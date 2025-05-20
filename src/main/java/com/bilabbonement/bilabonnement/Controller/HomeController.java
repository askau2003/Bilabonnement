package com.bilabbonement.bilabonnement.Controller;

import com.bilabbonement.bilabonnement.Model.*;
import com.bilabbonement.bilabonnement.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final BilService bilService;
    private final BrugerService brugerService;
    private final LejekontraktService lejekontraktService;
    private final SkaderapportService skaderapportService;
    private final TransportService transportService;
    private final ForhaandsaftaleService forhaandsaftaleService;
    private final AdresseService adresseService;
    private final KundeService kundeService;
    private final BynavnService bynavnService;

    @Autowired
    public HomeController(BilService bilService, BrugerService brugerService, LejekontraktService lejekontraktService,
                          SkaderapportService skaderapportService, TransportService transportService,
                          ForhaandsaftaleService forhaandsaftaleService, AdresseService adresseService, KundeService kundeService, BynavnService bynavnService) {
        this.bilService = bilService;
        this.brugerService = brugerService;
        this.lejekontraktService = lejekontraktService;
        this.skaderapportService = skaderapportService;
        this.transportService = transportService;
        this.forhaandsaftaleService = forhaandsaftaleService;
        this.adresseService = adresseService;
        this.kundeService = kundeService;
        this.bynavnService = bynavnService;
    }


    @GetMapping("/")
    public String index() {
        return "home/logind";
    }


    // Her håndterer vi login-knappen fra HTML'en
    @PostMapping("/logind")
    public String logind(@RequestParam String brugernavn,
                         @RequestParam String adgangskode,
                         HttpSession session,
                         Model model) {
        // Tjekker om brugeren findes i databasen og at adgangskoden matcher
        Bruger bruger = brugerService.validerLogin(brugernavn, adgangskode);

        if (bruger != null) {
            // Gemmer brugeren i session, så vi kan huske hvem der er logget ind
            session.setAttribute("bruger", bruger);

            // Tjekker hvilken rolle brugeren har, og sender videre til den rigtige side
            switch (bruger.getRolle().toLowerCase()) {
                case "administrationsmedarbejder":
                    return "redirect:/logindhome"; // sender admin-brugere til admin-siden
                case "forretningsudvikler":
                    return "redirect:/forretninghome"; // sender forretningsudviklere til deres side
                case "superadmin":
                    return "redirect:/superadminhome"; // Kan se begge sider på engang
                default:
                    // Hvis brugeren har en ukendt rolle, kan vi evt. vise en fejlside eller logge ud
                    return "redirect:/";
            }
        } else {
            model.addAttribute("fejlbesked", "Forkert brugernavn eller adgangskode");
            // Hvis login fejler, så bliver man bare på login-siden igen
            return "home/logind";
        }
    }

    @GetMapping("/forretninghome")
    public String visUdlejedeBiler(Model model) {
        List<BilUdlejningStatistik> statistikList = bilService.findAntalUdlejningerPrBil();
        model.addAttribute("statistik", statistikList);
        return "home/forretninghome";
    }


    @GetMapping("/logindhome")
    public String showLogindHome() {
        return "home/logindhome";
    }

    // Viser superadmin-siden hvis brugeren er logget ind som superadmin
    @GetMapping("/superadminhome")
    public String visSuperadminHome(HttpSession session, Model model) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");

        // Tjekker om brugeren er superadmin
        if (bruger != null && bruger.getRolle().equalsIgnoreCase("superadmin")) {
            model.addAttribute("brugere", brugerService.findAlleBrugere()); // Tilføjer alle brugere til visning
            return "home/superadminhome";
        } else {
            return "redirect:/"; // Sender tilbage til forsiden hvis ikke superadmin
        }
    }

    @GetMapping("/opretbruger")
    public String redirectOpretBruger() {
        return "redirect:/superadminhome";
    }

    // Opretter en ny bruger via formular
    @PostMapping("/opretBruger")
    public String opretBruger(@RequestParam String brugernavn,
                              @RequestParam String adgangskode,
                              @RequestParam String rolle,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        Bruger nuvaerendeBruger = (Bruger) session.getAttribute("bruger");

        // Tjekker om bruger er superadmin før oprettelse
        if (nuvaerendeBruger == null || !nuvaerendeBruger.getRolle().equalsIgnoreCase("superadmin")) {
            return "redirect:/superadminhome";
        }

        Bruger nyBruger = new Bruger(brugernavn, adgangskode, rolle);
        boolean succes = brugerService.opretBruger(nyBruger);

        // Tilføjer besked alt efter om oprettelse lykkedes
        if (succes) {
            redirectAttributes.addFlashAttribute("besked", "Bruger oprettet!");
        } else {
            redirectAttributes.addFlashAttribute("fejl", "Brugernavn eksisterer allerede!");
        }

        return "redirect:/superadminhome"; // Loader siden igen med opdateret liste
    }

    // addFlashAttribute bruges til at sende beskeder med til næste side efter redirect,
    // fordi normale model-data ikke følger med ved redirect.


    @GetMapping("/sletBruger")
    public String redirectSletBruger() {
        return "redirect:/superadminhome";
    }

    // Sletter en bruger via POST-request
    @PostMapping("/sletBruger")
    public String sletBruger(@RequestParam String brugernavn, HttpSession session, RedirectAttributes redirectAttributes) {
        Bruger nuvaerendeBruger = (Bruger) session.getAttribute("bruger");

        if (nuvaerendeBruger == null || !nuvaerendeBruger.getRolle().equalsIgnoreCase("superadmin")) {
            return "redirect:/";
        }

        boolean succes = brugerService.sletBruger(brugernavn);

        if (succes) {
            redirectAttributes.addFlashAttribute("besked", "Bruger " + brugernavn + " er slettet.");
        } else {
            redirectAttributes.addFlashAttribute("fejl", "Kunne ikke slette brugeren " + brugernavn);
        }
        return "redirect:/superadminhome";
    }

    // addFlashAttribute bruges til at sende beskeder med til næste side efter redirect,
    // fordi normale model-data ikke følger med ved redirect.


    // side til og oprette kontrakt
    @GetMapping("/opretkontrakt")
    public String visOpretKontraktForm(Model model) {
        model.addAttribute("kontrakt", new Lejekontrakt());
        List<Adresse> adresseList = adresseService.selectAll();
        model.addAttribute("adresseList", adresseList);

        List<Bil> bilList = bilService.selectVognnummer();
        model.addAttribute("bilList", bilList);

        List<Kunde> kundeList = kundeService.selectLejerID();
        model.addAttribute("kundeList", kundeList);

        model.addAttribute("rapporter", skaderapportService.hentAlleRapporter());
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

        return "redirect:/dataTilfoejet";//vis at kontrakten er blevet oprettet
    }


    // Viser formularen til at oprette en skadesrapport

    @GetMapping("/skadesrapporter")
    public String visAlleRapporter(Model model) {
        model.addAttribute("rapporter", skaderapportService.hentAlleRapporter());
        return "home/skadesrapporter"; // <- du skal have en HTML med dette navn
    }

    // Håndterer form submission og gemmer skadesrapporten
    @PostMapping("/skadesrapport")
    public String gemSkadesrapport(@ModelAttribute Skaderapport skadesrapport) {
        skaderapportService.gemNyRapport(skadesrapport);
        return "redirect:/skadesrapporter";
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

    // Billager side
    @GetMapping("/lagerstatus")
    public String visBilLager(Model model) {
        model.addAttribute("biler", bilService.findAll());
        return "home/lagerstatus";
    }

    @GetMapping("/reserveredeBiler")
    public String reserveredeBiler(Model model) {
        List<Bil> bilList = bilService.reserveredeBiler();
        model.addAttribute("bilList", bilList);
        return "home/reserveredeBiler";
    }

    @GetMapping("/udlevering")
    public String visUdleveresBiler(Model model) {
        List<Bil> bilList = bilService.findUdleveresBiler();
        model.addAttribute("bilList", bilList);
        return "home/udlevering";
    }

    @PostMapping("/opdaterStatus")
    public String opdaterBilStatus(@RequestParam(value = "udlejedeBiler", required = false) List<Integer> udlejedeBiler) {
        if (udlejedeBiler != null) {
            for (int vognnummer : udlejedeBiler) {
                bilService.opdaterBilStatus(vognnummer, "Udlejet");
            }
        }
        return "redirect:/udlevering";
    }


    @GetMapping("/opretForhaandsaftale")
    public String opretForhaandsaftale(Model model) {
        List<Adresse> adresseList = adresseService.selectAll();
        model.addAttribute("adresseList", adresseList);

        List<Bil> bilList = bilService.selectVognnummer();
        model.addAttribute("bilList", bilList);

        List<Kunde> kundeList = kundeService.selectLejerID();
        model.addAttribute("kundeList", kundeList);
        return "home/opretForhaandsaftale";
    }

    @PostMapping("/opretForhaandsaftale")
    public String opretForhaandsaftale(@ModelAttribute Forhaandsaftale forhaandsaftale) {
        forhaandsaftaleService.opretForhaandsaftale(forhaandsaftale);
        return "redirect:/dataTilfoejet"; // godkendelses side
    }

    @GetMapping("/dataTilfoejet")
    public String dataTilfoejet() {
        return "home/dataTilfoejet";
    }

    @GetMapping("/indkoebBil")
    public String indkoebBil() {
        return "home/indkoebBil";
    }

    @GetMapping("/statusbiler")
    public String visBilerGrupperet(Model model) {
        // Hent biler grupperet efter status til visning i kanban eller tabel
        Map<String, List<Bil>> grupperet = bilService.findAllGroupedByStatus();
        model.addAttribute("bilerGrupperet", grupperet);

        // Hent alle biler til dropdown-menuen, hvor brugeren kan vælge bil til statusændring
        List<Bil> alleBiler = bilService.findAll();
        model.addAttribute("alleBiler", alleBiler);

        return "home/statusbiler";
    }

    @PostMapping("/bil/skiftStatus")
    public String skiftStatus(@RequestParam int vognnummer, @RequestParam String nyStatus) {
        // Opdater bilens status i databasen via service-laget
        bilService.opdaterBilStatus(vognnummer, nyStatus);

        // Redirect tilbage til statusbiler-siden for at se opdateringen
        return "redirect:/statusbiler";
    }

    @GetMapping("/alarmer")
    public String alarmer() {
        return "home/alarmer";
    }

    @GetMapping("/afleveringer")
    public String visAfleveringer(Model model) {
        List<Lejekontrakt> kontrakter = lejekontraktService.findAlleAktive();
        model.addAttribute("kontrakter", kontrakter);
        return "home/afleveringer";
    }

    @GetMapping("/opretAdresse")
    public String opretAdresse(Model model) {
        List<Bynavn> bynavnList = bynavnService.selectAll();
        model.addAttribute("bynavnList",bynavnList);
        return "home/opretAdresse";
    }

    @PostMapping("/opretAdresse")
    public String opretAdresse(@ModelAttribute Adresse adresse) {
        adresseService.opretAdresse(adresse);
        return "redirect:/dataTilfoejet"; // godkendelses side
    }

    @GetMapping("/opretkunde")
    public String opretkunde(){return "home/opretkunde";}
}