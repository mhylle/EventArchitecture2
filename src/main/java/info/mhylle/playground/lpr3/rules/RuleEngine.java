package info.mhylle.playground.lpr3.rules;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine
{
  private static RuleEngine _instance;

  private List<Rule> rules;

  private RuleEngine()
  {
    rules = new ArrayList<>();
  }

  public static RuleEngine getInstance()
  {
    if (_instance == null) {
      _instance = new RuleEngine();
    }
    return _instance;
  }

  public void addRule(Rule rule)
  {
    rules.add(rule);
  }

  public boolean run(Object o)
  {
    boolean result = true;
    for (Rule rule : rules) {
      if (rule.handle(o)) {
        result &= rule.process(o);
      }
    }
    return result;
  }
}
