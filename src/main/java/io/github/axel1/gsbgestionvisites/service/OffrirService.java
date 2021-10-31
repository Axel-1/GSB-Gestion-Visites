package io.github.axel1.gsbgestionvisites.service;

import io.github.axel1.gsbgestionvisites.entity.Offrir;
import io.github.axel1.gsbgestionvisites.repository.OffrirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OffrirService {
    private final OffrirRepository offrirRepository;

    @Autowired
    public OffrirService(OffrirRepository offrirRepository) {
        this.offrirRepository = offrirRepository;
    }

    public Offrir saveOffrir(Offrir offrir) {
        return offrirRepository.save(offrir);
    }

    public List<Offrir> saveOffrirs(List<Offrir> offrirs) {
        List<Offrir> savedOffrirs = new ArrayList<>();

        for (Offrir offrir : offrirs) {
            savedOffrirs.add(saveOffrir(offrir));
        }

        return savedOffrirs;
    }
}
