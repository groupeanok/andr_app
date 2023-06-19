/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import classes.factory;
import entites.Session;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Groupe Anok
 */
public class SessionDAO implements Serializable {
 EntityManager entityManager = factory.getemf().createEntityManager();

    public Session selectFind(String code) {
        return entityManager.find(Session.class, code);
    }
    
    

    public List<Session> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Session.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<Session> lst_devise = qsniv.getResultList();
//        entityManager.close();
        return lst_devise;
    }
    
     public List<Session> ChercheSession(String s) {
        Query query = entityManager.createNamedQuery("Session.findByIdMachine");
        query.setParameter("idMachine", s);
        
        List<Session> appui_k = query.getResultList();
        return appui_k;
    }

    public Session insert(Session u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        return u;
    }

    public void delete(Session u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(Session u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.merge(u);
        entityManager.getTransaction().commit();
//        return u;
    }

}
