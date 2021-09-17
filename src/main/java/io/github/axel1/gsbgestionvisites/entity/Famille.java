package io.github.axel1.gsbgestionvisites.entity;

import javax.persistence.*;

@Entity
@Table
public class Famille {
    @Id
    private String id;
    private String libelle;

    public Famille() {
    }

    public Famille(String id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                "id='" + id + '\'' +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
