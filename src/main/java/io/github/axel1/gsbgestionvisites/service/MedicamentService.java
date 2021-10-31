package io.github.axel1.gsbgestionvisites.service;

import io.github.axel1.gsbgestionvisites.entity.Medicament;
import io.github.axel1.gsbgestionvisites.repository.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentService {
    private final MedicamentRepository medicamentRepository;

    @Autowired
    public MedicamentService(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;
    }

    public List<Medicament> getAllMedicament() {
        return medicamentRepository.findAll();
    }

    public Medicament getMedicamentById(String id) {
        return medicamentRepository.findById(id).get();
    }
}
