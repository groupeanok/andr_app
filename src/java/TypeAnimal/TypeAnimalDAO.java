/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TypeAnimal;

import classes.factory;
import entites.Animaltypes;
import entites.TypeAnimal;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TypeAnimalDAO implements Serializable {

    EntityManager entityManager = factory.getemf().createEntityManager();

    public TypeAnimal selectFind(String code) {
        return entityManager.find(TypeAnimal.class, code);
    }

    public List<TypeAnimal> selectAll() {
        Query qsniv = entityManager.createNamedQuery("TypeAnimal.findAll"); //, Nivo.class);
        List<TypeAnimal> cycles = qsniv.getResultList();
//        entityManager.close();
        return cycles;
    }
      
       public int der_ind_min(Animaltypes natpro) {
        Query query = entityManager.createNamedQuery("TypeAnimal.findByIdanimalType");
        query.setParameter("animalTypeID", natpro);
       List<TypeAnimal> operateurs = query.getResultList();
        return operateurs.size() + 1;
    }
//    der_ind_min(pa);

    public void insert(TypeAnimal u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
    }

    public void delete(TypeAnimal u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(TypeAnimal u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.merge(u);
        entityManager.getTransaction().commit();
    }
}
