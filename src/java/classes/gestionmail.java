/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

//import anok_imis.Param_sys.Anok_obj_ParamsysDAO;
//import anok_imis.entites.*;
import Param_sys.Anok_obj_ParamsysDAO;
import entites.Acteur;
import entites.Collecte;
import entites.Offre;
import entites.Parametres;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.commons.validator.EmailValidator;

/**
 *
 * @author KONOMBO
 */
public class gestionmail {

    static Parametres paramsys = new Anok_obj_ParamsysDAO().selectActif();

    public static Session mailsession() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", paramsys.getSmtpServer()); //
//        System.out.println(paramsys.getUserExp() + " - " +paramsys.getMdpExp());
        props.put("mail.smtp.user", paramsys.getUserExp());
        props.put("mail.smtp.password", paramsys.getMdpExp());
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        SecurityManager security = System.getSecurityManager();
        System.out.println("Security Manager" + security);
//la valeur est null  
// digitalbanking-bf@ubagroupe.com               
        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
//        Session mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(paramsys.getUserExp(), paramsys.getMdpExp());
            }
        });
        return mailSession;
    }

    
    public static boolean melvalide(String mel) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return true;
    }

    public static void mail_exp_Offre(List<Acteur> actor_list,Offre offr) {
        try {
            Session mailSession = mailsession();
            Transport transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
//            message.setFrom(new InternetAddress("konombo@hotmail.com"));
            String sms = "<p> Veuillez trouvez ci dessous, les informations sur une offre <br>";
            sms = sms + " Ref. Offre    : " + offr.getIdOffre() + "<br>";
            sms = sms + " Acteur : " + offr.getIdActeur().getNomActeur() + " " + offr.getIdActeur().getPrenomActeur()+ "<br>";
            if (offr.getIdProduit() != null) {
                sms = sms + " Viviers      : " + offr.getIdProduit().getIdProduit() + " " + offr.getIdProduit().getLibproduitEN()+ "<br>";
            } else {
                sms = sms + " Animal      : " + offr.getIdTypean().getIdTypean() + " " + offr.getIdTypean().getLibtypeanEN() + "<br>";
            }
//            sms = sms + " ExpÃ©diteur   : " + emp_from.getPrenom() + "" + emp_from.getNom() + "<br>";
            sms = sms + " Objet        : " + offr.getLibelleOffre() + "<br>";
            sms = sms + " Description  : " + offr.getTypeOffre() + "<br>";
//            Session mailSession = mailsession();
//            Transport transport = mailSession.getTransport();
//            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(paramsys.getEmailExp()));
            message.setSubject(paramsys.getIdApp() + " - " + paramsys.getNomApp() + "  - Alerte Bourses Virtuelles SIM 2G");
            message.setContent(sms, "text/html; charset=ISO-8859-1");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("oumar.konombo@cilss.int"));            
            Acteur actor;
            boolean excc = false;
            Iterator<Acteur> itr = actor_list.iterator();
            while (itr.hasNext()) {
                actor = itr.next();
                if (!actor.getEmailActeur().equals("N/A")) {
                    if (melvalide(actor.getEmailActeur())) {                // On test la validité du mail
                        message.addRecipient(Message.RecipientType.CC, new InternetAddress(actor.getEmailActeur()));
                        excc = true;
                    }
                }
            }
            transport.connect();
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            if (excc) {
                transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
            }
            transport.close();
        } catch (javax.mail.MessagingException e) {
//            JOptionPane.showMessageDialog(null, "ERREUR !! Probleme de livraison du mail ", "AGRIC-TRADE", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void mail_exp_Collecte(List<Acteur> actor_list,Collecte offr) {
        try {
            String sms = "<p> Veuillez trouvez ci dessous, les informations sur une offre <br>";
            sms = sms + " Ref. Collectee    : " + offr.getIdCollecte() + "<br>";
            sms = sms + " Acteur : " + offr.getIdActeur().getNomActeur() + " " + offr.getIdActeur().getNomActeur() + "<br>";
            if (offr.getIdProduit()!= null) {
                sms = sms + " Vivrier     : " + offr.getIdProduit().getIdProduit()+ " " + offr.getIdProduit().getLibproduitEN()+ "<br>";
            } else {
                sms = sms + " Betail      : " + offr.getIdTypean().getIdTypean() + " " + offr.getIdTypean().getLibtypeanEN()+ "<br>";
            }
            sms = sms + " Objet        : " + offr.getNumMoytrans() + "<br>";
            sms = sms + " Moyen transp : " + offr.getNumMoytrans() + "<br>";
            sms = sms + " Description  : " + offr.getIdCollecte() + "<br>";
            Session mailSession = mailsession();
            Transport transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            
                        message.setFrom(new InternetAddress(paramsys.getEmailExp()));
            message.setSubject(paramsys.getIdApp() + " - " + paramsys.getNomApp() + "  - Alerte Arrivée d'une Collecte");
            message.setContent(sms, "text/html; charset=ISO-8859-1");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("oumar.konombo@cilss.int"));            
            Acteur actor;
            boolean excc = false;
            Iterator<Acteur> itr = actor_list.iterator();
            while (itr.hasNext()) {
                actor = itr.next();
                if (!actor.getEmailActeur().equals("N/A")) {
                    if (melvalide(actor.getEmailActeur())) {                // On test la validité du mail
                        message.addRecipient(Message.RecipientType.CC, new InternetAddress(actor.getEmailActeur()));
                        excc = true;
                    }
                }
            }
            transport.connect();
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            if (excc) {
                transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
            }
//            transport.sendMessage(message, message.getRecipients(Message.RecipientType.BCC));
            transport.close();
        } catch (javax.mail.MessagingException e) {
            System.out.println(e.getMessage());
//            JOptionPane.showMessageDialog(null, "ERREUR !! Probleme de livraison du mail ", "ANOK_SUIVI-MEMO", JOptionPane.ERROR_MESSAGE);
        }
    }

}
