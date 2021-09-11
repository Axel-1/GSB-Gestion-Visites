package io.github.axel1.gsbgestionvisites;

import io.github.axel1.gsbgestionvisites.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger LOG =
            LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
    private final FamilleRepository familleRepository;
    private final MedecinRepository medecinRepository;
    private final MedicamentRepository medicamentRepository;
    private final OffrirRepository offrirRepository;
    private final RapportRepository rapportRepository;
    private final VisiteurRepository visiteurRepository;

    @Autowired
    public CommandLineAppStartupRunner(FamilleRepository familleRepository, MedecinRepository medecinRepository, MedicamentRepository medicamentRepository, OffrirRepository offrirRepository, RapportRepository rapportRepository, VisiteurRepository visiteurRepository) {
        this.familleRepository = familleRepository;
        this.medecinRepository = medecinRepository;
        this.medicamentRepository = medicamentRepository;
        this.offrirRepository = offrirRepository;
        this.rapportRepository = rapportRepository;
        this.visiteurRepository = visiteurRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
