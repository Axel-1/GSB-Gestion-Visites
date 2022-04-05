package io.github.axel1.gsbgestionvisites.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Visiteur {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String login;
    private String mdp;
    private String adresse;
    private String cp;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "visiteurs_roles",
            joinColumns = @JoinColumn(
                    name = "visiteur_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
    private String ville;
    private LocalDate dateEmbauche;
    @OneToMany(mappedBy = "visiteur", fetch = FetchType.EAGER)
    private Set<Rapport> rapports;

    public Visiteur() {
    }

    public Visiteur(String id, String nom, String prenom, String login, String mdp, String adresse, String cp, Set<Role> roles, String ville, LocalDate dateEmbauche, Set<Rapport> rapports) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.adresse = adresse;
        this.cp = cp;
        this.roles = roles;
        this.ville = ville;
        this.dateEmbauche = dateEmbauche;
        this.rapports = rapports;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Set<Rapport> getRapports() {
        return rapports;
    }

    public void setRapports(Set<Rapport> rapports) {
        this.rapports = rapports;
    }

    @Override
    public String toString() {
        return "Visiteur{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", login='" + login + '\'' +
                ", mdp='" + mdp + '\'' +
                ", adresse='" + adresse + '\'' +
                ", cp='" + cp + '\'' +
                ", roles=" + roles +
                ", ville='" + ville + '\'' +
                ", dateEmbauche=" + dateEmbauche +
                ", rapports=" + rapports +
                '}';
    }
}
