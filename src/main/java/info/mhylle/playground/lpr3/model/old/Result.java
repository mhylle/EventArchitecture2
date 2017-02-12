package info.mhylle.playground.lpr3.model.old;

import info.mhylle.playground.lpr3.model.SKS.SksCode;

import java.time.LocalDateTime;
import java.util.UUID;

public class Result implements DataElement
{
  private UUID id;
  private SksCode type;
  private String value;
  private LocalDateTime startTime;

  public Result()
  {
    this.id = UUID.randomUUID();
  }

  public String getId()
  {
    return id.toString();
  }

  public SksCode getType()
  {
    return type;
  }

  public void setType(SksCode type)
  {
    this.type = type;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public LocalDateTime getStartTime()
  {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime)
  {
    this.startTime = startTime;
  }

  @Override public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Result result = (Result) o;

    return id.equals(result.id);
  }

  @Override public int hashCode()
  {
    return id.hashCode();
  }
}
