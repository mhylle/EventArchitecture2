package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.model.SKS.SksCode;
import info.mhylle.playground.lpr3.model.SKS.SorCode;

import java.time.LocalDateTime;
import java.util.UUID;

public class Residency implements DataElement
{
  private UUID id;
  private SorCode unit;
  private SksCode abcense;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public Residency()
  {
    id = UUID.randomUUID();
  }

  @Override public String getId()
  {
    return id.toString();
  }

  public SorCode getUnit()
  {
    return unit;
  }

  public void setUnit(SorCode unit)
  {
    this.unit = unit;
  }

  public SksCode getAbcense()
  {
    return abcense;
  }

  public void setAbcense(SksCode abcense)
  {
    this.abcense = abcense;
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

  @Override public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Residency residency = (Residency) o;

    return id.equals(residency.id);
  }

  @Override public int hashCode()
  {
    return id.hashCode();
  }
}
