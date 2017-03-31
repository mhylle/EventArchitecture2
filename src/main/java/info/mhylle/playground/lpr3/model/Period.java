package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

public class Period
{
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public Period()
  {
    this(null, null);
  }

  public Period(LocalDateTime startTime)
  {
    this(startTime, null);
  }

  public Period(LocalDateTime startTime, LocalDateTime endTime)
  {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @XmlElement(name = "StartTime")
  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  public LocalDateTime getStartTime()
  {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime)
  {
    this.startTime = startTime;
  }

  @XmlElement(name = "EndTime")
  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  public LocalDateTime getEndTime()
  {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime)
  {
    this.endTime = endTime;
  }

  public int compareTo(Period p) {
    if (startTime == null) {
      return -1;
    }

    LocalDateTime p2s = p.startTime;

    if (p2s == null) {
      return 1;
    }

    return startTime.compareTo(p2s);
  }
}
