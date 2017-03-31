package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.*;
import info.mhylle.playground.lpr3.model.SKS.SorCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.UUID;

public class EpisodeOfCareElement
{
  private UUID id;
  private Referral referral;
  private Condition condition;
  private Patient patient;
  private SorCode responsibleUnit;
  private Period period;
  private EpisodeOfCare episodeOfCare;
  private EpisodeOfCareElement previous;


  public EpisodeOfCareElement()
  {
    id = UUID.randomUUID();
  }

  @XmlElement(name = "Id")
  public UUID getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = UUID.fromString(id);
  }

  @XmlElement(name = "ResponsibleUnit")
  public SorCode getResponsibleUnit()
  {
    return responsibleUnit;
  }

  public void setResponsibleUnit(SorCode responsibleUnit)
  {
    this.responsibleUnit = responsibleUnit;
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

  @XmlElement(name = "Referral")
  @XmlJavaTypeAdapter(ReferralIdAdapter.class)
  public Referral getReferral()
  {
    return referral;
  }

  public void setReferral(Referral referral)
  {
    this.referral = referral;
  }

  @XmlElement(name = "Condition")
  @XmlJavaTypeAdapter(ConditionIdAdapter.class)
  /**
   * Condition will end up being used as the EpisodeOfCareLabel
   */
  public Condition getCondition()
  {
    return condition;
  }

  public void setCondition(Condition condition)
  {
    this.condition = condition;
  }

  @XmlElement(name = "Previous")
  @XmlJavaTypeAdapter(EpisodeOfCareElementIdAdapter.class)
  public EpisodeOfCareElement getPrevious()
  {
    return previous;
  }

  public void setPrevious(EpisodeOfCareElement previous)
  {
    this.previous = previous;
  }

  @XmlElement(name = "EpisodeOfCare")
  @XmlJavaTypeAdapter(EpisodeOfCareIdAdapter.class)
  public EpisodeOfCare getEpisodeOfCare()
  {
    return episodeOfCare;
  }

  public void setEpisodeOfCare(EpisodeOfCare episodeOfCare)
  {
    this.episodeOfCare = episodeOfCare;
  }
}
