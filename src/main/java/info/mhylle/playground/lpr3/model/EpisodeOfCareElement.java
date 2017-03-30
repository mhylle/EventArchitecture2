package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.ConditionIdAdapter;
import info.mhylle.playground.lpr3.adaptors.EpisodeOfCareElementIdAdapter;
import info.mhylle.playground.lpr3.adaptors.PatientIdAdapter;
import info.mhylle.playground.lpr3.adaptors.ReferralIdAdapter;
import info.mhylle.playground.lpr3.model.SKS.SorCode;
import info.mhylle.playground.lpr3.model.SKS.episodeofcareelement.StatusCode;

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
  public EpisodeOfCareElement getPrevious() {
    return previous;
  }

  public void setPrevious(EpisodeOfCareElement previous) {
    this.previous = previous;
  }
}
