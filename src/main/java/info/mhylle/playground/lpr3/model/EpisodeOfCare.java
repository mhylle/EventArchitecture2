package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.ConditionIdAdapter;
import info.mhylle.playground.lpr3.adaptors.PatientIdAdapter;
import info.mhylle.playground.lpr3.model.SKS.episodeofcare.StatusCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EpisodeOfCare {
  private UUID id;
  private Condition condition;
  private Period period;
  private StatusCode status;
  private List<EpisodeOfCareElement> episodeOfCareElements;

  public EpisodeOfCare() {
    id = UUID.randomUUID();
    episodeOfCareElements = new ArrayList<>();
  }

  @XmlElement(name = "Id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @XmlElement(name = "Condition")
  @XmlJavaTypeAdapter(ConditionIdAdapter.class)
  public Condition getCondition() {
    return condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }

  @XmlElement(name = "Period")
  public Period getPeriod() {
    return period;
  }

  public void setPeriod(Period period) {
    this.period = period;
  }

  @XmlElement(name = "Status")
  public StatusCode getStatus() {
    return status;
  }

  public void setStatus(StatusCode status) {
    this.status = status;
  }

  @XmlElement(name = "EpisodeOfCareElements")
  public List<EpisodeOfCareElement> getEpisodeOfCareElements() {
    return episodeOfCareElements;
  }

  public void addEpisodeOfCareElement(EpisodeOfCareElement episodeOfCareElement) {
    episodeOfCareElements.add(episodeOfCareElement);
  }
}
