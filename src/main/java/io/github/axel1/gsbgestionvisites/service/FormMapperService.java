package io.github.axel1.gsbgestionvisites.service;

import io.github.axel1.gsbgestionvisites.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormMapperService {
    private final RapportService rapportService;
    private final OffrirService offrirService;
    private final MedecinService medecinService;
    private final MedicamentService medicamentService;

    @Autowired
    public FormMapperService(RapportService rapportService, OffrirService offrirService, MedecinService medecinService, MedicamentService medicamentService) {
        this.rapportService = rapportService;
        this.offrirService = offrirService;
        this.medecinService = medecinService;
        this.medicamentService = medicamentService;
    }

    public Rapport toRapport(RapportForm rapportForm, Visiteur visiteur) throws Exception {
        Rapport rapport = rapportForm.getRapport();
        Optional<Medecin> medecinOptional = medecinService.findMedecinById(rapportForm.getMedecinId());

        if (medecinOptional.isPresent()) {
            Medecin medecin = medecinOptional.get();
            rapport.setMedecin(medecin);
            rapport.setVisiteur(visiteur);
            return rapport;
        } else {
            throw new Exception();
        }
    }

    public Rapport toRapport(RapportEditForm rapportEditForm, Rapport rapport) {
        rapport.setMotif(rapportEditForm.getMotif());
        rapport.setBilan(rapportEditForm.getBilan());
        return rapport;
    }

    public List<Offrir> toOffrirs(RapportForm rapportForm, Rapport rapport) {
        List<Offrir> offrirs = new ArrayList<>();
        for (OffrirForm offrirForm : rapportForm.getOffrirForms()) {
            Optional<Medicament> medicamentOptional = medicamentService.findMedicamentById(offrirForm.getMedicamentId());

            if (medicamentOptional.isPresent()) {
                Medicament medicament = medicamentOptional.get();
                offrirs.add(new Offrir(medicament, rapport, offrirForm.getQuantite()));
            }
        }
        return offrirs;
    }
}
