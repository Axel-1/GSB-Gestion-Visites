package io.github.axel1.gsbgestionvisites.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OffrirForm {
    @NotBlank
    private String medicamentId;
    @NotNull
    private Long quantite;

    public OffrirForm() {
    }

    public OffrirForm(String medicamentId, Long quantite) {
        this.medicamentId = medicamentId;
        this.quantite = quantite;
    }

    public String getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(String medicamentId) {
        this.medicamentId = medicamentId;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "OffrirForm{" +
                "medicamentId='" + medicamentId + '\'' +
                ", quantite=" + quantite +
                '}';
    }
}
