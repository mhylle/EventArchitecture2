package info.mhylle.playground.lpr3.model.old;

import info.mhylle.playground.lpr3.adaptors.LocalDateTimeAdapter;
import info.mhylle.playground.lpr3.model.SKS.SksCode;
import info.mhylle.playground.lpr3.model.SKS.SorCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EpisodeOfCareElement implements DataElement
{
  private EpisodeOfCareElement previous;
  private SorCode responsibleUnit;
  private SksCode episodeOfCareLabel;

  private LocalDateTime startTime;

  private LocalDateTime endTime;
  private SksCode terminationType;

  private List<Contact> contacts;
  private List<Procedure> procedures;

  private List<EpisodeOfCareMarker> episodeOfCareMarkers;
  private Referral referral;
  private UUID id;

  public EpisodeOfCareElement()
  {
    id = UUID.randomUUID();
    contacts = new ArrayList<>();
    procedures = new ArrayList<>();
    episodeOfCareMarkers = new ArrayList<>();
  }


  public EpisodeOfCareElement getPrevious()
  {
    return previous;
  }

  public void setPrevious(EpisodeOfCareElement previous)
  {
    this.previous = previous;
  }

  public SorCode getResponsibleUnit()
  {
    return responsibleUnit;
  }

  public void setResponsibleUnit(SorCode responsibleUnit)
  {
    this.responsibleUnit = responsibleUnit;
  }

  public SksCode getEpisodeOfCareLabel()
  {
    return episodeOfCareLabel;
  }

  public void setEpisodeOfCareLabel(SksCode episodeOfCareLabel)
  {
    this.episodeOfCareLabel = episodeOfCareLabel;
  }

  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  public LocalDateTime getStartTime()
  {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime)
  {
    this.startTime = startTime;
  }

  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  public LocalDateTime getEndTime()
  {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime)
  {
    this.endTime = endTime;
  }

  public SksCode getTerminationType()
  {
    return terminationType;
  }

  public void setTerminationType(SksCode terminationType)
  {
    this.terminationType = terminationType;
  }

  public List<Contact> getContacts()
  {
    return contacts;
  }

  public void addContact(Contact contact)
  {
    this.contacts.add(contact);
  }

  public List<Procedure> getProcedures()
  {
    return procedures;
  }

  public void addProcedure(Procedure procedure)
  {
    this.procedures.add(procedure);
  }

  public List<EpisodeOfCareMarker> getEpisodeOfCareMarkers()
  {
    return episodeOfCareMarkers;
  }

  public void addEpisodeOfCareMarker(EpisodeOfCareMarker episodeOfCareMarker)
  {
    this.episodeOfCareMarkers.add(episodeOfCareMarker);
  }

  public Referral getReferral()
  {
    return referral;
  }

  public void setReferral(Referral referral)
  {
    this.referral = referral;
  }

  @Override public String toString()
  {
    String output = "EpisodeOfCareElement{" +
      "id=" + id +
      "previous=" + previous +
      ", responsibleUnit=" + responsibleUnit +
      ", episodeOfCareLabel=" + episodeOfCareLabel +
      ", startTime=" + startTime +
      ", endTime=" + endTime +
      ", terminationType=" + terminationType +
      ", referral=" + referral +
      '}';

    for (Contact contact : contacts) {
      output += "\n";
      output += contact;
    }

    for (Procedure procedure : procedures) {
      output += "\n";
      output += procedure;
    }

    for (EpisodeOfCareMarker episodeOfCareMarker : episodeOfCareMarkers) {
      output += "\n";
      output += episodeOfCareMarker;
    }
    return output;
  }

  @XmlElement(name = "Id")
  public String getId()
  {
    return id.toString();
  }

  @Override public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    EpisodeOfCareElement episodeOfCareElement = (EpisodeOfCareElement) o;

    return id.equals(episodeOfCareElement.id);
  }

  @Override public int hashCode()
  {
    return id.hashCode();
  }
}
