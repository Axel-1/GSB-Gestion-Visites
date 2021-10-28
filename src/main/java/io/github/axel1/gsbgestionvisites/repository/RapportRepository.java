package io.github.axel1.gsbgestionvisites.repository;

import io.github.axel1.gsbgestionvisites.entity.Rapport;
import io.github.axel1.gsbgestionvisites.entity.Visiteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RapportRepository extends JpaRepository<Rapport, Long> {
    List<Rapport> findByVisiteur(Visiteur visiteur);

    Rapport findByVisiteurAndId(Visiteur visiteur, Long id);

    List<Rapport> findByVisiteurAndDate(Visiteur visiteur, LocalDate date);
}
