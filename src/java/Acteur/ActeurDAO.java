/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Acteur;

/**
 *
 * @author Somda
 */
//public class OperateurDAO {
//    
//}
//package model.dao;
import classes.factory;
//import static classes.gestionmail.mail_exp_candidature;
import entites.Acteur;
import entites.Pays;
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
public class ActeurDAO implements Serializable {
//private static final String JPA_UNIT_NAME = "jsf-crud";
//private EntityManager entityManager;

    EntityManager entityManager = factory.getemf().createEntityManager();

    public Acteur selectFind(String code) {
        return entityManager.find(Acteur.class, code);
    }  
    
        public List<Acteur> select_bypays(Pays pays) {
        Query query = entityManager.createNamedQuery("Acteur.findbypays"); //, Nivo.class);        
        query.setParameter("nationalite", pays);
        return query.getResultList();
    } 
    
    public int select_nb_col(Pays pa) {
        Query query = entityManager.createNamedQuery("Acteur.nbre");
        query.setParameter("nationalite", pa);
//        query.setParameter("typerealisation", ty);
        Object taille = query.getSingleResult();
        if (taille == null) {
            return 1;
        } else {
            Integer i = Integer.valueOf(taille.toString());
            return i + 1;
        }
    }

    public List<Acteur> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Acteur.findAll"); //, Nivo.class);
//        entityManager.close();
//        TblCollecte.findAll
        List<Acteur> operateurs = qsniv.getResultList();
        return operateurs;
    }

    public Acteur insert(Acteur u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        return u;
    }

    public void delete(Acteur u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public Acteur update(Acteur u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        u = entityManager.merge(u);
        entityManager.getTransaction().commit();
        return u;
    }
}
