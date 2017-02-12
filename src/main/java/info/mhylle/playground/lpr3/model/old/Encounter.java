package info.mhylle.playground.lpr3.model.old;

import info.mhylle.playground.lpr3.model.SKS.ReasonSksCode;
import info.mhylle.playground.lpr3.model.SKS.encounter.EncounterClass;
import info.mhylle.playground.lpr3.model.SKS.encounter.Priority;
import info.mhylle.playground.lpr3.model.SKS.encounter.Type;
import info.mhylle.playground.lpr3.model.SKS.encounter.StatusCode;

import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;
import java.util.UUID;

public class Encounter {
    private UUID id;
    private StatusCode status;
    private EncounterClass encounterClass;
    private Type type;
    private Priority priority;
    private UUID patient;
    private UUID episodeOfCareElement;
    private UUID referral;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReasonSksCode reason;
    private UUID location;

    public Encounter() {
        id = UUID.randomUUID();
    }

    @XmlElement(name="Id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    @XmlElement(name="Status")
    public StatusCode getStatus() {
        return status;
    }

    public void setStatus(StatusCode status) {
        this.status = status;
    }

    @XmlElement(name="EncounterClass")
    public EncounterClass getEncounterClass() {
        return encounterClass;
    }

    public void setEncounterClass(EncounterClass encounterClass) {
        this.encounterClass = encounterClass;
    }

    @XmlElement(name="Type")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @XmlElement(name="Priority")
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @XmlElement(name="Patient")
    public UUID getPatient() {
        return patient;
    }

    public void setPatient(UUID patient) {
        this.patient = patient;
    }
    @XmlElement(name="EpisodeOfCareElement")
    public UUID getEpisodeOfCareElement() {
        return episodeOfCareElement;
    }

    public void setEpisodeOfCareElement(UUID episodeOfCareElement) {
        this.episodeOfCareElement = episodeOfCareElement;
    }
    @XmlElement(name="Referral")
    public UUID getReferral() {
        return referral;
    }

    public void setReferral(UUID referral) {
        this.referral = referral;
    }

    @XmlElement(name="StartTime")
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @XmlElement(name="EndTime")
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @XmlElement(name="Reason")
    public ReasonSksCode getReason() {
        return reason;
    }

    public void setReason(ReasonSksCode reason) {
        this.reason = reason;
    }

    @XmlElement(name="Location")
    public UUID getLocation() {
        return location;
    }

    public void setLocation(UUID location) {
        this.location = location;
    }
}
