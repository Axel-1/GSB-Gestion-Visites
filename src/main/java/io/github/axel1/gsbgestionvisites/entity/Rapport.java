package io.github.axel1.gsbgestionvisites.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String motif;
    private String bilan;

    @ManyToOne
    private Visiteur visiteur;
    @ManyToOne
    private Medecin medecin;

    @OneToMany(mappedBy = "rapport", fetch = FetchType.EAGER)
    private Set<Offrir> rapportOffrir;

    public Rapport() {
    }

    public Rapport(Long id, LocalDate date, String motif, String bilan, Visiteur visiteur, Medecin medecin) {
        this.id = id;
        this.date = date;
        this.motif = motif;
        this.bilan = bilan;
        this.visiteur = visiteur;
        this.medecin = medecin;
    }

    public Rapport(Long id, LocalDate date, String motif, String bilan, Visiteur visiteur, Medecin medecin, Set<Offrir> rapportOffrir) {
        this.id = id;
        this.date = date;
        this.motif = motif;
        this.bilan = bilan;
        this.visiteur = visiteur;
        this.medecin = medecin;
        this.rapportOffrir = rapportOffrir;
    }

    public void addRapportOffrir(Offrir offrir) {
        offrir.setRapport(this);
        rapportOffrir.add(offrir);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public Visiteur getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Set<Offrir> getRapportOffrir() {
        return rapportOffrir;
    }

    public void setRapportOffrir(Set<Offrir> rapportOffrir) {
        this.rapportOffrir = rapportOffrir;
    }

    @Override
    public String toString() {
        return "Rapport{" +
                "id=" + id +
                ", date=" + date +
                ", motif='" + motif + '\'' +
                ", bilan='" + bilan + '\'' +
                ", visiteur=" + visiteur +
                ", medecin=" + medecin +
                ", rapportOffrir=" + rapportOffrir +
                '}';
    }
}
