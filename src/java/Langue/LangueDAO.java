/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Langue;

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
import entites.Langue;
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
public class LangueDAO implements Serializable {
    
EntityManager entityManager = factory.getemf().createEntityManager();

    public Langue selectFind(String code) {
        return entityManager.find(Langue.class, code);
    }

    public List<Langue> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Langue.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<Langue> langues = qsniv.getResultList();
//        entityManager.close();
        return langues;
    }

    public void insert(Langue u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
//        return u;
    }

    public void delete(Langue u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(Langue u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.merge(u);
        entityManager.getTransaction().commit();
//        return u;
    }


}