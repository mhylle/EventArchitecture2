package info.mhylle.playground.lpr3.services;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.EpisodeOfCare;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/episodesOfCare")
public class EpisodeOfCareRoutes
{
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<EpisodeOfCare> episodeOfCare()
  {
    return Repository.getInstance().getEpisodesOfCare();
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public EpisodeOfCare episodeOfCare(@PathParam("id") String id)
  {
    return Repository.getInstance().getEpisodesOfCare().stream()
        .filter(episodeOfCare -> episodeOfCare.getId().toString().equals(id))
        .findFirst().orElse(null);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public String episodeOfCareElement(EpisodeOfCare episodeOfCare)
  {
    Repository.getInstance().addEpisodeOfCare(episodeOfCare);
    return episodeOfCare.getId().toString();
  }
}
