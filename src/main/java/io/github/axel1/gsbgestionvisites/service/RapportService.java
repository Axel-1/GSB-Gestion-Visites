package io.github.axel1.gsbgestionvisites.service;

import io.github.axel1.gsbgestionvisites.repository.RapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RapportService {
    private final RapportRepository rapportRepository;

    @Autowired
    public RapportService(RapportRepository rapportRepository) {
        this.rapportRepository = rapportRepository;
    }
}
