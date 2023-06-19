/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package District;

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
import entites.DaDistrict;
import entites.DaRegion;
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
public class DistrictDAO implements Serializable {
EntityManager entityManager = factory.getemf().createEntityManager();

    public DaDistrict selectFind(String code) {
        return entityManager.find(DaDistrict.class, code);
    }

    public List<DaDistrict> selectAll() {
        Query qsniv = entityManager.createNamedQuery("DaDistrict.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<DaDistrict> lst_district = qsniv.getResultList();
//        entityManager.close();
        return lst_district;
    }
    
        public List<DaDistrict> select_min_pays(String pa) {
        Query query = entityManager.createNamedQuery("DaDistrict.findByPays"); //, Nivo.class);
        query.setParameter("idpays", pa);
//        TblCollecte.findAll
        List<DaDistrict> operateurs = query.getResultList();
        return operateurs;
    }
    
    public int der_ind_min(DaRegion pa) {
        Query query = entityManager.createNamedQuery("DaDistrict.dernordre");
        query.setParameter("idRegion", pa);
        Object taille = query.getSingleResult();
        if (taille == null) {
            return 1;
        } else {
            Integer i = Integer.valueOf(taille.toString());
            return i + 1;
        }
    }

    public DaDistrict insert(DaDistrict u) {
//        entityManager.getTransaction().begin();
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        return u;
    }

    public void delete(DaDistrict u) {
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public DaDistrict update(DaDistrict u) {
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        u = entityManager.merge(u);
        entityManager.getTransaction().commit();
        return u;
    }


}