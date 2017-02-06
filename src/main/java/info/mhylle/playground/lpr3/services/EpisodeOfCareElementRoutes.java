package info.mhylle.playground.lpr3.services;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.EpisodeOfCareElement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/episodeofcareelements")
public class EpisodeOfCareElementRoutes
{
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<EpisodeOfCareElement> episodeOfCareElement()
  {
    System.out.println("Standard Get ");
    List<EpisodeOfCareElement> episodeOfCareElements = Repository.getInstance().getEpisodeOfCareElements();
    System.out.println("Standard Get ");
    return episodeOfCareElements;
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public EpisodeOfCareElement episodeOfCareElement(@PathParam("id") String id)
  {
    System.out.println("Get Episode of Care Elements with id");
    return Repository.getInstance().getEpisodeOfCareElements().stream()
      .filter(episodeOfCareElement -> episodeOfCareElement.getId().toString().equals(id))
      .findFirst().orElse(null);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public String episodeOfCareElement(EpisodeOfCareElement episodeOfCareElement)
  {
    System.out.println("episodeOfCareElement = " + episodeOfCareElement);
    System.out.println("Creating EpisodeOfCareElement");
    Repository.getInstance().addEpisodeOfCareElement(episodeOfCareElement);
    return episodeOfCareElement.getId();
  }
}
