package info.mhylle.playground.lpr3.model.old;

import info.mhylle.playground.lpr3.model.SKS.SksCode;
import info.mhylle.playground.lpr3.model.SKS.SorCode;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class Procedure implements DataElement
{
  private UUID id;
  private SksCode[] code;
  private SksCode alternative;
  private SksCode actionSpecification;
  private SksCode contrastAgent;
  private SksCode personelCategory;
  private SksCode indication;
  private SorCode producer;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public Procedure()
  {
    id = UUID.randomUUID();
  }

  @Override public String getId()
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

  public LocalDateTime getStartTime()
  {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime)
  {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime()
  {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime)
  {
    this.endTime = endTime;
  }

  @Override public String toString()
  {
    return "Procedure{" +
      "code=" + Arrays.toString(code) +
      ", alternative=" + alternative +
      ", actionSpecification=" + actionSpecification +
      ", contrastAgent=" + contrastAgent +
      ", personelCategory=" + personelCategory +
      ", indication=" + indication +
      ", producer=" + producer +
      ", startTime=" + startTime +
      ", endTime=" + endTime +
      '}';
  }

  @Override public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Procedure procedure = (Procedure) o;

    return id.equals(procedure.id);
  }

  @Override public int hashCode()
  {
    return id.hashCode();
  }
}
