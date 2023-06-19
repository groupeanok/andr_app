package servlets.agrictrade;

import Acteur.ActeurDAO;
import Produits.ProduitDAO;
import Offres.OffresDAO;
import TypeAnimal.TypeAnimalDAO;
import Units.UnitsDAO;
import User.UserDAO;
import classes.DateHelper;
import static classes.DateHelper.df;
import entites.Acteur;
import entites.Offre;
import entites.Operateur;
import entites.Produit;
import entites.TypeAnimal;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONArray;


public class OffreServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
               ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
       OffresDAO pDao = new OffresDAO();
       ProduitDAO prodDao = new ProduitDAO();
       ActeurDAO actorDao = new ActeurDAO();
       TypeAnimalDAO typeaDao = new TypeAnimalDAO();
       UserDAO userDao = new UserDAO();
       Offre offre;
       Operateur oper;
      
        String formu;
        String libelle = "";
        int price = 0;
        int qty = 0;
        
        String oper_dc = request.getParameter("oper_dc");      // Le code de l'operateur qui fait la saisie
        String ref_entity = request.getParameter("ref");       // Cela contient soit le code du Vivier ou celui de l'animal
        String ope_type = request.getParameter("operation");   // quatres valeur possible : BF, BL, SL, SF
        String lib_value = request.getParameter("libelle");
        String prix_value = request.getParameter("prix");
        String qte_value = request.getParameter("qte");
        String idactor = request.getParameter("actor");
        String unit    = request.getParameter("unit");
        String devise  = request.getParameter("devise");
        String photo = request.getParameter("photo");
        String coordx  = request.getParameter("coordx");
        String coordy = request.getParameter("coordy");
        
        Produit str = prodDao.selectFind(ref_entity);
        TypeAnimal type_an = typeaDao.selectFind(ref_entity);
        oper = userDao.selectFind(oper_dc);         
//        System.out.println("Voila mes parametres recus");
//        System.out.println(oper_dc + " - " + ref_entity +" - " +   ope_type+" - " +   lib_value+" - " +   prix_value+" - " +   qte_value);
        if (lib_value != null) {
            libelle = request.getParameter("libelle");
        }
        if (prix_value != null) {
            price = Integer.parseInt(request.getParameter("prix"));
        }
        if (qte_value != null) {
            qty = Integer.parseInt(request.getParameter("qte"));
        }
        if(ope_type.equals("BF") || ope_type.equals("BL"))
            formu = "ACH";
        else
            formu = "VNT";
        
          ArrayList<BigObjet> userdata = new ArrayList<>(1); 

         try {

        String reportDate = df.format(new Date());
        String id_acteur = idactor;
        Acteur actor = actorDao.selectFind(id_acteur);          
        Date date_offre = new Date();
        // cele doit venir comme parametre
        int der_ind = pDao.select_nb_offre(formu);
        String id_real = oper.getNationalite().getIdPays() + reportDate + der_ind;
        Offre ap_cata = pDao.selectFind(id_real);
      
        while (ap_cata != null) {
            der_ind++;
            id_real = oper.getNationalite().getIdPays() + reportDate + der_ind;
            ap_cata = pDao.selectFind(id_real);
        }
       if(type_an == null && str == null){
           userdata.add(new BigObjet("ERREUR",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
       }
       else {  
        offre = new Offre(id_real);
        offre.setIdProduit(str);
        offre.setIdTypean(type_an);
        offre.setTypeOffre(formu);
        offre.setIdActeur(actor);
        offre.setValide(false);
        offre.setConclus(false);
        offre.setStatut("En cours");
        offre.setQteOffre(qty);
        offre.setPrixOffre(price);
        offre.setLibelleOffre(libelle);
        offre.setDateoffre(date_offre);
        offre.setDateVal(DateHelper.Ajouterjours(date_offre, 7));
        offre.setDatecre(new Date());
        offre.setDatemaj(new Date());
        offre.setOpcre(oper);
        offre.setOpmaj(oper); 
        offre.setCoordx(Float.valueOf(coordx));
        offre.setCoordy(Float.valueOf(coordy));
        
        if(unit.isEmpty()){
            offre.setIdunit(new UnitsDAO().selectAll().get(0));
        } else {
        offre.setIdunit(new UnitsDAO().selectFind(unit));
        }
        offre.setCodedev(devise);
        
        if (!photo.isEmpty()) {
                    
        byte[] data = DatatypeConverter.parseBase64Binary(photo);
                    String path = id_real.replace("/", "") + "MCOM.png";
                    File file = new File("ImgAndroid");
                    if(!file.exists()){
                        file.mkdir();
                    }
                    File file1 = new File("ImgAndroid/"+path);
                    
                    try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file1))) {
                        outputStream.write(data);
                    } catch (IOException e) {
//                        e.printStackTrace();
                    }                 
                    offre.setPhoto(id_real.replace("/", "") + "MCOM.png");
                } else {
                    offre.setPhoto("aucunephoto.png");
                }
        
            pDao.insert(offre);   
            
            userdata.add(new BigObjet("SUCCESS",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            
        
        // Debut partie envoie d'alerte 
//        
//        ArrayList<String> _sms_recipients = new ArrayList();
//        Alertetype alerte = new AlerteDAO().selectFind(formu);
//        if (alerte.isSms() || alerte.isMel()) {
//            String msg = "";
//            if ("VNT".equals(offre.getTypeOffre())) {
//                msg = msg + " VENTE ";
//            } else {
//                msg = msg + " ACHAT ";
//            }             
//            if (offre.getIdProduit() == null) {
//                msg = msg + " BETAIL : " + offre.getIdTypean().getLibtypeanEN() + ", Pays : " + offre.getIdActeur().getNationalite().getNomPays() + ", Nombre :" + offre.getQteOffre() + ", Prix :" + offre.getPrixOffre() + " Valable jusqu'au " + DateHelper.df.format(offre.getDateVal());
//            } else {
//                msg = msg + " VIVRIER : " + offre.getIdProduit().getLibproduitEN()+ ", Pays : " + offre.getIdActeur().getNationalite().getNomPays() + ",  Quantité :" + offre.getQteOffre() + ", Prix :" + offre.getPrixOffre() + " Valable jusqu'au " + DateHelper.df.format(offre.getDateVal());
//            }
//
//            if ("VNT".equals(offre.getTypeOffre())) {
//                msg = msg + ", VENDEUR : " + offre.getIdActeur().getNomActeur() + " " + offre.getIdActeur().getPrenomActeur();
//            } else {
//                msg = msg + ", ACHETEUR : " + offre.getIdActeur().getNomActeur() + " " + offre.getIdActeur().getPrenomActeur();
//            }
//            msg = msg + " Acceder à la bourse virtuelle a l'URL : www.agrictrade.org ";
//            // L'alerte SMS est activé
//            if (alerte.isSms()) {
//                SmsObject sms = new SmsObject();
//                alerte.getActeurList().forEach((act_ref) -> {
//                    _sms_recipients.add(act_ref.getTelActeur());
//                });
//                sms.sendSms(msg, _sms_recipients);
//            }
//            // L'alerte Mail est activé
//            if (alerte.isMel()) {
//                    gestionmail.mail_exp_Offre(alerte.getActeurList(), offre);
//            }
//        }
        // Fin partie envoie d'alerte 
     }      
        }
         catch(IOException | NullPointerException ee){           
            userdata.add(new BigObjet("ERREUR",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            
         }        
   }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    //}
}
