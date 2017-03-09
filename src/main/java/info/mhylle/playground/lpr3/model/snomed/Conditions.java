package info.mhylle.playground.lpr3.model.snomed;

import java.util.HashMap;
import java.util.Map;

public class Conditions
{
  private Map<Integer, String> codes;


  public Conditions()
  {
    codes = new HashMap<>();
    codes.put(109006, "Anxiety disorder of childhood OR adolescence (disorder)");
    codes.put(122003, "Choroidal hemorrhage (disorder)");
    codes.put(127009, "Miscarriage with tear of cervix");
    codes.put(129007, "Homoiothermia (finding)");
    codes.put(134006, "Decreased hair growth (finding)");
    codes.put(140004, "Chronic pharyngitis (disorder)");
  }

  public Map<Integer, String> getCodes()
  {
    return codes;
  }
}
