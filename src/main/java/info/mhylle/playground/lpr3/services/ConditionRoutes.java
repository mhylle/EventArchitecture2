package info.mhylle.playground.lpr3.services;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Condition;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/conditions")
public class ConditionRoutes
{
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Condition> conditions()
  {
    return Repository.getInstance().getConditions();
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Condition referrals(@PathParam("id") String id)
  {
    System.out.println("Getting condition for " + id);
    Condition condition = Repository.getInstance().getConditions().stream()
        .filter(c -> c.getId().toString().equals(id))
        .findFirst().orElse(null);
    if (condition != null) {
      System.out.println("returning a condition, " + condition.getCode().name());
    } else {
      System.out.println("condition was null");
    }
    return condition;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void conditions(Condition condition)
  {
    Repository.getInstance().addCondition(condition);
  }
}
