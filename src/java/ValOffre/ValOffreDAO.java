/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValOffre;

import classes.factory;
import entites.ValOffre;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Groupe Anok
 */
public class ValOffreDAO implements Serializable{
    
           
EntityManager entityManager = factory.getemf().createEntityManager();

    public ValOffre selectFind(String code) {
        return entityManager.find(ValOffre.class, code);
    }

    public List<ValOffre> selectAll() {
        Query qsniv = entityManager.createNamedQuery("ValOffre.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<ValOffre> valoffre = qsniv.getResultList();
//        entityManager.close();
        return valoffre;
    }

    public void insert(ValOffre u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
//        return u;
    }

    public void delete(ValOffre u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(ValOffre u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.merge(u);
        entityManager.getTransaction().commit();
//        return u;
    }
    
}

