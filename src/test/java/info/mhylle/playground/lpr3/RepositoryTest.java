package info.mhylle.playground.lpr3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.mhylle.playground.lpr3.model.old.EpisodeOfCareElement;
import info.mhylle.playground.lpr3.model.old.Patient;
import info.mhylle.playground.lpr3.model.SKS.SksCode;
import info.mhylle.playground.lpr3.model.SKS.SorCode;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by mnh on 01-02-2017.
 */
public class RepositoryTest
{
  private static final String PATIENTS_SAVEFILE = "c:/temp/EventArchitecture/patients.json";
  private EpisodeOfCareElement e1;

  private List<Patient> patients;

  @Before
  public void createDummyData()
  {
    patients = new ArrayList<>();
    e1 = new EpisodeOfCareElement();
    e1.setResponsibleUnit(SorCode.AROS);
    e1.setEpisodeOfCareLabel(SksCode.LABEL_CANCER);
    e1.setStartTime(LocalDateTime.now());

    Patient p1 = new Patient();
    p1.setAlternativeId("2512484916");
    p1.setName("Nancy Berggren");
    p1.addEpisodeOfCareElement(e1);
    patients.add(p1);
    Patient p2 = new Patient();
    p2.setAlternativeId("0302700397");
    p2.setName("Martin Hylleberg");
    p2.addEpisodeOfCareElement(e1);
    patients.add(p2);
  }

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
  public void testLoadExistingDataFile() {
    List<Patient> patientList = loadObjects(new File(PATIENTS_SAVEFILE), patients);
    assertNotNull(patientList);

    loadPatients();
    assertNotNull(patients);
    assertTrue(patients.size() > 0);
    assertTrue(patients.size() > 0);
  }

  private void loadPatients() {
    Gson gson = new Gson();
    try {
      patients = gson.fromJson(new FileReader(new File(PATIENTS_SAVEFILE)), new TypeToken<List<Patient>>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
  private <T> List<T> loadObjects(File saveFile, List data)
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

  @Test
  public void testUpdateDataFile() {
    Gson gson = new Gson();

    String jSONpatients = gson.toJson(this.patients);

    try (FileWriter file = new FileWriter(PATIENTS_SAVEFILE)) {
      file.write(jSONpatients);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Patient p = patients.get(0);

    Patient p1 = new Patient();
    p1.setId(p.getId());
    p1.setName(p.getName());
    p1.setAlternativeId(p.getAlternativeId());

    for (EpisodeOfCareElement e : p.getEpisodeOfCareElements()) {
      p1.addEpisodeOfCareElement(e);
    }

    EpisodeOfCareElement e1 = new EpisodeOfCareElement();
    e1.setResponsibleUnit(SorCode.AAR_KIR_CLI);
    e1.setEpisodeOfCareLabel(SksCode.LABEL_KOL);
    e1.setStartTime(LocalDateTime.now());
    p1.addEpisodeOfCareElement(e1);
    int i = patients.indexOf(p);
    patients.set(i, p1);
    jSONpatients = gson.toJson(patients);

    try (FileWriter file = new FileWriter(PATIENTS_SAVEFILE)) {
      file.write(jSONpatients);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      patients = gson.fromJson(new FileReader(PATIENTS_SAVEFILE), new TypeToken<List<Patient>>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    assertNotNull(patients);

  }
}
