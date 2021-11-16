package io.github.axel1.gsbgestionvisites.configuration;

import io.github.axel1.gsbgestionvisites.entity.Rapport;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MotifConfiguration {
    private List<String> motifs = new ArrayList<>();

    public MotifConfiguration() {
        motifs.add("Demande du médecin");
        motifs.add("Recommandation de confrère");
        motifs.add("Installation nouvelle");
        motifs.add("Visite annuelle");
        motifs.add("Prise de contact");
        motifs.add("Conseil d'un collègue");
        motifs.add("Nouveau médecin, prise de contact");
    }

    public List<String> getMotifs() {
        return motifs;
    }

    public boolean validate(Rapport rapport) {
        return this.motifs.contains(rapport.getMotif());
    }
}
