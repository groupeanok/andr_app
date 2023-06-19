/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PosteDouane;

import classes.factory;
import entites.PosteDouane;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Groupe Anok
 */
public class PosteDouaneDAO implements Serializable {
   
    
    EntityManager entityManager = factory.getemf().createEntityManager();

    public PosteDouane selectFind(String code) {
        return entityManager.find(PosteDouane.class, code);
    }

    public List<PosteDouane> selectAll() {
//        Query qsniv = entityManager.createNamedQuery("Groupe.findgrp"); //, Nivo.class);
        Query qsniv = entityManager.createNamedQuery("PosteDouane.findAll"); //, Nivo.class);        
        List<PosteDouane> grp = qsniv.getResultList();
//        entityManager.close();
        return grp;
    }
    
   public List<PosteDouane> select_bypays(String pays) {
        Query query = entityManager.createNamedQuery("PosteDouane.findByIdPays"); //, Nivo.class);        
        query.setParameter("idPays", pays);
        return query.getResultList();
    }

    public void insert(PosteDouane u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
    }

    public void delete(PosteDouane u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(PosteDouane u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.merge(u);
        entityManager.getTransaction().commit();
    }

}

