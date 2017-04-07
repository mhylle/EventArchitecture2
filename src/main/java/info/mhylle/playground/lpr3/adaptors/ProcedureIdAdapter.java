package info.mhylle.playground.lpr3.adaptors;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Procedure;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by mnh on 02-02-2017.
 */
public class ProcedureIdAdapter extends XmlAdapter<String, Procedure>
{

  @Override
  public Procedure unmarshal(String id) throws Exception
  {
    try {
      return Repository.getInstance().getProcedures().stream()
          .filter(procedure -> procedure.getId().toString().equals(id))
          .findFirst().orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String marshal(Procedure procedure) throws Exception
  {
    if (procedure != null) {
      return procedure.getId().toString();
    }
    return null;
  }
}
