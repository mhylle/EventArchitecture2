package info.mhylle.playground.lpr3.adaptors;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Referral;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by mnh on 02-02-2017.
 */
public class ReferralIdAdapter extends XmlAdapter<String, Referral>
{

  @Override
  public Referral unmarshal(String id) throws Exception
  {
    try {
      return Repository.getInstance().getReferrals().stream()
          .filter(referral -> referral.getId().toString().equals(id))
          .findFirst().orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String marshal(Referral referral) throws Exception
  {
    if (referral != null) {
      return referral.getId().toString();
    }
    return null;
  }
}
