/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Devises;

/**
 *
 * @author Somda
 */
//public class OperateurDAO {
//    

//}

//package model.dao;
import classes.factory;
//import entites.AnokLot;
import entites.Devise;
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
public class DevisesDAO implements Serializable {
EntityManager entityManager = factory.getemf().createEntityManager();

    public Devise selectFind(String code) {
        return entityManager.find(Devise.class, code);
    }

    public List<Devise> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Devise.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<Devise> lst_devise = qsniv.getResultList();
//        entityManager.close();
        return lst_devise;
    }

    public Devise insert(Devise u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        return u;
    }

    public void delete(Devise u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public Devise update(Devise u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        u = entityManager.merge(u);
        entityManager.getTransaction().commit();
        return u;
    }


}