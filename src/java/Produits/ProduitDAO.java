/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits;

import classes.factory;
import entites.NatureProduit;
import entites.Produit;
//import entites.CraFiliere;
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
public class ProduitDAO implements Serializable {
EntityManager entityManager = factory.getemf().createEntityManager();
    
    public List<Produit> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Produit.findAll"); //, Nivo.class);
        List<Produit> operateurs = qsniv.getResultList();
//        entityManager.close();
        return operateurs;
    }
    public Produit selectFind(String code) {
        return entityManager.find(Produit.class, code);
//        Query query = entityManager.createNamedQuery("CraFiliere.findByCodefiliere"); //, Nivo.class);
//        query.setParameter("codefiliere", code);
//                        List<CraFiliere> lst = query.getResultList();
//        if (lst.isEmpty()) {
//            return null;
//        } else {
//            return lst.get(0);
//        }
//        return (CraFiliere) query.getSingleResult();
    }
        
    public String select_nb_ins_tot() {
        Query query = entityManager.createNamedQuery("Produit.nbre");
        Object taille = query.getSingleResult();
        if (taille == null) {
            return "01";
        } else {
            Integer i = Integer.valueOf(taille.toString());
            return String.valueOf(i + 1);
        }
    }
    
    public int der_ind_min(NatureProduit natpro) {
        Query query = entityManager.createNamedQuery("Produit.findByIdNatproduit");
        query.setParameter("idNatproduit", natpro);
        List<Produit> operateurs = query.getResultList();
        return operateurs.size() + 1;
    }
//    der_ind_min(pa);
    
    public void insert(Produit u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
//        return u;
    }

    public void delete(Produit u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(Produit u) {
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