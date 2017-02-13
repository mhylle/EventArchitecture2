package info.mhylle.playground.lpr3.model.SKS.encounter;

/**
 * Created by mhyll on 11-02-2017.
 */
public enum Type
{
  DRAFT("Draft"),
  REQUESTED("Requested"),
  ACTIVE("Active"),
  CANCELLED("Cancelled"),
  ACCEPTED("Accepted"),
  REJECTED("Rejected"),
  COMPLETED("Completed");

  private String code;

  Type(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}
