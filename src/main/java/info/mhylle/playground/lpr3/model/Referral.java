package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.EncounterIdAdapter;
import info.mhylle.playground.lpr3.adaptors.PatientIdAdapter;
import info.mhylle.playground.lpr3.model.SKS.ReasonSksCode;
import info.mhylle.playground.lpr3.model.SKS.referral.StatusCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.UUID;

public class Referral
{
  private UUID id;
  private StatusCode status;
  private Patient patient;
  private Encounter encounter;
  private ReasonSksCode reason;

  public Referral()
  {
    id = UUID.randomUUID();
  }

  @XmlElement(name = "Id")
  public UUID getId()
  {
    return id;
  }

  public void setId(UUID id)
  {
    this.id = id;
  }

  @XmlElement(name = "Status")
  public StatusCode getStatus()
  {
    return status;
  }

  public void setStatus(StatusCode status)
  {
    this.status = status;
  }

  @XmlElement(name = "Patient")
  @XmlJavaTypeAdapter(PatientIdAdapter.class)
  public Patient getPatient()
  {
    return patient;
  }

  public void setPatient(Patient patient)
  {
    this.patient = patient;
  }

  @XmlElement(name = "Reason")
  public ReasonSksCode getReason()
  {
    return reason;
  }

  public void setReason(ReasonSksCode reason)
  {
    this.reason = reason;
  }

  @XmlElement(name = "Encounter")
  @XmlJavaTypeAdapter(EncounterIdAdapter.class)
  public Encounter getEncounter()
  {
    return encounter;
  }

  public void setEncounter(Encounter encounter)
  {
    this.encounter = encounter;
  }
}
