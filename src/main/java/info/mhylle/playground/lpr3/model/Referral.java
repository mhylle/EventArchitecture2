package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.LocalDateTimeAdapter;
import info.mhylle.playground.lpr3.adaptors.PatientIdAdapter;
import info.mhylle.playground.lpr3.model.SKS.ReasonSksCode;
import info.mhylle.playground.lpr3.model.SKS.referral.StatusCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.UUID;

public class Referral {
  private UUID id;
  private StatusCode status;
  private ReasonSksCode reason;
  private LocalDateTime authoredOn;

  public Referral() {
    id = UUID.randomUUID();
  }

  @XmlElement(name = "Id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @XmlElement(name = "Status")
  public StatusCode getStatus() {
    return status;
  }

  public void setStatus(StatusCode status) {
    this.status = status;
  }

  @XmlElement(name = "Reason")
  public ReasonSksCode getReason() {
    return reason;
  }

  public void setReason(ReasonSksCode reason) {
    this.reason = reason;
  }


  @XmlElement(name = "AuthoredOn")
  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  public LocalDateTime getAuthoredOn() {
    return authoredOn;
  }

  public void setAuthoredOn(LocalDateTime authoredOn) {
    this.authoredOn = authoredOn;
  }
}
