package info.mhylle.playground.lpr3.services;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.old.Diagnose;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//@Path("/diagnoses")
public class DiagnosisRoutes
{
//  @GET
//  @Produces(MediaType.APPLICATION_JSON)
//  public Response diagnosis()
//  {
//    return Response.status(200).entity(
//      new GenericEntity<List<Diagnose>>(Repository.getInstance().getDiagnoses())
//      {
//      }).build();
////    return Repository.getInstance().getDiagnoses();
//  }
//
//  @GET
//  @Path("{id}")
//  @Produces(MediaType.APPLICATION_JSON)
//  public Diagnose diagnosis(@PathParam("id") String id)
//  {
//    return Repository.getInstance().getDiagnoses().stream()
//      .filter(diagnose -> diagnose.getId() == id)
//      .findFirst().orElse(null);
//  }
//
//  @POST
//  @Consumes(MediaType.APPLICATION_JSON)
//  public void diagnosis(Diagnose diagnose)
//  {
//    Repository.getInstance().addDiagnose(diagnose);
//  }
}
