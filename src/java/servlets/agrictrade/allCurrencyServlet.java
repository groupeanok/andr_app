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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Groupe Anok
 */
public class allCurrencyServlet extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");   
       
        ArrayList<BigObjet> list = new ArrayList();
         Connection conn_imis = SQLutils.conn_at();
         
         String sql = "SELECT * FROM `currency_value` ORDER BY `year` DESC, `month` DESC LIMIT 100";
         
         ResultSet rs; 
         try (Statement statem = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            rs = statem.executeQuery(sql);                                          
            while (rs.next()) {                                
                BigObjet b = new BigObjet(rs.getString("currency"),rs.getString("month"),rs.getString("year"));
                b.setCh4(rs.getString("dollar_value"));
                b.setCh5(genre(rs.getString("currency")));                 
                list.add(b);
                }
            conn_imis.close();
        
          } catch (SQLException | NullPointerException e) {
            
        } 
         
        response.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(list);
        PrintWriter pw = response.getWriter();
        pw.write(array.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);              
    }

    @Override
    
    public String getServletInfo() {
        return "Short description";
    } // </editor-fold>
    
      public String genre(String mois) {
       
       String legenre ="";
       
         switch (mois) {
            case "XOF":
                legenre = "CFA BCEAO";
                break ;
            case "CVE" : 
                legenre = "Escudos CapVert";
              break; 
            case "GHS" : 
                legenre = "Cedi ghanéen";
              break; 
            case "GMD" : 
                legenre = "Dalasi";
              break; 
              case "NGN" : 
                legenre = "Naira";
              break; 
            case "XAF": 
                legenre = "CFA BEAC";
              break; 
            default :
                legenre = mois;
                break;
         }
       return legenre;
   }
    
}
