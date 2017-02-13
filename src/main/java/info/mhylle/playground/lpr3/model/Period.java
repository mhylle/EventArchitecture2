package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

/**
 * Created by mhyll on 12-02-2017.
 */
public class Period
{
  private LocalDateTime startTime;
  private LocalDateTime endTime;

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
}
