/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import logic.Generator;
import logic.Person;

/**
 * REST Web Service
 *
 * @author T430
 */
@Path("sampleData")
public class ApiResource {

    @Context
    private UriInfo context;

    public ApiResource() {
    }

    Generator gen = new Generator();

    @GET
    @Path("{amount}/{starting}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("amount") int amount, @PathParam("starting") int starting) {
        ArrayList<Person> sample = (ArrayList<Person>) gen.generate(amount, starting);
        return new Gson().toJson(sample, List.class);
    }

    /**
     * PUT method for updating or creating an instance of ApiResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
