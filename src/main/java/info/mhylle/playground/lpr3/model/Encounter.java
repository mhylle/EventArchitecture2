package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.PatientIdAdapter;
import info.mhylle.playground.lpr3.model.SKS.encounter.EncounterClass;
import info.mhylle.playground.lpr3.model.SKS.encounter.StatusCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Encounter
{
  private UUID id;
  private StatusCode status;
  private EncounterClass encounterClass;
  private Period period;
  private Condition actionDiagnosis;
  private List<Condition> biDiagnoses;
  private List<Procedure> procedures;


  public Encounter()
  {
    id = UUID.randomUUID();
    biDiagnoses = new ArrayList<>();
    procedures = new ArrayList<>();
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

  @XmlElement(name = "Period")
  public Period getPeriod()
  {
    return period;
  }

  public void setPeriod(Period period)
  {
    this.period = period;
  }

  @XmlElement(name = "ActionDiagnosis")
  public Condition getActionDiagnosis()
  {
    return actionDiagnosis;
  }

  public void setActionDiagnosis(Condition actionDiagnosis)
  {
    this.actionDiagnosis = actionDiagnosis;
  }

  @XmlElement(name = "BiDiagnosis")
  public List<Condition> getBiDiagnoses()
  {
    return biDiagnoses;
  }

  public void addBiDiagnosis(Condition biDiagnosis)
  {
    this.biDiagnoses.add(biDiagnosis);
  }

  @XmlElement(name = "Procedures")
  public List<Procedure> getProcedures()
  {
    return procedures;
  }

  public void addProcedure(Procedure procedure)
  {
    procedures.add(procedure);
  }
}
