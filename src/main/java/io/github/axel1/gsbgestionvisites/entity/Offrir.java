package io.github.axel1.gsbgestionvisites.entity;

import javax.persistence.*;

@Entity
@Table
public class Offrir {
    @EmbeddedId
    private OffrirPK id = new OffrirPK();

    @ManyToOne
    @MapsId("idMedicament")
    private Medicament medicament;

    @ManyToOne
    @MapsId("idRapport")
    private Rapport rapport;

    @Column(name = "quantite")
    private Long quantite;

    public Offrir() {
    }

    public Offrir(Medicament medicament, Rapport rapport, Long quantite) {
        this.id.setIdMedicament(medicament.getId());
        this.id.setIdRapport(rapport.getId());
        this.medicament = medicament;
        this.rapport = rapport;
        this.quantite = quantite;
    }

    public OffrirPK getId() {
        return id;
    }

    public void setId(OffrirPK id) {
        this.id = id;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
        this.id.setIdMedicament(medicament.getId());
    }

    public Rapport getRapport() {
        return rapport;
    }

    public void setRapport(Rapport rapport) {
        this.rapport = rapport;
        this.id.setIdRapport(rapport.getId());
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Offrir{" +
                "medicament=" + medicament +
                ", quantite=" + quantite +
                '}';
    }
}
