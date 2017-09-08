/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Pet;
import factory.Factory;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author t470
 */
@Path("pethospital")
public class restPet {

    @Context
    private UriInfo context;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
    private Factory factory = new Factory(emf);
    private Gson gson = new Gson();

    /**
     * Creates a new instance of GenericResource
     */
    public restPet() {
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<Pet> list = factory.getAllPets();
        JsonArray jsonA = new JsonArray();
        JsonObject jsonO;

        for (Pet pet : list) {
            jsonO = new JsonObject();

            jsonO.addProperty("id", pet.getId());
            jsonO.addProperty("name", pet.getName());
            jsonO.addProperty("birth", pet.getBirth().toString());
            jsonO.addProperty("species", pet.getSpecies());
            jsonO.addProperty("first_name", pet.getOwnerId().getFirstName());
            jsonO.addProperty("last_name", pet.getOwnerId().getLastName());

            jsonA.add(jsonO);
        }
        return gson.toJson(jsonA);
    }

    @GET
    @Path("/numberofpets")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonSize() {
        List<Pet> list = factory.getAllPets();
        return "{\"petCount\":" + list.size() + "}";
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
