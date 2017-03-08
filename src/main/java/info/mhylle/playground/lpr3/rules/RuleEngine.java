package info.mhylle.playground.lpr3.rules;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {
    private static RuleEngine _instance;

    private List<Rule> rules;

    private RuleEngine() {
        rules = new ArrayList<>();
    }

    public static RuleEngine getInstance() {
        if (_instance == null) {
            _instance = new RuleEngine();
        }
        return _instance;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void run() {
        for (Rule rule : rules) {
            if (rule.handle()) {
                rule.process();
            }
        }
    }

    public void run(Object o) {
        for (Rule rule : rules) {
            if (rule.handle(o)) {
                rule.process(o);
            }
        }
    }
}
