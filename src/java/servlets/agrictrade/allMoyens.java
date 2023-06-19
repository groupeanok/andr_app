
package servlets.agrictrade;

//import Point_Collecte.Point_CollecteDAO;
import Moyen_transp.Moyen_transDAO;
import java.io.IOException;
import java.io.PrintWriter;
import entites.MoyenTrans;
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

public class allMoyens extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        
        List<MoyenTrans> collectedata = new Moyen_transDAO().selectAll();        
      
      ArrayList<BigObjet> list = new ArrayList();
    for (MoyenTrans mt : collectedata ) { 

              list.add(new BigObjet(mt.getIdMoyTrans(),mt.getLibmtEN(),mt.getLibmtFR()));  
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
