package info.mhylle.playground.lpr3.commands;

import info.mhylle.playground.lpr3.model.Encounter;
import info.mhylle.playground.lpr3.model.Referral;
import info.mhylle.playground.lpr3.model.SKS.referral.StatusCode;

/**
 * Created by mhyll on 11-02-2017.
 */
public class ReceiveReferralCommand implements Command {
    private Referral referral;

    public ReceiveReferralCommand(Referral referral) {
        this.referral = referral;
    }

    @Override
    public void execute() {
        referral.setStatus(StatusCode.ACCEPTED);
        associateWithEpisodeOfCare();
    }

    private void associateWithEpisodeOfCare() {
//        Encounter encounter= referral.getEncounter();
    }
}
