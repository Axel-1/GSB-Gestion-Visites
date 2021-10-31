package io.github.axel1.gsbgestionvisites.entity;

import java.util.List;

public class RapportForm {
    private Rapport rapport;
    private Long medecinId;
    private List<OffrirForm> offrirForms;

    public RapportForm() {
    }

    public RapportForm(Rapport rapport, Long medecinId, List<OffrirForm> offrirForms) {
        this.rapport = rapport;
        this.medecinId = medecinId;
        this.offrirForms = offrirForms;
    }

    public Rapport getRapport() {
        return rapport;
    }

    public void setRapport(Rapport rapport) {
        this.rapport = rapport;
    }

    public Long getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(Long medecinId) {
        this.medecinId = medecinId;
    }

    public List<OffrirForm> getOffrirForms() {
        return offrirForms;
    }

    public void setOffrirForms(List<OffrirForm> offrirForms) {
        this.offrirForms = offrirForms;
    }

    @Override
    public String toString() {
        return "RapportForm{" +
                "rapport=" + rapport +
                ", medecinId=" + medecinId +
                ", offrirForms=" + offrirForms +
                '}';
    }
}
