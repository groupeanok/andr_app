/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Point_Collecte;

import classes.factory;
import entites.Pays;
import entites.PointCollecte;
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
public class Point_CollecteDAO implements Serializable {
    
EntityManager entityManager = factory.getemf().createEntityManager();


    public PointCollecte selectFind(String code) {
        return entityManager.find(PointCollecte.class, code);
    }

    public List<PointCollecte> selectAll() {
        Query qsniv = entityManager.createNamedQuery("PointCollecte.findAll");
        List<PointCollecte> operateurs = qsniv.getResultList();
        return operateurs;
    }
    
//        public List<PointCollecte> select_min_pays(String pa) {
//        Query query = entityManager.createNamedQuery("PointCollecte.findByPays"); //, Nivo.class);
//        query.setParameter("idpays", pa);
////        TblCollecte.findAll
//        List<PointCollecte> operateurs = query.getResultList();
//        return operateurs;
//    }
    
    public int der_ind_min(Pays pa) {
        Query query = entityManager.createNamedQuery("PointCollecte.dernordre");
        query.setParameter("idPays", pa);
        Object taille = query.getSingleResult();
        if (taille == null) {
            return 1;
        } else {
            Integer i = Integer.valueOf(taille.toString());
            return i + 1;
        }
    }

    public PointCollecte insert(PointCollecte u) {
//        entityManager.getTransaction().begin();
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        return u;
    }

    public void delete(PointCollecte u) {
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public PointCollecte update(PointCollecte u) {
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        u = entityManager.merge(u);
        entityManager.getTransaction().commit();
        return u;
    }


}