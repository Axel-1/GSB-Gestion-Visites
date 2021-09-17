package io.github.axel1.gsbgestionvisites.repository;

import io.github.axel1.gsbgestionvisites.entity.Offrir;
import io.github.axel1.gsbgestionvisites.entity.OffrirPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffrirRepository extends JpaRepository<Offrir, OffrirPK> {
}
