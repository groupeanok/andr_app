/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperateurDetail;


import classes.factory;
import entites.OperateurDetail;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Groupe Anok
 */
public class OperateurDetailDAO implements Serializable {
    
    EntityManager entityManager = factory.getemf().createEntityManager();
     
    public OperateurDetail selectFind(String code) {
        return entityManager.find(OperateurDetail.class, code);
    }

    public List<OperateurDetail> selectAll() {
        Query qsniv = entityManager.createNamedQuery("OperateurDetail.findAll");
        List<OperateurDetail> operateurs = qsniv.getResultList();
        return operateurs;
    }
     public List<OperateurDetail> selectcpof_user(String code_user) {
        Query query = entityManager.createNamedQuery("OperateurDetail.findByCodeoper");
        query.setParameter("codeoper", code_user);
        List<OperateurDetail> cpsof_user = query.getResultList();
        return cpsof_user;
    }
    
    
    
//        public List<OperateurDetail> select_min_pays(String pa) {
//        Query query = entityManager.createNamedQuery("OperateurDetail.findByPays"); //, Nivo.class);
//        query.setParameter("idpays", pa);
////        TblCollecte.findAll
//        List<OperateurDetail> operateurs = query.getResultList();
//        return operateurs;
//    }
//    
//    public int der_ind_min(Pays pa) {
//        Query query = entityManager.createNamedQuery("OperateurDetail.dernordre");
//        query.setParameter("idPays", pa);
//        Object taille = query.getSingleResult();
//        if (taille == null) {
//            return 1;
//        } else {
//            Integer i = Integer.parseInt(taille.toString());
//            return i + 1;
//        }
//    }

    public OperateurDetail insert(OperateurDetail u) {
//        entityManager.getTransaction().begin();
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        return u;
    }

    public void delete(OperateurDetail u) {
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public OperateurDetail update(OperateurDetail u) {
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        u = entityManager.merge(u);
        entityManager.getTransaction().commit();
        return u;
    }

    
}
