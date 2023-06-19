
package servlets.agrictrade;

import Nature_Produit.Nature_ProduitDAO;
import entites.NatureProduit;
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
 * @author DedOuena
 */

public class allProduct extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");       
        List<NatureProduit> collectedata = new Nature_ProduitDAO().selectAll();
      ArrayList<BigObjet> list = new ArrayList();
      
    for (NatureProduit mt : collectedata ) { 
               BigObjet b = new BigObjet(mt.getIdNatproduit(),mt.getLibNatproduit(),mt.getLibNatproduit());  
               b.setCh4(mt.getLibnatproduitPt());
               list.add(b);
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
