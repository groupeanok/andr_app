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
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author DedOuena
 */
public class droitServlet extends HttpServlet {

    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");    
        String login = request.getParameter("username");
        String codeoper = request.getParameter("codeoper");
        String group = request.getParameter("groupe");
        String opermaj = request.getParameter("copeopermaj");
 
//        UserDAO opDao = new UserDAO();
//         Operateur op = null;
//          Groupe groupe;
          
////          System.out.println("111");


//         try {
//            op = opDao.selectFind(codeoper);
//            System.out.println(op);
//         } catch(NoResultException e){          
//             response = null;
//         }

          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          String date = df.format(new Date());
         
         ArrayList<BigObjet> userdata = new ArrayList<>();    
        
        // debut du changement avec requête direct requete
          String sql = "UPDATE `operateur` SET `idgroupe` = '"+group+"', `OPMAJ` = '"+opermaj+"', `DATEMAJ` = '"+date+"'   WHERE `operateur`.`CODEOPER` = '"+codeoper+"' " ;
          
           Connection conn_imis = SQLutils.conn_at();
           
        try {
            Statement statem = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);         
               
            statem.executeUpdate(sql);
            
             BigObjet b = new BigObjet(login,"",codeoper);
                userdata.add(b);
                
              JSONArray array = new JSONArray(userdata);
              PrintWriter pw = response.getWriter();
              pw.write(array.toString());
              
              conn_imis.close();
        
           } catch (SQLException ex) {
             response = null;
             System.out.println(ex.getMessage());
           }
        
//        if (op != null) {
//            
//            try {
//               groupe = new GroupeDAO().selectFind(group);
//                op.setMeloper(login);
//                   op.setOpmaj(opDao.selectFind(opermaj));
//                   op.setDatemaj(new Date());
//                   op.setIdgroupe(groupe);
////                System.out.println(groupe);   
//                opDao.update(op);
                
               
              
//               System.out.println("JSONarray data " + array.toString());
//            }
//            catch (IOException e) {
//                response = null;
//            }            
           
//        }
  
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

}
