package info.mhylle.playground.lpr3.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.mhylle.playground.lpr3.model.*;
import info.mhylle.playground.lpr3.rules.RuleEngine;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repository
{
  private static final String ENCOUNTERS_SAVEFILE = "c:/temp/EventArchitecture/encounters.json";
  private static final String EPISODEOFCAREELEMENTS_SAVEFILE = "c:/temp/EventArchitecture/episodeOfCareElements.json";
  private static final String EPISODESOFCARE_SAVEFILE = "c:/temp/EventArchitecture/episodesOfCare.json";
  private static final String PATIENTS_SAVEFILE = "c:/temp/EventArchitecture/patients.json";
  private static final String REFERRALS_SAVEFILE = "c:/temp/EventArchitecture/referrals.json";
  private static final String CONDITIONS_SAVEFILE = "c:/temp/EventArchitecture/conditions.json";
  private static final String PROCEDURES_SAVEFILE = "c:/temp/EventArchitecture/procedures.json";
  private static Repository _instance;
  private List<Patient> patients;
  private List<Encounter> encounters;
  private List<Condition> conditions;
  private List<EpisodeOfCareElement> episodeOfCareElements;
  private List<Referral> referrals;
  private List<EpisodeOfCare> episodesOfCare;
  private List<EpisodeOfCare> episodeOfCare;
  private List<Procedure> procedures;

  private Repository()
  {
    loadData();
  }

  public static Repository getInstance()
  {
    if (_instance == null) {
      _instance = new Repository();
    }

    return _instance;
  }

  private void loadData()
  {
    patients = new ArrayList<>();
    encounters = new ArrayList<>();
    episodeOfCare = new ArrayList<>();
    episodeOfCareElements = new ArrayList<>();
    episodesOfCare = new ArrayList<>();
    referrals = new ArrayList<>();
    conditions = new ArrayList<>();
    procedures = new ArrayList<>();

    File patientSaveFile = new File(PATIENTS_SAVEFILE);
    File episodesOfCareSaveFile = new File(EPISODEOFCAREELEMENTS_SAVEFILE);
    File episodeOfCareElementsSaveFile = new File(EPISODEOFCAREELEMENTS_SAVEFILE);
    File contactsSaveFile = new File(ENCOUNTERS_SAVEFILE);
    File referralsSaveFile = new File(REFERRALS_SAVEFILE);
    File conditionsSaveFile = new File(CONDITIONS_SAVEFILE);
    File proceduresSaveFile = new File(PROCEDURES_SAVEFILE);

    if (patientSaveFile.exists()) {
      loadPatients();
    }
    if (episodesOfCareSaveFile.exists()) {
      loadEpisodesOfCare();
    }
    if (episodeOfCareElementsSaveFile.exists()) {
      loadEpisodeOfCareElements();
    }
    if (contactsSaveFile.exists()) {
      loadEncounters();
    }
    if (contactsSaveFile.exists()) {
      loadEncounters();
    }
    if (referralsSaveFile.exists()) {
      loadReferrals();
    }
    if (conditionsSaveFile.exists()) {
      loadConditions();
    }
    if (proceduresSaveFile.exists()) {
      loadProcedures();
    }
  }

  private void loadPatients()
  {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(new File(PATIENTS_SAVEFILE))) {
      patients = gson.fromJson(reader, new TypeToken<List<Patient>>()
      {
      }.getType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadEpisodeOfCareElements()
  {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(new File(EPISODEOFCAREELEMENTS_SAVEFILE))) {
      episodeOfCareElements = gson.fromJson(reader, new TypeToken<List<EpisodeOfCareElement>>()
      {
      }.getType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadEpisodesOfCare()
  {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(new File(EPISODESOFCARE_SAVEFILE))) {
      episodesOfCare = gson.fromJson(reader, new TypeToken<List<EpisodeOfCare>>()
      {
      }.getType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadEncounters()
  {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(new File(ENCOUNTERS_SAVEFILE))) {
      encounters = gson.fromJson(reader, new TypeToken<List<Encounter>>()
      {
      }.getType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadReferrals()
  {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(new File(REFERRALS_SAVEFILE))) {
      referrals = gson.fromJson(reader, new TypeToken<List<Referral>>()
      {
      }.getType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadConditions()
  {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(new File(CONDITIONS_SAVEFILE))) {
      conditions = gson.fromJson(reader, new TypeToken<List<Condition>>()
      {
      }.getType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadProcedures()
  {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(new File(PROCEDURES_SAVEFILE))) {
      procedures = gson.fromJson(reader, new TypeToken<List<Procedure>>()
      {
      }.getType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void savePatients()
  {
    File saveFile = new File(PATIENTS_SAVEFILE);
    saveData(patients, saveFile);
  }

  private void saveReferrals()
  {
    File saveFile = new File(REFERRALS_SAVEFILE);
    saveData(referrals, saveFile);
  }

  private void saveEpisodeOfCareElements()
  {
    File saveFile = new File(EPISODEOFCAREELEMENTS_SAVEFILE);
    saveData(episodeOfCareElements, saveFile);
  }
  private void saveEpisodesOfCare()
  {
    File saveFile = new File(EPISODESOFCARE_SAVEFILE);
    saveData(episodesOfCare, saveFile);
  }

  private void saveContacts()
  {
    File saveFile = new File(ENCOUNTERS_SAVEFILE);
    saveData(encounters, saveFile);
  }

  private void saveConditions()
  {
    File saveFile = new File(CONDITIONS_SAVEFILE);
    saveData(conditions, saveFile);
  }
  private void saveProcedures()
  {
    File saveFile = new File(PROCEDURES_SAVEFILE);
    saveData(procedures, saveFile);
  }

  private <T> void saveData(List<T> dataList, File saveFile)
  {
    Gson gson = new Gson();
    String jSONdata = gson.toJson(dataList);

    try (FileWriter file = new FileWriter(saveFile)) {
      file.write(jSONdata);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void addPatient(Patient patient)
  {
    patients.add(patient);
    savePatients();
  }

  public List<Patient> getPatients()
  {
    return patients;
  }

  public void addEncounter(Encounter contact)
  {
    encounters.add(contact);
    saveContacts();
  }

  public List<Encounter> getEncounters()
  {
    return encounters;
  }

  public void addCondition(Condition condition)
  {
    conditions.add(condition);
    saveConditions();
  }

  public List<Condition> getConditions()
  {
    return conditions;
  }

  public void addEpisodeOfCareElement(EpisodeOfCareElement episodeOfCareElement)
  {
    episodeOfCareElements.add(episodeOfCareElement);
    saveEpisodeOfCareElements();
  }

  public List<EpisodeOfCareElement> getEpisodeOfCareElements()
  {
    System.out.println("returning episodes of care elements, I have " + episodeOfCareElements.size() + " elements");
    return episodeOfCareElements;
  }

  public List<EpisodeOfCare> getEpisodesOfCare() {
    return episodesOfCare;
  }

  public void addEpisodeOfCare(EpisodeOfCare episodeOfCare) {
    this.episodesOfCare.add(episodeOfCare);
    saveEpisodesOfCare();
  }

  public List<Referral> getReferrals()
  {
    return referrals;
  }

  public void addReferral(Referral referral)
  {
    this.referrals.add(referral);
  }

  public void updatePatient(Patient p)
  {
    int i = patients.indexOf(p);
    patients.set(i, p);
    savePatients();
  }

  public void updateReferral(Referral referral)
  {
    int i = referrals.indexOf(referral);
    referrals.set(i, referral);
    saveReferrals();
  }

  public void updateEpisodeOfCareElement(EpisodeOfCareElement eoce)
  {
    int i = episodeOfCareElements.indexOf(eoce);
    episodeOfCareElements.set(i, eoce);
    saveEpisodeOfCareElements();
  }

  public void updateConditions(Condition condition)
  {
    int i = conditions.indexOf(condition);
    conditions.set(i, condition);
    saveConditions();
  }

  public List<Procedure> getProcedures()
  {
    return procedures;
  }
  public void addProcedure(Procedure procedure)
  {
    procedures.add(procedure);
    saveProcedures();
  }
}
