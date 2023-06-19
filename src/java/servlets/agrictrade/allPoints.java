
package servlets.agrictrade;

import Point_Collecte.Point_CollecteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import entites.PointCollecte;
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

public class allPoints extends HttpServlet {
 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");   
        List<PointCollecte> collectedata = new Point_CollecteDAO().selectAll();

      ArrayList<BigObjet> list = new ArrayList();
      BigObjet b;
    for (PointCollecte mt : collectedata ) { 
       
              if(mt.getIdPtcollecte().length()<=10 && mt.getIdPtcollecte().equals(mt.getIdPtcollecte().replaceAll(" ", ""))) {
               b = new BigObjet(mt.getIdPtcollecte(),mt.getLibPtcollecte(),mt.getIdPays().getIdPays());
              if(mt.getPtc())
               b.setCh4("1");
              else
                b.setCh4("0");
              
              list.add(b);
              }
     }  
    response.setCharacterEncoding("UTF-8");
       JSONArray array = new JSONArray(list);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            //System.out.println("JSONarray data " + array.toString());       
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

}
