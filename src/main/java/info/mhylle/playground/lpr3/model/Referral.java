package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.LocalDateTimeAdapter;
import info.mhylle.playground.lpr3.model.SKS.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.UUID;

public class Referral implements DataElement
{
  private UUID id;
  private CauseSksCode cause;
  private ReferralSksCode type;
  private SorCode referringParty;
  private FreeChoiceSksCode ownChoice;
  private LocalDateTime referred_at;
  private Patient patient;

  public Referral()
  {
    id = UUID.randomUUID();
  }

  @XmlElement(name = "Id")
  public String getId()
  {
    return id.toString();
  }

  @XmlElement(name = "Patient")
  public Patient getPatient()
  {
    return patient;
  }

  public void setPatient(Patient patient)
  {
    this.patient = patient;
  }

  @XmlElement(name = "Cause")
  public CauseSksCode getCause()
  {
    return cause;
  }

  public void setCause(CauseSksCode cause)
  {
    this.cause = cause;
  }

  @XmlElement(name = "Type")
  public ReferralSksCode getType()
  {
    return type;
  }

  public void setType(ReferralSksCode type)
  {
    this.type = type;
  }

  @XmlElement(name = "ReferringParty")
  public SorCode getReferringParty()
  {
    return referringParty;
  }

  public void setReferringParty(SorCode referringParty)
  {
    this.referringParty = referringParty;
  }

  @XmlElement(name = "OwnChoice")
  public FreeChoiceSksCode getOwnChoise()
  {
    return ownChoice;
  }

  public void setOwnChoise(FreeChoiceSksCode ownChoise)
  {
    this.ownChoice = ownChoise;
  }

  @XmlElement(name = "ReferredAt")
  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  public LocalDateTime getReferredAt()
  {
    return referred_at;
  }

  public void setReferredAt(LocalDateTime time)
  {
    this.referred_at = time;
  }

  @Override public String toString()
  {
    return "Referral{" +
      "id=" + id +
      ", cause=" + cause +
      ", type=" + type +
      ", referringParty=" + referringParty +
      ", ownChoice=" + ownChoice +
      ", time=" + referred_at +
      '}';
  }

  @Override public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Referral referral= (Referral) o;

    return id.equals(referral.id);
  }

  @Override public int hashCode()
  {
    return id.hashCode();
  }
}
