package io.github.axel1.gsbgestionvisites.service;

import io.github.axel1.gsbgestionvisites.entity.Medecin;
import io.github.axel1.gsbgestionvisites.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedecinService {
    private final MedecinRepository medecinRepository;

    @Autowired
    public MedecinService(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    public List<Medecin> getAllMedecin() {
        return medecinRepository.findAll();
    }

    public List<Medecin> findMedecinByNom(String nom) {
        return medecinRepository.findByNomContaining(nom);
    }
}
