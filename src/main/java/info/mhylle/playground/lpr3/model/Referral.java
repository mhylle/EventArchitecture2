package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.model.SKS.SksCode;
import info.mhylle.playground.lpr3.model.SKS.SorCode;

import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;
import java.util.UUID;

public class Referral implements DataElement
{
  private UUID id;
  private SksCode cause;
  private SksCode type;
  private SorCode referringParty;
  private SksCode ownChoise;
  private LocalDateTime time;

  public Referral()
  {
    id = UUID.randomUUID();
  }

  @XmlElement(name = "Id")
  public String getId()
  {
    return id.toString();
  }

  public SksCode getCause()
  {
    return cause;
  }

  public void setCause(SksCode cause)
  {
    this.cause = cause;
  }

  public SksCode getType()
  {
    return type;
  }

  public void setType(SksCode type)
  {
    this.type = type;
  }

  public SorCode getReferringParty()
  {
    return referringParty;
  }

  public void setReferringParty(SorCode referringParty)
  {
    this.referringParty = referringParty;
  }

  public SksCode getOwnChoise()
  {
    return ownChoise;
  }

  public void setOwnChoise(SksCode ownChoise)
  {
    this.ownChoise = ownChoise;
  }

  public LocalDateTime getTime()
  {
    return time;
  }

  public void setTime(LocalDateTime time)
  {
    this.time = time;
  }

  @Override public String toString()
  {
    return "Referral{" +
      "id=" + id +
      ", cause=" + cause +
      ", type=" + type +
      ", referringParty=" + referringParty +
      ", ownChoise=" + ownChoise +
      ", time=" + time +
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
