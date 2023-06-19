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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Groupe Anok
 */
public class newCurrencyValueServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
//        String devise;  //= request.getParameter("devise");
        String mois = request.getParameter("mois");
        String currentvalue = request.getParameter("currentvalue");

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String annee = String.valueOf(cal.get(Calendar.YEAR));

        ArrayList<BigObjet> userdata = new ArrayList<>(1);

        Connection conn_imis = SQLutils.conn_at();
        
        String[] mesElems = currentvalue.split("::");
        
        int nbr = 0;
        
        for (String mesElem : mesElems) {
              String[] mesElems2 = mesElem.split("--");
            
        String sql2 = "SELECT * FROM `currency_value` WHERE currency  ='" + mesElems2[0] + "' AND month ='" + mois + "'"
                + " AND year ='" + annee + "'";

        ResultSet rs;
        int nbre_rel = 0;

        try {
//            Statement statem = conn_imis.createStatement();
Statement statem  = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statem.executeQuery(sql2);

            if (rs != null) {
                rs.last();
                nbre_rel = rs.getRow();
            }

            if (nbre_rel > 0) {

              nbr ++;

            } else {

                String query = "insert into currency_value (currency, dollar_value, month, year) values (?, ?, ?, ?)";

                PreparedStatement preparedStmt = conn_imis.prepareStatement(query);
                preparedStmt.setString(1, mesElems2[0]);
                preparedStmt.setString(2, mesElems2[1]);
                preparedStmt.setString(3, mois);
                preparedStmt.setString(4, annee);

                preparedStmt.execute();
            }
               
         } catch (SQLException hex) {
               nbr ++; 
               System.out.println(hex.getMessage());
//             conn_imis.close();
          }
        }
        
        try {
            conn_imis.close();
        } catch (SQLException ex) {
            Logger.getLogger(newCurrencyValueServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          if(nbr == mesElems.length) {
                userdata.add(new BigObjet("erreur1", ""));
                JSONArray array = new JSONArray(userdata);
                PrintWriter pw = response.getWriter();
                pw.write(array.toString());
                
            } else {
               userdata.add(new BigObjet("SUCCESS", ""));
                JSONArray array = new JSONArray(userdata);
                PrintWriter pw = response.getWriter();
                pw.write(array.toString());
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
