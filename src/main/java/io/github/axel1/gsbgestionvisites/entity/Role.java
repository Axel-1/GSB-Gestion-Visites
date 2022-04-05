package io.github.axel1.gsbgestionvisites.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<Visiteur> visiteurs;

    public Role() {
    }

    public Role(Long id, String name, Set<Visiteur> visiteurs) {
        this.id = id;
        this.name = name;
        this.visiteurs = visiteurs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Visiteur> getVisiteurs() {
        return visiteurs;
    }

    public void setVisiteurs(Set<Visiteur> visiteurs) {
        this.visiteurs = visiteurs;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", visiteurs=" + visiteurs +
                '}';
    }
}
