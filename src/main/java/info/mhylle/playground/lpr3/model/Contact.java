package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.adaptors.LocalDateTimeAdapter;
import info.mhylle.playground.lpr3.model.SKS.SksCode;
import info.mhylle.playground.lpr3.model.SKS.SorCode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Contact implements DataElement
{
  private UUID id;
  private SorCode responsibleUnit;
  private SksCode type;
  private SksCode priority;
  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  private LocalDateTime startTime;
  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  private LocalDateTime endTime;
  @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
  private LocalDateTime treatmentStart;

  private Referral referral;
  private List<Residency> residencyList;
  private Diagnose actionDiagnose;
  private Diagnose biDiagnose;
  private List<PaymentInformation> paymentInformations;
  private List<Procedure> procedures;

  public Contact()
  {
    id = UUID.randomUUID();
    residencyList = new ArrayList<>();
    paymentInformations = new ArrayList<>();
    procedures = new ArrayList<>();

  }

  @XmlElement(name = "Id")
  public String getId()
  {
    return id.toString();
  }

  public SorCode getResponsibleUnit()
  {
    return responsibleUnit;
  }

  public void setResponsibleUnit(SorCode responsibleUnit)
  {
    this.responsibleUnit = responsibleUnit;
  }

  public SksCode getType()
  {
    return type;
  }

  public void setType(SksCode type)
  {
    this.type = type;
  }

  public SksCode getPriority()
  {
    return priority;
  }

  public void setPriority(SksCode priority)
  {
    this.priority = priority;
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

  public LocalDateTime getTreatmentStart()
  {
    return treatmentStart;
  }

  public void setTreatmentStart(LocalDateTime treatmentStart)
  {
    this.treatmentStart = treatmentStart;
  }

  public Referral getReferral()
  {
    return referral;
  }

  public void setReferral(Referral referral)
  {
    this.referral = referral;
  }

  public List<Residency> getResidencyList()
  {
    return residencyList;
  }

  public void addResidency(Residency residency)
  {
    this.residencyList.add(residency);
  }

  public void removeResidency(Residency residency)
  {
    this.residencyList.remove(residency);
  }

  public Diagnose getActionDiagnose()
  {
    return actionDiagnose;
  }

  public void setActionDiagnose(Diagnose actionDiagnose)
  {
    this.actionDiagnose = actionDiagnose;
  }

  public Diagnose getBiDiagnose()
  {
    return biDiagnose;
  }

  public void setBiDiagnose(Diagnose biDiagnose)
  {
    this.biDiagnose = biDiagnose;
  }

  public List<PaymentInformation> getPaymentInformations()
  {
    return paymentInformations;
  }

  public void addPaymentInformation(PaymentInformation paymentInformation)
  {
    this.paymentInformations.add(paymentInformation);
  }

  public void removePaymentInformation(PaymentInformation paymentInformation)
  {
    this.paymentInformations.remove(paymentInformation);
  }

  public List<Procedure> getProcedures()
  {
    return procedures;
  }

  public void addProcedure(Procedure procedure)
  {
    this.procedures.add(procedure);
  }

  public void removeProcedure(Procedure procedure)
  {
    this.procedures.remove(procedure);
  }

  @Override public String toString()
  {
    return "Contact{" +
      "id=" + id +
      '}';
  }

  @Override public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contact contact = (Contact) o;

    return id.equals(contact.id);
  }

  @Override public int hashCode()
  {
    return id.hashCode();
  }
}
