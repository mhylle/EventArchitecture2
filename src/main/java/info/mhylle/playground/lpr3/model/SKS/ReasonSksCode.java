package info.mhylle.playground.lpr3.model.SKS;

/**
 * Created by mnh on 25-01-2017.
 */
public enum ReasonSksCode
{
  HEAD_AND_CANCER_PACKAGE_START("AFB02A"),
  HEAD_AND_CANCER_ELUCIDATION("AFB02B"),
  HEAD_AND_CANCER_DECISION("AFB02C"),
  HEAD_AND_CANCER_INITIAL_TREATMENT_START("AFB02F"),
  HEAD_AND_CANCER_FOLLOWUP("AFB02P"),
  HEAD_AND_CANCER_END("AFB02X"),
  PANCREAS_CANCER_PACKAGE_START("AFB07A"),
  PANCREAS_CANCER_ELUCIDATION("AFB07B"),
  PANCREAS_CANCER_DECISION("AFB07C"),
  PANCREAS_CANCER_INITIAL_TREATMENT_START("AFB07F"),
  PANCREAS_CANCER_FOLLOWUP("AFB07P"),
  PANCREAS_CANCER_END("AFB07X"),

  AAF("Booking"),
  AAF1("Indlæggelse"),
  AAF11("Genindlæggelse"),
  AAF12("Indlæggelse i brugerstyret seng"),
  AAF13("Udskrivning fra brugerstyret seng"),
  AAF2("Ambulant"),
  AAF20("Forundersøgelse"),
  AAF21("Førstegangsbesøg"),
  AAF22("Ambulant besøg"),
  AAF23("Kontrolbesøg"),
  AAF3("Skadestuebesøg"),
  AAF4("Tilsyn"),
  AAF5("Telefonkontakt"),
  AAF6("Hjemmebesøg"),
  AAF7("Udebesøg"),
  AAF8("Andre 'booking/henvisning'"),
  AAF81("Planlagt fødsel"),
  AAF82("Virksomhedsbesøg"),
  AAF83("Institutionsbesøg"),
  AAF84("Stuegang"),
  AAF85("Henvisning uden tidsbestilling (drop in)"),
  AAF9("Administrativ kontakt");

  private String code;

  ReasonSksCode(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}