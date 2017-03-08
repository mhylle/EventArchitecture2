package info.mhylle.playground.lpr3.services;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Encounter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/encounters")
public class ContactRoutes {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Encounter> encounters() {
        return Repository.getInstance().getEncounters();
    }

    @GET
    @Path("{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Encounter contacts(@PathParam("uid") String uid) {
        return Repository.getInstance().getEncounters().stream()
                .filter(c -> c.getId().toString().equals(uid))
                .findFirst().orElse(null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void contacts(Encounter encounter) {
        Repository.getInstance().addEncounter(encounter);
    }
}
