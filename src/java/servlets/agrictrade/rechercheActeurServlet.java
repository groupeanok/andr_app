/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Acteur.ActeurDAO;
import Pays.PaysDAO;
import classes.SQLutils;
import entites.Acteur;
import entites.Pays;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Groupe Anok
 */
public class rechercheActeurServlet extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");   
        String pays = request.getParameter("pays");
        String specialite = request.getParameter("specialite");
        
      //  List<Acteur> actdata = new ArrayList<Acteur>() ;
        
       if(!pays.isEmpty() && specialite.isEmpty()) {
           Pays payss = new PaysDAO().selectFind(pays);
           List<Acteur> actdata  = new ActeurDAO().select_bypays(payss);
           ArrayList<BigObjet> list = new ArrayList();
        BigObjet acteur;
        for (Acteur acteurdata : actdata) {
            acteur = new BigObjet(acteurdata.getIdActeur(),acteurdata.getNomActeur(),acteurdata.getPrenomActeur());
            acteur.setCh4(acteurdata.getNationalite().getNomPays());
            acteur.setCh5(acteurdata.getEmailActeur());
            acteur.setCh6(acteurdata.getTelActeur());
            acteur.setCh7(acteurdata.getIdAssoc().getNomAssoc());

            list.add(acteur);
        }
        response.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(list);
        PrintWriter pw = response.getWriter();
        pw.write(array.toString());
        //System.out.println("JSONarray data " + array.toString());
       } else if(!specialite.isEmpty() && pays.isEmpty()) {
           
            Connection conn_imis = SQLutils.conn_at();
         
        String sql = "SELECT id_acteur FROM actorspecialite WHERE id_specialite='"+specialite+"'";
        ResultSet rs;
        String actor;
        try (Statement statem  = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            rs = statem.executeQuery(sql);
            List<Acteur> actdata = new ArrayList();
            while (rs.next()) {
                actor = rs.getString("id_acteur");               
                actdata.add(new ActeurDAO().selectFind(actor));
            }            
            ArrayList<BigObjet> list = new ArrayList();
        BigObjet acteur;
        for (Acteur acteurdata : actdata) {
            acteur = new BigObjet(acteurdata.getIdActeur(),acteurdata.getNomActeur(),acteurdata.getPrenomActeur());
            acteur.setCh4(acteurdata.getNationalite().getNomPays());
            acteur.setCh5(acteurdata.getEmailActeur());
            acteur.setCh6(acteurdata.getTelActeur());
            acteur.setCh7(acteurdata.getIdAssoc().getNomAssoc());

            list.add(acteur);
        }
        response.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(list);
        PrintWriter pw = response.getWriter();
        pw.write(array.toString());
        } catch (SQLException e) {
           // System.out.println(e.getMessage());
        } 
            
        } else {
        Connection conn_imis = SQLutils.conn_at();
       
        String sql = "SELECT * FROM actorspecialite acs, acteur a WHERE acs.id_acteur = a.id_acteur and nationalite='"+pays+"' and id_specialite ='"+specialite+"'";
        List<Acteur> actdata = new ArrayList();
        ResultSet rs;
        String actor;
        try (Statement statem  = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            rs = statem.executeQuery(sql);
            while (rs.next()) {
                actor = rs.getString("id_acteur");
                actdata.add(new ActeurDAO().selectFind(actor));
            }
             ArrayList<BigObjet> list = new ArrayList();
        BigObjet acteur;
        for (Acteur acteurdata : actdata) {
            acteur = new BigObjet(acteurdata.getIdActeur(),acteurdata.getNomActeur(),acteurdata.getPrenomActeur());
            acteur.setCh4(acteurdata.getNationalite().getNomPays());
            acteur.setCh5(acteurdata.getEmailActeur());
            acteur.setCh6(acteurdata.getTelActeur());
            acteur.setCh7(acteurdata.getIdAssoc().getNomAssoc());

            list.add(acteur);
        }
        response.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(list);
        PrintWriter pw = response.getWriter();
        pw.write(array.toString());
        } catch (SQLException e) {
           // System.out.println(e.getMessage());
         }        
       }        
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
