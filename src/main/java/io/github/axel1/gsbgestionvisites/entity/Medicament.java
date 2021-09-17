package io.github.axel1.gsbgestionvisites.entity;

import javax.persistence.*;

@Entity
@Table
public class Medicament {
    @Id
    private String id;
    private String nomCommercial;
    private String composition;
    private String effets;
    private String contreIndications;
    @ManyToOne
    private Famille famille;

    public Medicament() {
    }

    public Medicament(String id, String nomCommercial, String composition, String effets, String contreIndictations, Famille famille) {
        this.id = id;
        this.nomCommercial = nomCommercial;
        this.composition = composition;
        this.effets = effets;
        this.contreIndications = contreIndictations;
        this.famille = famille;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomCommercial() {
        return nomCommercial;
    }

    public void setNomCommercial(String nomCommercial) {
        this.nomCommercial = nomCommercial;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getEffets() {
        return effets;
    }

    public void setEffets(String effets) {
        this.effets = effets;
    }

    public String getContreIndications() {
        return contreIndications;
    }

    public void setContreIndications(String contreIndications) {
        this.contreIndications = contreIndications;
    }

    public Famille getFamille() {
        return famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id='" + id + '\'' +
                ", nomCommercial='" + nomCommercial + '\'' +
                ", composition='" + composition + '\'' +
                ", effets='" + effets + '\'' +
                ", contreIndications='" + contreIndications + '\'' +
                ", famille=" + famille +
                '}';
    }
}
