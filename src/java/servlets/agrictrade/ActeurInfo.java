/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Acteur.ActeurDAO;
import classes.SQLutils;
import entites.Acteur;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author DedOuena
 */
public class ActeurInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");

        List<Acteur> actdata = new ActeurDAO().selectAll();
        ArrayList<BigObjet> list = new ArrayList();
        BigObjet acteur;
        Connection conn_imis = SQLutils.conn_at();
        String asso, spec;
        String sql_asso, sql_spe;
        ResultSet rs, rs2;
        Statement statem, statem2;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (Acteur acteurdata : actdata) {
            acteur = new BigObjet(acteurdata.getIdActeur(), acteurdata.getNomActeur(), acteurdata.getPrenomActeur());
            acteur.setCh4(acteurdata.getNationalite().getNomPays());
            acteur.setCh5(acteurdata.getEmailActeur());
            acteur.setCh6(acteurdata.getTelActeur());
            asso = "";
            spec = "";
            try {
                sql_asso = "SELECT cigle FROM association WHERE id_assoc ='" + acteurdata.getIdAssoc() + "'";
                statem = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                statem = conn_imis.createStatement();
                rs = statem.executeQuery(sql_asso);
                if (rs != null) {
                    while (rs.next()) {
                        asso = rs.getString("cigle");
                    }
//                    acteur.setCh7(asso);
                }

                sql_spe = "SELECT lib_spe_FR FROM specialite, actorspecialite WHERE id_acteur ='" + acteurdata.getIdActeur() + "' AND "
                        + " specialite.id_specialite = actorspecialite.id_specialite";
                statem2 = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                statem = conn_imis.createStatement();
                rs2 = statem2.executeQuery(sql_spe);
                if (rs2 != null) {
                    while (rs2.next()) {
                        spec += rs2.getString("lib_spe_FR") + ", ";
                    }
//                    acteur.setCh7(asso);
                }
//                else {
//                    acteur.setCh7(asso);//                }                
            } catch (NullPointerException | SQLException hex) {
//                acteur.setCh7(asso);
//                System.out.println(hex.getMessage());
            }
            acteur.setCh7(asso);
            acteur.setCh8(spec);
            acteur.setCh9(acteurdata.getPhone2());
            acteur.setCh10(acteurdata.getPhone3());
            acteur.setCh11(acteurdata.getSexe());
            acteur.setCh12(acteurdata.getSimType());
            acteur.setCh13(df.format(acteurdata.getDatecre()));
            list.add(acteur);
        }
        try {
            conn_imis.close();
        } catch (SQLException ex) {
//            Logger.getLogger(ActeurInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(list);
        PrintWriter pw = response.getWriter();
        pw.write(array.toString());
        // System.out.println("JSONarray data " + array.toString()); 
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
