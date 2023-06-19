/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Groupe;

import classes.factory;
import entites.Groupe;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * C'est le DAO qui permet d'effectuer des opérations portant sur une personne
 * dans la base de données.
 *
 * @author <a href="mailto:djo.mos.contact@gmail.com">djo.mos</a>
 *
 */
public class GroupeDAO implements Serializable {

    
    EntityManager entityManager = factory.getemf().createEntityManager();

    public Groupe selectFind(String code) {
        return entityManager.find(Groupe.class, code);
    }

    public List<Groupe> selectAll() {
//        Query qsniv = entityManager.createNamedQuery("Groupe.findgrp"); //, Nivo.class);
        Query qsniv = entityManager.createNamedQuery("Groupe.findAll"); //, Nivo.class);        
        List<Groupe> grp = qsniv.getResultList();
//        entityManager.close();
        return grp;
    }

    public void insert(Groupe u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
    }

    public void delete(Groupe u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(Groupe u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.merge(u);
        entityManager.getTransaction().commit();
    }

}
