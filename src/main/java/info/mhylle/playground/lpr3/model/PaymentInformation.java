package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.model.SKS.SksCode;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by mnh on 25-01-2017.
 */
public class PaymentInformation implements DataElement
{
  private UUID id;
  private SksCode paymentAgreement;
  private String payer;
  private SksCode specializationLevel;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public PaymentInformation()
  {
    id = UUID.randomUUID();
  }

  @Override public String getId()
  {
    return id.toString();
  }

  public SksCode getPaymentAgreement()
  {
    return paymentAgreement;
  }

  public void setPaymentAgreement(SksCode paymentAgreement)
  {
    this.paymentAgreement = paymentAgreement;
  }

  public String getPayer()
  {
    return payer;
  }

  public void setPayer(String payer)
  {
    this.payer = payer;
  }

  public SksCode getSpecializationLevel()
  {
    return specializationLevel;
  }

  public void setSpecializationLevel(SksCode specializationLevel)
  {
    this.specializationLevel = specializationLevel;
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

    PaymentInformation paymentInformation = (PaymentInformation) o;

    return id.equals(paymentInformation.id);
  }

  @Override public int hashCode()
  {
    return id.hashCode();
  }
}
