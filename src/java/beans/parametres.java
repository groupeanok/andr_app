/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.factory;
import entites.Operateur;
//import entity.GinCandidat;
//import entity.TblGroupe;
//import entity.TblNatureSujet;
//import entity.TblNiveauPays;
//import entity.TblPays;
//import entity.TblSujet;
//import entity.TblTypeSujet;
//import entity.TblUniteAdministrative;
//import entity.TblUniteGeographique;
//import entity.TblVariable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Somda
 */
@ManagedBean(name="parametres")
@RequestScoped //@SessionScoped
public class parametres {

    private final EntityManager entityManager = factory.getemf().createEntityManager();

    public List<Operateur> getTable_Dom() {
        Query qsniv = entityManager.createNamedQuery("Operateur.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<Operateur> data = qsniv.getResultList();
        return data;
    }

//    public List<TblGroupe> getTable_Grp() {
//        Query qsniv = entityManager.createNamedQuery("TblGroupe.findAll"); //, Nivo.class);
////        TblCollecte.findAll
//        List<TblGroupe> data = qsniv.getResultList();
//        return data;
//    }
//
//    public List<TblNatureSujet> getTable_Nat_Suj() {
//        Query qsniv = entityManager.createNamedQuery("TblNatureSujet.findAll"); //, Nivo.class);
////        TblCollecte.findAll
//        List<TblNatureSujet> data = qsniv.getResultList();
//        return data;
//    }
//
//    public List<TblNiveauPays> getTable_Niv_p() {
//        Query qsniv = entityManager.createNamedQuery("TblNiveauPays.findAll"); //, Nivo.class);
////        TblCollecte.findAll
//        List<TblNiveauPays> data = qsniv.getResultList();
//        return data;
//    }

    public List<Operateur> getTable_Pays() {
        Query qsniv = entityManager.createNamedQuery("Operateur.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<Operateur> data = qsniv.getResultList();
        return data;
    }

//    public List<TblSujet> getTable_Sujet() {
//        Query qsniv = entityManager.createNamedQuery("TblSujet.findAll"); //, Nivo.class);
////        TblCollecte.findAll
//        List<TblSujet> data = qsniv.getResultList();
//        return data;
//    }
//
//    public List<TblTypeSujet> getTable_Type_Sujet() {
//        Query qsniv = entityManager.createNamedQuery("TblTypeSujet.findAll"); //, Nivo.class);
////        TblCollecte.findAll
//        List<TblTypeSujet> data = qsniv.getResultList();
//        return data;
//    }
    
//      public List<TblUniteGeographique> getTable_UG() {
//        Query qsniv = entityManager.createNamedQuery("TblUniteGeographique.findAll"); //, Nivo.class);
////        TblCollecte.findAll
//        List<TblUniteGeographique> data = qsniv.getResultList();
//        return data;
//    }  
//    
//            public List<TblUniteAdministrative> getTable_UA() {
//        Query qsniv = entityManager.createNamedQuery("TblUniteAdministrative.findAll"); //, Nivo.class);
////        TblCollecte.findAll
//        List<TblUniteAdministrative> data = qsniv.getResultList();
//        return data;
//    }  
//      
//                  public List<TblVariable> getTable_Var() {
//        Query qsniv = entityManager.createNamedQuery("TblVariable.findAll"); //, Nivo.class);
////        TblCollecte.findAll
//        List<TblVariable> data = qsniv.getResultList();
//        return data;
//    }  
      
}
