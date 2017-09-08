/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import entity.Pet;
import factory.Factory;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author t470
 */
public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
        Factory f = new Factory(emf);
        List<Pet> list = f.getAllPets();
        
        for (Pet pet : list) {
            System.out.println(pet);
        }
    }
}
