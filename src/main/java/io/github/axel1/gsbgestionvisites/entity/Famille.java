package io.github.axel1.gsbgestionvisites.entity;

import javax.persistence.*;

@Entity
@Table
public class Famille {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;

    public Famille() {
    }

    public Famille(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Famille{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
