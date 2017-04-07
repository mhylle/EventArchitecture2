package info.mhylle.playground.lpr3.model.SKS;

// enums cannot be numbers, so all enums in sor is prepended with an A
public enum SorCode
{
  OPT_NS_THR_LGRYM("7039"),
  OPT_FEIS("7040"),
  AROS("7042"),
  AAR_KIR_CLI("7043"),
  ODD_OPT_LHANS("7046"),
  A662037("Fælles AKUT Afdeling Overafd."),
  A6620371("Fælles AKUT Afdeling"),
  A6620372("Hospitalsvisitation"),
  A6620377("Fælles Akut Klinik"),
  A6620378("Skadestuen Fælles AKUT Afdeling"),
  A6620379("Center for Voldtægtsofre"),
  A662044("Rygvisitation Overafd."),
  A6620440("Rygvisitation"),
  A662004("Billeddiagnostisk Overafd. Skejby"),
  A6620040("Billeddiagnostisk afdeling BDA SKS"),
  A6620042("MR-Centret SKS"),
  A662006("Fysioterapi- og Ergoterapi Overafd."),
  A6620066("Fysioterapi- og Ergoterapiafdelingen, NBG"),
  A6620067("Fysioterapi- og Ergoterapiafdelingen, THG"),
  A6620069("Fysioterapi- og Ergoterapiafdelingen, SKS");

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
