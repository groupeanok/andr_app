/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Alerte;

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
import entites.Alertetype;
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
public class AlerteDAO implements Serializable {
//private static final String JPA_UNIT_NAME = "jsf-crud";
//private EntityManager entityManager;

    EntityManager entityManager = factory.getemf().createEntityManager();

    public Alertetype selectFind(String code) {
        return entityManager.find(Alertetype.class, code);
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

    public List<Alertetype> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Alertetype.findAll"); //, Nivo.class);
//        entityManager.close();
//        TblCollecte.findAll
        List<Alertetype> operateurs = qsniv.getResultList();
        return operateurs;
    }

    public Alertetype insert(Alertetype u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        return u;
    }

    public void delete(Alertetype u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public Alertetype update(Alertetype u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        u = entityManager.merge(u);
        entityManager.getTransaction().commit();
        return u;
    }
}
