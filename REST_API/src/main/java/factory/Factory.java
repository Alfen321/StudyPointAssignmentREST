/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import entity.Pet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author t470
 */
public class Factory {

    private EntityManagerFactory emf;

    public Factory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Pet> getAllPets() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Pet.findAll").getResultList();
    }

}
