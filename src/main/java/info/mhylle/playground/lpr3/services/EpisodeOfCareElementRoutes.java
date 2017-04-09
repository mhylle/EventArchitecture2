package info.mhylle.playground.lpr3.services;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.EpisodeOfCareElement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/episodeofcareelements")
public class EpisodeOfCareElementRoutes
{
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<EpisodeOfCareElement> episodeOfCareElement()
  {
    return Repository.getInstance().getEpisodeOfCareElements();
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public EpisodeOfCareElement episodeOfCareElement(@PathParam("id") String id)
  {
    return Repository.getInstance().getEpisodeOfCareElements().stream()
        .filter(episodeOfCareElement -> episodeOfCareElement.getId().toString().equals(id))
        .findFirst().orElse(null);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public String episodeOfCareElement(EpisodeOfCareElement episodeOfCareElement)
  {
    Repository.getInstance().addEpisodeOfCareElement(episodeOfCareElement);
    return episodeOfCareElement.getId().toString();
  }
}
