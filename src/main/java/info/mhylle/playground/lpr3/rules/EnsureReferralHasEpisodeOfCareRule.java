package info.mhylle.playground.lpr3.rules;

import info.mhylle.playground.lpr3.model.EpisodeOfCare;
import info.mhylle.playground.lpr3.model.EpisodeOfCareElement;
import info.mhylle.playground.lpr3.model.Patient;
import info.mhylle.playground.lpr3.model.Referral;

import java.util.List;

public class EnsureReferralHasEpisodeOfCareRule implements Rule {
  @Override
  public boolean handle() {
    return false;
  }

  @Override
  public boolean process() {
    return false;
  }

  @Override
  public boolean handle(Object o) {
    return o instanceof Referral;
  }


  /**
   * If we already added the referral to an episode of care we are happy, otherwise we are not..
   * @param o the object to process
   * @return true if the rule was passes. false if not.
   */
  public boolean process(Object o) {
    System.out.println("Lpr3Application.process");
    Referral referral = (Referral) o;

//    Patient p = referral.getPatient();
//
//    if (p != null) {
//      List<EpisodeOfCare> episodesOfCare = p.getEpisodesOfCare();
//      if (!episodesOfCare.isEmpty()) {
//        for (EpisodeOfCare episodeOfCare : episodesOfCare) {
//          List<EpisodeOfCareElement> episodeOfCareElements = episodeOfCare.getEpisodeOfCareElements();
//          if (!episodeOfCareElements.isEmpty()) {
//            for (EpisodeOfCareElement episodesOfCareElement : episodeOfCareElements) {
//              if (episodesOfCareElement.getReferral() != null) {
//                if (episodesOfCareElement.getReferral().equals(referral.getId())) {
//                  // referral was already associated to an episode of care element, do nothing
//                  return true;
//                }
//              }
//            }
//          } else {
//            return false;
//          }
//        }
//      }


//    }
    return false;
  }
}
