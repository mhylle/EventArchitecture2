package info.mhylle.playground.lpr3.adaptors;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Condition;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ConditionIdAdapter extends XmlAdapter<String, Condition>
{

  @Override
  public Condition unmarshal(String id) throws Exception
  {
    try {
      return Repository.getInstance().getConditions().stream()
          .filter(condition -> condition.getId().toString().equals(id))
          .findFirst().orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String marshal(Condition condition) throws Exception
  {
    if (condition != null) {
      return condition.getId().toString();
    }
    return null;
  }
}
