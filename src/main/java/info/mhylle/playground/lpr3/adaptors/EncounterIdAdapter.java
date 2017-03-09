package info.mhylle.playground.lpr3.adaptors;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Encounter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by mnh on 02-02-2017.
 */
public class EncounterIdAdapter extends XmlAdapter<String, Encounter>
{

  @Override
  public Encounter unmarshal(String id) throws Exception
  {
    try {
      return Repository.getInstance().getEncounters().stream()
          .filter(encounter -> encounter.getId().toString().equals(id))
          .findFirst().orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String marshal(Encounter encounter) throws Exception
  {
    if (encounter != null) {
      return encounter.getId().toString();
    }
    return null;
  }
}
