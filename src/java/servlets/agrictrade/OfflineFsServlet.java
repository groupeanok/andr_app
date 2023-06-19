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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Groupe Anok
 */
public class OfflineFsServlet extends HttpServlet {

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
        int pd_byunit = 0, nbre_sacpan = 0, pd_bysac = 0, prix_transunit = 0, cour_avec_dollard;
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

        String collecte_id = request.getParameter("collecte_id");
        String nat_value = request.getParameter("nature");
        String pays_value = request.getParameter("pays");
        id_ptch = request.getParameter("ptch");
        id_ptdech = request.getParameter("ptdch");
        id_actor = request.getParameter("actor");
        String taux = request.getParameter("cour_avec_dollard");
        String pbByunit = request.getParameter("pd_byunit");
        String nbreSac = request.getParameter("nbre_sacpan");
        String pbBysac = request.getParameter("pd_bysac");
        String priTransunit = request.getParameter("prix_transunit");
//        String prix_petit = request.getParameter("prixpetit");
//        String qte_petit = request.getParameter("nbrepetit");
//        String prix_moyen = request.getParameter("prixmoyen");
//        String qte_moyen = request.getParameter("nbremoyen");
//        String prix_gros = request.getParameter("prixgros");
//        String qte_gros = request.getParameter("nbregros");
        String id_oper = request.getParameter("oper_dc");
        String plate_num = request.getParameter("numero");
        String ref_entity = request.getParameter("ref");                  
        String id_moyen = request.getParameter("moyen");
        String dateColl = request.getParameter("datecoll");
        String longi = request.getParameter("longitude");
        String lati = request.getParameter("latitude");
        String otherfes = request.getParameter("fees");
        String Cpc = request.getParameter("cpc");
        String Costums = request.getParameter("costums");
        String unit = request.getParameter("units");
        String declaration = request.getParameter("declaration");
        String devise = request.getParameter("devise");

//        System.out.println(Costums);
//        System.out.println(Cpc);
//        System.out.println(otherfes);
//        System.out.println(ref_entity);
//        System.out.println(declaration);
//        System.out.println(id_ptch);
//        System.out.println(id_ptdech);
////        System.out.println(prix_gros);
//        System.out.println(dateColl);
//        System.out.println(unit);
////        System.out.println(qte_gros);
////        System.out.println(prix_petit);
//        System.out.println(pays_value);
//        System.out.println(collecte_id);

        String enregOK = "::";

        String[] list_collecte = collecte_id.split(":::");
//        String[] list_nat_value = nat_value.split(":::");
//        String[] list_pays_value = pays_value.split(":::");
        String[] list_id_ptch = id_ptch.split(":::");
        String[] list_id_ptdech = id_ptdech.split(":::");
        String[] list_id_actor = id_actor.split(":::");
        String[] list_taux = taux.split(":::");
        String[] list_pbByunit = pbByunit.split(":::");
        String[] list_nbreSac = nbreSac.split(":::");
        String[] list_pbBysac = pbBysac.split(":::");
        String[] list_priTransunit = priTransunit.split(":::");
//        String[] list_prix_petit = prix_petit.split(":::");
//        String[] list_qte_petit = qte_petit.split(":::");
//        String[] list_prix_moyen = prix_moyen.split(":::");
//        String[] list_qte_moyen = qte_moyen.split(":::");
//        String[] list_prix_gros = prix_gros.split(":::");
//        String[] list_qte_gros = qte_gros.split(":::");
        String[] list_plate_num = plate_num.split(":::");
        String[] list_ref_entity = ref_entity.split(":::");
        String[] list_id_moyen = id_moyen.split(":::");
//        String[] list_longi = longi.split(":::");
//        String[] list_lati = lati.split(":::");
        String[] list_otherfes = otherfes.split(":::");
        String[] list_Cpc = Cpc.split(":::");
        String[] list_Costums = Costums.split(":::");
        String[] list_unit = unit.split(":::");
        String[] list_declaration = declaration.split(":::");
        String[] list_devise = devise.split(":::");
        String[] list_dateColl = dateColl.split(":::");

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

        int nbre = list_collecte.length;

        ArrayList<BigObjet> userdata = new ArrayList<>(1);

        Produit str;
        TypeAnimal type_an;
        MoyenTrans moy_transp;
        Devise dev;

        for (int i = 0; i < nbre; i++) {
            str = prodDao.selectFind(list_ref_entity[i]);
            type_an = typeaDao.selectFind(list_ref_entity[i]);
            moy_transp = moytransDao.selectFind(list_id_moyen[i]);
            dev = new DevisesDAO().selectFind(list_devise[i]);

//        nat_value = list_nat_value[i];
//        pays_value = list_pays_value[i];
            id_ptch = list_id_ptch[i];
            id_ptdech = list_id_ptdech[i];
            id_actor = list_id_actor[i];
            taux = list_taux[i];
            pbByunit = list_pbByunit[i];
            nbreSac = list_nbreSac[i];
            pbBysac = list_pbBysac[i];
            priTransunit = list_priTransunit[i];
//            prix_petit = list_prix_petit[i];
//            qte_petit = list_qte_petit[i];
//            prix_moyen = list_prix_moyen[i];
//            qte_moyen = list_qte_moyen[i];
//            prix_gros = list_prix_gros[i];
//            qte_gros = list_qte_gros[i];
            plate_num = list_plate_num[i];
//        longi = list_longi[i];
//        lati = list_lati[i];
            otherfes = list_otherfes[i];
            Cpc = list_Cpc[i];
            Costums = list_Costums[i];
            unit = list_unit[i];
            declaration = list_declaration[i];
            dateColl = list_dateColl[i];

            if (nat_value != null) {
                formu = nat_value;
            }

            // recuperation de l'id du pays
            if (pays_value != null) {
                id_pays = pays_value;
            }
            
//            if (prix_petit != null) {
//                prix_small = Integer.parseInt(prix_petit);
//            }
//            if (qte_petit != null) {
//                nbre_small = Integer.parseInt(qte_petit);
//            }
//            if (prix_moyen != null) {
//                prix_medium = Integer.parseInt(prix_moyen);
//            }
//            if (qte_moyen != null) {
//                nbre_medium = Integer.parseInt(qte_moyen);
//            }
//            if (prix_gros != null) {
//                prix_big = Integer.parseInt(prix_gros);
//            }
//            if (qte_gros != null) {
//                nbre_big = Integer.parseInt(qte_gros);
//            }
            
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

            cour_avec_dollard = Integer.parseInt(taux);
            actor = actorDao.selectFind(id_actor);

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date dateCol = new Date();
            try {
                dateCol = (Date) format.parse(dateColl);
            } catch (ParseException ex) {
                // Logger.getLogger(CollecteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

            pt_ch = ptcolDao.selectFind(id_ptch);
            pt_dech = ptcolDao.selectFind(id_ptdech);

            String reportDate = df.format(new Date());
            int der_ind = pDao.select_nb_collecte(pt_col);
            String id_real = id_pays + reportDate+"FS" + der_ind;
            Collecte ap_cata = pDao.selectFind(id_real);

            while (ap_cata != null) {
                der_ind++;
                id_real = id_pays + reportDate+"FS" + der_ind;
                ap_cata = pDao.selectFind(id_real);
            }
            if (type_an == null && str == null) {
                // on supprime quand même
                enregOK += list_collecte[i] + "::";
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
                collecte.setCoordx(Float.valueOf(longi));
                collecte.setCoordy(Float.valueOf(lati));
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

                enregOK += list_collecte[i] + "::";

            }
       
        } catch (PersistenceException ex) {
            // Logger.getLogger(CollecteServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
        }    
        userdata.add(new BigObjet("SUCCESS", enregOK));
        JSONArray array = new JSONArray(userdata);
        PrintWriter pw = response.getWriter();
        pw.write(array.toString());
        
//        System.out.println("JSONarray data " + array.toString());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
