package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.model.SKS.ReasonSksCode;
import info.mhylle.playground.lpr3.model.SKS.referral.StatusCode;

import javax.xml.bind.annotation.XmlElement;
import java.util.UUID;

public class Referral
{
  private UUID id;
  private StatusCode status;
  private UUID patient;
  private ReasonSksCode reason;
  private UUID encounter;

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
  public UUID getPatient()
  {
    return patient;
  }

  public void setPatient(UUID patient)
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
  public UUID getEncounter()
  {
    return encounter;
  }

  public void setEncounter(UUID encounter)
  {
    this.encounter = encounter;
  }
}
