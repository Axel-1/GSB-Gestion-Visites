package io.github.axel1.gsbgestionvisites.controller;

import io.github.axel1.gsbgestionvisites.entity.Medecin;
import io.github.axel1.gsbgestionvisites.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedecinController {
    private final MedecinRepository medecinRepository;

    @Autowired
    public MedecinController(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    @GetMapping(path = "/medecin")
    public String medecin(Model model) {
        Medecin medecin1 = medecinRepository.findById(5L).get();
        model.addAttribute("prenom", medecin1.getPrenom());
        return "medecin";
    }
}
