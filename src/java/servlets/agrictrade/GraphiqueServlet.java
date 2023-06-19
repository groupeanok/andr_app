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
public class GraphiqueServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");        
        
       Calendar cal = Calendar.getInstance();
       cal.add(Calendar.DAY_OF_MONTH,-60);
       Date mdate = cal.getTime();
       
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        ArrayList<BigObjet> list = new ArrayList();
          BigObjet c;
          
          String prix ="", moiss ="";
          String mil ="",riz="",mais="",sorgho="";
          
        Connection conn_imis = SQLutils.conn_at();
        
        String sql = " SELECT YEAR(col2g.DATECRE) annee, MONTH(col2g.DATECRE) mois, AVG(prix_gros) as moyen " +
                       "FROM collecte col2g, type_animal anim WHERE " +
                       "col2g.type_collecte  ='ANI' AND (col2g.id_typean = anim.id_typean AND (anim.AnimalTypeID = 'CAT' OR anim.AnimalTypeID = 'CATT') ) " +
                        "GROUP BY YEAR(col2g.DATECRE), MONTH(col2g.DATECRE) ORDER BY YEAR(col2g.DATECRE) DESC, MONTH(col2g.DATECRE) DESC LIMIT 4";
              
// bovins
         String sql1 = " SELECT count(*) as nbre"
                 + " FROM collecte coll, type_animal anim" +
                 " WHERE DATE(coll.datecre) >= '" +df.format(mdate)+"' AND coll.type_collecte='ANI'"
                 + " AND (coll.id_typean = anim.id_typean AND (anim.AnimalTypeID = 'CAT' OR anim.AnimalTypeID = 'CATT'))";

// ovins         
         String sql2 = " SELECT count(*) as nbre"
                 + " FROM collecte coll, type_animal anim" +
                 " WHERE DATE(coll.datecre) >= '" +df.format(mdate)+"' AND coll.type_collecte='ANI'"
                 + " AND (coll.id_typean = anim.id_typean AND anim.AnimalTypeID = 'SHE')";

// caprin         
          String sql3 = " SELECT count(*) as nbre"
                 + " FROM collecte coll, type_animal anim" +
                 " WHERE DATE(coll.datecre) >= '" +df.format(mdate)+"' AND coll.type_collecte='ANI'"
                 + " AND (coll.id_typean = anim.id_typean AND anim.AnimalTypeID = 'GOA')";

// asins          
        String sql4 =" SELECT count(*) as nbre"
                 + " FROM collecte coll, type_animal anim" +
                 " WHERE DATE(coll.datecre) >= '" +df.format(mdate)+"' AND coll.type_collecte='ANI'"
                 + " AND (coll.id_typean = anim.id_typean AND anim.AnimalTypeID = 'DON')";

        
        ResultSet rs; 
        ResultSet rs1;
        ResultSet rs2;
        ResultSet rs3;
        ResultSet rs4;
        
        Double moyen = 0.0 ; int mois = 0, annee = 0 ;
//        AnimalsDAO aniDao = new AnimalsDAO();
//        Animals animal;
        try (Statement statem = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            rs = statem.executeQuery(sql);
            while (rs.next()) {
                prix += rs.getString("moyen")+"::";
                mois = Integer.valueOf(rs.getString("mois"));
                moiss += genre(String.valueOf(mois)) +"::";
                annee = Integer.valueOf(rs.getString("annee"));              
         
            
          } 
            
            
            rs1 = statem.executeQuery(sql1);
            while (rs1.next()) {
                mais += rs1.getString("nbre");                         
          }
            
            rs2 = statem.executeQuery(sql2);
            while (rs2.next()) {
                mil += rs2.getString("nbre");
              
            
          }
            rs3 = statem.executeQuery(sql3);
            while (rs3.next()) {
                riz += rs3.getString("nbre");
               
            
          }
            rs4 = statem.executeQuery(sql4);
            while (rs4.next()) {
                sorgho += rs4.getString("nbre");
                           
          }
                        
        } catch (SQLException | NullPointerException e) {
            
        } 
             
            c = new BigObjet(prix,moiss,mais); 
            c.setCh4(mil);
            c.setCh5(riz);
            c.setCh6(sorgho);
            
            list.add(c);
            
            JSONArray array = new JSONArray(list);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            
//            System.out.println(array.toString());
        
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    
     public String genre(String mois) {
       
       String legenre ="";
       
         switch (mois) {
            case "1":
                legenre = "JAN";
                break ;
            case "2" : 
                legenre = "FEV";
              break; 
            case "3" : 
                legenre = "MAR";
              break; 
            case "4" : 
                legenre = "AVR";
              break; 
              case "5" : 
                legenre = "MAI";
              break; 
              case "6": 
                legenre = "JUN";
              break; 
              case "7" : 
                legenre = "JUL";
              break; 
              case "8" : 
                legenre = "AOU";
              break; 
              case "9" : 
                legenre = "SEP";
              break; 
               case "10" : 
                legenre = "OCT";
              break;
              case "11" :
                  legenre = "NOV";
                  break;
              case "12" :
                  legenre = "DEC";
                  break;               
         }
       return legenre;
   }  


}
