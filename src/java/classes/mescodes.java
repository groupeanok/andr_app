/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

//import cilssimis.entites.Exercice;
//import cilssimis.entites.Journal;
//import anok_imis.entites.Nature;
//import anok_imis.entites.Plconvention;
//import anok_imis.entites.Site;
//import entites.Annee;
//import entites.InsThematique;
//import entites.Ministere;
import entites.Pays;
//import entites.Plconvention;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
//import static org.apache.commons.lang.StringUtils.leftPad;
//import static org.apache.commons.lang.StringUtils.leftPad
//import javax.swing.text.MaskFormatter;

/**
 *
 * @author Mohamed
 */
public class mescodes {

    // calcul du code de l'engagement, avance, paiement, reversement
    //calcul du code necessaire à la codification des parametres
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

    // Requette permettant de determiner le numero de la derniere vente ou Appro de la journée
    public static Long dernordre(EntityManager em, String req, Date date) {
        Query query = em.createNamedQuery(req);
        query.setParameter("date", date);
        try {
            return (Long) query.getSingleResult();
        } catch (NullPointerException ex) {
            return 0l;
        }
    }

    public static String gencode(String ref, Integer x) {
        String nor; //= ref; //an.toString();
        if (x < 9) {
            nor = "0" + x;
        } else //    {
        {
            nor = "" + x;
        }
        ref = ref.replace("/", "");
        String refpi = ref + "-" + nor; //String.Valueof(an);
        return refpi.replace(" ", ""); //refpi;
    }

//    public static String RandomAlphaNum(int length) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = length; i > 0; i -= 12) {
//            int n = min(12, abs(i));
//            sb.append(leftPad(Long.toString(round(random() * pow(36, n)), 36), n, '0'));
//        }
//        return sb.toString();
//    }

//    public static class RandomString {
//
//        static final char[] symbols = new char[36];
//       private final Random random = new Random();
//
//        private final char[] buf;
//        static {
//            for (int idx = 0; idx < 10; ++idx) {
//                symbols[idx] = (char) ('0' + idx);
//            }
//            for (int idx = 10; idx < 36; ++idx) {
//                symbols[idx] = (char) ('a' + idx - 10);
//            }
//        }
//
// 
//
//        public RandomString(int length) {
//            if (length < 1) {
//                throw new IllegalArgumentException("length < 1: " + length);
//            }
//            buf = new char[length];
//        }
//
//        public String nextString() {
//            for (int idx = 0; idx < buf.length; ++idx) {
//                buf[idx] = symbols[random.nextInt(symbols.length)];
//            }
//            return new String(buf);
//        }
//    }
    ///------------------------------------
    // Gestion des immobilisations
    //-------------------------------------
//    public static String codifimmo(Nature nat, Site site, Plconvention conv, int ref) {
//        String code = nat.getCnat() + conv.getCodeconv() + site.getCodesite() + DateHelper.annee() + DateHelper.mois() + ref;
//        return code.replace(" ", "");
//    }
    
        ///------------------------------------
    // Gestion des immobilisations
    //-------------------------------------
//    public static String codifimmo(Pays pa, Annee an, InsThematique dom, String conv, int ref) {
//        String code = pa.getIdPays()+ an.getIdannee()+ conv + dom.getIdtheme()+ ref;
//        return code.replace(" ", "");
//    }
//    
//        ///------------------------------------
//    // Gestion des immobilisations
//    //-------------------------------------
//    public static String code_etudiant(Pays pa, Annee an, int ref) {
//        String code = pa.getIdPays()+ an.getIdannee()+ ref;
//        return code.replace(" ", "");
//    }
    
//        public static String code_appui_cata(Plconvention an, Pays pa, int ref) {
//        String code = an.getCodeconv()+ pa.getIdPays()+ ref;
//        return code.replace(" ", "");
//    }
    
    public static String code_point_foc(Pays pa, int ref) {
        String code = pa.getIdPays() + ref;
        return code.replace(" ", "");
    }
    
//    public static String code_structure(Ministere pa, int ref) {
//        String code = pa.getIdMinistere()+ ref;
//        return code.replace(" ", "");
//    }
    
    
    private mescodes() {
    }
}
