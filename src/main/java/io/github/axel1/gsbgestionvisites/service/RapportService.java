package io.github.axel1.gsbgestionvisites.service;

import io.github.axel1.gsbgestionvisites.entity.Rapport;
import io.github.axel1.gsbgestionvisites.entity.Visiteur;
import io.github.axel1.gsbgestionvisites.repository.RapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RapportService {
    private final RapportRepository rapportRepository;

    @Autowired
    public RapportService(RapportRepository rapportRepository) {
        this.rapportRepository = rapportRepository;
    }

    public Rapport getRapportByVisiteurAndId(Visiteur visiteur, Long id) {
        return rapportRepository.findByVisiteurAndId(visiteur, id);
    }

    public Rapport saveRapport(Rapport rapport) {
        return rapportRepository.save(rapport);
    }

    public List<Rapport> getRapportByVisiteur(Visiteur visiteur) {
        return rapportRepository.findByVisiteur(visiteur);
    }

    public List<Rapport> getRapportByVisiteurAndDate(Visiteur visiteur, LocalDate localDate) {
        return rapportRepository.findByVisiteurAndDate(visiteur, localDate);
    }
}
