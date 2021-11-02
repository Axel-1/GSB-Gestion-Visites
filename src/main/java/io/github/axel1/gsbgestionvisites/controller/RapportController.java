package io.github.axel1.gsbgestionvisites.controller;

import io.github.axel1.gsbgestionvisites.entity.*;
import io.github.axel1.gsbgestionvisites.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/rapports")
public class RapportController {
    private final RapportService rapportService;
    private final MedecinService medecinService;
    private final MedicamentService medicamentService;
    private final FormMapperService formMapperService;
    private final OffrirService offrirService;

    @Autowired
    public RapportController(RapportService rapportService, MedecinService medecinService, MedicamentService medicamentService, FormMapperService formMapperService, OffrirService offrirService) {
        this.rapportService = rapportService;
        this.medecinService = medecinService;
        this.medicamentService = medicamentService;
        this.formMapperService = formMapperService;
        this.offrirService = offrirService;
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

        model.addAttribute("title", "Rapports / Détails");
        model.addAttribute("rapport", rapport);

        return "detailsRapport";
    }

    @GetMapping("/{id}/edit")
    public String editRapportById(Model model, @PathVariable("id") Long id, Authentication authentication) {
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Visiteur visiteur = myUserPrincipal.getVisiteur();
        List<Medecin> medecinList = medecinService.getAllMedecin();

        Rapport rapport = rapportService.getRapportByVisiteurAndId(visiteur, id);

        model.addAttribute("title", "Rapports / Détails / Modifier");
        model.addAttribute("medecinList", medecinList);
        model.addAttribute("rapport", rapport);

        return "editRapport";
    }

    @PostMapping("/{id}")
    public String saveRapport(Authentication authentication, @ModelAttribute("rapport") Rapport rapportEdit, @PathVariable("id") Long id, Model model) {
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Visiteur visiteur = myUserPrincipal.getVisiteur();
        Optional<Rapport> rapportOptional = rapportService.findRapportByVisiteurAndId(visiteur, id);

        if (rapportOptional.isPresent()) {
            Rapport rapport = rapportOptional.get();
            rapport.setBilan(rapportEdit.getBilan());
            rapport.setMotif(rapportEdit.getMotif());
            rapportService.saveRapport(rapport);
        }

        return "redirect:" + id.toString();
    }

    @GetMapping("/new")
    public String createRapport(Model model) {
        RapportForm rapportForm = new RapportForm();
        rapportForm.setRapport(new Rapport());
        List<Medecin> medecinList = medecinService.getAllMedecin();
        List<Medicament> medicamentList = medicamentService.getAllMedicament();
        model.addAttribute("title", "Rapports / Nouveau");
        model.addAttribute("rapportForm", rapportForm);
        model.addAttribute("medecinList", medecinList);
        model.addAttribute("medicamentList", medicamentList);
        return "formRapport";
    }

    @PostMapping("")
    public String submitRapport(Authentication authentication, @Valid @ModelAttribute("rapportForm") RapportForm rapportForm, BindingResult bindingResult, Model model) {
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Visiteur visiteur = myUserPrincipal.getVisiteur();

        if (bindingResult.hasErrors()) {
            List<Medecin> medecinList = medecinService.getAllMedecin();
            List<Medicament> medicamentList = medicamentService.getAllMedicament();

            model.addAttribute("medecinList", medecinList);
            model.addAttribute("medicamentList", medicamentList);
            model.addAttribute("title", "Rapports / Nouveau");

            return "formRapport";
        } else {
            Rapport rapport = formMapperService.toRapport(rapportForm, visiteur);
            Rapport savedRapport = rapportService.saveRapport(rapport);

            List<Offrir> offrirs = formMapperService.toOffrirs(rapportForm, savedRapport);
            offrirService.saveOffrirs(offrirs);

            return "redirect:rapports/" + savedRapport.getId().toString();
        }
    }
}
