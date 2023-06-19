/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Region;

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
import entites.DaRegion;
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
public class RegionDAO implements Serializable {
EntityManager entityManager = factory.getemf().createEntityManager();

    public DaRegion selectFind(String code) {
        return entityManager.find(DaRegion.class, code);
    }

    public List<DaRegion> selectAll() {
        Query qsniv = entityManager.createNamedQuery("DaRegion.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<DaRegion> operateurs = qsniv.getResultList();
//        entityManager.close();
        return operateurs;
    }
    
//        public List<DaRegion> select_min_pays(String pa) {
//        Query query = entityManager.createNamedQuery("DaRegion.findByPays"); //, Nivo.class);
//        query.setParameter("idpays", pa);
////        TblCollecte.findAll
//        List<DaRegion> operateurs = query.getResultList();
//        return operateurs;
//    }
    
    public int der_ind_min(Pays pa) {
        Query query = entityManager.createNamedQuery("DaRegion.dernordre");
        query.setParameter("idPays", pa);
        Object taille = query.getSingleResult();
        if (taille == null) {
            return 1;
        } else {
            Integer i = Integer.valueOf(taille.toString());
            return i + 1;
        }
    }

    public DaRegion insert(DaRegion u) {
//        entityManager.getTransaction().begin();
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        return u;
    }

    public void delete(DaRegion u) {
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public DaRegion update(DaRegion u) {
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        u = entityManager.merge(u);
        entityManager.getTransaction().commit();
        return u;
    }


}