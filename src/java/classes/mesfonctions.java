package classes;



//import Pays.PaysBean;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
//import objet_anok_PRS.Anok_obj_3;
//import objet_anok.Anok_obj_prs;
//import org.openswing.swing.client.ComboBoxControl;
//import org.openswing.swing.client.CurrencyControl;
//import org.openswing.swing.domains.java.Domain;
//import org.openswing.swing.internationalization.java.EnglishOnlyResourceFactory;
//import org.openswing.swing.internationalization.java.XMLResourcesFactory;
//import org.openswing.swing.mdi.client.InternalFrame;
//import org.openswing.swing.util.client.ClientSettings;

/**
 *
 * @author dell
 */
public class mesfonctions {

//    public static String code = "";
    static EntityManager entityManager = factory.getemf().createEntityManager();

    public static void formattercode(JFormattedTextField ctrl, String racine, int longeur, int nivencours) {
        MaskFormatter formatplan = null;
        String mask = "*";
        String rac = racine;
        int reste = longeur - (rac.trim()).length();
        if (nivencours == 1) {
            reste = longeur;
            rac = "";
        }
        for (int i = 1; i < reste; i++) {
            mask = mask + "*";
        }
        try {
            formatplan = new MaskFormatter((rac.trim()) + mask);
        } catch (ParseException ex) {
            Logger.getLogger(mesfonctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultFormatterFactory factory = new DefaultFormatterFactory(formatplan);
        ctrl.setFormatterFactory(factory);
        ctrl.setValue(racine);
    }
//
//    public static void enttab(javax.swing.table.DefaultTableModel hd) {
//        hd.addColumn((Object) "N°Ordre");
//        hd.addColumn((Object) "Act. Bail");
//        hd.addColumn((Object) "Rub. CILSS");
//        hd.addColumn((Object) "Libellé");
//        hd.addColumn((Object) "N°Compte");
//        hd.addColumn((Object) "Débit");
//        hd.addColumn((Object) "Crédit");
//    }
//
//    public static void entodenc(javax.swing.table.DefaultTableModel hd) {
//        hd.addColumn((Object) "N°Ordre");
//        hd.addColumn((Object) "Libellé");
//        hd.addColumn((Object) "N°Compte");
//        hd.addColumn((Object) "Débit");
//        hd.addColumn((Object) "Crédit");
//    }


    //fonction permettant de donner le resultat d'une requette à trois parametres
    public static BigDecimal valrqsql(EntityManager em, String sql, String ref1, String ref2, String ref3, String ref4) {
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, ref1);
        query.setParameter(2, ref2);
        query.setParameter(3, ref3);
        query.setParameter(4, ref4);
        List v = (List) query.getSingleResult();
        if (v != null) {
            return (BigDecimal) v.get(0);
        } else {
            return BigDecimal.ZERO;
        }
    }

    //fonction permettant de donner le resultat d'une requette à trois parametres
    public static BigDecimal valrqsql(EntityManager em, String sql, String ref1, String ref2, String ref3) {
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, ref1);
        query.setParameter(2, ref2);
        query.setParameter(3, ref3);
        List v = (List) query.getSingleResult();
        if (v != null) {
            return (BigDecimal) v.get(0);
        } else {
            return BigDecimal.ZERO;
        }
    }

    //fonction permettant de donner le resultat d'une requette à trois parametres
    public static BigDecimal valrqsqlunerub(EntityManager em, String sql, String ref1, String ref2) {
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, ref1);
        query.setParameter(2, ref2);
//        query.setParameter(3, ref3);
        List v = (List) query.getSingleResult();
        if (v != null) {
            return (BigDecimal) v.get(0);
        } else {
            return BigDecimal.ZERO;
        }
    }

    //fonction permettant de donner le resultat d'une requette à trois parametres
    public static BigDecimal valrqsqlunerub(EntityManager em, String sql, String ref1) {
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, ref1);
//        query.setParameter(2, ref2);
//        query.setParameter(3, ref3);
        List v = (List) query.getSingleResult();
        if (v != null) {
            return (BigDecimal) v.get(0);
        } else {
            return BigDecimal.ZERO;
        }
    }

    //fonction permettant de donner le resultat d'une requette à trois parametres
    public static Long mtrqsql(EntityManager em, String sql, String ref1, String ref2, String ref3) {
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, ref1);
        query.setParameter(2, ref2);
        query.setParameter(3, ref3);
        List v = (List) query.getSingleResult();
        if (v != null) {
            return (Long) v.get(0);
        } else {
            return 0l;
        }
    }

    public static Long nbelt(EntityManager em, String sql, String ref1, String ref2, String ref3) {
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, ref1);
        query.setParameter(2, ref2);
        query.setParameter(3, ref3);

        try {
            List v = (List) query.getSingleResult();
            Long val = (Long) v.get(0);
            return val;
        } catch (java.lang.ClassCastException ex) {
            return 0l;
        }
    }



    
    public mesfonctions() {
    }


    
    
}
