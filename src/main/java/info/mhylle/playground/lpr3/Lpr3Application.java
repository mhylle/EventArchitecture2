package info.mhylle.playground.lpr3;

import info.mhylle.playground.lpr3.rules.EnsureReferralHasEpisodeOfCareRule;
import info.mhylle.playground.lpr3.rules.RuleEngine;

import javax.ws.rs.core.Application;

public class Lpr3Application extends Application
{
  public Lpr3Application()
  {
    System.out.println("Lpr3Application.Lpr3Application");
    RuleEngine.getInstance().addRule(new EnsureReferralHasEpisodeOfCareRule());
  }
}
