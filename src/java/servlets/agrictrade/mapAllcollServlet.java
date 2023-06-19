/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Point_Collecte.Point_CollecteDAO;
import entites.PointCollecte;
import java.io.IOException;
import java.io.PrintWriter;
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
public class mapAllcollServlet extends HttpServlet {

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");        
//         Point_CollecteDAO operDao = new Point_CollecteDAO();         
         List<PointCollecte> pt_list = new Point_CollecteDAO().selectAll();
//           UserDAO pDao = new UserDAO();
         ArrayList<BigObjet> list = new ArrayList();
         if(pt_list.isEmpty())
             list.add(null);
         else {
         
         //List<OperateurDetail> op_det;
         BigObjet elemMap;
         
            for (PointCollecte pt : pt_list) {                   
             elemMap = new BigObjet(pt.getLibPtcollecte(),pt.getIdPays().getNomPays(),"");
             elemMap.setVal1(pt.getCoordx());
             elemMap.setVal2(pt.getCoordy());
              
              try {             
               
                elemMap.setCh3(pt.getPosteid().getPosteName());
              } catch (NullPointerException e){
                  elemMap.setCh3("Aucun");
              }
             if(elemMap.getVal1()!= 0 || elemMap.getVal2() != 0)
             { list.add(elemMap); }
         }
        }
         
        JSONArray array = new JSONArray(list);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            //System.out.println("JSONarray data " + array.toString());       
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doGet(request,response);        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    } // </editor-fold>
}
