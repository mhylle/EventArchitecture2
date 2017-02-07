package info.mhylle.playground.lpr3.model.SKS;

/**
 * Created by mnh on 07-02-2017.
 */
public enum FreeChoiseSksCode
{
  ALDB00("Inget fritvalg"),
  ALDB01("frit sygehusvalg"),
  ALDB02("udvidet frit sygehusvalg");

  private String code;

  FreeChoiseSksCode(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}
