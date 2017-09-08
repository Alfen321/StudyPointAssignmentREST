/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.google.gson.Gson;
import entity.Person;
import java.util.List;

/**
 *
 * @author t470
 */
public class JSONConverter implements InterfaceJSONConverter {

    Gson gs = new Gson();

    @Override
    public Person getPersonFromJson(String js) {
        return gs.fromJson(js, Person.class);
    }

    @Override
    public String getJSONFromPerson(Person p) {
        return gs.toJson(p);
    }

    @Override
    public String getJSONFromPerson(List<Person> persons) {
        return gs.toJson(persons);
    }

}
