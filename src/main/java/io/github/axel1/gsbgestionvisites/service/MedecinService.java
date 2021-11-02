package io.github.axel1.gsbgestionvisites.service;

import io.github.axel1.gsbgestionvisites.entity.Medecin;
import io.github.axel1.gsbgestionvisites.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Medecin getMedecinById(Long id) {
        return medecinRepository.findById(id).get();
    }

    public Optional<Medecin> findMedecinById(Long id) {
        return medecinRepository.findById(id);
    }

    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }
}
