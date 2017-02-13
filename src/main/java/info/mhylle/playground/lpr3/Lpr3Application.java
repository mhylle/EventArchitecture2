package info.mhylle.playground.lpr3;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Encounter;
import info.mhylle.playground.lpr3.model.EpisodeOfCareElement;
import info.mhylle.playground.lpr3.model.Patient;
import info.mhylle.playground.lpr3.model.Referral;
import info.mhylle.playground.lpr3.rules.Rule;
import info.mhylle.playground.lpr3.rules.RuleEngine;

import javax.ws.rs.core.Application;
import java.util.List;
import java.util.UUID;

public class Lpr3Application extends Application
{
  public Lpr3Application()
  {
    RuleEngine.getInstance().addRule(new Rule()
    {
      @Override public boolean handle()
      {
        return false;
      }

      @Override public boolean process()
      {
        return false;
      }

      @Override public boolean handle(Object o)
      {
        return o instanceof Referral;
      }

      @Override public boolean process(Object o)
      {
        Referral referral = (Referral) o;
        UUID encounter = referral.getEncounter();
        if (encounter != null) {
          Encounter e = Repository.getInstance().getEncounters().stream().filter(r -> r.getId().equals(encounter))
            .findFirst().orElse(null);
          if (e != null) {
            UUID patient = e.getPatient();
            if (patient != null) {
              Patient p = Repository.getInstance().getPatients().stream().filter(r -> r.getId().equals(patient))
                .findFirst().orElse(null);
              if (p != null) {
                List<UUID> episodesOfCareElements = p.getEpisodesOfCareElements();
                for (UUID episodesOfCareElement : episodesOfCareElements) {
                  EpisodeOfCareElement eoce = Repository.getInstance().getEpisodeOfCareElements().stream().filter(r -> r.getId().equals(episodesOfCareElement))
                    .findAny().orElse(null);
                  if (eoce != null) {
                    if (eoce.getReferral() != null) {
                      if (eoce.getReferral().equals(referral.getId())) {
                        return true;
                      }
                    }
                  }
                }
                for (UUID episodesOfCareElement : episodesOfCareElements) {
                  EpisodeOfCareElement eoce = Repository.getInstance().getEpisodeOfCareElements().stream().filter(r -> r.getId().equals(episodesOfCareElement))
                    .findAny().orElse(null);
                  if (eoce != null) {
                    if (eoce.getPeriod().getStartTime().isEqual(e.getPeriod().getStartTime())
                      || eoce.getPeriod().getStartTime().isBefore(e.getPeriod().getStartTime())) {
                      if (eoce.getPeriod().getEndTime().isEqual(e.getPeriod().getEndTime())
                        || eoce.getPeriod().getEndTime().isAfter(e.getPeriod().getEndTime())) {
                        // eoce starts at or before encounter start and ends after or at encounter end.
                        // we will use this eoce for the referral..
                        eoce.setReferral(referral.getId());
                        Repository.getInstance().updateEpisodeOfCareElement(eoce);
                      }
                    }
                  }
                }
              }
            }
          }
        }
        return false;
      }
    });
  }
}
