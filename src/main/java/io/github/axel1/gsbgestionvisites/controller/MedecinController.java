package io.github.axel1.gsbgestionvisites.controller;

import io.github.axel1.gsbgestionvisites.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/medecins")
public class MedecinController {
    private final MedecinService medecinService;

    @Autowired
    public MedecinController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    @GetMapping(path = "")
    public String searchMedecin(Model model, @RequestParam(defaultValue = "") String nom) {
        model.addAttribute("title", "Médecins");
        model.addAttribute("search", "medecins");
        model.addAttribute("medecins", medecinService.getMedecinByNom(nom));
        return "listMedecin";
    }
}
