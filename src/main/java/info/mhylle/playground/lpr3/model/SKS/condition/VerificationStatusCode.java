package info.mhylle.playground.lpr3.model.SKS.condition;

public enum VerificationStatusCode
{
  PROVISIONAL("Provisional"),
  DIFFERENTIAL("Differential"),
  CONFIRMED("Confirmed"),
  REFUTED("Refuted"),
  ENTERED_IN_ERROR("Entered in error"),
  UNKNOWN("Unknown");
  private String code;

  VerificationStatusCode(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}
