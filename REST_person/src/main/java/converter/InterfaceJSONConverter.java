/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entity.Person;
import java.util.List;

/**
 *
 * @author t470
 */
public interface InterfaceJSONConverter {

    public Person getPersonFromJson(String js);

    public String getJSONFromPerson(Person p);

    public String getJSONFromPerson(List<Person> persons);
}
