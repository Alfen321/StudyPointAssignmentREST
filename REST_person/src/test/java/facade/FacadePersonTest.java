/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import deploy.DeploymentConfiguration;
import entity.Person;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author t470
 */
public class FacadePersonTest {

    public FacadePersonTest() {
    }

    private static FacadePerson instance;
    private static EntityManagerFactory emf;
    private static List<Person> list = new ArrayList<Person>();

    @BeforeClass
    public static void setUpClass() {
        instance = new FacadePerson();
        list.add(new Person(100L, "John", "Doe", 123123));
        list.add(new Person(101L, "test2", "test", 123123));
        list.add(new Person(102L, "test3", "test", 123123));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        instance.addEntityManagerFactory(emf);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        EntityManager result = instance.getEntityManager();
        assertNotNull(result);
    }

    @Test
    public void testGetPerson() {
        System.out.println("getPerson");
        Long id = 100L;
        Person result = instance.getPerson(id);
        Person expResult = list.get(0);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPersons() {
        System.out.println("getPersons");
        List<Person> expResult = list;
        List<Person> result = instance.getPersons();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddPerson() {
        System.out.println("addPerson");
        Person person = new Person(104L, "Blum", "Dum", 123123);
        Person result = instance.addPerson(person);
        Person expResult = instance.getPerson(104L);
        assertEquals(expResult, result);
    }

    @Test
    public void testEditPerson() {
        System.out.println("editPerson");
        Person person = new Person(100L, "John", "Doel", 123123);
        Person result = instance.editPerson(person);
        Person expResult = instance.getPerson(100L);
        assertEquals(expResult, result);
    }

    @Test()
    public void testDeletePerson() {
        System.out.println("deletePerson");
        Long id = 101L;
        Person result = instance.deletePerson(id);
        Person expResult = instance.getPerson(101L);
        assertNull(expResult);
    }

}
