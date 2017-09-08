package com.mycompany.quote;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("quote")
public class ApiResource {

    private Gson gson;
    private static Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };

    private JsonArray mapToJson(Map<Integer, String> map) {
        JsonObject object = new JsonObject();
        JsonArray ja = new JsonArray();

        for (int i = 1; i <= map.size(); i++) {
            JsonObject jo = new JsonObject();

            jo.addProperty("id", "" + i);
            jo.addProperty("quote", map.get(i));

            ja.add(jo);
        }

        return ja;
    }

    @Context
    private UriInfo context;

    public ApiResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public String getAllQuotes() {
        return new Gson().toJson(quotes, Map.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/random")
    public String getRandomQuote() {

        int mapSize = quotes.size();

        int slot = (int) (Math.random() * mapSize);

        return new Gson().toJson(mapToJson(quotes).get(slot), JsonArray.class);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postPerson(String content) {
        JsonObject body = new JsonParser().parse(content).getAsJsonObject();
        String quote = "";
        int id = -1;
        
        if (body.has("quote")) {
            quote = body.get("quote").getAsString();
        }
        if (body.has("id")) {
            id = body.get("id").getAsInt();
        }

        if(id != -1){
            quotes.put(id, quote);
        }
        
        return this.getAllQuotes();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String deletePerson(@PathParam("id") int id) {

        quotes.remove(id);

        return this.getAllQuotes();
    }
}
