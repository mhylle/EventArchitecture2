package info.mhylle.playground.lpr3.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.mhylle.playground.lpr3.model.*;
import info.mhylle.playground.lpr3.model.SKS.SksCode;
import info.mhylle.playground.lpr3.model.SKS.SorCode;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Repository
{
  private static final String CONTACTS_SAVEFILE = "c:/temp/EventArchitecture/contacts.json";
  private static final String EPISODEOFCAREELEMENTS_SAVEFILE = "c:/temp/EventArchitecture/episodeOfCareElements.json";
  private static final String PATIENTS_SAVEFILE = "c:/temp/EventArchitecture/patients.json";
  private static final String REFERRALS_SAVEFILE = "c:/temp/EventArchitecture/referrals.json";
  private List<Patient> patients;
  private List<Contact> contacts;
  private List<Diagnose> diagnoses;
  private List<EpisodeOfCareElement> episodeOfCareElements;

  private static Repository _instance;
  private List<Referral> referrals;

  private Repository()
  {
    loadData();
  }

  private void loadData()
  {
    patients = new ArrayList<>();
    contacts = new ArrayList<>();
    episodeOfCareElements = new ArrayList<>();
    diagnoses = new ArrayList<>();
    referrals= new ArrayList<>();

    File patientSaveFile = new File(PATIENTS_SAVEFILE);
    File episodeOfCareElementsSaveFile = new File(EPISODEOFCAREELEMENTS_SAVEFILE);
    File contactsSaveFile = new File(CONTACTS_SAVEFILE);
    File referralsSaveFile = new File(REFERRALS_SAVEFILE);
    boolean loadedData = false;
    if (patientSaveFile.exists()) {
      loadPatients();
      loadedData = true;
    }
    if (episodeOfCareElementsSaveFile.exists()) {
      loadEpisodeOfCareElements();
      loadedData = true;
    }
    if (contactsSaveFile.exists()) {
      loadContacts();
      loadedData = true;
    }
    if (contactsSaveFile.exists()) {
      loadContacts();
      loadedData = true;
    }
    if (referralsSaveFile.exists()) {
      loadReferrals();
      loadedData = true;
    }

    if (!loadedData) {
      createDummyData();
    }
  }

  private void loadPatients() {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(new File(PATIENTS_SAVEFILE))){
      patients = gson.fromJson(reader, new TypeToken<List<Patient>>()
      {
      }.getType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void loadEpisodeOfCareElements() {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(new File(EPISODEOFCAREELEMENTS_SAVEFILE))){
      episodeOfCareElements= gson.fromJson(reader, new TypeToken<List<EpisodeOfCareElement>>()
      {
      }.getType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void loadContacts() {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(new File(CONTACTS_SAVEFILE))){
      contacts = gson.fromJson(reader, new TypeToken<List<Contact>>()
      {
      }.getType());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void loadReferrals() {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(new File(REFERRALS_SAVEFILE))){
      referrals = gson.fromJson(reader, new TypeToken<List<Referral>>()
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

  private void saveEpisodeOfCareElements()
  {
    File saveFile = new File(EPISODEOFCAREELEMENTS_SAVEFILE);
    saveData(episodeOfCareElements, saveFile);
  }

  private void saveContacts()
  {
    File saveFile = new File(CONTACTS_SAVEFILE);
    saveData(contacts, saveFile);
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

  public static Repository getInstance()
  {
    if (_instance == null) {
      _instance = new Repository();
    }
    return _instance;
  }

  private void createDummyData()
  {

    Contact contact1 = createContact(SorCode.AROS, SksCode.AMBULANT, SksCode.AKUT_DAYTIME, LocalDateTime.now());
    Contact contact2 = createContact(SorCode.AAR_KIR_CLI, SksCode.INDLAEGGELSE, SksCode.ELEKTIV, LocalDateTime.now());
    addContact(contact1);
    addContact(contact2);

    EpisodeOfCareElement e1 = new EpisodeOfCareElement();
    e1.addContact(contact1);
    e1.addContact(contact2);
    e1.setResponsibleUnit(SorCode.AROS);
    e1.setEpisodeOfCareLabel(SksCode.LABEL_CANCER);
    e1.setStartTime(LocalDateTime.now());
    addEpisodeOfCareElement(e1);

    Patient p1 = new Patient();
    p1.setAlternativeId("2512484916");
    p1.setName("Nancy Berggren");
    p1.addEpisodeOfCareElement(e1);

    addPatient(p1);
  }

  private Contact createContact(SorCode responsibleUnit, SksCode type, SksCode priority, LocalDateTime startTime)
  {
    Contact contact = new Contact();
    setBaseContactInformation(contact, responsibleUnit, type, priority, startTime);
    createResidency(contact);
    createActionDiagnosis(contact);
    createPaymentInformation(contact);
    return contact;
  }

  private void createPaymentInformation(Contact contact)
  {
    PaymentInformation paymentInformation = new PaymentInformation();
    paymentInformation.setPaymentAgreement(SksCode.MONTHLY);
    paymentInformation.setPayer(SksCode.MUNICIPALITYPAYED.toString());
    paymentInformation.setSpecializationLevel(SksCode.SPECIALIZATION_REGIONAL);
    paymentInformation.setStartTime(LocalDateTime.now());
    contact.addPaymentInformation(paymentInformation);
  }

  private void createActionDiagnosis(Contact contact)
  {
    Diagnose actionDiagnose = new Diagnose();
    actionDiagnose.setAlternative(SksCode.OPERATION_EXCISION);
    actionDiagnose.setCode(SksCode.OPERATION_DESTRUCTION);
    contact.setActionDiagnose(actionDiagnose);
    addDiagnose(actionDiagnose);
  }

  private void createResidency(Contact contact)
  {
    Residency residency1 = new Residency();
    residency1.setStartTime(LocalDateTime.of(2014, 9, 1, 0, 0));
    residency1.setEndTime(LocalDateTime.of(2016, 9, 1, 0, 0));
    Residency residency2 = new Residency();
    residency2.setStartTime(LocalDateTime.of(2016, 9, 1, 0, 0));
    contact.addResidency(residency1);
    contact.addResidency(residency2);
  }

  private void setBaseContactInformation(Contact contact, SorCode responsibleUnit, SksCode type, SksCode priority, LocalDateTime startTime)
  {
    contact.setResponsibleUnit(responsibleUnit);
    contact.setType(type);
    contact.setPriority(priority);
    contact.setStartTime(startTime);
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

  public void addContact(Contact contact)
  {
    contacts.add(contact);
    saveContacts();
  }

  public List<Contact> getContacts()
  {
    return contacts;
  }

  public void addEpisodeOfCareElement(EpisodeOfCareElement episodeOfCareElement)
  {
    episodeOfCareElements.add(episodeOfCareElement);
    saveEpisodeOfCareElements();
  }

  public List<EpisodeOfCareElement> getEpisodeOfCareElements()
  {
    return episodeOfCareElements;
  }

  public List<Diagnose> getDiagnoses()
  {
    return diagnoses;
  }

  public void addDiagnose(Diagnose diagnose)
  {
    this.diagnoses.add(diagnose);
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
}
