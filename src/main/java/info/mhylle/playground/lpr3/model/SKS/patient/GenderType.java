package info.mhylle.playground.lpr3.model.SKS.patient;

/**
 * Created by mhyll on 11-02-2017.
 */
public enum GenderType
{
  MALE("Male"),
  FEMALE("Female"),
  OTHER("Other"),
  UNKNOWN("Unknown");

  private String code;

  GenderType(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}
