package io.github.axel1.gsbgestionvisites.repository;

import io.github.axel1.gsbgestionvisites.entity.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportRepository extends JpaRepository<Rapport, Long> {
}
