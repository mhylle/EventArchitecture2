package info.mhylle.playground.lpr3.model.SKS.procedure;

public enum StatusCode
{
  PREPARATION("preparation"),
  IN_PROGRESS("In progress"),
  SUSPENDED("Suspended"),
  ABORTED("Aborted"),
  COMPLETED("Completed"),
  ENTERED_IN_ERROR("Entered in error"),
  UNKNOWN("Unknown");
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
