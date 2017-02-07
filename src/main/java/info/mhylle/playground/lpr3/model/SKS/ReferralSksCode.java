package info.mhylle.playground.lpr3.model.SKS;

/**
 * Created by mnh on 25-01-2017.
 */
public enum ReferralSksCode
{
  ALDA00("Ingen henviser"),
  ALDA11("Akutordning"),
  ALDA20("Klinisk enhed"),
  ALDA30("Praktiserende læge"),
  ALDA31("Praktiserende special læge"),
  ALDA40("Nyfødt"),
  ALDA50("Udlandet"),
  ALDA90("Anden (UNS)");


  private String code;

  ReferralSksCode(String code)
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }
}