package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.LocalDateTimeAdapter;
import info.mhylle.playground.lpr3.model.SKS.patient.GenderType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Patient {
  private UUID id;
  private String alternativeId;
  private String name;
  private GenderType gender;
  private LocalDateTime birthday;
  private List<EpisodeOfCareElement> episodeOfCareElements;
  private List<EpisodeOfCare> episodesOfCare;

  public Patient() {
    id = UUID.randomUUID();
    episodeOfCareElements = new ArrayList<>();
    episodesOfCare = new ArrayList<>();
  }

  @XmlElement(name = "Id")
  public UUID getId() {
    return id;
  }

  public void setId(String id) {
    this.id = UUID.fromString(id);
  }

  @XmlElement(name = "AlternativeId")
  public String getAlternativeId() {
    return alternativeId;
  }

  public void setAlternativeId(String alternativeId) {
    this.alternativeId = alternativeId;
  }

  @XmlElement(name = "Name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlElement(name = "Gender")
  public GenderType getGender() {
    return gender;
  }

  public void setGender(GenderType gender) {
    this.gender = gender;
  }

  @XmlElement(name = "Birthdate")
  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  public LocalDateTime getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDateTime birthday) {
    this.birthday = birthday;
  }

  public void addEpisodeOfCareElement(EpisodeOfCareElement id) {
    episodeOfCareElements.add(id);
  }

  @XmlElement(name = "EpisodesOfCareElements")
  public List<EpisodeOfCareElement> getEpisodesOfCareElements() {
    return episodeOfCareElements;
  }


  public void addEpisodeOfCare(EpisodeOfCare episodeOfCare) {
    episodesOfCare.add(episodeOfCare);
  }

  @XmlElement(name = "EpisodeOfCare")
  public List<EpisodeOfCare> getEpisodesOfCare() {
    return episodesOfCare;
  }
}
