package io.github.axel1.gsbgestionvisites.entity;

import javax.persistence.*;

@Entity
@Table
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String SpecialiteComplementaire;
    private String departement;

    public Medecin() {
    }

    public Medecin(Long id, String nom, String prenom, String adresse, String tel, String specialiteComplementaire, String departement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        SpecialiteComplementaire = specialiteComplementaire;
        this.departement = departement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSpecialiteComplementaire() {
        return SpecialiteComplementaire;
    }

    public void setSpecialiteComplementaire(String specialiteComplementaire) {
        SpecialiteComplementaire = specialiteComplementaire;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "Medecin{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", SpecialiteComplementaire='" + SpecialiteComplementaire + '\'' +
                ", departement='" + departement + '\'' +
                '}';
    }
}
