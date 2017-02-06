package info.mhylle.playground.lpr3.services;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Contact;
import info.mhylle.playground.lpr3.model.Referral;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/referrals")
public class ReferralRoutes
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
  public Referral referrals(@PathParam("id") String id)
  {
    return Repository.getInstance().getReferrals().stream()
      .filter(r -> r.getId().toString().equals(id))
      .findFirst().orElse(null);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void referrals(Referral contact)
  {
    Repository.getInstance().addReferral(contact);
  }
}
