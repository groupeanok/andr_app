/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nature_Produit;

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
import entites.NatureProduit;
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
public class Nature_ProduitDAO implements Serializable {

    EntityManager entityManager = factory.getemf().createEntityManager();

    public NatureProduit selectFind(String code) {
        return entityManager.find(NatureProduit.class, code);
    }

    public List<NatureProduit> selectAll() {
        Query qsniv = entityManager.createNamedQuery("NatureProduit.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<NatureProduit> operateurs = qsniv.getResultList();
//        entityManager.close();
        return operateurs;
    }

    public NatureProduit insert(NatureProduit u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        return u;
    }

    public void delete(NatureProduit u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public NatureProduit update(NatureProduit u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        u = entityManager.merge(u);
        entityManager.getTransaction().commit();
        return u;
    }

}
