package io.github.axel1.gsbgestionvisites.controller;

import io.github.axel1.gsbgestionvisites.entity.*;
import io.github.axel1.gsbgestionvisites.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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
        // Retrieve the object of the logged in Visiteur
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
        // Retrieve the object of the logged in Visiteur
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Visiteur visiteur = myUserPrincipal.getVisiteur();

        Optional<Rapport> rapportOptional = rapportService.findRapportByVisiteurAndId(visiteur, id);

        if (rapportOptional.isPresent()) {
            Rapport rapport = rapportOptional.get();

            model.addAttribute("title", "Rapports / Détails");
            model.addAttribute("rapport", rapport);

            return "detailsRapport";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/edit")
    public String editRapportById(Model model, @PathVariable("id") Long id, Authentication authentication) {
        // Retrieve the object of the logged in Visiteur
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Visiteur visiteur = myUserPrincipal.getVisiteur();

        Optional<Rapport> rapportOptional = rapportService.findRapportByVisiteurAndId(visiteur, id);

        if (rapportOptional.isPresent()) {
            Rapport rapport = rapportOptional.get();
            RapportEditForm rapportEditForm = new RapportEditForm(rapport.getId(), rapport.getMotif(), rapport.getBilan());

            model.addAttribute("title", "Rapports / Détails / Modifier");
            model.addAttribute("rapportEditForm", rapportEditForm);

            return "editRapport";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public String saveRapport(Authentication authentication, @PathVariable("id") Long id, @Valid @ModelAttribute("rapportEditForm") RapportEditForm rapportEditForm, BindingResult bindingResult, Model model) {
        // Retrieve the object of the logged in Visiteur
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Visiteur visiteur = myUserPrincipal.getVisiteur();
        Optional<Rapport> rapportOptional = rapportService.findRapportByVisiteurAndId(visiteur, id);

        if (rapportOptional.isPresent()) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("title", "Rapports / Détails / Modifier");

                return "editRapport";
            } else {
                Rapport rapport = rapportOptional.get();
                rapportService.saveRapport(formMapperService.toRapport(rapportEditForm, rapport));
                return "redirect:" + id.toString();
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/new")
    public String createRapport(Model model) {
        // Create empty RapportForm
        RapportForm rapportForm = new RapportForm();
        // Create empty Rapport and add it to the RapportForm
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
        // Retrieve the object of the logged in Visiteur
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
            try {
                Rapport rapport = formMapperService.toRapport(rapportForm, visiteur);

                Rapport savedRapport = rapportService.saveRapport(rapport);

                List<Offrir> offrirs = formMapperService.toOffrirs(rapportForm, savedRapport);
                offrirService.saveOffrirs(offrirs);
                // Redirect to the newly created Rapport
                return "redirect:rapports/" + savedRapport.getId().toString();
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
