/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Specialite;

import classes.factory;
import entites.Specialite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Groupe Anok
 */
public class SpecialiteDAO {
    
    EntityManager entityManager = factory.getemf().createEntityManager();
    
    public List<Specialite> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Specialite.findAll"); //, Nivo.class);        
        List<Specialite> specialite = qsniv.getResultList();
//        entityManager.close();
        return specialite;
    }
    
    public Specialite selectFind(String code) {
        return entityManager.find(Specialite.class, code);
    }
//    @NamedQuery(name = "Association.nbre", query = "SELECT count(g) FROM Association g where g.codepays =:codepays"),
   
    
    public Specialite insert(Specialite u) {
        EntityManager em = factory.getemf().createEntityManager();
//        entityManager.getTransaction().begin();
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(u);
        em.getTransaction().commit();
        return u;
    }

    public void delete(Specialite u) {
//        entityManager.getTransaction().begin();
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public Specialite update(Specialite u) {
//        entityManager.getTransaction().begin();
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        u = entityManager.merge(u);
        entityManager.getTransaction().commit();
        return u;
    }
    
}
