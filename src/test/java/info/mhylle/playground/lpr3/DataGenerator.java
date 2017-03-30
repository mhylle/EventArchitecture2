package info.mhylle.playground.lpr3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.mhylle.playground.lpr3.model.*;
import info.mhylle.playground.lpr3.model.SKS.ReasonSksCode;
import info.mhylle.playground.lpr3.model.SKS.SksCode;
import info.mhylle.playground.lpr3.model.SKS.SorCode;
import info.mhylle.playground.lpr3.model.SKS.condition.VerificationStatusCode;
import info.mhylle.playground.lpr3.model.SKS.encounter.EncounterClass;
import info.mhylle.playground.lpr3.model.SKS.patient.GenderType;
import info.mhylle.playground.lpr3.model.SKS.referral.StatusCode;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
  private static final int NR_OF_PATIENTS = 30;
  private static final int NR_OF_EPISODES_OF_CARE = 4;
  private static final int NR_OF_REFERRALS = 6;
  private static final int NR_OF_ENCOUNTERS = 8;
  private static final String ENCOUNTERS_SAVEFILE = "c:/temp/EventArchitecture/encounters.json";
  private static final String EPISODEOFCAREELEMENTS_SAVEFILE = "c:/temp/EventArchitecture/episodeOfCareElements.json";
  private static final String PATIENTS_SAVEFILE = "c:/temp/EventArchitecture/patients.json";
  private static final String REFERRALS_SAVEFILE = "c:/temp/EventArchitecture/referrals.json";
  private List<String> firstnames = new ArrayList<>();
  private List<String> lastnames = new ArrayList<>();
  private List<SksCode> labels = new ArrayList<>();
  private List<SorCode> responsibleUnits = new ArrayList<>();
  private List<Patient> patients;
  private List<Encounter> encounters;
  private List<Referral> referrals;
  private List<EpisodeOfCareElement> episodeOfCareElements;

  @Before
  public void populateLists() {
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
  public void generatePatients() {
    loadPatients();
    Random random = new Random();
    for (int i = 0; i < NR_OF_PATIENTS; i++) {
      Patient p = new Patient();
      String firstName = firstnames.get(new Random().nextInt(firstnames.size()));
      String lastName = lastnames.get(new Random().nextInt(lastnames.size()));
      p.setName(firstName + " " + lastName);
      String alternativeId = "";
      for (int j = 0; j < 10; j++) {
        int nextInt = random.nextInt(10);
        if (j == 2 || j == 4) {
          if (nextInt == 0) {
            nextInt = 1;
          }
        }
        alternativeId += nextInt;
      }
      p.setAlternativeId(alternativeId);
      double percentage = random.nextDouble();
      if (percentage > 0.95) {
        p.setGender(GenderType.OTHER);
      } else if (percentage > 0.9) {
        p.setGender(GenderType.UNKNOWN);
      } else {
        if (random.nextDouble() > 0.5) {
          p.setGender(GenderType.FEMALE);
        } else {
          p.setGender(GenderType.MALE);
        }
      }

      p.setGender(GenderType.values()[random.nextInt(GenderType.values().length)]);

      LocalDateTime birthday = createDate(random, 2017, 85);
      p.setBirthday(birthday);
      patients.add(p);

//      savePatients();
    }

    savePatients();
  }

  @Test
  public void generateEpisodesOfCare() {
    loadPatients();
    loadEpisodesOfCare();
    Random random = new Random();
    for (int i = 0; i < NR_OF_EPISODES_OF_CARE; i++) {
      EpisodeOfCareElement eoce = new EpisodeOfCareElement();
      SorCode responsibleUnit = responsibleUnits.get(random.nextInt(responsibleUnits.size()));
      eoce.setResponsibleUnit(responsibleUnit);

      eoce.setStatus(info.mhylle.playground.lpr3.model.SKS.episodeofcareelement.StatusCode.values()[random.nextInt(info.mhylle.playground.lpr3.model.SKS.episodeofcareelement.StatusCode.values().length)]);
      if (patients != null && patients.size() > 0) {
        Patient patient = patients.get(random.nextInt(patients.size()));
        eoce.setPatient(patient);
      }

      LocalDateTime startTime = createDate(random, 2017, 5);
      Period period = new Period();
      period.setStartTime(startTime);
      switch (eoce.getStatus()) {
        case FINISHED:
        case CANCELLED:
          // Fall through - if we are done we need an end time.
          LocalDateTime endTime = createDate(random, 2017, 1);
          period.setEndTime(endTime);
          flipTime(period);
          break;
        default:
          break;
      }
      eoce.setPeriod(period);

      Condition condition = new Condition();
      condition.setVerificationStatus(VerificationStatusCode.values()[random.nextInt(VerificationStatusCode.values().length)]);
      condition.setPatient(eoce.getPatient());
      Period conditionPeriod = new Period(startTime);
      condition.setPeriod(conditionPeriod);

      eoce.setCondition(condition);

      episodeOfCareElements.add(eoce);
    }

    saveEpisodesOfCareElements();
  }

  @Test
  public void generateReferrals() {
    loadPatients();
    loadReferrals();
    Random random = new Random();

    if (patients != null && patients.size() > 0) {
      Patient patient = patients.get(random.nextInt(patients.size()));
      LocalDateTime now = LocalDateTime.now();

      Referral headCancerStart = new Referral();
      headCancerStart.setPatient(patient);
      headCancerStart.setStatus(StatusCode.REQUESTED);
      headCancerStart.setReason(ReasonSksCode.HEAD_AND_CANCER_PACKAGE_START);
      LocalDateTime headCancerStartTime = now.minusDays(30);
      headCancerStart.setAuthoredOn(headCancerStartTime);

      referrals.add(headCancerStart);

      Referral headCancerElucidation = new Referral();
      headCancerElucidation.setPatient(patient);
      headCancerElucidation.setStatus(StatusCode.REQUESTED);
      headCancerElucidation.setReason(ReasonSksCode.HEAD_AND_CANCER_ELUCIDATION);
      LocalDateTime headCancerElucidationTime = now.minusDays(25);
      headCancerElucidation.setAuthoredOn(headCancerElucidationTime);

      referrals.add(headCancerElucidation);

      Referral headCancerDecision = new Referral();
      headCancerDecision.setPatient(patient);
      headCancerDecision.setStatus(StatusCode.REQUESTED);
      headCancerDecision.setReason(ReasonSksCode.HEAD_AND_CANCER_DECISION);
      LocalDateTime headCancerDecisionTime = now.minusDays(20);
      headCancerDecision.setAuthoredOn(headCancerDecisionTime);

      referrals.add(headCancerDecision);

      Referral headCancerTreatment = new Referral();
      headCancerTreatment.setPatient(patient);
      headCancerTreatment.setStatus(StatusCode.REQUESTED);
      headCancerTreatment.setReason(ReasonSksCode.HEAD_AND_CANCER_INITIAL_TREATMENT_START);
      LocalDateTime headCancerTreatmentTime = now.minusDays(15);
      headCancerTreatment.setAuthoredOn(headCancerTreatmentTime);

      referrals.add(headCancerTreatment);

      Referral headCancerFollowUp = new Referral();
      headCancerFollowUp.setPatient(patient);
      headCancerFollowUp.setStatus(StatusCode.REQUESTED);
      headCancerFollowUp.setReason(ReasonSksCode.HEAD_AND_CANCER_FOLLOWUP);
      LocalDateTime headCancerFollowUpTime = now.minusDays(10);
      headCancerFollowUp.setAuthoredOn(headCancerFollowUpTime);

      referrals.add(headCancerFollowUp);

      Referral headCancerEnd = new Referral();
      headCancerEnd.setPatient(patient);
      headCancerEnd.setStatus(StatusCode.REQUESTED);
      headCancerEnd.setReason(ReasonSksCode.HEAD_AND_CANCER_END);
      LocalDateTime headCancerEndTime = now.minusDays(5);
      headCancerEnd.setAuthoredOn(headCancerEndTime);

      referrals.add(headCancerEnd);
    }

//
//    for (int i = 0; i < NR_OF_REFERRALS; i++) {
//      Referral referral = new Referral();
//      if (random.nextDouble() < 0.8) {
//        referral.setStatus(StatusCode.REQUESTED);
//      } else {
//        referral.setStatus(info.mhylle.playground.lpr3.model.SKS.referral.StatusCode.values()[random.nextInt(info.mhylle.playground.lpr3.model.SKS.referral.StatusCode.values().length)]);
//      }
//
//      referral.setReason(ReasonSksCode.values()[random.nextInt(ReasonSksCode.values().length)]);
//
//      if (encounters != null && encounters.size() > 0) {
//        if (random.nextDouble() < 0.75) {
//          Encounter encounter = encounters.get(random.nextInt(encounters.size()));
//          referral.setEncounter(encounter);
//        }
//      }
//      referrals.add(referral);
//    }
    saveReferrals();
  }

  @Test
  public void generateStandardReferrals() {
    loadPatients();
    loadReferrals();
    Patient patient = patients.get(new Random().nextInt(patients.size()));
    Referral r1 = new Referral();
    Referral r2 = new Referral();
    Referral r3 = new Referral();
    Referral r4 = new Referral();
    Referral r5 = new Referral();
    Referral r6 = new Referral();
    r1.setPatient(patient);
    r1.setReason(ReasonSksCode.HEAD_AND_CANCER_PACKAGE_START);
    r1.setStatus(StatusCode.REQUESTED);
    r2.setPatient(patient);
    r2.setReason(ReasonSksCode.HEAD_AND_CANCER_ELUCIDATION);
    r2.setStatus(StatusCode.REQUESTED);
    r3.setPatient(patient);
    r3.setReason(ReasonSksCode.HEAD_AND_CANCER_DECISION);
    r3.setStatus(StatusCode.REQUESTED);
    r4.setPatient(patient);
    r4.setReason(ReasonSksCode.HEAD_AND_CANCER_INITIAL_TREATMENT_START);
    r4.setStatus(StatusCode.REQUESTED);
    r5.setPatient(patient);
    r5.setReason(ReasonSksCode.HEAD_AND_CANCER_FOLLOWUP);
    r5.setStatus(StatusCode.REQUESTED);
    r6.setPatient(patient);
    r6.setReason(ReasonSksCode.HEAD_AND_CANCER_END);
    r6.setStatus(StatusCode.REQUESTED);
    referrals.add(r1);
    referrals.add(r2);
    referrals.add(r3);
    referrals.add(r4);
    referrals.add(r5);
    referrals.add(r6);
    saveReferrals();
  }

  @Test
  public void generateEncounters() {
    loadPatients();
    loadEncounters();
    loadReferrals();

    Random random = new Random();
    for (int i = 0; i < NR_OF_ENCOUNTERS; i++) {
      Encounter encounter = new Encounter();
      encounter.setStatus(info.mhylle.playground.lpr3.model.SKS.encounter.StatusCode.values()[random.nextInt(info.mhylle.playground.lpr3.model.SKS.encounter.StatusCode.values().length)]);
      encounter.setEncounterClass(EncounterClass.values()[random.nextInt(EncounterClass.values().length)]);
      if (patients != null && patients.size() > 0) {
        if (random.nextDouble() < 0.75) {
          Patient patient = patients.get(random.nextInt(patients.size()));
          encounter.setPatient(patient);
        }
      }
      LocalDateTime startTime = createDate(random, 2017, 5);
      Period period = new Period();
      period.setStartTime(startTime);
      switch (encounter.getStatus()) {
        case FINISHED:
        case CANCELLED:
          // Fall through - if we are done we need an end time.
          LocalDateTime endTime = createDate(random, 2017, 2);
          period.setEndTime(endTime);
          flipTime(period);
          break;
        default:
          break;
      }

      encounter.setPeriod(period);
      encounters.add(encounter);
    }

    saveEncounters();
  }

  private void flipTime(Period period) {
    LocalDateTime startTime = period.getStartTime();
    LocalDateTime endTime = period.getEndTime();
    if (startTime.isBefore(endTime)) {
      period.setStartTime(startTime);
      period.setEndTime(endTime);
    } else {
      period.setStartTime(endTime);
      period.setEndTime(startTime);
    }
  }

  private LocalDateTime createDate(Random random, int beforeYear, int range) {
    int yearOffSet = random.nextInt(range);

    int year = beforeYear - yearOffSet;
    LocalDateTime dateTime = LocalDateTime.of(year, 1, 1, 0, 0, 0);
    dateTime = dateTime.plusMonths(random.nextInt(13));
    dateTime = dateTime.plusDays(random.nextInt(30));
    dateTime = dateTime.plusHours(random.nextInt(24));
    dateTime = dateTime.plusMinutes(random.nextInt(60));
    dateTime = dateTime.plusSeconds(random.nextInt(60));
    return dateTime;
  }

  private void savePatients() {
    Gson gson = new Gson();
    String jSONpatients = gson.toJson(this.patients);

    try (FileWriter file = new FileWriter(PATIENTS_SAVEFILE)) {
      file.write(jSONpatients);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveEpisodesOfCareElements() {
    Gson gson = new Gson();
    String jSONEpisodesOfCareElements = gson.toJson(this.episodeOfCareElements);

    try (FileWriter file = new FileWriter(EPISODEOFCAREELEMENTS_SAVEFILE)) {
      file.write(jSONEpisodesOfCareElements);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveReferrals() {
    Gson gson = new Gson();
    String jSONReferrals = gson.toJson(this.referrals);

    try (FileWriter file = new FileWriter(REFERRALS_SAVEFILE)) {
      file.write(jSONReferrals);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveEncounters() {
    Gson gson = new Gson();
    String jSONEncounters = gson.toJson(this.encounters);

    try (FileWriter file = new FileWriter(ENCOUNTERS_SAVEFILE)) {
      file.write(jSONEncounters);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadPatients() {
    Gson gson = new Gson();
    try {
      patients = gson.fromJson(new FileReader(new File(PATIENTS_SAVEFILE)), new TypeToken<List<Patient>>() {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (patients == null) {
      patients = new ArrayList<>();
    }
  }

  private void loadReferrals() {
    Gson gson = new Gson();
    try {
      referrals = gson.fromJson(new FileReader(new File(REFERRALS_SAVEFILE)), new TypeToken<List<Referral>>() {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (referrals == null) {
      referrals = new ArrayList<>();
    }
  }

  private void loadEncounters() {
    Gson gson = new Gson();
    try {
      encounters = gson.fromJson(new FileReader(new File(ENCOUNTERS_SAVEFILE)), new TypeToken<List<Encounter>>() {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (encounters == null) {
      encounters = new ArrayList<>();
    }
  }

  private void loadEpisodesOfCare() {
    Gson gson = new Gson();
    try {
      episodeOfCareElements = gson.fromJson(new FileReader(new File(EPISODEOFCAREELEMENTS_SAVEFILE)), new TypeToken<List<EpisodeOfCareElement>>() {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (episodeOfCareElements == null) {
      episodeOfCareElements = new ArrayList<>();
    }
  }
}
