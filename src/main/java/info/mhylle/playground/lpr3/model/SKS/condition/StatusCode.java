package info.mhylle.playground.lpr3.model.SKS.condition;

public enum StatusCode
{
  PLANNED("Planned"),
  WAITLIST("Waitlist"),
  ACTIVE("Active"),
  ONHOLD("On Hold"),
  FINISHED("Finished"),
  CANCELLED("Cancelled");
  private String code;

  StatusCode(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}
