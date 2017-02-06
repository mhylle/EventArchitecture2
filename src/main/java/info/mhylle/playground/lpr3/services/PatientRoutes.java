package info.mhylle.playground.lpr3.services;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.EpisodeOfCareElement;
import info.mhylle.playground.lpr3.model.Patient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/patients")
public class PatientRoutes
{
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Patient> patients()
  {
    return Repository.getInstance().getPatients();
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Patient patients(@PathParam("id") String id)
  {
    return Repository.getInstance().getPatients().stream()
      .filter(patient -> patient.getId().toString().equals(id))
      .findFirst().orElse(null);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public String patients(Patient patient)
  {
    Repository.getInstance().addPatient(patient);
    return patient.getId();
  }

  @PUT
  @Path("{pid}/episodeofcareelement/{eid}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response addEpisodeOfCareElement(@PathParam("pid") String pid, @PathParam("eid") String eid)
  {
    System.out.println("pid = " + pid);
    List<Patient> patients = Repository.getInstance().getPatients();
    Patient p = patients.stream()
      .filter(patient -> patient.getId().toString().equals(pid))
      .findFirst().orElse(null);

    if (p == null) {
      return Response.status(200).entity("The patient was not found").build();
    }

    EpisodeOfCareElement e = Repository.getInstance().getEpisodeOfCareElements().stream()
      .filter(episodeOfCareElement -> episodeOfCareElement.getId().toString().equals(eid))
      .findFirst().orElse(null);

    if (e == null) {
      return Response.status(200).entity("The EpisodeOfCareElement was not found.").build();
    }
    if (p != null && e != null) {
      System.out.println("adding new episode of care element to patient");
      p.addEpisodeOfCareElement(e);
      System.out.println("New p = " + p);
    }
    Repository.getInstance().updatePatient(p);
    return Response.status(200).entity(p).build();
  }
}
