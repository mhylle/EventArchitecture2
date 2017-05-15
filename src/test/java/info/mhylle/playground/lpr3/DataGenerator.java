package info.mhylle.playground.lpr3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.mhylle.playground.lpr3.model.*;
import info.mhylle.playground.lpr3.model.SKS.ReasonSksCode;
import info.mhylle.playground.lpr3.model.SKS.SksCode;
import info.mhylle.playground.lpr3.model.SKS.SorCode;
import info.mhylle.playground.lpr3.model.SKS.condition.ConditionCode;
import info.mhylle.playground.lpr3.model.SKS.encounter.EncounterClass;
import info.mhylle.playground.lpr3.model.SKS.patient.GenderType;
import info.mhylle.playground.lpr3.model.SKS.referral.StatusCode;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator
{
  private static final int NR_OF_PATIENTS = 30;
  //  private static final int NR_OF_EPISODES_OF_CARE = 4;
  //  private static final int NR_OF_REFERRALS = 6;
//  private static final int NR_OF_ENCOUNTERS = 8;
  private static final String ENCOUNTERS_SAVEFILE = "c:/temp/EventArchitecture/encounters.json";
  private static final String EPISODEOFCAREELEMENTS_SAVEFILE = "c:/temp/EventArchitecture/episodeOfCareElements.json";
  private static final String EPISODESOFCARE_SAVEFILE = "c:/temp/EventArchitecture/episodesOfCare.json";
  private static final String PATIENTS_SAVEFILE = "c:/temp/EventArchitecture/patients.json";
  private static final String REFERRALS_SAVEFILE = "c:/temp/EventArchitecture/referrals.json";
  private static final String PROCEDURES_SAVEFILE = "c:/temp/EventArchitecture/procedures.json";
  private static final String CONDITIONS_SAVEFILE = "c:/temp/EventArchitecture/conditions.json";
  private List<String> firstnames = new ArrayList<>();
  private List<String> lastnames = new ArrayList<>();
  //  private List<SorCode> responsibleUnits = new ArrayList<>();
  private List<Patient> patients;
  private List<Encounter> encounters;
  private List<Referral> referrals;
  private List<EpisodeOfCareElement> episodeOfCareElements;
  private List<EpisodeOfCare> episodesOfCare;
  private List<Procedure> procedures;
  private List<Condition> conditions;

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

//    responsibleUnits.add(SorCode.AAR_KIR_CLI);
//    responsibleUnits.add(SorCode.AROS);
//    responsibleUnits.add(SorCode.ODD_OPT_LHANS);
//    responsibleUnits.add(SorCode.OPT_FEIS);
//    responsibleUnits.add(SorCode.OPT_NS_THR_LGRYM);
  }


  @Test
  public void moveDatesToMoreRecent()
  {
    loadReferrals();
    loadEncounters();
    loadEpisodesOfCare();
    loadPatients();
    loadConditions();
    loadEpisodeOfCareElements();
    loadProcedures();


//    for (Encounter encounter : encounters) {
//      Period period = encounter.getPeriod();
//      moveTimePeriod(period);
//    }
//    saveEncounters();
//    for (Condition condition : conditions) {
//      Period period = condition.getPeriod();
//      moveTimePeriod(period);
//    }
//    saveConditions();
    for (EpisodeOfCareElement episodeOfCareElement : episodeOfCareElements) {
      Period period = episodeOfCareElement.getPeriod();
      moveTimePeriod(period);
    }
    saveEpisodesOfCareElements();
    for (Procedure procedure : procedures) {
      Period period = procedure.getPeriod();
      moveTimePeriod(period);
    }
    saveProcedures();


//    for (EpisodeOfCare episodeOfCare : episodesOfCare) {
//      Period period = episodeOfCare.getPeriod();
//      LocalDateTime startTime = period.getStartTime();
//      System.out.println("startTime before = " + startTime);
//      startTime = startTime.plusDays(30);
//      period.setStartTime(startTime);
//      if (period.getEndTime() != null) {
//        LocalDateTime endTime = period.getEndTime();
//        endTime = endTime.plusDays(30);
//        period.setEndTime(endTime);
//      }
//    }
//    saveEpisodesOfCare();
//    episodesOfCare = new ArrayList<>();
//    loadEpisodesOfCare();
//    for (EpisodeOfCare episodeOfCare : episodesOfCare) {
//      Period period = episodeOfCare.getPeriod();
//      LocalDateTime startTime = period.getStartTime();
//      System.out.println("startTime after = " + startTime);
//    }
  }

  @Test
  public void moveDate()
  {
    loadReferrals();
    loadEncounters();
    loadEpisodesOfCare();
    loadPatients();
    loadConditions();
    loadEpisodeOfCareElements();
    loadProcedures();


    movePatients();
    savePatients();

    moveEpisodesOfCare(episodesOfCare);
    saveEpisodesOfCare();

    for (Encounter encounter : encounters) {
      Period period = encounter.getPeriod();
      moveTimePeriod(period);
    }
    saveEncounters();
    for (Condition condition : conditions) {
      Period period = condition.getPeriod();
      moveTimePeriod(period);
    }
    saveConditions();

    moveEpisodeOfCareElements(episodeOfCareElements);
    saveEpisodesOfCareElements();
    for (Procedure procedure : procedures) {
      Period period = procedure.getPeriod();
      moveTimePeriod(period, -10);
    }
    saveProcedures();


  }

  private void movePatients()
  {
    for (Patient patient : patients) {
      List<EpisodeOfCare> episodesOfCare = patient.getEpisodesOfCare();
      moveEpisodesOfCare(episodesOfCare);
    }
  }

  private void moveEpisodesOfCare(List<EpisodeOfCare> episodesOfCare)
  {
    for (EpisodeOfCare episodeOfCare : episodesOfCare) {
      Period episodeOfCarePeriod = episodeOfCare.getPeriod();
      moveTimePeriod(episodeOfCarePeriod);
      List<EpisodeOfCareElement> episodeOfCareElements = episodeOfCare.getEpisodeOfCareElements();
      moveEpisodeOfCareElements(episodeOfCareElements);
      Condition episodeOfCareCondition = episodeOfCare.getCondition();
      Period episodeOfCareConditionPeriod = episodeOfCareCondition.getPeriod();
      moveTimePeriod(episodeOfCareConditionPeriod);
    }
  }

  private void moveEpisodeOfCareElements(List<EpisodeOfCareElement> episodeOfCareElements)
  {
    for (EpisodeOfCareElement episodeOfCareElement : episodeOfCareElements) {
      Period episodeOfCareElementPeriod = episodeOfCareElement.getPeriod();
      moveTimePeriod(episodeOfCareElementPeriod);
      Condition condition = episodeOfCareElement.getCondition();
      Period conditionPeriod = condition.getPeriod();
      moveTimePeriod(conditionPeriod);

      List<Encounter> encounters = episodeOfCareElement.getEncounters();
      moveEncounters(encounters);
      List<Procedure> procedures = episodeOfCareElement.getProcedures();
      for (Procedure procedure : procedures) {
        Period procedurePeriod = procedure.getPeriod();
        moveTimePeriod(procedurePeriod);
      }
    }
  }

  private void moveEncounters(List<Encounter> encounters)
  {
    for (Encounter encounter : encounters) {
      Period encounterPeriod = encounter.getPeriod();
      moveTimePeriod(encounterPeriod);
      Condition actionDiagnosis = encounter.getActionDiagnosis();
      Period actionDiagnosisPeriod = actionDiagnosis.getPeriod();
      moveTimePeriod(actionDiagnosisPeriod);
      List<Condition> biDiagnoses = encounter.getBiDiagnoses();
      for (Condition biDiagnosis : biDiagnoses) {
        Period biDiagnosisPeriod = biDiagnosis.getPeriod();
        moveTimePeriod(biDiagnosisPeriod);
      }
      List<Procedure> procedures = encounter.getProcedures();
      for (Procedure procedure : procedures) {
        Period procedurePeriod = procedure.getPeriod();
        moveTimePeriod(procedurePeriod);
      }
    }
  }

  private void moveTimePeriod(Period period)
  {
    if (period != null) {
      LocalDateTime startTime = period.getStartTime();
      System.out.println("startTime before = " + startTime);
      startTime = startTime.plusDays(30);
      period.setStartTime(startTime);
      if (period.getEndTime() != null) {
        LocalDateTime endTime = period.getEndTime();
        endTime = endTime.plusDays(30);
        period.setEndTime(endTime);
      }
    }
  }

  private void moveTimePeriod(Period period, int numberOfDays)
  {
    if (period != null) {
      LocalDateTime startTime = period.getStartTime();
      System.out.println("startTime before = " + startTime);
      startTime = startTime.plusDays(numberOfDays);
      period.setStartTime(startTime);
      if (period.getEndTime() != null) {
        LocalDateTime endTime = period.getEndTime();
        endTime = endTime.plusDays(numberOfDays);
        period.setEndTime(endTime);
      }
    }
  }

  @Test
  public void createPatientEpisodesOfCare()
  {
    Random random = new Random();
    Patient patient = generatePatient(random);

    EpisodeOfCare backPainEpisodeOfCare = generateBackPainEpisodeOfCare(patient);
    generateBackPainEpisodeOfCareElements(patient, backPainEpisodeOfCare);

    EpisodeOfCare cancerEpisodeOfCare = generateCancerEpisodeOfCare(patient);
    generateCancerEpisodeOfCareElements(patient, cancerEpisodeOfCare);


    savePatients();
    saveEpisodesOfCare();
    saveEpisodesOfCareElements();
    saveEncounters();
    saveReferrals();
    saveProcedures();
    saveConditions();
  }


  private void generateBackPainEpisodeOfCareElements(Patient patient, EpisodeOfCare episodeOfCare)
  {
    loadEpisodeOfCareElements();
    loadProcedures();
    loadReferrals();
    loadConditions();

    EpisodeOfCareElement initialEoce = new EpisodeOfCareElement();

    Condition neckOrBackPainCondition = new Condition();
    neckOrBackPainCondition.setCode(ConditionCode.DM540);
    initialEoce.setCondition(neckOrBackPainCondition);

    Referral initialReferral = new Referral();
    initialReferral.setReason(ReasonSksCode.AAF3);
    initialReferral.setStatus(StatusCode.ACCEPTED);
    initialEoce.setReferral(initialReferral);
    LocalDateTime start = episodeOfCare.getPeriod().getStartTime();
    LocalDateTime end = episodeOfCare.getPeriod().getStartTime().plusHours(2);
    initialEoce.setPeriod(new Period(start, end));
    initialEoce.setResponsibleUnit(SorCode.A662037);
    episodeOfCare.addEpisodeOfCareElement(initialEoce);
    referrals.add(initialReferral);
    episodeOfCareElements.add(initialEoce);
    conditions.add(neckOrBackPainCondition);

    LocalDateTime visitTime = createDate(end, end.plusDays(ThreadLocalRandom.current().nextInt(30)));
    for (int i = 0; i < 3; i++) {
      EpisodeOfCareElement episodeOfCareElement = new EpisodeOfCareElement();
      episodeOfCareElement.setCondition(neckOrBackPainCondition);

      Referral referral = new Referral();
      referral.setReason(ReasonSksCode.AAF3);
      referral.setStatus(StatusCode.ACCEPTED);
      episodeOfCareElement.setReferral(referral);
      episodeOfCareElement.setPeriod(new Period(visitTime, visitTime.plusHours(2)));
      visitTime = createDate(episodeOfCareElement.getPeriod().getEndTime(), visitTime.plusDays(ThreadLocalRandom.current().nextInt(5)));
      episodeOfCareElement.setResponsibleUnit(SorCode.A662037);
      episodeOfCare.addEpisodeOfCareElement(episodeOfCareElement);
      referrals.add(referral);
      episodeOfCareElements.add(episodeOfCareElement);
    }

    EpisodeOfCareElement backPainTreatmentEoce = new EpisodeOfCareElement();
    Condition neckPainCondition = new Condition();
    neckPainCondition.setCode(ConditionCode.DM540A);
    backPainTreatmentEoce.setCondition(neckPainCondition);
    Referral referral = new Referral();
    referral.setReason(ReasonSksCode.AAF1);
    referral.setStatus(StatusCode.ACCEPTED);
    backPainTreatmentEoce.setReferral(referral);
    backPainTreatmentEoce.setPeriod(new Period(visitTime, visitTime.plusHours(2)));
    createDate(backPainTreatmentEoce.getPeriod().getEndTime(), visitTime.plusDays(ThreadLocalRandom.current().nextInt(30) + 10));
    backPainTreatmentEoce.setResponsibleUnit(SorCode.A6620066);
    episodeOfCare.addEpisodeOfCareElement(backPainTreatmentEoce);
    conditions.add(neckPainCondition);

    Encounter admissionEncounter = createNeckPainAdmissionEncounter(backPainTreatmentEoce, neckPainCondition);

    LocalDateTime procedureTime = admissionEncounter.getPeriod().getStartTime().plusHours(2);
    for (int i = 0; i < 5; i++) {
      Procedure procedure = new Procedure();
      procedure.setActionSpecification(SksCode.OPERATION_OTHER);
      procedure.setPeriod(new Period(procedureTime, procedureTime.plusHours(4)));
      procedureTime = procedureTime.plusDays(1);
      procedure.setStatus(info.mhylle.playground.lpr3.model.SKS.condition.StatusCode.FINISHED);
      admissionEncounter.addProcedure(procedure);
      procedures.add(procedure);
    }

    episodeOfCareElements.add(backPainTreatmentEoce);

  }

  private EpisodeOfCare generateBackPainEpisodeOfCare(Patient patient)
  {
    loadEpisodesOfCare();
    loadConditions();
    EpisodeOfCare episodeOfCare = new EpisodeOfCare();
    Condition condition = new Condition();
    condition.setCode(ConditionCode.DM54);
    episodeOfCare.setCondition(condition);
    Period period = new Period();
    period.setStartTime(createDate(LocalDateTime.now().minusDays(30), LocalDateTime.now()));
    episodeOfCare.setPeriod(period);
    episodeOfCare.setStatus(info.mhylle.playground.lpr3.model.SKS.episodeofcare.StatusCode.ACTIVE);
    episodesOfCare.add(episodeOfCare);
    conditions.add(condition);
    patient.addEpisodeOfCare(episodeOfCare);
    return episodeOfCare;
  }

  private void generateCancerEpisodeOfCareElements(Patient patient, EpisodeOfCare episodeOfCare)
  {
    loadEpisodeOfCareElements();
    loadProcedures();
    loadReferrals();
    loadConditions();

    EpisodeOfCareElement initialEoce = new EpisodeOfCareElement();

    Condition ischiasCondition = new Condition();
    ischiasCondition.setCode(ConditionCode.DM543);
    initialEoce.setCondition(ischiasCondition);

    Referral initialReferral = new Referral();
    initialReferral.setReason(ReasonSksCode.AAF2);
    initialReferral.setStatus(StatusCode.ACCEPTED);
    initialEoce.setReferral(initialReferral);
    LocalDateTime start = episodeOfCare.getPeriod().getStartTime();
    LocalDateTime end = episodeOfCare.getPeriod().getStartTime().plusHours(2);
    initialEoce.setPeriod(new Period(start, end));
    initialEoce.setResponsibleUnit(SorCode.A6620440);
    episodeOfCare.addEpisodeOfCareElement(initialEoce);
    referrals.add(initialReferral);
    episodeOfCareElements.add(initialEoce);
    conditions.add(ischiasCondition);

    LocalDateTime visitTime = createDate(end, end.plusDays(ThreadLocalRandom.current().nextInt(30)));
    for (int i = 0; i < 2; i++) {
      EpisodeOfCareElement episodeOfCareElement = new EpisodeOfCareElement();
      episodeOfCareElement.setCondition(ischiasCondition);

      Referral referral = new Referral();
      referral.setReason(ReasonSksCode.AAF2);
      referral.setStatus(StatusCode.ACCEPTED);
      episodeOfCareElement.setReferral(referral);
      episodeOfCareElement.setPeriod(new Period(visitTime, visitTime.plusHours(2)));
      visitTime = createDate(episodeOfCareElement.getPeriod().getEndTime(), visitTime.plusDays(ThreadLocalRandom.current().nextInt(10)));
      episodeOfCareElement.setResponsibleUnit(SorCode.A6620440);
      episodeOfCare.addEpisodeOfCareElement(episodeOfCareElement);
      referrals.add(referral);
      episodeOfCareElements.add(episodeOfCareElement);
    }

    EpisodeOfCareElement loinIschiasEoce = new EpisodeOfCareElement();
    Condition loinIschiasCondition = new Condition();
    loinIschiasCondition.setCode(ConditionCode.DM544);
    loinIschiasEoce.setCondition(loinIschiasCondition);
    Referral referral = new Referral();
    referral.setReason(ReasonSksCode.AAF22);
    referral.setStatus(StatusCode.ACCEPTED);
    loinIschiasEoce.setReferral(referral);
    loinIschiasEoce.setPeriod(new Period(visitTime, visitTime.plusHours(2)));
    loinIschiasEoce.setResponsibleUnit(SorCode.A6620066);
    episodeOfCare.addEpisodeOfCareElement(loinIschiasEoce);
    conditions.add(loinIschiasCondition);

    Encounter admissionEncounter = createNeckPainAdmissionEncounter(loinIschiasEoce, loinIschiasCondition);

    LocalDateTime procedureTime = admissionEncounter.getPeriod().getStartTime().plusHours(2);
    for (int i = 0; i < 5; i++) {
      Procedure procedure = new Procedure();
      procedure.setActionSpecification(SksCode.OPERATION_NERVESYSTEM);
      procedure.setPeriod(new Period(procedureTime, procedureTime.plusHours(2)));
      procedureTime = procedureTime.plusDays(2);
      procedure.setStatus(info.mhylle.playground.lpr3.model.SKS.condition.StatusCode.FINISHED);
      admissionEncounter.addProcedure(procedure);
      procedures.add(procedure);
    }

    episodeOfCareElements.add(loinIschiasEoce);

  }

  private EpisodeOfCare generateCancerEpisodeOfCare(Patient patient)
  {
    loadEpisodesOfCare();
    loadConditions();
    EpisodeOfCare episodeOfCare = new EpisodeOfCare();
    Condition condition = new Condition();
    condition.setCode(ConditionCode.DM543);
    episodeOfCare.setCondition(condition);
    Period period = new Period();
    period.setStartTime(createDate(LocalDateTime.now().minusDays(90), LocalDateTime.now().minusDays(30)));
    episodeOfCare.setPeriod(period);
    episodeOfCare.setStatus(info.mhylle.playground.lpr3.model.SKS.episodeofcare.StatusCode.ACTIVE);
    episodesOfCare.add(episodeOfCare);
    conditions.add(condition);
    patient.addEpisodeOfCare(episodeOfCare);
    return episodeOfCare;
  }


  private Encounter createNeckPainAdmissionEncounter(EpisodeOfCareElement backPainTreatmentEoce, Condition neckPainCondition)
  {
    loadEncounters();
    Encounter admissionEncounter = new Encounter();
    admissionEncounter.setPeriod(new Period(backPainTreatmentEoce.getPeriod().getStartTime(), backPainTreatmentEoce.getPeriod().getEndTime().minusDays(4)));
    admissionEncounter.setStatus(info.mhylle.playground.lpr3.model.SKS.encounter.StatusCode.FINISHED);
    admissionEncounter.setActionDiagnosis(neckPainCondition);
    admissionEncounter.setEncounterClass(EncounterClass.INPATIENT);
    backPainTreatmentEoce.addEncounter(admissionEncounter);
    encounters.add(admissionEncounter);
    return admissionEncounter;
  }

  private Patient generatePatient(Random random)
  {
    loadPatients();
    Patient p = new Patient();
    String firstName = firstnames.get(new Random().nextInt(firstnames.size()));
    String lastName = lastnames.get(new Random().nextInt(lastnames.size()));
    p.setName(firstName + " " + lastName);
    LocalDateTime startDate = LocalDateTime.of(1970, 1, 1, 0, 0);
    LocalDateTime endDate = LocalDateTime.of(2000, 1, 1, 0, 0);
    LocalDateTime birthday = createDate(startDate, endDate);
    p.setBirthday(birthday);
    String alternativeId = birthday.getDayOfMonth() < 10 ? "0" + birthday.getDayOfMonth() : "" + birthday.getDayOfMonth();
    alternativeId += birthday.getMonthValue() < 10 ? "0" + birthday.getMonthValue() : "" + birthday.getMonthValue();
    alternativeId += birthday.getYear();
    for (int j = 0; j < 4; j++) {
      int nextInt = random.nextInt(10);
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
    patients.add(p);
    return p;

  }


  @Test
  public void generatePatients()
  {
    loadPatients();
    Random random = new Random();
    for (int i = 0; i < NR_OF_PATIENTS; i++) {
      Patient p = generatePatient(random);
      patients.add(p);
    }

    savePatients();
  }

//  @Test
//  public void generateEpisodesOfCareElements()
//  {
//    loadPatients();
//    loadEpisodeOfCareElements();
//    Random random = new Random();
//    for (int i = 0; i < NR_OF_EPISODES_OF_CARE; i++) {
//      EpisodeOfCareElement eoce = new EpisodeOfCareElement();
//      SorCode responsibleUnit = responsibleUnits.get(random.nextInt(responsibleUnits.size()));
//      eoce.setResponsibleUnit(responsibleUnit);
//
//      if (patients != null && patients.size() > 0) {
//        Patient patient = patients.get(random.nextInt(patients.size()));
//        eoce.setPatient(patient);
//      }
//
//      LocalDateTime startTime = createDate(random, 2017, 5);
//      Period period = new Period();
//      period.setStartTime(startTime);
//
//      if (random.nextLong() > 0.9) {
//        // Fall through - if we are done we need an end time.
//        LocalDateTime endTime = createDate(random, 2017, 1);
//        period.setEndTime(endTime);
//        flipTime(period);
//      }
//      eoce.setPeriod(period);
//
//      Condition condition = new Condition();
//      condition.setVerificationStatus(VerificationStatusCode.values()[random.nextInt(VerificationStatusCode.values().length)]);
//      condition.setPatient(eoce.getPatient());
//      Period conditionPeriod = new Period(startTime);
//      condition.setPeriod(conditionPeriod);
//
//      eoce.setCondition(condition);
//
//      conditions.add(condition);
//      episodeOfCareElements.add(eoce);
//    }
//
//    saveEpisodesOfCareElements();
//  }

  @Test
  public void generateReferrals()
  {
    loadPatients();
    loadReferrals();
    Random random = new Random();

    if (patients != null && patients.size() > 0) {
      Patient patient = patients.get(random.nextInt(patients.size()));
      LocalDateTime now = LocalDateTime.now();

      Referral headCancerStart = new Referral();
//      headCancerStart.setPatient(patient);
      headCancerStart.setStatus(StatusCode.REQUESTED);
      headCancerStart.setReason(ReasonSksCode.HEAD_AND_CANCER_PACKAGE_START);
      LocalDateTime headCancerStartTime = now.minusDays(30);
      headCancerStart.setAuthoredOn(headCancerStartTime);

      referrals.add(headCancerStart);

      Referral headCancerElucidation = new Referral();
//      headCancerElucidation.setPatient(patient);
      headCancerElucidation.setStatus(StatusCode.REQUESTED);
      headCancerElucidation.setReason(ReasonSksCode.HEAD_AND_CANCER_ELUCIDATION);
      LocalDateTime headCancerElucidationTime = now.minusDays(25);
      headCancerElucidation.setAuthoredOn(headCancerElucidationTime);

      referrals.add(headCancerElucidation);

      Referral headCancerDecision = new Referral();
//      headCancerDecision.setPatient(patient);
      headCancerDecision.setStatus(StatusCode.REQUESTED);
      headCancerDecision.setReason(ReasonSksCode.HEAD_AND_CANCER_DECISION);
      LocalDateTime headCancerDecisionTime = now.minusDays(20);
      headCancerDecision.setAuthoredOn(headCancerDecisionTime);

      referrals.add(headCancerDecision);

      Referral headCancerTreatment = new Referral();
//      headCancerTreatment.setPatient(patient);
      headCancerTreatment.setStatus(StatusCode.REQUESTED);
      headCancerTreatment.setReason(ReasonSksCode.HEAD_AND_CANCER_INITIAL_TREATMENT_START);
      LocalDateTime headCancerTreatmentTime = now.minusDays(15);
      headCancerTreatment.setAuthoredOn(headCancerTreatmentTime);

      referrals.add(headCancerTreatment);

      Referral headCancerFollowUp = new Referral();
//      headCancerFollowUp.setPatient(patient);
      headCancerFollowUp.setStatus(StatusCode.REQUESTED);
      headCancerFollowUp.setReason(ReasonSksCode.HEAD_AND_CANCER_FOLLOWUP);
      LocalDateTime headCancerFollowUpTime = now.minusDays(10);
      headCancerFollowUp.setAuthoredOn(headCancerFollowUpTime);

      referrals.add(headCancerFollowUp);

      Referral headCancerEnd = new Referral();
//      headCancerEnd.setPatient(patient);
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
  public void generateStandardReferrals()
  {
    loadPatients();
    loadReferrals();
    Patient patient = patients.get(new Random().nextInt(patients.size()));
    Referral r1 = new Referral();
    Referral r2 = new Referral();
    Referral r3 = new Referral();
    Referral r4 = new Referral();
    Referral r5 = new Referral();
    Referral r6 = new Referral();
//    r1.setPatient(patient);
    r1.setReason(ReasonSksCode.HEAD_AND_CANCER_PACKAGE_START);
    r1.setStatus(StatusCode.REQUESTED);
//    r2.setPatient(patient);
    r2.setReason(ReasonSksCode.HEAD_AND_CANCER_ELUCIDATION);
    r2.setStatus(StatusCode.REQUESTED);
//    r3.setPatient(patient);
    r3.setReason(ReasonSksCode.HEAD_AND_CANCER_DECISION);
    r3.setStatus(StatusCode.REQUESTED);
//    r4.setPatient(patient);
    r4.setReason(ReasonSksCode.HEAD_AND_CANCER_INITIAL_TREATMENT_START);
    r4.setStatus(StatusCode.REQUESTED);
//    r5.setPatient(patient);
    r5.setReason(ReasonSksCode.HEAD_AND_CANCER_FOLLOWUP);
    r5.setStatus(StatusCode.REQUESTED);
//    r6.setPatient(patient);
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

//  @Test
//  public void generateEncounters()
//  {
//    loadPatients();
//    loadEncounters();
//    loadReferrals();
//
//    Random random = new Random();
//    for (int i = 0; i < NR_OF_ENCOUNTERS; i++) {
//      Encounter encounter = new Encounter();
//      encounter.setStatus(info.mhylle.playground.lpr3.model.SKS.encounter.StatusCode.values()[random.nextInt(info.mhylle.playground.lpr3.model.SKS.encounter.StatusCode.values().length)]);
//      encounter.setEncounterClass(EncounterClass.values()[random.nextInt(EncounterClass.values().length)]);
//      if (patients != null && patients.size() > 0) {
//        if (random.nextDouble() < 0.75) {
//          Patient patient = patients.get(random.nextInt(patients.size()));
//          encounter.setPatient(patient);
//        }
//      }
//      LocalDateTime startTime = createDate(random, 2017, 5);
//      Period period = new Period();
//      period.setStartTime(startTime);
//      switch (encounter.getStatus()) {
//        case FINISHED:
//        case CANCELLED:
//          // Fall through - if we are done we need an end time.
//          LocalDateTime endTime = createDate(random, 2017, 2);
//          period.setEndTime(endTime);
//          flipTime(period);
//          break;
//        default:
//          break;
//      }
//
//      encounter.setPeriod(period);
//      encounters.add(encounter);
//    }
//
//    saveEncounters();
//  }

//  private void flipTime(Period period)
//  {
//    LocalDateTime startTime = period.getStartTime();
//    LocalDateTime endTime = period.getEndTime();
//    if (startTime.isBefore(endTime)) {
//      period.setStartTime(startTime);
//      period.setEndTime(endTime);
//    } else {
//      period.setStartTime(endTime);
//      period.setEndTime(startTime);
//    }
//  }

//  private LocalDateTime createDate(Random random, int beforeYear, int range)
//  {
//    int yearOffSet = random.nextInt(range);
//
//    int year = beforeYear - yearOffSet;
//    LocalDateTime dateTime = LocalDateTime.of(year, 1, 1, 0, 0, 0);
//    dateTime = dateTime.plusMonths(random.nextInt(13));
//    dateTime = dateTime.plusDays(random.nextInt(30));
//    dateTime = dateTime.plusHours(random.nextInt(24));
//    dateTime = dateTime.plusMinutes(random.nextInt(60));
//    dateTime = dateTime.plusSeconds(random.nextInt(60));
//    return dateTime;
//  }

  private LocalDateTime createDate(LocalDateTime after, LocalDateTime before)
  {
    LocalDateTime result;

    long millis = after.until(before, ChronoUnit.MILLIS);
    result = after.plus(ThreadLocalRandom.current().nextLong(millis), ChronoUnit.MILLIS);
    return result;
  }

  private void savePatients()
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

  private void saveEpisodesOfCareElements()
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

  private void saveEpisodesOfCare()
  {
    Gson gson = new Gson();
    String jSONEpisodesOfCare = gson.toJson(this.episodesOfCare);

    try (FileWriter file = new FileWriter(EPISODESOFCARE_SAVEFILE)) {
      file.write(jSONEpisodesOfCare);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveReferrals()
  {
    Gson gson = new Gson();
    String jSONReferrals = gson.toJson(this.referrals);

    try (FileWriter file = new FileWriter(REFERRALS_SAVEFILE)) {
      file.write(jSONReferrals);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveEncounters()
  {
    Gson gson = new Gson();
    String jSONEncounters = gson.toJson(this.encounters);

    try (FileWriter file = new FileWriter(ENCOUNTERS_SAVEFILE)) {
      file.write(jSONEncounters);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveProcedures()
  {
    Gson gson = new Gson();
    String jSONProcedures = gson.toJson(this.procedures);

    try (FileWriter file = new FileWriter(PROCEDURES_SAVEFILE)) {
      file.write(jSONProcedures);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveConditions()
  {
    Gson gson = new Gson();
    String jSONConditions = gson.toJson(this.conditions);

    try (FileWriter file = new FileWriter(CONDITIONS_SAVEFILE)) {
      file.write(jSONConditions);
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadProcedures()
  {
    Gson gson = new Gson();
    try {
      procedures = gson.fromJson(new FileReader(new File(PROCEDURES_SAVEFILE)), new TypeToken<List<Procedure>>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (procedures == null) {
      procedures = new ArrayList<>();
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
      referrals = gson.fromJson(new FileReader(new File(REFERRALS_SAVEFILE)), new TypeToken<List<Referral>>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (referrals == null) {
      referrals = new ArrayList<>();
    }
  }

  private void loadEncounters()
  {
    Gson gson = new Gson();
    try {
      encounters = gson.fromJson(new FileReader(new File(ENCOUNTERS_SAVEFILE)), new TypeToken<List<Encounter>>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (encounters == null) {
      encounters = new ArrayList<>();
    }
  }

  private void loadEpisodesOfCare()
  {
    Gson gson = new Gson();
    try {
      episodesOfCare = gson.fromJson(new FileReader(new File(EPISODESOFCARE_SAVEFILE)), new TypeToken<List<EpisodeOfCare>>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (episodesOfCare == null) {
      episodesOfCare = new ArrayList<>();
    }
  }

  private void loadConditions()
  {
    Gson gson = new Gson();
    try {
      conditions = gson.fromJson(new FileReader(new File(CONDITIONS_SAVEFILE)), new TypeToken<List<Condition>>()
      {
      }.getType());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    if (conditions == null) {
      conditions = new ArrayList<>();
    }
  }

  private void loadEpisodeOfCareElements()
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
