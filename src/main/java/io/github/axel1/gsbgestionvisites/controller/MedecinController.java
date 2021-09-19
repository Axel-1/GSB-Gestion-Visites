package io.github.axel1.gsbgestionvisites.controller;

import io.github.axel1.gsbgestionvisites.entity.Medecin;
import io.github.axel1.gsbgestionvisites.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("isSearchEnabled", true);
        model.addAttribute("searchParam", "nom");
        model.addAttribute("medecins", medecinService.findMedecinByNom(nom));
        return "listMedecin";
    }

    @GetMapping("/{id}")
    public String getMedecinById(Model model, @PathVariable("id") Long id) {
        Medecin medecin = medecinService.getMedecinById(id);

        model.addAttribute("title", "Médecins");
        model.addAttribute("medecin", medecin);
        return "detailsMedecin";
    }

    @GetMapping("/{id}/edit")
    public String editMedecinById(Model model, @PathVariable("id") Long id) {
        Medecin medecin = medecinService.getMedecinById(id);

        model.addAttribute("title", "Médecins");
        model.addAttribute("medecin", medecin);
        return "formMedecin";
    }

    @PostMapping("/save")
    public String submitMedecin(@ModelAttribute Medecin medecin, Model model) {
        medecinService.saveMedecin(medecin);
        Long id = medecin.getId();
        return "redirect:" + id.toString();
    }
}
