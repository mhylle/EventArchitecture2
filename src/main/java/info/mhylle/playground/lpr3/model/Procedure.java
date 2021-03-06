package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.model.SKS.SksCode;
import info.mhylle.playground.lpr3.model.SKS.SorCode;
import info.mhylle.playground.lpr3.model.SKS.condition.StatusCode;

import javax.xml.bind.annotation.XmlElement;
import java.util.Arrays;
import java.util.UUID;

public class Procedure
{
  private UUID id;
  private SksCode[] code;
  private SksCode alternative;
  private SksCode actionSpecification;
  private SksCode contrastAgent;
  private SksCode personelCategory;
  private SksCode indication;
  private SorCode producer;
  private Period period;
  private StatusCode status;

  public Procedure()
  {
    id = UUID.randomUUID();
  }

  public String getId()
  {
    return id.toString();
  }

  public SksCode[] getCode()
  {
    return code;
  }

  public void setCode(SksCode[] code)
  {
    this.code = code;
  }

  public SksCode getAlternative()
  {
    return alternative;
  }

  public void setAlternative(SksCode alternative)
  {
    this.alternative = alternative;
  }

  public SksCode getActionSpecification()
  {
    return actionSpecification;
  }

  public void setActionSpecification(SksCode actionSpecification)
  {
    this.actionSpecification = actionSpecification;
  }

  public SksCode getContrastAgent()
  {
    return contrastAgent;
  }

  public void setContrastAgent(SksCode contrastAgent)
  {
    this.contrastAgent = contrastAgent;
  }

  public SksCode getPersonelCategory()
  {
    return personelCategory;
  }

  public void setPersonelCategory(SksCode personelCategory)
  {
    this.personelCategory = personelCategory;
  }

  public SksCode getIndication()
  {
    return indication;
  }

  public void setIndication(SksCode indication)
  {
    this.indication = indication;
  }

  public SorCode getProducer()
  {
    return producer;
  }

  public void setProducer(SorCode producer)
  {
    this.producer = producer;
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

  @XmlElement(name = "Status")
  public StatusCode getStatus()
  {
    return status;
  }

  public void setStatus(StatusCode status)
  {
    this.status = status;
  }

  @Override
  public String toString()
  {
    return "Procedure{" +
        "code=" + Arrays.toString(code) +
        ", alternative=" + alternative +
        ", actionSpecification=" + actionSpecification +
        ", contrastAgent=" + contrastAgent +
        ", personelCategory=" + personelCategory +
        ", indication=" + indication +
        ", producer=" + producer +
        '}';
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Procedure procedure = (Procedure) o;

    return id.equals(procedure.id);
  }

  @Override
  public int hashCode()
  {
    return id.hashCode();
  }
}
