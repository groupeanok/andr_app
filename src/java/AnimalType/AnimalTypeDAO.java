/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnimalType;

import classes.factory;
import entites.Animaltypes;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Groupe Anok
 */

public class AnimalTypeDAO implements Serializable {

   EntityManager entityManager = factory.getemf().createEntityManager();

    public Animaltypes selectFind(String code) {
        return entityManager.find(Animaltypes.class, code);
    }

    public List<Animaltypes> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Animaltypes.findAll"); //, Nivo.class);
        List<Animaltypes> cycles = qsniv.getResultList();
//        entityManager.close();
        return cycles;
    }

    public void insert(Animaltypes u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
    }

    public void delete(Animaltypes u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(Animaltypes u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.merge(u);
        entityManager.getTransaction().commit();
    }

}
