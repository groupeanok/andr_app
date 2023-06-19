/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OffresConclus;

import classes.factory;
import entites.Offreconclus;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Groupe Anok
 */
public class OffresConclusDAO implements Serializable{
    
               
EntityManager entityManager = factory.getemf().createEntityManager();

    public Offreconclus selectFind(String code) {
        return entityManager.find(Offreconclus.class, code);
    }

    public List<Offreconclus> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Offreconclus.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<Offreconclus> valoffre = qsniv.getResultList();
//        entityManager.close();
        return valoffre;
    }

    public void insert(Offreconclus u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
//        return u;
    }

    public void delete(Offreconclus u) {
            if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(Offreconclus u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.merge(u);
        entityManager.getTransaction().commit();
//        return u;
    }
    
}
