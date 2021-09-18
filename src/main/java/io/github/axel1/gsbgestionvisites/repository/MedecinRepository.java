package io.github.axel1.gsbgestionvisites.repository;

import io.github.axel1.gsbgestionvisites.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    List<Medecin> findByNomContaining(String nom);
}
