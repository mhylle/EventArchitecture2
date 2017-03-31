package info.mhylle.playground.lpr3.adaptors;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.EpisodeOfCare;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EpisodeOfCareIdAdapter extends XmlAdapter<String, EpisodeOfCare>
{

  @Override
  public EpisodeOfCare unmarshal(String id) throws Exception
  {
    try {
      return Repository.getInstance().getEpisodesOfCare().stream()
          .filter(episodeOfCare -> episodeOfCare.getId().toString().equals(id))
          .findFirst().orElse(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String marshal(EpisodeOfCare episodeOfCare) throws Exception
  {
    if (episodeOfCare != null) {
      return episodeOfCare.getId().toString();
    }
    return null;
  }
}
