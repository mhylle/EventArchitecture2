package info.mhylle.playground.lpr3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.mhylle.playground.lpr3.model.Patient;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

public class RepositoryTest
{
  private static final String PATIENTS_SAVEFILE = "c:/temp/EventArchitecture/patients.json";

  private List<Patient> patients;


  @Test
  public void testSaveFile()
  {
    Gson gson = new Gson();
    String jSONpatients = gson.toJson(this.patients);

    try (FileWriter file = new FileWriter(PATIENTS_SAVEFILE)) {
      file.write(jSONpatients);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testLoadFile()
  {
    Gson gson = new Gson();
    try {
      patients = gson.fromJson(new FileReader(PATIENTS_SAVEFILE), new TypeToken<List<Patient>>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    assertNotNull(patients);
  }

  @Test
  public void testLoadExistingDataFile()
  {
    List patientList = loadObjects(new File(PATIENTS_SAVEFILE), patients);
    assertNotNull(patientList);

    loadPatients();
    assertNotNull(patients);
    assertTrue(patients.size() > 0);
    assertTrue(patients.size() > 0);
  }

  private void loadPatients()
  {
    Gson gson = new Gson();
    try {
      patients = gson.fromJson(new FileReader(new File(PATIENTS_SAVEFILE)), new TypeToken<List<Patient>>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private <T> List loadObjects(File saveFile, List data)
  {
    Gson gson = new Gson();
    try {
      data = gson.fromJson(new FileReader(saveFile), new TypeToken<T>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return data;
  }
}
