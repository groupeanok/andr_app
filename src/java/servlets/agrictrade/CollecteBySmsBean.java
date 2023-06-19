/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Groupe Anok
 */
public class CollecteBySmsBean extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        String formu = "";
        String id_ptch = "";
        String id_ptdech = "";
        String id_pays;
        String id_actor = "";
        int pd_byunit = 0, nbre_sacpan = 0, pd_bysac = 0, prix_transunit = 0;
        float cour_avec_dollard;
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

        String pays_value = "BF";

        String leText = request.getParameter("text");

//        System.out.println(leText);

        leText = leText.replaceAll("##", "# #");

        String[] list_collecte = leText.split("#");

        response.setCharacterEncoding("UTF-8");

        String nat_value = "";

        String taux = "";
        String priTransunit = "";

        String pbByunit = "";
        String nbreSac = "";
        String pbBysac = "";
        String unit = "";

        String prix_petit = "";
        String qte_petit = "";
        String prix_moyen = "";
        String qte_moyen = "";
        String prix_gros = "";
        String qte_gros = "";

        String id_oper = "";
        String plate_num = "";
        String ref_entity = "";
        String id_moyen = "";
        String dateColl = "";
        String otherfes = "";
        String Cpc = "";
        String Costums = "";
        String declaration = "";
        
        boolean syntaxeok = false; 

        String devise = request.getParameter("devise");

        switch (list_collecte.length) {

            case 20: {

                nat_value = list_collecte[0]; // request.getParameter("nature");
                id_ptch = list_collecte[1]; //request.getParameter("ptch");
                id_ptdech = list_collecte[2]; //request.getParameter("ptdch");
                id_moyen = list_collecte[3]; //request.getParameter("moyen");
                plate_num = list_collecte[4]; //request.getParameter("numero");
                pbByunit = list_collecte[5]; //request.getParameter("pd_byunit");
                nbreSac = list_collecte[6]; //request.getParameter("nbre_sacpan");
                pbBysac = list_collecte[7]; //request.getParameter("pd_bysac");
                priTransunit = list_collecte[8]; //request.getParameter("prix_transunit");
                ref_entity = list_collecte[9]; //request.getParameter("ref");   
                taux = list_collecte[10]; //request.getParameter("cour_avec_dollard"); 
                id_actor = list_collecte[11]; //request.getParameter("actor");
                dateColl = list_collecte[12]; //request.getParameter("datecoll");     
                id_oper = list_collecte[13]; //request.getParameter("oper_dc");
                otherfes = list_collecte[14]; //request.getParameter("fees");
                Cpc = list_collecte[15]; //request.getParameter("cpc");
                Costums = list_collecte[16]; //request.getParameter("costums");
                 unit = list_collecte[17]; //request.getParameter("units");
                declaration = list_collecte[18]; //request.getParameter("declaration"); 
                devise = list_collecte[19]; //request.getParameter("devise"); 

                prix_petit = null ; //request.getParameter("prixpetit");
                qte_petit = null; //request.getParameter("nbrepetit");
                prix_moyen = null; //request.getParameter("prixmoyen");
                qte_moyen = null; //request.getParameter("nbremoyen");
                prix_gros = null; //request.getParameter("prixgros");
                qte_gros = null; //request.getParameter("nbregros");

                break;
            }

            case 22: {

                nat_value = list_collecte[0]; // request.getParameter("nature");
                id_ptch = list_collecte[1]; //request.getParameter("ptch");
                id_ptdech = list_collecte[2]; //request.getParameter("ptdch");
                 id_moyen = list_collecte[3]; //request.getParameter("moyen");
                plate_num = list_collecte[4]; //request.getParameter("numero");
                qte_gros = list_collecte[5]; //request.getParameter("nbregros");
                prix_gros = list_collecte[6]; //request.getParameter("prixgros");
                qte_moyen = list_collecte[7]; //request.getParameter("nbremoyen");
                 prix_moyen = list_collecte[8]; //request.getParameter("prixmoyen");
                qte_petit = list_collecte[9]; //request.getParameter("nbrepetit");
                prix_petit = list_collecte[10]; //request.getParameter("prixpetit");
                 priTransunit = list_collecte[11]; //request.getParameter("prix_transunit");
                ref_entity = list_collecte[12]; //request.getParameter("ref");
                taux = list_collecte[13]; //request.getParameter("cour_avec_dollard");            
                id_actor = list_collecte[14]; //request.getParameter("actor");
                dateColl = list_collecte[15]; //request.getParameter("datecoll");           
                id_oper = list_collecte[16]; //request.getParameter("oper_dc");
                otherfes = list_collecte[17]; //request.getParameter("fees");
                Cpc = list_collecte[18]; //request.getParameter("cpc");
                Costums = list_collecte[19]; //request.getParameter("costums");
                declaration = list_collecte[20]; //request.getParameter("declaration"); 
                devise = list_collecte[21]; //request.getParameter("devise");

                pbByunit = null ; //request.getParameter("pd_byunit");
                nbreSac = null; //request.getParameter("nbre_sacpan");
                pbBysac = null; //request.getParameter("pd_bysac");
                unit = null; //request.getParameter("units");
                break;
            }
            default: {
                 syntaxeok =true;
                break;
            }
        }

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
//        if (pays_value != null) {
        id_pays = pays_value;
//        }
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

            pt_col = ptcolDao.selectFind("BF9");

        }

        cour_avec_dollard = Float.parseFloat(taux);
        actor = actorDao.selectFind(id_actor);
       
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
//            syntaxeok =true;
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
            collecte.setCoordx(Float.valueOf(0));
            collecte.setCoordy(Float.valueOf(0));
            collecte.setCpcRegime(Cpc);
            collecte.setOtherfes(Integer.parseInt(otherfes));
            collecte.setCodedev(dev);
            if (declaration.isEmpty()) {
                collecte.setDecNumber(" ");
            } else {
                collecte.setDecNumber(declaration);
            }
            if (Costums.equalsIgnoreCase("Yes")) {
                collecte.setPassageDouane(true);
            } else {
                collecte.setPassageDouane(false);
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

            collecte.setPhoto("aucunephoto.png");

            pDao.insert(collecte);
            
            if(syntaxeok) {
                PrintWriter pw = response.getWriter();
                pw.write("Une erreur survenue veuillez verifier les données saisies");
            } else {
            PrintWriter pw = response.getWriter();
            pw.write("Enregistrement effectué avec succès, merci pour votre confiance");
            }
            
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
