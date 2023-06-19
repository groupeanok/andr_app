package servlets.agrictrade;

import Acteur.ActeurDAO;
import Collecte.CollecteDAO;
import Devises.DevisesDAO;
import Moyen_transp.Moyen_transDAO;
import Point_Collecte.Point_CollecteDAO;
import Produits.ProduitDAO;
import TypeAnimal.TypeAnimalDAO;
import Units.UnitsDAO;
import User.UserDAO;
import classes.factory;
import entites.Acteur;
import entites.Collecte;
import entites.Devise;
import entites.MoyenTrans;
import entites.Operateur;
import entites.OperateurDetail;
import entites.PointCollecte;
import entites.Produit;
import entites.TypeAnimal;
import entites.Units;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONArray;

public class CollecteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        String formu = "";
        String id_ptch;
        String id_ptdech;
        String id_pays = "";
        String id_actor;
        int pd_byunit = 0, nbre_sacpan = 0, pd_bysac = 0, prix_transunit = 0; float cour_avec_dollard;
        int nbre_big = 0, prix_big = 0, nbre_small = 0, prix_small = 0, nbre_medium = 0, prix_medium = 0;

        Collecte collecte;
        CollecteDAO pDao = new CollecteDAO();
        ProduitDAO prodDao = new ProduitDAO();
        TypeAnimalDAO typeaDao = new TypeAnimalDAO();
        ActeurDAO actorDao = new ActeurDAO();
        Point_CollecteDAO ptcolDao = new Point_CollecteDAO();
        Moyen_transDAO moytransDao = new Moyen_transDAO();
        UserDAO userDao = new UserDAO();
        PointCollecte pt_ch, pt_dech, pt_col;
        Acteur actor;
        Operateur oper;

        Units units = new Units();
        UnitsDAO unitsdao = new UnitsDAO();

        String nat_value = request.getParameter("nature");  // Cette information peut etre VI ou ANI
        String pays_value = request.getParameter("pays");

        id_ptch = request.getParameter("ptch");
        id_ptdech = request.getParameter("ptdch");
        id_actor = request.getParameter("actor");
        String taux = request.getParameter("cour_avec_dollard");
        String pbByunit = request.getParameter("pd_byunit");
        String nbreSac = request.getParameter("nbre_sacpan");
        String pbBysac = request.getParameter("pd_bysac");
        String priTransunit = request.getParameter("prix_transunit");

        String prix_petit = request.getParameter("prixpetit");
        String qte_petit = request.getParameter("nbrepetit");
        String prix_moyen = request.getParameter("prixmoyen");
        String qte_moyen = request.getParameter("nbremoyen");
        String prix_gros = request.getParameter("prixgros");
        String qte_gros = request.getParameter("nbregros");
        String id_oper = request.getParameter("oper_dc");
        String plate_num = request.getParameter("numero");
        String ref_entity = request.getParameter("ref");        // Cela contient soit le code du Vivier, sois celui de l'animal          
        String id_moyen = request.getParameter("moyen");
        String dateColl = request.getParameter("datecoll");
        String longi = request.getParameter("longitude");
        String lati = request.getParameter("latitude");

        // Les new 
        String otherfes = request.getParameter("fees");
        String Cpc = request.getParameter("cpc");
        String Costums = request.getParameter("costums");
        String unit = request.getParameter("units");
        String declaration = request.getParameter("declaration");

        String photo = request.getParameter("photo");
        String devise = request.getParameter("devise");
        
        

        Produit str = prodDao.selectFind(ref_entity);
        TypeAnimal type_an = typeaDao.selectFind(ref_entity);
        MoyenTrans moy_transp = moytransDao.selectFind(id_moyen);
        
        Devise dev = new DevisesDAO().selectFind(devise);

//        BufferedImage image = null;
//        byte[] imageByte;

        if (nat_value != null) {
            formu = nat_value;
        }

        // recuperation de l'id du pays
        if (pays_value != null) {
            id_pays = pays_value;
        }
        if (prix_petit != null) {
            prix_small = Integer.parseInt(prix_petit);
        }
        if (qte_petit != null) {
            nbre_small = Integer.parseInt(qte_petit);
        }
        if (prix_moyen != null) {
            prix_medium = Integer.parseInt(prix_moyen);
        }
        if (qte_moyen != null) {
            nbre_medium = Integer.parseInt(qte_moyen);
        }
        if (prix_gros != null) {
            prix_big = Integer.parseInt(prix_gros);
        }
        if (qte_gros != null) {
            nbre_big = Integer.parseInt(qte_gros);
        }
        if (pbByunit != null) {
            pd_byunit = Integer.parseInt(pbByunit);
        }
        if (nbreSac != null) {
            nbre_sacpan = Integer.parseInt(nbreSac);
        }
        if (pbBysac != null) {
            pd_bysac = Integer.parseInt(pbBysac);
        }
        if (priTransunit != null) {
            prix_transunit = Integer.parseInt(priTransunit);
        }
        if (unit != null) {
            units = unitsdao.selectFind(unit);
        }

        oper = userDao.selectFind(id_oper);

        EntityManager entityManager = factory.getemf().createEntityManager();
        Query query = entityManager.createNamedQuery("OperateurDetail.findByCodeoper");
        query.setParameter("codeoper", oper.getCodeoper());
        List<OperateurDetail> data_lst = query.getResultList();
        if (!data_lst.isEmpty()) {
            pt_col = data_lst.get(0).getIdPtcollecte();
        } else {
            // Cela doit etre corriger car on choisi anormalement un
            // point de collecte
            pt_col = ptcolDao.selectFind("BF9");
        }

        cour_avec_dollard = Float.parseFloat(taux);
        actor = actorDao.selectFind(id_actor);
        ArrayList<BigObjet> userdata = new ArrayList<>(1);
        // DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dateCol = new Date();
        try {
            dateCol = (Date) format.parse(dateColl);
        } catch (ParseException ex) {
            // Logger.getLogger(CollecteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

//        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

            pt_ch = ptcolDao.selectFind(id_ptch);
            pt_dech = ptcolDao.selectFind(id_ptdech);

            String reportDate = df.format(new Date());
            int der_ind = pDao.select_nb_collecte(pt_col);
            String id_real = id_pays + reportDate + der_ind;
            Collecte ap_cata = pDao.selectFind(id_real);
            
            while (ap_cata != null) {
                der_ind++;
                id_real = id_pays + reportDate + der_ind;
                ap_cata = pDao.selectFind(id_real);
            }
            
            if (type_an == null && str == null) {
                userdata.add(new BigObjet("ERREUR", ""));
                JSONArray array = new JSONArray(userdata);
                PrintWriter pw = response.getWriter();
                pw.write(array.toString());
            } else {
                collecte = new Collecte(id_real);
                collecte.setIdTypean(type_an);
                collecte.setIdProduit(str);
                collecte.setIdPays(pt_col.getIdPays());
                collecte.setIdPtcharge(pt_ch);
                collecte.setIdPtdecharge(pt_dech);
                collecte.setIdPtcollecte(pt_col);
                collecte.setIdMoyTrans(moy_transp);
                collecte.setNumMoytrans(plate_num);
                collecte.setIdActeur(actor);
                collecte.setTypeCollecte(formu);
                collecte.setDatecre(new Date());
                collecte.setDatemaj(new Date());
                collecte.setDateCollecte(dateCol);
                collecte.setOpcre(oper);
                collecte.setOpmaj(oper);
                collecte.setCoursAvecDollard(cour_avec_dollard);
                collecte.setPrixtranspunit(prix_transunit);
                /* les nouvo champs*/
                collecte.setCoordx(Float.parseFloat(longi));
                collecte.setCoordy(Float.parseFloat(lati));
                collecte.setCpcRegime(Cpc);
                collecte.setOtherfes(Integer.parseInt(otherfes));
                collecte.setCodedev(dev);
//                if (declaration.isEmpty()) {
//                    collecte.setDecNumber(" ");
//                } else {
//                    collecte.setDecNumber(declaration);
//                }
                if (Costums.equalsIgnoreCase("Yes")) {
                    collecte.setPassageDouane(true);
                    if (declaration.isEmpty()) {
                        collecte.setDecNumber(" ");
                    } else {
                        collecte.setDecNumber(declaration);
                    }
                } else {
                    collecte.setPassageDouane(false);
                    collecte.setDecNumber(" ");
                }                
                collecte.setValide(true);

                if (formu.equalsIgnoreCase("VI")) {
                    collecte.setPdbysac(pd_bysac);
                    collecte.setNbreSacpan(nbre_sacpan);
                    collecte.setPdbyunite(pd_byunit);
                    collecte.setUnitid(units);
                }
                // else {}            
                if (formu.equalsIgnoreCase("ANI")) {
                    collecte.setNbreGros(nbre_big);
                    collecte.setNbrePetit(nbre_small);
                    collecte.setPrixGros(prix_big);
                    collecte.setPrixPetit(prix_small);
                    collecte.setNbreMoyen(nbre_medium);
                    collecte.setPrixMoyen(prix_medium);
                }
                if (!photo.isEmpty()) {
                    
//                    BASE64Decoder decoder = new BASE64Decoder();
//                    imageByte = decoder.decodeBuffer(photo);
//                    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
//                    image = ImageIO.read(bis);
//                    bis.close();
//
//                 
//                    String nomImage = id_real.replace("-", "")+".png";
//                    File outputfile = new File(nomImage);
//                    
//                    ImageIO.write(image, "png", outputfile);

                    byte[] data = DatatypeConverter.parseBase64Binary(photo);
                    String path = id_real.replace("-", "") + ".png";
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
                    collecte.setPhoto(id_real.replace("-", "") + ".png");
                } else {
                    collecte.setPhoto("aucunephoto.png");
                }
                        
                pDao.insert(collecte);
                userdata.add(new BigObjet("SUCCESS", ""));
                JSONArray array = new JSONArray(userdata);
                PrintWriter pw = response.getWriter();
                pw.write(array.toString());
                //  System.out.println("Base64 : " + photo);
            }

//        } catch (IOException | NumberFormatException hx) {
//            userdata.add(new BigObjet("ERREUR", ""));
//            JSONArray array = new JSONArray(userdata);
//            PrintWriter pw = response.getWriter();
//            pw.write(array.toString());
//            // System.out.println("JSONarray data " + array.toString());
//        }
        
        
      //  Alertetype alerte = new AlerteDAO().selectFind("DC");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
