package info.mhylle.playground.lpr3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.mhylle.playground.lpr3.model.*;
import info.mhylle.playground.lpr3.model.SKS.*;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator
{
  private static final int NR_OF_PATIENTS = 30;
  private static final int NR_OF_EPISODES_OF_CARE = 4;
  private static final int NR_OF_REFERRALS = 6;
  private List<String> firstnames = new ArrayList<>();
  private List<String> lastnames = new ArrayList<>();
  private List<SksCode> labels = new ArrayList<>();
  private List<SorCode> responsibleUnits = new ArrayList<>();
  private static final String CONTACTS_SAVEFILE = "c:/temp/EventArchitecture/contacts.json";
  private static final String EPISODEOFCAREELEMENTS_SAVEFILE = "c:/temp/EventArchitecture/episodeOfCareElements.json";
  private static final String PATIENTS_SAVEFILE = "c:/temp/EventArchitecture/patients.json";
  private static final String REFERRALS_SAVEFILE = "c:/temp/EventArchitecture/referrals.json";
  private List<Patient> patients;
  private List<Contact> contacts;
  private List<Diagnose> diagnoses;
  private List<Referral> referrals;
  private List<EpisodeOfCareElement> episodeOfCareElements;

  @Before
  public void populateLists()
  {
    firstnames.add("Anders");
    firstnames.add("Bent");
    firstnames.add("Casper");
    firstnames.add("Dennis");
    firstnames.add("Erik");
    firstnames.add("Finn");
    firstnames.add("Gert");
    firstnames.add("Henrik");
    firstnames.add("Ib");
    firstnames.add("Jens");
    firstnames.add("Kim");
    firstnames.add("Lars");
    firstnames.add("Martin");

    lastnames.add("Andersen");
    lastnames.add("Bjerre");
    lastnames.add("Cavalier");
    lastnames.add("Delaurant");
    lastnames.add("Eriksen");
    lastnames.add("Frederiksen");
    lastnames.add("Gaardbo");
    lastnames.add("Haagh");
    lastnames.add("Immanuelsen");
    lastnames.add("Jensen");
    lastnames.add("Kragh");
    lastnames.add("Larsen");

    labels.add(SksCode.LABEL_CANCER);
    labels.add(SksCode.LABEL_DIABETES);
    labels.add(SksCode.LABEL_KOL);

    responsibleUnits.add(SorCode.AAR_KIR_CLI);
    responsibleUnits.add(SorCode.AROS);
    responsibleUnits.add(SorCode.ODD_OPT_LHANS);
    responsibleUnits.add(SorCode.OPT_FEIS);
    responsibleUnits.add(SorCode.OPT_NS_THR_LGRYM);
  }

  @Test
  public void generatePatients()
  {
    loadPatients();
    for (int i = 0; i < NR_OF_PATIENTS; i++) {
      Patient p = new Patient();
      String firstName = firstnames.get(new Random().nextInt(firstnames.size()));
      String lastName = lastnames.get(new Random().nextInt(lastnames.size()));
      p.setName(firstName + " " + lastName);
      String alternativeId = "";
      for (int j = 0; j < 10; j++) {
        int nextInt = new Random().nextInt(10);
        if (j == 2 || j == 4) {
          if (nextInt == 0) {
            nextInt = 1;
          }
        }
        alternativeId += nextInt;
      }
      p.setAlternativeId(alternativeId);
      patients.add(p);
    }

    savePatients();
  }

  @Test
  public void generateEpisodesOfCare()
  {
    loadEpisodesOfCare();
    Random random = new Random();
    for (int i = 0; i < NR_OF_EPISODES_OF_CARE; i++) {
      SksCode label = labels.get(random.nextInt(labels.size()));
      SorCode responsibleUnit = responsibleUnits.get(random.nextInt(responsibleUnits.size()));

      EpisodeOfCareElement eoce = new EpisodeOfCareElement();
      eoce.setEpisodeOfCareLabel(label);
      eoce.setResponsibleUnit(responsibleUnit);

      LocalDateTime startTime = createDate(random);
      LocalDateTime endTime = createDate(random);
      if (startTime.isBefore(endTime)) {
        eoce.setStartTime(startTime);
        eoce.setEndTime(endTime);
      } else {
        eoce.setStartTime(endTime);
        eoce.setEndTime(startTime);
      }

      episodeOfCareElements.add(eoce);
    }

    saveEpisodesOfCareElements();
  }

  @Test
  public void generateReferrals()
  {
    loadReferrals();

    Random random = new Random();
    for (int i = 0; i < NR_OF_REFERRALS; i++) {
      Referral referral = new Referral();
      referral.setType(ReferralSksCode.values()[random.nextInt(ReferralSksCode.values().length)]);
      referral.setReferringParty(SorCode.values()[random.nextInt(SorCode.values().length)]);
      referral.setCause(CauseSksCode.values()[random.nextInt(CauseSksCode.values().length)]);
      if (random.nextDouble() < 0.8) {
        referral.setOwnChoise(FreeChoiseSksCode.ALDB00);
      } else {
        if (random.nextDouble() < 0.5) {
          referral.setOwnChoise(FreeChoiseSksCode.ALDB01);
        } else {
          referral.setOwnChoise(FreeChoiseSksCode.ALDB02);
        }
      }
      LocalDateTime startTime = createDate(random);
      referral.seteferredAt(startTime);
      referrals.add(referral);
    }

    saveReferrals();
  }

  private LocalDateTime createDate(Random random)
  {
    int yearOffSet = random.nextInt(10) - 5;
    LocalDateTime firstDate = LocalDateTime.now().plusYears(yearOffSet);
    LocalDateTime monthsAdded = firstDate.plusMonths(random.nextInt(13));
    LocalDateTime daysAdded = monthsAdded.plusDays(random.nextInt(30));
    LocalDateTime hoursAdded = daysAdded.plusHours(random.nextInt(24));
    LocalDateTime minutesAdded = hoursAdded.plusMinutes(random.nextInt(60));
    return minutesAdded.plusSeconds(random.nextInt(60));
  }

  public void savePatients()
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

  public void saveEpisodesOfCareElements()
  {
    Gson gson = new Gson();
    String jSONEpisodesOfCareElements = gson.toJson(this.episodeOfCareElements);

    try (FileWriter file = new FileWriter(EPISODEOFCAREELEMENTS_SAVEFILE)) {
      file.write(jSONEpisodesOfCareElements);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void saveReferrals()
  {
    Gson gson = new Gson();
    String jSONReferrals= gson.toJson(this.referrals);

    try (FileWriter file = new FileWriter(REFERRALS_SAVEFILE)) {
      file.write(jSONReferrals);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
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
    if (patients == null) {
      patients = new ArrayList<>();
    }
  }

  private void loadReferrals()
  {
    Gson gson = new Gson();
    try {
      patients = gson.fromJson(new FileReader(new File(REFERRALS_SAVEFILE)), new TypeToken<List<Referral>>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (referrals == null) {
      referrals = new ArrayList<>();
    }
  }

  private void loadEpisodesOfCare()
  {
    Gson gson = new Gson();
    try {
      episodeOfCareElements = gson.fromJson(new FileReader(new File(EPISODEOFCAREELEMENTS_SAVEFILE)), new TypeToken<List<EpisodeOfCareElement>>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (episodeOfCareElements == null) {
      episodeOfCareElements = new ArrayList<>();
    }
  }
}
