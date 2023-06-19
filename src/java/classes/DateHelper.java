//package ####.util;
package classes;

//import cilssimis.entites.Aldrtalerte;
//import anok_imis.entites.Alertetype;
//import anok_imis.entites.Exercice;
//import anok_imis.entites.Journal;
//import anok_imis.entites.Plconvention;
//import anok_imis.entites.Site;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
//import org.apache.tools.ant.util.DateUtils;
//import org.jdesktop.swingx.calendar.DateUtils;
//import javax.swing.JOptionPane;

/** Helper de date (sur les Calendar) pour effectuer des operations sur les jours ouvrables **/
public abstract class DateHelper
{
    /** Permet de d�finir les jours non ouvrables dans une semaine **/

    /** DIMANCHE **/
//Date cejour = new Date();
    
    
    public static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");

    public static Date cejour = new Date(); //, dt;
    
    public static final int DIMANCHE = 1;
    /** LUNDI **/
    public static final int LUNDI    = 2;
    /** MARDI **/
    public static final int MARDI    = 4;
    /** MERCREDI **/
    public static final int MERCREDI = 8;
    /** JEUDI **/
    public static final int JEUDI    = 16;
    /** VENDREDI **/
    public static final int VENDREDI = 32;
    /** SAMEDI **/
    public static final int SAMEDI   = 64;
    /** Definitions des jours NON ouvrables dans la semaine **/
    private static int joursNonOuvrables = SAMEDI | DIMANCHE;
    /** Nombre de jours ouvrables dans une semaine **/
    private static int nbrJoursOuvrablesParSemaine = 5;
//    private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /** D�finit la liste des jours non ouvrables pour le systemes
     * @return 
     **/
    
    public Date getCejour() {
        return cejour;
    }

    public void setCejour(Date serv) {
        cejour = serv;
    }
    
    
    
    
    public static void setNonOuvrables(int flags)
    { joursNonOuvrables = flags;

      /** Calcul du nombre de jours ouvrables dans une semaine **/
      nbrJoursOuvrablesParSemaine = 7;
      for(int i=1;i<128;i=i*2)   if((joursNonOuvrables & i) == i) nbrJoursOuvrablesParSemaine--;
    }

    /** Ajoute n "nombre" de jours ouvrables (sans gerer les jours f�ri�s) � une date
     * @param calendar Date � laquel on veut ajouter les jours ouvrables
     * @param nombre Nombre de jours ouvrables a ajouter (sans gerer les jours f�ri�s)
     **/
    private static void addJoursOuvrables(Calendar calendar, int nombre)
    {
        /** Initialisation du nombre de jours (dans l'absolu) de deplacement � effectuer **/
        int n = Math.abs(nombre);

        /** Initialisation du mode de deplacement (arriere / avant) **/
        boolean forwardMode = (nombre > 0);

        /** Calcul du jour de depart dans la semaine (Dimanche = 1,Lundi = 2,...,Samedi = 7) **/
        int jourDepart        = calendar.get(Calendar.DAY_OF_WEEK);

        /** Initialisation du nombre de jours effectif � ajouter **/
        int nbrJoursEffectifs = 0;

        /** Initialisation du nombre de jours "ouvrables" correspondant aux nombre de jours � ajouter **/
        int nbrJoursOuvrables = 0;

        /** Nombre de jours ouvrables devant �tre encore ajout�s **/
        int nbrJoursOuvrablesRestant = 0;

        /** Deplacement vers Samedi suivant (ou precedent) **/
        int jour = jourDepart;
        while(true)
        {   /** Incrementation ou decrementation du jour dans la semaine **/
            if(forwardMode) jour++;
            else            jour--;
            if(jour == 8) jour = 1;
            if(jour == 0) jour = 7;

            /** Un jour effectif de plus **/
            nbrJoursEffectifs++;

            /** Verification si celui ci est ouvrable ou non **/
            int currentDay = (int)Math.pow(2, jour - 1);
            if((joursNonOuvrables & currentDay) == 0) nbrJoursOuvrables++;  // Un jour ouvrable de plus

            /** Si on tombe sur le nombre de jours ouvrable qu'on d�sire, alors on peut s'arretter **/
            if(nbrJoursOuvrables == n) break;

            /** Si on tombe le Samedi, on s'arrette (puisqu'on essaie d'atteindre le 1er Samedi) **/
            if(jour == Calendar.SATURDAY) break;
        }

        /** Si le nombre de jours ouvrables n'est pas celui recherch�, alors il faut continuer la recherce **/
        if(nbrJoursOuvrables != n)
        {
            /** Calculons le nombre de jours ouvrables devant encore �tre ajout�s **/
            nbrJoursOuvrablesRestant = n - nbrJoursOuvrables;

            /** Calculons le nombre de semaine pleine pouvant �tre ajout�e **/
            int nbrFullWeeks = nbrJoursOuvrablesRestant / nbrJoursOuvrablesParSemaine;
            if(nbrJoursOuvrablesRestant%nbrJoursOuvrablesParSemaine == 0) nbrFullWeeks--;

            /** Ajoutons ces semaines pleines au jours effectifs, et on sera � la derniere semaine **/
            nbrJoursEffectifs += (nbrFullWeeks * 7);

            /** Mettons � jour le nombre de jours ouvrables qu'il restera � gerer apres ca **/
            nbrJoursOuvrablesRestant -= (nbrFullWeeks * nbrJoursOuvrablesParSemaine);
            nbrJoursOuvrables        += (nbrFullWeeks * nbrJoursOuvrablesParSemaine);
        }

        /** On peut ajouter les jours effectifs calcul�s (nbrJoursEffectifs)
         *  Ce qui correspondra au nombre de jours "ouvrables" nbrJoursOuvrables
         **/
        if(!forwardMode) nbrJoursEffectifs = nbrJoursEffectifs * -1;
        calendar.add(Calendar.DAY_OF_MONTH,nbrJoursEffectifs);

        /** Si le nombre de jours "ouvrables" � gerer n'est pas nulle, on relance la procedure **/
        if(nbrJoursOuvrablesRestant > 0)
        {  if(!forwardMode) nbrJoursOuvrablesRestant = nbrJoursOuvrablesRestant * -1;
           addJoursOuvrables(calendar, nbrJoursOuvrablesRestant);
        }
    }

    /** Soustrait un certains nombre de jours ouvrables � une date<br>
     *  @param calendar Date dont on doit soustraire des jours ouvrables
     *  @param nombre Nombre � soustraire
     **/
    public static void subJoursOuvrables(Calendar calendar,int nombre) throws Exception
    { addJoursOuvrables(calendar,(-1)*nombre);    }

    /** Calcule la diff�rence entre deux dates en nombre de jours ouvrables<br>
     *  @param debut Date de d�but
     *  @param fin   Date de fin
     *  @return la diff�rence entre les 2 dates en nbre de jours ouvrables
     **/
    public int diffJoursOuvrables(Calendar debut,Calendar fin)
    {   int Result = 0;

        /** Determination de la date inferieur **/
        Calendar minDate = debut;
        Calendar maxDate = fin;
        if( debut.after(fin) )
        { minDate = fin;
          maxDate = debut;
        }

        /** Nombre de jour de diff�rence entre les 2 dates (sans prendre en compte la notion "ouvrable"**/
        int diffJoursEffectifs = (int)diffJours(minDate,maxDate);

        /** Calcul de la differente en nombre de jours ouvrables � partir du nombre de jours effectifs **/
        if(diffJoursEffectifs > 0)
          Result = diffJoursOuvrablesWith(minDate,diffJoursEffectifs);

        /** Retourne le r�sultat **/
        return Result;
    }

    /** M�thode interne s'occupant de faire la diff�rence entre deux dates en nombre de jours ouvrables (sans gerer les jours f�ri�s)
     * @param stop  Date de fin
     * @param diffJour  Nombre de jour de diff�rences entre les 2 dates
     * @return int avec le "nombre" de jours ouvrables de diff�rence
     **/
    private static int diffJoursOuvrablesWith(Calendar start, int diffJoursEffectif)
    {
        /** Calcul du jour de depart dans la semaine (Dimanche = 1,Lundi = 2,...,Samedi = 7) **/
        int jourDepart        = start.get(Calendar.DAY_OF_WEEK);

        /** Initialisation du nombre de jours effectif � ajouter **/
        int nbrJoursEffectifs = 0;

        /** Initialisation du nombre de jours "ouvrables" correspondant aux nombre de jours de diff�rence **/
        int nbrJoursOuvrables = 0;

        /** Nombre de jours effectifs devant �tre encore ajout�s **/
        int nbrJoursEffectifsRestant = 0;

        /** Deplacement vers Samedi suivant (ou precedent) **/
        int jour = jourDepart;
        while(true)
        {   /** Si on tombe sur le nombre de jours effectifs qu'on d�sire, alors on peut s'arretter **/

            /** Incrementation ou decrementation du jour dans la semaine **/
            jour++;
            if(jour == 8) jour = 1;

            /** Un jour effectif de plus **/
            nbrJoursEffectifs++;
            if(nbrJoursEffectifs == diffJoursEffectif) break;

            /** Verification si celui ci est ouvrable ou non **/
            int currentDay = (int)Math.pow(2, jour - 1);
            if((joursNonOuvrables & currentDay) == 0) nbrJoursOuvrables++;  // Un jour ouvrable de plus

            /** Si on tombe le Samedi, on s'arrette (puisqu'on essaie d'atteindre le 1er Samedi) **/
            if(jour == SAMEDI) break;
        }

        /** Si le nombre de jours ouvrables n'est pas celui recherch�, alors il faut continuer la recherce **/
        if(nbrJoursEffectifs != diffJoursEffectif)
        {
            /** Calculons le nombre de jours ouvrables devant encore �tre ajout�s **/
            nbrJoursEffectifsRestant = diffJoursEffectif - nbrJoursEffectifs;

            /** Calculons le nombre de semaine pleine pouvant �tre ajout�e **/
            int nbrFullWeeks = nbrJoursEffectifsRestant / 7;
            if(nbrJoursEffectifsRestant%7 == 0) nbrFullWeeks--;

            /** Ajoutons ces semaines pleines au jours ouvrables, et on sera � la derniere semaine **/
            nbrJoursOuvrables += (nbrFullWeeks * nbrJoursOuvrablesParSemaine);

            /** Mettons � jour le nombre de jours ouvrables qu'il restera � gerer apres ca **/
            nbrJoursEffectifsRestant -= (nbrFullWeeks * 7);
            nbrJoursEffectifs        += (nbrFullWeeks * 7);
        }

        /** On peut ajouter les jours effectifs calculer (nbrJoursEffectifs)
         *  Ce qui correspondra au nombre de jours "ouvrables" nbrJoursOuvrables
         **/

        /**
         * Si le nombre de jours "ouvrables" � gerer n'est pas nulle, on relance
         * la procedure *
         */
        int joursOuvrablesSupplementaire = 0;
        if (nbrJoursEffectifsRestant > 0) {
            Calendar tmpCalendar = new GregorianCalendar();
            tmpCalendar.setTime(start.getTime());
            tmpCalendar.add(Calendar.DAY_OF_MONTH, nbrJoursEffectifs);

            joursOuvrablesSupplementaire = diffJoursOuvrablesWith(tmpCalendar, nbrJoursEffectifsRestant);
        }
        /**
         * Retour du r�sultat *
         */
        return nbrJoursOuvrables + joursOuvrablesSupplementaire;
    }

    /** Retourne la difference en nombre de jours entre deux dates **/
    private static long diffJours(Calendar date1,Calendar date2)
    {   long diff; //  = 0;
        long a = date1.getTimeInMillis();
        long b = date2.getTimeInMillis();

        if(a > b) diff = a - b;
        else      diff = b - a;

        return (diff / 86400000);
    }
    
        static final long ONE_HOUR = 60 * 60 * 1000L;

    public static long daysBetween(Date d1, Date d2) {
        long diff = (d1.getTime() - d2.getTime() + ONE_HOUR) / (ONE_HOUR * 24);
//        System.out.println(diff);
        return diff; //((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
    }
    
//    /**
//     * Retourne la difference en nombre de jours entre deux dates *
//     */
//    public static long diffJours(Date date1, Date date2) {
////        long diff  = 0;
////        long a = date1.getTimeInMillis();
////        long b = date2.getTimeInMillis();
//        // Get msec from each, and subtract.
////    long diff = date1.getTime() - date2.getTime();
//        long diff = date1.getDate() - date2.getDate();
////    long diff = date1.
//
//        System.out.println("The 21st century (up to " + date1 + ") is "
//                + (diff / (1000 * 60 * 60 * 24)) + " days old.");
////    
////        if(a > b) diff = a - b;
////        else      diff = b - a;
////        System.out.println(diff);
//        return (diff / 86400000);
//    }

    public static int jourmois() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        return day;
    }
    
    public static int moisencours() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    public static String extranne(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt); // = dt;
        return Integer.toString(cal.get(Calendar.YEAR));
//        return Integer.toString((Calendar.getInstance()).setTime(dt));
    }
    
    public static int annee() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public static int mois() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }
    

    public static int dernierjour() {
        Calendar cal = Calendar.getInstance();
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static Date premdaythisan() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        try {
            return df.parse("01/01/" + year);
        } catch (ParseException ex) {
            Logger.getLogger(DateHelper.class.getName()).log(Level.SEVERE, null, ex);
            return new Date();
        }
    }
    
//    public static Date dernval(EntityManager em, Journal journ,Site site,Plconvention convention, Exercice exercice) {
//        Query query = em.createNamedQuery("Validation.dernval");
//        query.setParameter("site", site);
//        query.setParameter("exercice", exercice);
//        query.setParameter("convention", convention);
//        query.setParameter("journal", journ);
//        
//        //query.setMaxResults(1);
//        Date dt = (Date) query.getSingleResult();
//        try {
//            return dt;
//        } catch (NoResultException ex) {
//            return new Date();
//        }
//    }
    
    public static Date derdaythisan() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        try {
            return df.parse("31/12/" + year);
        } catch (ParseException ex) {
            Logger.getLogger(DateHelper.class.getName()).log(Level.SEVERE, null, ex);
            return new Date();
        }
    }

//    public static boolean dansexo(Date dt, Exercice exo) {
//        if (!extranne(dt).equals(exo.getExercice())) {
//            return false;
//        }
//        return true;
//    }
    
//    public static boolean dansexo(Date dt) {
//        if (DateHelper.derdaythisan().before(dt)) 
//            return false;
//        
//        if (dt.before(DateHelper.premdaythisan())) 
//            return false;
//        
//        return true;
//    }
      
    
    // Cette fonction permet de degarer le premier jour utilisé par le Systeme
    
    public static Date PremJourSys(EntityManager em) {
        Query query = em.createNamedQuery("Exercice.dateinitsys");
        query.setMaxResults(1);
        Date dt = (Date) query.getSingleResult();
        try {
            return dt;
        } catch (NoResultException ex) {
            return new Date();
        }
    }
    

    public static Date Ajouterjours(Date dt, Integer days) {
        GregorianCalendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(dt);
        calendar.setTime(dt);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
    
//    public static boolean dtalerte(Alertetype al) {
//        Integer jour = 0;
//        Calendar cal2 = Calendar.getInstance();
////        cal2.setTime(cejour);
//        cal2.setTime(new Date());
////        new Date()
//        
//        Calendar cal1 = Calendar.getInstance();
//        Integer mois = cal1.get(Calendar.MONTH) + 1;
//        Integer annee = cal1.get(Calendar.YEAR);
//        
//        int i = 0;
//        while (jour <= 30) {
//            try {
//                jour = al.getPrejour() + i * al.getFrequence();
//                i++;
//                cal1.setTime(df.parse(jour.toString() + "/" + mois.toString() + "/" + annee.toString()));
//                boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
//                        && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
//
//                if (sameDay) {
//                    return true;
//                }
//            } catch (ParseException ex) {
//                Logger.getLogger(DateHelper.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return false;
//    }
    
       public static String getDateTime() {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
       
       
       public static String Ecart_date(Date date1, Date date2){
        GregorianCalendar calStr1 = new GregorianCalendar();
        GregorianCalendar calStr2 = new GregorianCalendar();
//        Date date1 = null;
//        Date date2 = null;
//        try {
//            date1 = new SimpleDateFormat("dd/MM/yyyy").parse("25/01/2006");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        try {
//            date2 = new SimpleDateFormat("dd/MM/yyyy").parse("28/09/2010");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        calStr1.setTime(date1);
        calStr2.setTime(date2);
        int nbMois = 0;
       
        while(calStr1.before(calStr2)){
             calStr1.add(GregorianCalendar.MONTH, 1);
             nbMois++;
        }
        int nbAnnees = nbMois / 12;
        nbMois = (nbMois % 12) - 1;

         GregorianCalendar calStr0 = new GregorianCalendar();
         calStr0.setTime(date1);
         calStr0.add(GregorianCalendar.YEAR, nbAnnees);
         calStr0.add(GregorianCalendar.MONTH, nbMois);
         long nbJours = (calStr2.getTimeInMillis() - calStr0.getTimeInMillis()) / 86400000;
            
//         System.out.print("Nb Annees : "+nbAnnees+"\n");
//         System.out.print("Nb Mois : "+nbMois+"\n");
//         System.out.print("Nb Jours : "+nbJours+"\n");
         
         return nbAnnees + " an(s) " + nbMois + " mois(s) " + nbJours + " Jours(s)";
   }
//}}
       
       public static Date premdayofyear(int year) {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
        try {
            return df.parse("01/01/" + year);
        } catch (ParseException ex) {
//            Logger.getLogger(DateHelper.class.getName()).log(Level.SEVERE, null, ex);
            return new Date();
        }
    }

    public static Date derdayofyear(int year) {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
        try {
            return df.parse("31/12/" + year);
        } catch (ParseException ex) {
            Logger.getLogger(DateHelper.class.getName()).log(Level.SEVERE, null, ex);
            return new Date();
        }
    }    
       public static int Ecart_date_nb_annee(Date date1, Date date2){
        GregorianCalendar calStr1 = new GregorianCalendar();
        GregorianCalendar calStr2 = new GregorianCalendar();

        calStr1.setTime(date1);
        calStr2.setTime(date2);
        int nbMois = 0;
       
        while(calStr1.before(calStr2)){
             calStr1.add(GregorianCalendar.MONTH, 1);
             nbMois++;
        }
        int nbAnnees = nbMois / 12;
        nbMois = (nbMois % 12) - 1;

         GregorianCalendar calStr0 = new GregorianCalendar();
         calStr0.setTime(date1);
         calStr0.add(GregorianCalendar.YEAR, nbAnnees);
         calStr0.add(GregorianCalendar.MONTH, nbMois);
         long nbJours = (calStr2.getTimeInMillis() - calStr0.getTimeInMillis()) / 86400000;
            
//         System.out.print("Nb Annees : "+nbAnnees+"\n");
//         System.out.print("Nb Mois : "+nbMois+"\n");
//         System.out.print("Nb Jours : "+nbJours+"\n");
         
         return nbAnnees; /// + " an(s) " + nbMois + " mois(s) " + nbJours + " Jours(s)";
   }
//}}
           public static String Extraire_MOIS(Date dt) {
        GregorianCalendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(dt);
        return getMois(calendar.get(Calendar.MONTH));
    }
       public static String getMois(int mois) {
        String x = "";
        switch (mois) {
            case 1:
                x = "janvier";
                break;
            case 2:
                x = "fevrier";
                break;
            case 3:
                x = "mars";
                break;
            case 4:
                x = "avril";
                break;
            case 5:
                x = "mai";
                break;
            case 6:
                x = "juin";
                break;
            case 7:
                x = "juillet";
                break;
            case 8:
                x = "aout";
                break;
            case 9:
                x = "septembre";
                break;
            case 10:
                x = "octobre";
                break;
            case 11:
                x = "novembre";
                break;
            case 12:
                x = "decembre";
                break;
            default:
                break;
        }
        return x;
    }
}