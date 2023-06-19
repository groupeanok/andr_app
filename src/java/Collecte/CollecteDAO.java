/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Collecte;

import classes.factory;
import entites.Collecte;
import entites.PointCollecte;
import java.io.Serializable;
import java.util.Date;
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
public class CollecteDAO implements Serializable {

    EntityManager entityManager = factory.getemf().createEntityManager();

    public List<Collecte> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Collecte.findAll"); 
        List<Collecte> appui_k = qsniv.getResultList();
        return appui_k;
    }
        public List<Collecte> AllCollecteTrie() {
        Query qsniv = entityManager.createNamedQuery("Collecte.find2All"); 
        List<Collecte> appui_k = qsniv.getResultList();
        return appui_k;
    }
        
        
    public List<Collecte> AllCollecteTriebyDate(Date d1, Date d2) {
//                    @NamedQuery(name = "Collecte.findByInterval", query = "SELECT c FROM Collecte c WHERE c.dateCollecte >=:datedeb and c.dateCollecte <=:datefin"),
        Query query = entityManager.createNamedQuery("Collecte.findByInterval");
        query.setParameter("datedeb", d1);
        query.setParameter("datefin", d2);
        List<Collecte> appui_k = query.getResultList();
        return appui_k;
    }
                

    public Collecte selectFind(String code) {
        return entityManager.find(Collecte.class, code);
    }
    
    public List<Collecte> select_type_collecte(String code) {
        Query query = entityManager.createNamedQuery("Collecte.findByTypeCollecte"); //, Nivo.class);
        query.setParameter("typeCollecte", code);
        List<Collecte> appui_k = query.getResultList();
        return appui_k;
    }
   
        public List<Collecte> select_inter_collecte(Date d1, Date d2) {
        Query query = entityManager.createNamedQuery("Collecte.findByInterval"); //, Nivo.class);
        query.setParameter("datedeb", d1);
        query.setParameter("datefin", d2);
        List<Collecte> appui_k = query.getResultList();
//        System.out.println(appui_k.size());
        return appui_k;
    }
    
//        public List<Collecte> select_type_prod(String code) {
//        Query query = entityManager.createNamedQuery("Collecte.findByTypeCollecte"); //, Nivo.class);
//        query.setParameter("typeCollecte", code);
//        List<Collecte> appui_k = query.getResultList();
//        return appui_k;
//    }

    public int select_nb_collecte(PointCollecte pa) {
//        @NamedQuery(name = "Collecte.nbre", query = "SELECT count(g) FROM Collecte g where c.dateCollecte = :dateCollecte AND g.idPtcharge=:idPtcharge"),
        Query query = entityManager.createNamedQuery("Collecte.nbre");
        query.setParameter("dateCollecte", new Date());
        query.setParameter("idPtcollecte", pa);
        Object taille = query.getSingleResult();
//                Object taille = query.getSingleResult();
        if (taille == null) {
            return 1;
        } else {
            Integer i = Integer.valueOf(taille.toString());
            return i + 1;
        }
    }

//    public String select_nb_ins_tot() {
//        Query query = entityManager.createNamedQuery("Formation.nbre");
//        Object taille = query.getSingleResult();
//        if (taille == null) {
//            return "01";
//        } else {
//            Integer i = Integer.parseInt(taille.toString());
//            return String.valueOf(i + 1);
//        }
//    }
    public void insert(Collecte u) {
//        entityManager.getTransaction().begin();
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
//        return u;
    }

    public void delete(Collecte u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(Collecte u) {
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
