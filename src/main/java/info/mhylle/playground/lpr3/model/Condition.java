package info.mhylle.playground.lpr3.model;

import info.mhylle.playground.lpr3.model.SKS.condition.CategoryCode;
import info.mhylle.playground.lpr3.model.SKS.condition.ConditionCode;
import info.mhylle.playground.lpr3.model.SKS.condition.VerificationStatusCode;

import javax.xml.bind.annotation.XmlElement;
import java.util.UUID;

public class Condition
{
  private UUID id;
  private Period period;
  private CategoryCode category;
  private ConditionCode code;
  private VerificationStatusCode verificationStatus;

  public Condition()
  {
    id = UUID.randomUUID();
  }

  @XmlElement(name = "Id")
  public UUID getId()
  {
    return id;
  }

  public void setId(UUID id)
  {
    this.id = id;
  }

  @XmlElement(name = "Period")
  public Period getPeriod()
  {
    return period;
  }

  public void setPeriod(Period period)
  {
    this.period = period;
  }

  @XmlElement(name = "Category")
  public CategoryCode getCategory()
  {
    return category;
  }

  public void setCategory(CategoryCode category)
  {
    this.category = category;
  }

  @XmlElement(name = "Code")
  public ConditionCode getCode()
  {
    return code;
  }

  public void setCode(ConditionCode code)
  {
    this.code = code;
  }

  @XmlElement(name = "VerificationStatus")
  public VerificationStatusCode getVerificationStatus() {
    return verificationStatus;
  }

  public void setVerificationStatus(VerificationStatusCode verificationStatus) {
    this.verificationStatus = verificationStatus;
  }
}
