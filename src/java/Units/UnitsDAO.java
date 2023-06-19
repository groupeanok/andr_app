/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

/**
 *
 * @author Groupe Anok
 */

import classes.factory;
import entites.Units;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UnitsDAO implements Serializable  {
    
      EntityManager entityManager = factory.getemf().createEntityManager();

    public Units selectFind(String code) {
        return entityManager.find(Units.class, code);
    }

    public List<Units> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Units.findAll"); //, Nivo.class);
        List<Units> cycles = qsniv.getResultList();
//        entityManager.close();
        return cycles;
    }

    public void insert(Units u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
    }

    public void delete(Units u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(Units u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.merge(u);
        entityManager.getTransaction().commit();
    }

    
}
