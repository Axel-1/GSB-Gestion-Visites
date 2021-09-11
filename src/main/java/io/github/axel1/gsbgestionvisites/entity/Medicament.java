package io.github.axel1.gsbgestionvisites.entity;

import javax.persistence.*;

@Entity
@Table
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomCommercial;
    private String composition;
    private String effets;
    private String contreIndictations;
    @ManyToOne
    @JoinColumn(name = "id_famille")
    private Famille famille;

    public Medicament() {
    }

    public Medicament(Long id, String nomCommercial, String composition, String effets, String contreIndictations, Famille famille) {
        this.id = id;
        this.nomCommercial = nomCommercial;
        this.composition = composition;
        this.effets = effets;
        this.contreIndictations = contreIndictations;
        this.famille = famille;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getContreIndictations() {
        return contreIndictations;
    }

    public void setContreIndictations(String contreIndictations) {
        this.contreIndictations = contreIndictations;
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
                "id=" + id +
                ", nomCommercial='" + nomCommercial + '\'' +
                ", composition='" + composition + '\'' +
                ", effets='" + effets + '\'' +
                ", contreIndictations='" + contreIndictations + '\'' +
                ", famille=" + famille +
                '}';
    }
}
