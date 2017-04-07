package info.mhylle.playground.lpr3.services;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Procedure;
import info.mhylle.playground.lpr3.model.Referral;
import info.mhylle.playground.lpr3.model.SKS.referral.StatusCode;
import info.mhylle.playground.lpr3.rules.RuleEngine;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/procedures")
public class ProcedureRoutes
{
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Referral> referrals()
  {
    return Repository.getInstance().getReferrals();
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Procedure getProcedure(@PathParam("id") String id)
  {
    return Repository.getInstance().getProcedures().stream()
        .filter(r -> r.getId().toString().equals(id))
        .findFirst().orElse(null);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void procedures(Procedure procedure)
  {
    Repository.getInstance().addProcedure(procedure);
  }

}
