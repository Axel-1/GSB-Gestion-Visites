package io.github.axel1.gsbgestionvisites.entity;

import javax.validation.constraints.NotBlank;

public class RapportEditForm {
    private Long id;
    @NotBlank
    private String motif;
    @NotBlank
    private String bilan;

    public RapportEditForm() {
    }

    public RapportEditForm(Long id, String motif, String bilan) {
        this.id = id;
        this.motif = motif;
        this.bilan = bilan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    @Override
    public String toString() {
        return "RapportEditForm{" +
                "id=" + id +
                ", motif='" + motif + '\'' +
                ", bilan='" + bilan + '\'' +
                '}';
    }
}
