package info.mhylle.playground.lpr3.model.SKS;

public enum SorCode
{
  OPT_NS_THR_LGRYM("7039"),
  OPT_FEIS("7040"),
  AROS("7042"),
  AAR_KIR_CLI("7043"),
  ODD_OPT_LHANS("7046");

  private String code;

  SorCode(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}
