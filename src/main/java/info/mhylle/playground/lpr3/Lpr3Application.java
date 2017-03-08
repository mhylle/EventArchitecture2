package info.mhylle.playground.lpr3;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.*;
import info.mhylle.playground.lpr3.model.SKS.episodeofcare.StatusCode;
import info.mhylle.playground.lpr3.rules.Rule;
import info.mhylle.playground.lpr3.rules.RuleEngine;

import javax.ws.rs.core.Application;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Lpr3Application extends Application
{
  public Lpr3Application()
  {
    System.out.println("Lpr3Application.Lpr3Application");
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
        System.out.println("Lpr3Application.process");
        Referral referral = (Referral) o;
        UUID pid = referral.getPatient();

        if (pid != null) {
          Patient p = Repository.getInstance().getPatients().stream().filter(r -> r.getId().equals(pid))
            .findFirst().orElse(null);
          if (p != null) {
            List<UUID> episodesOfCareElements = p.getEpisodesOfCareElements();
            if (!episodesOfCareElements.isEmpty()) {
              for (UUID episodesOfCareElement : episodesOfCareElements) {
                EpisodeOfCareElement eoce = Repository.getInstance().getEpisodeOfCareElements().stream().filter(r -> r.getId().equals(episodesOfCareElement))
                  .findAny().orElse(null);
                if (eoce != null) {
                  if (eoce.getReferral() != null) {
                    if (eoce.getReferral().equals(referral.getId())) {
                      // referral was already associated to an episode of care element, do nothing
                      return true;
                    }
                  }
                }
              }

              for (UUID episodesOfCareElement : episodesOfCareElements) {
                EpisodeOfCareElement eoce = Repository.getInstance().getEpisodeOfCareElements().stream().filter(r -> r.getId().equals(episodesOfCareElement))
                  .findAny().orElse(null);
                if (eoce != null) {
                  if (referral.getEncounter() != null) {
                    Encounter e = Repository.getInstance().getEncounters().stream()
                      .filter(r -> r.getId().equals(referral.getEncounter()))
                      .findAny().orElse(null);
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
            } else {
              createEpisodeOfCareElement(referral, p);
            }
          }
        }
        return false;
      }
    });
  }

  private void createEpisodeOfCareElement(Referral referral, Patient p)
  {
    // there are no episode of care elements, create one for this referral.
    EpisodeOfCareElement eoce = new EpisodeOfCareElement();
    eoce.setReferral(referral.getId());
    eoce.setPatient(referral.getPatient());
    eoce.setStatus(StatusCode.ACTIVE);
    Period period = new Period();
    period.setStartTime(LocalDateTime.now());
    if (referral.getEncounter() != null) {
      Encounter e = Repository.getInstance().getEncounters().stream()
        .filter(r -> r.getId().equals(referral.getEncounter()))
        .findAny().orElse(null);
      if (e != null) {
        period.setStartTime(e.getPeriod().getStartTime());
        switch (e.getStatus()) {
          case ARRIVED:
            eoce.setStatus(StatusCode.ACTIVE);
            break;
          case PLANNED:
            eoce.setStatus(StatusCode.PLANNED);
            break;
          case INPROGRESS:
            eoce.setStatus(StatusCode.ACTIVE);
          case ONLEAVE:
            eoce.setStatus(StatusCode.ONHOLD);
            break;
          default:
            eoce.setStatus(StatusCode.ACTIVE);
        }
      }
    }
    eoce.setPeriod(period);
    p.addEpisodeOfCareElement(eoce.getId());
    Repository.getInstance().addEpisodeOfCareElement(eoce);
    Repository.getInstance().updatePatient(p);
  }
}