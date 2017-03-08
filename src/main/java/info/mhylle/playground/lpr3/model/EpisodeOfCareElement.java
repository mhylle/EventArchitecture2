package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.model.SKS.SorCode;
import info.mhylle.playground.lpr3.model.SKS.episodeofcare.StatusCode;

import javax.xml.bind.annotation.XmlElement;
import java.util.UUID;

public class EpisodeOfCareElement {
    private UUID id;
    private StatusCode status;
    private SorCode responsibleUnit;
    private UUID patient;
    private Period period;
    private UUID referral;
    private UUID condition;

    public EpisodeOfCareElement() {
        id = UUID.randomUUID();
    }

    @XmlElement(name = "Id")
    public UUID getId() {
        return id;
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    @XmlElement(name = "Status")
    public StatusCode getStatus() {
        return status;
    }

    public void setStatus(StatusCode status) {
        this.status = status;
    }

    @XmlElement(name = "ResponsibleUnit")
    public SorCode getResponsibleUnit() {
        return responsibleUnit;
    }

    public void setResponsibleUnit(SorCode responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }

    @XmlElement(name = "Patient")
    public UUID getPatient() {
        return patient;
    }

    public void setPatient(UUID patient) {
        this.patient = patient;
    }

    @XmlElement(name = "Period")
    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @XmlElement(name = "Referral")
    public UUID getReferral() {
        return referral;
    }

    public void setReferral(UUID referral) {
        this.referral = referral;
    }

    @XmlElement(name = "Condition")
    public UUID getCondition() {
        return condition;
    }

    public void setCondition(UUID condition) {
        this.condition = condition;
    }
}
