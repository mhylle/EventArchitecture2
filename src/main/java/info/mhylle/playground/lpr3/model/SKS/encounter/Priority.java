package info.mhylle.playground.lpr3.model.SKS.encounter;

public enum Priority
{
  IMM("Immediate"),
  EMG("Emergency"),
  URG("Urgent"),
  SURG("Semi-urgent"),
  NOURG("Non-urgent");
  private String code;

  Priority(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}
