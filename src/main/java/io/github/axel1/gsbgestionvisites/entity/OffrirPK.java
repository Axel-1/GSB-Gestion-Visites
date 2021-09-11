package io.github.axel1.gsbgestionvisites.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OffrirPK implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long idMedicament;
    private Long idRapport;

    public OffrirPK() {
    }

    public OffrirPK(Long idMedicament, Long idRapport) {
        this.idMedicament = idMedicament;
        this.idRapport = idRapport;
    }

    public Long getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(Long medicament) {
        this.idMedicament = medicament;
    }

    public Long getIdRapport() {
        return idRapport;
    }

    public void setIdRapport(Long rapport) {
        this.idRapport = rapport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffrirPK offrirPK = (OffrirPK) o;
        return idMedicament.equals(offrirPK.idMedicament) && idRapport.equals(offrirPK.idRapport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMedicament, idRapport);
    }

    @Override
    public String toString() {
        return "OffrirPK{" +
                "medicament=" + idMedicament +
                ", rapport=" + idRapport +
                '}';
    }
}
