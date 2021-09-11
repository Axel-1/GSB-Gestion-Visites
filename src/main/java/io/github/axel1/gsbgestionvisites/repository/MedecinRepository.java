package io.github.axel1.gsbgestionvisites.repository;

import io.github.axel1.gsbgestionvisites.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
}
