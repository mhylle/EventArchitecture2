package info.mhylle.playground.lpr3.model.SKS.encounter;

public enum EncounterClass
{
  INPATIENT("Inpatient"),
  OUTPATIENT("Outpatient"),
  AMBULATORY("Ambulatory"),
  EMERGENCY("Emergency"),
  HOME("Home"),
  FIELD("Field"),
  DAYTIME("Daytime"),
  VIRTUAL("Virtual"),
  OTHER("Other");

  private String code;

  EncounterClass(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}
