/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Moyen_transp;


import classes.factory;
import entites.MoyenTrans;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Moyen_transDAO implements Serializable {
EntityManager entityManager = factory.getemf().createEntityManager();

    public MoyenTrans selectFind(String code) {
        return entityManager.find(MoyenTrans.class, code);
//        Query query = entityManager.createNamedQuery("CraCycle.findByIdcycle"); //, Nivo.class);
//        query.setParameter("idcycle", code);
//        List<CraCycle> lst = query.getResultList();
//        if (lst.isEmpty()) {
//            return null;
//        } else {
//            return lst.get(0);
//        }
//        CraCycle oper = (CraCycle) query.getSingleResult();
//        return oper;
    }

    public List<MoyenTrans> selectAll() {
        Query qsniv = entityManager.createNamedQuery("MoyenTrans.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<MoyenTrans> operateurs = qsniv.getResultList();
//        entityManager.close();
        return operateurs;
    }

    public void insert(MoyenTrans u) {
              if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
//        return u;
    }

    public void delete(MoyenTrans u) {
              if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(MoyenTrans u) {
              if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = 
                entityManager.merge(u);
        entityManager.getTransaction().commit();
//        return u;
    }


}