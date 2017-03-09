package info.mhylle.playground.lpr3.adaptors;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Patient;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by mnh on 02-02-2017.
 */
public class PatientIdAdapter extends XmlAdapter<String, Patient>
{

  @Override
  public Patient unmarshal(String id) throws Exception
  {
    try {
      return Repository.getInstance().getPatients().stream()
          .filter(patient -> patient.getId().toString().equals(id))
          .findFirst().orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String marshal(Patient patient) throws Exception
  {
    if (patient != null) {
      return patient.getId().toString();
    }
    return null;
  }
}
