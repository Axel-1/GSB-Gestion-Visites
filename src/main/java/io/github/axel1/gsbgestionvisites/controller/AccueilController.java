package io.github.axel1.gsbgestionvisites.controller;

import io.github.axel1.gsbgestionvisites.entity.Rapport;
import io.github.axel1.gsbgestionvisites.entity.Visiteur;
import io.github.axel1.gsbgestionvisites.service.MyUserPrincipal;
import io.github.axel1.gsbgestionvisites.service.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AccueilController {

    public final RapportService rapportService;

    @Autowired
    public AccueilController(RapportService rapportService) {
        this.rapportService = rapportService;
    }

    @GetMapping(path = "")
    public String index(Model model, Authentication authentication) {
        // Retrieve the object of the logged in Visiteur
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Visiteur visiteur = myUserPrincipal.getVisiteur();

        List<Rapport> lastRapports = rapportService.findTop10ByVisiteurOrderByDesc(visiteur);

        model.addAttribute("rapports", lastRapports);
        model.addAttribute("nomVisiteur", visiteur.getNom());
        model.addAttribute("prenomVisiteur", visiteur.getPrenom());
        model.addAttribute("title", "Accueil");
        return "index";
    }

    @GetMapping(path = "/login")
    public String login(@RequestParam(name = "error", required = false) String error, Model model) {
        // Set the "error" attribute to true if the request parameter "error" is present
        if (error != null) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }

        model.addAttribute("title", "Connexion");
        return "login";
    }
}
