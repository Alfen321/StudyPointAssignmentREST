package rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Person;
import facade.FacadePerson;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import converter.InterfaceJSONConverter;
import converter.JSONConverter;
import deploy.DeploymentConfiguration;

@Path("person")
public class RestPerson {

    private FacadePerson fp;
    private InterfaceJSONConverter jc;

    public RestPerson() {
        fp = new FacadePerson();
        fp.addEntityManagerFactory(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
        jc = new JSONConverter();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public String getPersons() {
        return jc.getJSONFromPerson(fp.getPersons());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getPerson(@PathParam("id") Long id) {
        Person p = fp.getPerson(id);
        if (p != null) {
            return jc.getJSONFromPerson(p);
        }

        return "{}";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postPerson(String content) {
        JsonObject body = new JsonParser().parse(content).getAsJsonObject();
        String PersonFirstName = "";
        String PersonLastName = "";
        int PersonPhoneNumber = 0;

        if (body.has("firstName")) {
            PersonFirstName = body.get("firstName").getAsString();
        }
        if (body.has("lastName")) {
            PersonLastName = body.get("lastName").getAsString();
        }
        if (body.has("phoneNumber")) {
            PersonPhoneNumber = body.get("phoneNumber").getAsInt();
        }

        Person p = new Person(PersonFirstName, PersonLastName, PersonPhoneNumber);
        fp.addPerson(p);
        return jc.getJSONFromPerson(p);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putPerson(String content) {
        JsonObject body = new JsonParser().parse(content).getAsJsonObject();
        Person p = fp.getPerson(body.get("id").getAsLong());

        if (body.has("firstName")) {
            p.setFirstName(body.get("firstName").getAsString());
        }
        if (body.has("lastName")) {
            p.setLastName(body.get("lastName").getAsString());
        }
        if (body.has("phoneNumber")) {
            p.setPhoneNumber(body.get("phoneNumber").getAsInt());
        }

        fp.editPerson(p);

        return jc.getJSONFromPerson(p);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String deletePerson(@PathParam("id") Long id) {
        Person p = fp.deletePerson(id);

        return jc.getJSONFromPerson(p);
    }
}
