package info.mhylle.playground.lpr3.adaptors;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Condition;
import info.mhylle.playground.lpr3.model.EpisodeOfCareElement;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EpisodeOfCareElementIdAdapter extends XmlAdapter<String, EpisodeOfCareElement>
{

  @Override
  public EpisodeOfCareElement unmarshal(String id) throws Exception
  {
    try {
      return Repository.getInstance().getEpisodeOfCareElements().stream()
          .filter(episodeOfCareElement -> episodeOfCareElement.getId().toString().equals(id))
          .findFirst().orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String marshal(EpisodeOfCareElement episodeOfCareElement) throws Exception
  {
    if (episodeOfCareElement != null) {
      return episodeOfCareElement.getId().toString();
    }
    return null;
  }
}
