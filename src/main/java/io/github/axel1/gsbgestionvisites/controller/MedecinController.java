package io.github.axel1.gsbgestionvisites.controller;

import io.github.axel1.gsbgestionvisites.entity.Medecin;
import io.github.axel1.gsbgestionvisites.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

        model.addAttribute("title", "Médecins / Détails");
        model.addAttribute("medecin", medecin);
        return "detailsMedecin";
    }

    @GetMapping("/{id}/edit")
    public String editMedecinById(Model model, @PathVariable("id") Long id) {
        Medecin medecin = medecinService.getMedecinById(id);

        model.addAttribute("title", "Médecins / Détails / Modifier");
        model.addAttribute("medecin", medecin);
        return "editMedecin";
    }

    @PostMapping("/save")
    public String submitMedecin(@Valid @ModelAttribute("medecin") Medecin medecin, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Médecins / Détails / Modifier");
            return "editMedecin";
        }

        medecinService.saveMedecin(medecin);
        Long id = medecin.getId();
        return "redirect:" + id.toString();
    }
}
