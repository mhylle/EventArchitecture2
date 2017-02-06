package info.mhylle.playground.lpr3.services;

import info.mhylle.playground.lpr3.data.Repository;
import info.mhylle.playground.lpr3.model.Contact;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/contacts")
public class ContactRoutes
{
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Contact> contacts()
  {
    return Repository.getInstance().getContacts();
  }

  @GET
  @Path("{uid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Contact contacts(@PathParam("uid") String uid)
  {
    return Repository.getInstance().getContacts().stream()
      .filter(c -> c.getId().equals(uid))
      .findFirst().orElse(null);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void contacts(Contact contact)
  {
    Repository.getInstance().addContact(contact);
  }
}
