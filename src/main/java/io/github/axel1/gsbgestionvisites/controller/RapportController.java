package io.github.axel1.gsbgestionvisites.controller;

import io.github.axel1.gsbgestionvisites.entity.Medecin;
import io.github.axel1.gsbgestionvisites.entity.Rapport;
import io.github.axel1.gsbgestionvisites.entity.Visiteur;
import io.github.axel1.gsbgestionvisites.service.MedecinService;
import io.github.axel1.gsbgestionvisites.service.MyUserPrincipal;
import io.github.axel1.gsbgestionvisites.service.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/rapports")
public class RapportController {
    private final RapportService rapportService;
    private final MedecinService medecinService;

    @Autowired
    public RapportController(RapportService rapportService, MedecinService medecinService) {
        this.rapportService = rapportService;
        this.medecinService = medecinService;
    }

    @GetMapping(path = "")
    public String searchRapport(Model model, Authentication authentication, @RequestParam(defaultValue = "") String date) {
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Visiteur visiteur = myUserPrincipal.getVisiteur();
        if (Objects.equals(date, "")) {
            model.addAttribute("title", "Rapports");
            model.addAttribute("rapports", rapportService.getRapportByVisiteur(visiteur));
            return "listRapport";
        } else {
            LocalDate localDate = LocalDate.parse(date);
            model.addAttribute("title", "Rapports");
            model.addAttribute("rapports", rapportService.getRapportByVisiteurAndDate(visiteur, localDate));
            return "listRapport";
        }
    }

    @GetMapping("/{id}")
    public String getRapportById(Model model, @PathVariable("id") Long id, Authentication authentication) {
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Visiteur visiteur = myUserPrincipal.getVisiteur();

        Rapport rapport = rapportService.getRapportByVisiteurAndId(visiteur, id);

        model.addAttribute("title", "Rapports");
        model.addAttribute("rapport", rapport);

        return "detailsRapport";
    }

    @GetMapping("/new")
    public String createRapport(Model model) {
        Rapport rapport = new Rapport();
        List<Medecin> medecinList = medecinService.getAllMedecin();
        model.addAttribute("title", "Rapports");
        model.addAttribute("rapport", rapport);
        model.addAttribute("medecinList", medecinList);
        model.addAttribute("medecinId", 0);
        return "formRapport";
    }

    @PostMapping("/save")
    public String submitRapport(Authentication authentication, @ModelAttribute("rapport") Rapport rapport, @ModelAttribute("medecinId") Long medecinId, Model model) {
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Visiteur visiteur = myUserPrincipal.getVisiteur();

        rapport.setVisiteur(visiteur);
        rapport.setMedecin(medecinService.getMedecinById(medecinId));

        Rapport savedRapport = rapportService.saveRapport(rapport);
        Long id = savedRapport.getId();

        return "redirect:" + id.toString();
    }
}
