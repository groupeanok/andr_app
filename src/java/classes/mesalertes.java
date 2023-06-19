package classes;

//import cilssimis.Param_sys.Anok_obj_ParamsysDAO;
import Param_sys.Anok_obj_ParamsysDAO;
import entites.Alerteenv;
import entites.Alertetype;
//import static cilssimis.classes.gestionmail.mailsession;
import static classes.gestionmail.mailsession;
import entites.Acteur;
import entites.Operateur;
import entites.Parametres;
import java.sql.Connection;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class mesalertes {

    static Parametres paramsys = new Anok_obj_ParamsysDAO().selectActif();
    static long dot = 0l;
    static long pai = 0l;

    


    public static void alerter(final EntityManager em, Operateur oper, final Connection connection, Acteur op) throws ParseException, NoSuchProviderException {


//      Date dtavce, dtse, dtbt, dtsave, dtaid, dtmdt;        
        Query qmaxdate = em.createNamedQuery("Alerteenv.MaxDate");
        Date dt = (Date) qmaxdate.getSingleResult();
        if (dt == null) {
            dt = paramsys.getDebPremJob();
        }

//        if (!DateHelper.cejour.equals(dt)) {
        if (DateHelper.cejour.before(DateHelper.Ajouterjours(dt, 5))) {
            return;
        }
//        }

        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        //On enregistre l'alerte pour ne pas l'envoyer plusieurs fois
        em.persist(new Alerteenv(DateHelper.cejour, op.getIdActeur()));
        em.getTransaction().commit();

        
//        Query query = em.createNamedQuery("Alertetype.findAll");
        Query query = em.createNamedQuery("Alertetype.findActif");
        List<Alertetype> results = query.getResultList();
        if (results.isEmpty()) {
            return;
        }
//        System.out.print("Possible " + results.size());
//        if(results.size()>0)
//            return;        
        Alertetype at;
        List<Operateur> data;
        Iterator<Alertetype> itrb = results.iterator();
        
        
        Session mailSession = mailsession();
//        Transport transport = mailSession.getTransport();
        
//        Query qr;
        while (itrb.hasNext()) {
            at = itrb.next();
//            if (at.getActif()) {
            query = em.createNamedQuery("Operateur.AvecAlerte2"); 
            query.setParameter("codetypealrt", at.getCodetypealrt());
            data = query.getResultList();
            if (!data.isEmpty()) {
                switch (at.getCodetypealrt()) {
                    case "AVCE":
//                        query.setParameter("codetypealrt", at.getCodetypealrt());
//                        data = query.getResultList();
//                        if (!data.isEmpty()) {
//                            if ((nbavcedelaipro != 0l) || (nbavceexp != 0l)) {
//                                alerte_avce(em, at, connection);
//                                gestionmail.alerte_avce(mailSession,data, at);
//                            }
                        
                        break;

                    case "AIDAVCE":
//                        query.setParameter("codetypealrt", at.getCodetypealrt());
//                        data = query.getResultList();
                        if (!data.isEmpty()) {
//                            if ((nbavcedelaipro != 0l) || (nbavceexp != 0l)) {
//                                alerte_avce(em, at, connection);
//                                gestionmail.alerte_avce(mailSession,data, at);
//                            }
                        }
                        break;

                }
            }
        }
    }

    private mesalertes() {
    }

}
