
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Offres;

import classes.factory;
import entites.Offre;
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
public class OffresDAO implements Serializable {

    EntityManager entityManager = factory.getemf().createEntityManager();

    public List<Offre> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Offre.findAll"); 
        List<Offre> appui_k = qsniv.getResultList();
//         System.out.println("Voila le nombre Total appui : " + appui_k.size());
//entityManager.close();
        return appui_k;
    }

    public Offre selectFind(String code) {
        return entityManager.find(Offre.class, code);
    }
    
    public List<Offre> select_type_offre(String code) {
        Query query = entityManager.createNamedQuery("Offre.findByTypeOffre"); //, Nivo.class);
        query.setParameter("typeOffre", code);
        List<Offre> appui_k = query.getResultList();
        return appui_k;
    }
    
    public List<Offre> select_type_offre(String code, String nat) {
        String sql = "Offre.findByTypeOffrev";
        Query query = entityManager.createNamedQuery("Offre.findByTypeOffrea");
        if (nat.equals("VI")) {
            query = entityManager.createNamedQuery(sql); //, Nivo.class);
        }
        query.setParameter("typeOffre", code);
        List<Offre> appui_k = query.getResultList();
        return appui_k;
    }
    

    public int select_nb_offre(String typeco) {
//        @NamedQuery(name = "Collecte.nbre", query = "SELECT count(g) FROM Collecte g where c.dateCollecte = :dateCollecte AND g.idPtcharge=:idPtcharge"),
        Query query = entityManager.createNamedQuery("Offre.nbre");
        query.setParameter("dateCollecte", new Date());
        query.setParameter("typeOffre", typeco);
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
    public void insert(Offre u) {
//        entityManager.getTransaction().begin();
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
//        return u;
    }

    public void delete(Offre u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(Offre u) {
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
