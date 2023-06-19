/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Produits.ProduitDAO;
import entites.Produit;
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
 * @author Client
 */

public class allProduitoffline extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");   
        List<Produit> collectedata = new ProduitDAO().selectAll();
      ArrayList<BigObjet> list = new ArrayList();
      // Probleme ici si la liste de moyen de transport est superieur 
      // a celui des points de collecte
    for (Produit mt : collectedata ) { 
                BigObjet b = new BigObjet(mt.getIdProduit(),mt.getLibproduitEN(),mt.getIdNatproduit().getIdNatproduit()); 
                b.setCh4(mt.getLibproduitFR());
                b.setCh5(mt.getLibproduitPT());
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
