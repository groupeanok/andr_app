/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import classes.SQLutils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Groupe Anok
 */
public class AllCollecteFluxRQ extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        BigObjet ao;
        ArrayList<BigObjet> list = new ArrayList<>();
        Connection conn_imis = SQLutils.conn_at();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -36);
        Date mdate = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        List<Collecte> dataColl = new CollecteDAO().select_inter_collecte(mdate, new Date());
//        ArrayList<BigObjet> list = new ArrayList();
//        BigObjet c;        
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");         
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String type_flux  = request.getParameter("type_flux");
//        String debut = request.getParameter("debut");
//        String fin = request.getParameter("fin");
//        = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        String type_flux = "VI";

        String sql = " SELECT id_collecte as id_collecte, collecte.id_produit,	lib_produit_FR,lib_natproduit,p1.lib_ptcollecte as ptcharge, "
                + " p2.lib_ptcollecte as ptdecharge, lib_mt_FR, type_collecte,p3.lib_ptcollecte as ptcollecte"
                + ", nbre_sacpan, pdbyunite, pdbysac, prixtranspunit,otherfes, cpc_regime, dec_number, collecte.codedev  as codedev,"
                + " nom_acteur,  association.id_assoc as id_assoc, nom_assoc, prenom_acteur,tel_acteur, email_acteur, date_collecte,collecte.datecre as datecre, "
                + " cpc_regime, passage_douane, dec_number, cours_avec_dollard"
//                + " , p1.idPays as pcha, p2.idPays as pdech, p3.idPays as pcoll "
                + " , p1.idPays as pcha, p2.idPays as pdech, p3.idPays as pcoll, operateur.nomoper as collecteur "
                //                + " , collecte.codedev"
                //                    + " ,email_acteur, date_collecte,collecte.datecre as datecre"
                + " FROM collecte, acteur, pays, association, point_collecte p1, "
                + " point_collecte p2,point_collecte p3,"
                + " moyen_trans, produit, nature_produit, operateur "
                + " Where DATE(collecte.datecre) between '" + df.format(mdate) + "' AND '" + df.format(new Date()) + "'"
                + " AND collecte.type_collecte = " + "'" + type_flux + "'"
                + " and collecte.id_produit = produit.id_produit "
                + " and nature_produit.id_natproduit = produit.id_natproduit "
                + " and collecte.id_ptcharge = p1.id_ptcollecte "
                + " AND collecte.id_ptdecharge = p2.id_ptcollecte "
                + " and collecte.id_ptcollecte = p3.id_ptcollecte "
                + " and collecte.id_moy_trans = moyen_trans.id_moy_trans "
                + " and acteur.id_acteur = collecte.id_acteur "
                + " and acteur.id_assoc = association.id_assoc "
                + " and collecte.idPays = pays.idPays "
                + " and collecte.opcre = operateur.codeoper"
                + " ORDER BY collecte.datecre ASC ";

        if (type_flux.equals("ANI")) {
            sql = " SELECT id_collecte as id_collecte, collecte.id_typean,animaltypes.Name_FR,lib_typean_FR, p1.lib_ptcollecte as ptcharge, "
                    + " p2.lib_ptcollecte as ptdecharge, lib_mt_FR, p3.lib_ptcollecte as ptcollecte,type_collecte,  "
                    //                    + " , "
                    + " prixtranspunit, nbre_gros, prix_gros, "
                    + " nbre_moyen, prix_moyen, nbre_petit, prix_petit, otherfes, cpc_regime, dec_number, collecte.codedev as codedev,"
                    + " nom_acteur, association.id_assoc as id_assoc, nom_assoc, prenom_acteur, tel_acteur,email_acteur, date_collecte,collecte.datecre as datecre, "
                    + " cpc_regime, passage_douane, dec_number, cours_avec_dollard"
//                    + " , p1.idPays as pcha, p2.idPays as pdech, p3.idPays as pcoll "
                    + " , p1.idPays as pcha, p2.idPays as pdech, p3.idPays as pcoll, operateur.nomoper as collecteur "
                    //                    + ", collecte.codedev"
                    //                    + " ,email_acteur, date_collecte,collecte.datecre as datecre"
                    + " FROM collecte, acteur, pays, association, point_collecte p1, point_collecte p2,point_collecte p3,"
                    + " moyen_trans, type_animal, animaltypes, operateur "
                    + " Where DATE(collecte.datecre) between '" + df.format(mdate) + "' AND '" + df.format(new Date()) + "'"
                    + " AND collecte.type_collecte = " + "'" + type_flux + "'"
                    + " and collecte.id_typean = type_animal.id_typean "
                    + " and type_animal.AnimalTypeID = animaltypes.AnimalTypeID "
                    + " and collecte.id_ptcharge = p1.id_ptcollecte "
                    + " AND collecte.id_ptdecharge = p2.id_ptcollecte "
                    + " and collecte.id_ptcollecte = p3.id_ptcollecte "
                    + " and collecte.id_moy_trans = moyen_trans.id_moy_trans "
                    + " and acteur.id_acteur = collecte.id_acteur "
                    + " and acteur.id_assoc = association.id_assoc "
                    + " and collecte.idPays = pays.idPays "
                    + " and collecte.opcre = operateur.codeoper"
                    + " ORDER BY collecte.datecre ASC ";
        }
//        System.out.println(sql);
        ResultSet rs;
//        Anok_obj_ppp ao;
        long v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11;
//        Date d;      
        try (Statement statem = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            rs = statem.executeQuery(sql);
            while (rs.next()) {
                v1 = Double.valueOf(rs.getString("otherfes")).longValue();
                v2 = Double.valueOf(rs.getString("prixtranspunit")).longValue();
//                try {
//                    d = df.parse(rs.getString("datecre"));
//                } catch (ParseException e) {
////                    e.printStackTrace();
//                    d = new Date();
//                }
//                Double.valueOf(rs.getString("prixtranspunit")).longValue();
                ao = new BigObjet(rs.getString("id_collecte"), rs.getString("nom_acteur") + " " + rs.getString("prenom_acteur"), "");
                ao.setVal1(v2);
//                ao.setDtppp(rs.getDate("datecre"));
                ao.setCh1(rs.getString("lib_mt_FR"));
                ao.setCh2(rs.getString("ptcharge"));
                ao.setCh3(rs.getString("ptdecharge"));
                ao.setCh4(rs.getString("ptcollecte"));
                ao.setCh5(rs.getString("dec_number"));
                ao.setCh6(rs.getString("cpc_regime"));
                ao.setCh8(rs.getString("id_assoc"));
                ao.setCh9(type_flux);
                ao.setCh11(rs.getString("collecteur"));
//                ao.setCh10(dateFormat.format(d));
//                ao.setLibsup12(rs.getString("passage_douane"));
                if (rs.getString("passage_douane").equals("1")) {
                    ao.setCh12("OUI");
                    ao.setCh13(rs.getString("dec_number"));
                } else {
                    ao.setCh12("NON");
                    ao.setCh13("--");
                }

//                ao.setLibsup13(rs.getString("dec_number"));
                ao.setCh14(rs.getString("cours_avec_dollard"));
//                ao.setDtppp(rs.getDate("date_collecte"));
//                ao.setDtppp2(rs.getDate("datecre"));

                if (dateFormat.format(rs.getDate("datecre")).contains("00:00:00")) {
                    ao.setCh15(df.format(rs.getDate("datecre")));
                } else {
                    ao.setCh15(dateFormat.format(rs.getDate("datecre")));
                }
                ao.setCh16(rs.getString("codedev"));
                ao.setCh17(rs.getString("pcha")); // Pays de chargement   
                ao.setCh18(rs.getString("pdech")); // Pays de dechargement
                ao.setCh19(rs.getString("pcoll")); // Pays de Collecte                
//                       ao.setLibsup17(rs.getString("pcoll")); 
//                ao.setLibsup18(rs.getString("pcha"));               
//                ao.setLibsup19(rs.getString("pdech")); 
//                + " , p1.idPays as pcha, p2.idPays as pdech, p3.idPays as pcoll "  
//                 ao.setDtppp(rs.getDate("datecre"));
//                ao.setDtppp(d);
//                 ao.setDtppp2();

//                ao.setLibsup9(type);
                if (type_flux.equals("ANI")) {
                    ao.setCh5(rs.getString("Name_FR") + " / " + rs.getString("lib_typean_FR"));
                    v3 = Double.valueOf(rs.getString("nbre_gros")).longValue();
                    v4 = Double.valueOf(rs.getString("prix_gros")).longValue();
                    v5 = Double.valueOf(rs.getString("nbre_moyen")).longValue();
                    v6 = Double.valueOf(rs.getString("prix_moyen")).longValue();
                    v7 = Double.valueOf(rs.getString("nbre_petit")).longValue();
                    v8 = Double.valueOf(rs.getString("nbre_petit")).longValue();
//                    ao.setV3(v3);
//                    ao.setV4(v4);
//                    ao.setV5(v5);
//                    ao.setV6(v6);
//                    ao.setV7(v7);
//                    ao.setV8(v8);

                    ao.setCh20(rs.getString("nbre_gros"));
                    ao.setCh21(rs.getString("prix_gros"));
                    ao.setCh22(rs.getString("nbre_moyen"));
                    ao.setCh23(rs.getString("prix_moyen"));
                    ao.setCh24(rs.getString("nbre_petit"));
                    ao.setCh25(rs.getString("nbre_petit"));

                } else {
                    ao.setCh5(rs.getString("lib_natproduit") + " / " + rs.getString("lib_produit_FR"));
                    v9 = Double.valueOf(rs.getString("nbre_sacpan")).longValue();
                    v10 = Double.valueOf(rs.getString("pdbysac")).longValue();
                    v11 = Double.valueOf(rs.getString("pdbyunite")).longValue();
//                    ao.setV9(v9);
//                    ao.setV10(v10);
//                    ao.setV11(v11);

                    ao.setCh26(rs.getString("nbre_sacpan"));
                    ao.setCh27(rs.getString("pdbysac"));
                    ao.setCh28(rs.getString("pdbyunite"));
                }
//                mesao.add(ao);
                list.add(ao);
            }
            conn_imis.close();
        } catch (SQLException | NullPointerException e) {
//            System.out.println("Erreur : " + e.getMessage());
        }

        response.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(list);
        PrintWriter pw = response.getWriter();
        pw.write(array.toString());
        System.out.println("JSONarray data " + list.size());        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
