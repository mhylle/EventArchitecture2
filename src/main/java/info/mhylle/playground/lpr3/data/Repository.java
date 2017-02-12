package info.mhylle.playground.lpr3.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.mhylle.playground.lpr3.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Repository {
    private static final String ENCOUNTERS_SAVEFILE = "c:/temp/EventArchitecture/encounters.json";
    private static final String EPISODEOFCAREELEMENTS_SAVEFILE = "c:/temp/EventArchitecture/episodeOfCareElements.json";
    private static final String PATIENTS_SAVEFILE = "c:/temp/EventArchitecture/patients.json";
    private static final String REFERRALS_SAVEFILE = "c:/temp/EventArchitecture/referrals.json";
    private List<Patient> patients;
    private List<Encounter> encounters;
    private List<EpisodeOfCareElement> episodeOfCareElements;

    private static Repository _instance;
    private List<Referral> referrals;

    private Repository() {
        loadData();
    }

    private void loadData() {
        patients = new ArrayList<>();
        encounters = new ArrayList<>();
        episodeOfCareElements = new ArrayList<>();
        referrals = new ArrayList<>();

        File patientSaveFile = new File(PATIENTS_SAVEFILE);
        File episodeOfCareElementsSaveFile = new File(EPISODEOFCAREELEMENTS_SAVEFILE);
        File contactsSaveFile = new File(ENCOUNTERS_SAVEFILE);
        File referralsSaveFile = new File(REFERRALS_SAVEFILE);

        if (patientSaveFile.exists()) {
            loadPatients();
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
    }

    private void loadPatients() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(new File(PATIENTS_SAVEFILE))) {
            patients = gson.fromJson(reader, new TypeToken<List<Patient>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEpisodeOfCareElements() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(new File(EPISODEOFCAREELEMENTS_SAVEFILE))) {
            episodeOfCareElements = gson.fromJson(reader, new TypeToken<List<EpisodeOfCareElement>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEncounters() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(new File(ENCOUNTERS_SAVEFILE))) {
            encounters = gson.fromJson(reader, new TypeToken<List<Encounter>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadReferrals() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(new File(REFERRALS_SAVEFILE))) {
            referrals = gson.fromJson(reader, new TypeToken<List<Referral>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePatients() {
        File saveFile = new File(PATIENTS_SAVEFILE);
        saveData(patients, saveFile);
    }

    private void saveEpisodeOfCareElements() {
        File saveFile = new File(EPISODEOFCAREELEMENTS_SAVEFILE);
        saveData(episodeOfCareElements, saveFile);
    }

    private void saveContacts() {
        File saveFile = new File(ENCOUNTERS_SAVEFILE);
        saveData(encounters, saveFile);
    }

    private <T> void saveData(List<T> dataList, File saveFile) {
        Gson gson = new Gson();
        String jSONdata = gson.toJson(dataList);

        try (FileWriter file = new FileWriter(saveFile)) {
            file.write(jSONdata);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Repository getInstance() {
        if (_instance == null) {
            _instance = new Repository();
        }
        return _instance;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        savePatients();
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void addEncounter(Encounter contact) {
        encounters.add(contact);
        saveContacts();
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public void addEpisodeOfCareElement(EpisodeOfCareElement episodeOfCareElement) {
        episodeOfCareElements.add(episodeOfCareElement);
        saveEpisodeOfCareElements();
    }

    public List<EpisodeOfCareElement> getEpisodeOfCareElements() {
        return episodeOfCareElements;
    }

    public List<Referral> getReferrals() {
        return referrals;
    }

    public void addReferral(Referral referral) {
        this.referrals.add(referral);
    }

    public void updatePatient(Patient p) {
        int i = patients.indexOf(p);
        patients.set(i, p);
        savePatients();
    }
}
