
package ValCollecte;

import classes.factory;
import entites.ValCollecte;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Groupe Anok
 */
public class ValCollecteDAO implements Serializable  {
       
        
EntityManager entityManager = factory.getemf().createEntityManager();

    public ValCollecte selectFind(String code) {
        return entityManager.find(ValCollecte.class, code);
    }

    public List<ValCollecte> selectAll() {
        Query qsniv = entityManager.createNamedQuery("ValCollecte.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<ValCollecte> valcoll = qsniv.getResultList();
//        entityManager.close();
        return valcoll;
    }

    public void insert(ValCollecte u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
//        return u;
    }

    public void delete(ValCollecte u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
//        u = entityManager.merge(u);//<-Important
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(ValCollecte u) {
                        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
//        entityManager.getTransaction().begin();
        entityManager.merge(u);
        entityManager.getTransaction().commit();
//        return u;
    }
    
}
