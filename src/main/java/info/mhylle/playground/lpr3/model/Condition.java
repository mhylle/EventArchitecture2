package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.EncounterIdAdapter;
import info.mhylle.playground.lpr3.adaptors.PatientIdAdapter;
import info.mhylle.playground.lpr3.model.SKS.condition.CategoryCode;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.UUID;

public class Condition
{
  private UUID id;
  @NotNull
  private Patient patient;
  private Encounter encounter;
  private Period period;
  private CategoryCode category;
  private Integer code;

  public Condition()
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

  @XmlElement(name = "Period")
  public Period getPeriod()
  {
    return period;
  }

  public void setPeriod(Period period)
  {
    this.period = period;
  }

  @XmlElement(name = "Category")
  public CategoryCode getCategory()
  {
    return category;
  }

  public void setCategory(CategoryCode category)
  {
    this.category = category;
  }

  @XmlElement(name = "Code")
  public Integer getCode()
  {
    return code;
  }

  public void setCode(Integer code)
  {
    this.code = code;
  }
}
