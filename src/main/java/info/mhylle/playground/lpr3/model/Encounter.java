package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.PatientIdAdapter;
import info.mhylle.playground.lpr3.model.SKS.encounter.EncounterClass;
import info.mhylle.playground.lpr3.model.SKS.encounter.StatusCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.UUID;

public class Encounter
{
  private UUID id;
  private StatusCode status;
  private EncounterClass encounterClass;
  private Patient patient;
  private Period period;

  public Encounter()
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

  @XmlElement(name = "EncounterClass")
  public EncounterClass getEncounterClass()
  {
    return encounterClass;
  }

  public void setEncounterClass(EncounterClass encounterClass)
  {
    this.encounterClass = encounterClass;
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

  @XmlElement(name = "Period")
  public Period getPeriod()
  {
    return period;
  }

  public void setPeriod(Period period)
  {
    this.period = period;
  }
}
