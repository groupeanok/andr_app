/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Param_sys;

import entites.Parametres;
import classes.factory;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Anok_obj_ParamsysDAO implements Serializable {
    EntityManager entityManager = factory.getemf().createEntityManager();

    public Parametres selectActif() {
        Query query = entityManager.createNamedQuery("Parametres.findByActif");
//        query.setParameter("actif", true);
        try {
            return (Parametres) query.getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
