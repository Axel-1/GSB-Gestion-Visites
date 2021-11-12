package io.github.axel1.gsbgestionvisites.controller;

import io.github.axel1.gsbgestionvisites.entity.Medecin;
import io.github.axel1.gsbgestionvisites.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

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
        Optional<Medecin> medecinOptional = medecinService.findMedecinById(id);

        if (medecinOptional.isPresent()) {
            Medecin medecin = medecinOptional.get();

            model.addAttribute("title", "Médecins / Détails");
            model.addAttribute("medecin", medecin);
            return "detailsMedecin";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/edit")
    public String editMedecinById(Model model, @PathVariable("id") Long id) {
        Optional<Medecin> medecinOptional = medecinService.findMedecinById(id);

        if (medecinOptional.isPresent()) {
            Medecin medecin = medecinOptional.get();

            model.addAttribute("title", "Médecins / Détails / Modifier");
            model.addAttribute("medecin", medecin);
            return "editMedecin";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public String saveMedecin(@PathVariable("id") Long id, @Valid @ModelAttribute("medecin") Medecin medecinEdit, BindingResult bindingResult, Model model) {
        Optional<Medecin> medecinOptional = medecinService.findMedecinById(id);

        if (medecinOptional.isPresent()) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("title", "Médecins / Détails / Modifier");

                return "editMedecin";
            } else {
                Medecin medecin = medecinOptional.get();
                medecinEdit.setId(medecin.getId());
                medecinService.saveMedecin(medecinEdit);

                return "redirect:" + medecinEdit.getId();
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
