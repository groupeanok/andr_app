/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Association;

import classes.factory;
import entites.Association;
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
public class AssoDAO implements Serializable {
EntityManager entityManager = factory.getemf().createEntityManager();
    
    public List<Association> selectAll() {
        Query qsniv = entityManager.createNamedQuery("Association.findAll"); //, Nivo.class);        
        List<Association> operateurs = qsniv.getResultList();
//        entityManager.close();
        return operateurs;
    }
    
    public Association selectFind(String code) {
        return entityManager.find(Association.class, code);
    }
//    @NamedQuery(name = "Association.nbre", query = "SELECT count(g) FROM Association g where g.codepays =:codepays"),
    
    public int der_ind_min(Pays pays) {
        Query query = entityManager.createNamedQuery("Association.nbre");
        query.setParameter("codepays", pays);
        Object taille = query.getSingleResult();
        if (taille == null) {
            return 1;
        } else {
            Integer i = Integer.valueOf(taille.toString());
            return i + 1;
        }
    }
        
        public String select_nb_asso() {
        Query query = entityManager.createNamedQuery("Association.nbre");
        Object taille = query.getSingleResult();
        if (taille == null) {
            return "01";
        } else {
            Integer i = Integer.valueOf(taille.toString());
            return String.valueOf(i + 1);
        }
    }
    
    public Association insert(Association u) {
        EntityManager em = factory.getemf().createEntityManager();
//        entityManager.getTransaction().begin();
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(u);
        em.getTransaction().commit();
        return u;
    }

    public void delete(Association u) {
//        entityManager.getTransaction().begin();
                if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public Association update(Association u) {
//        entityManager.getTransaction().begin();
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        u = entityManager.merge(u);
        entityManager.getTransaction().commit();
        return u;
    }
}