/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;


import Pays.PaysDAO;
import Point_Collecte.Point_CollecteDAO;
import entites.Pays;
import entites.PointCollecte;
import java.io.IOException;
import java.io.PrintWriter;
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
public class motifPtcollServlet extends HttpServlet {

    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");     
        String idPointColl = request.getParameter("codecollpt");
        String libelle = request.getParameter("libelle");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String paysid = request.getParameter("nat");
 
       
         PointCollecte ptcollecte = null;
         
//         System.out.println(idPointColl);
//         System.out.println(libelle);
//         System.out.println(latitude);
//         System.out.println(paysid);
         
          Point_CollecteDAO ptcollectedao = new Point_CollecteDAO();
          
          ptcollecte = ptcollectedao.selectFind(idPointColl); 
         
         
          
          Pays pays = null;
          if(!paysid.isEmpty())
            pays = new PaysDAO().selectFind(paysid);
        
        ArrayList<BigObjet> userdata = new ArrayList<>();     
        if (ptcollecte != null) {
            
         try {
            ptcollecte.setLibPtcollecte(libelle);
            ptcollecte.setCoordx(Float.parseFloat(latitude));
            ptcollecte.setCoordy(Float.parseFloat(longitude));
            if(pays != null)
               ptcollecte.setIdPays(pays);
            
           ptcollectedao.update(ptcollecte);
                
                BigObjet b = new BigObjet("SUCCESS","","");
                userdata.add(b);
                JSONArray array = new JSONArray(userdata);
              PrintWriter pw = response.getWriter();
              pw.write(array.toString());
              
            }
        
            catch (IOException e) {
//                response = null;
            }  

//            System.out.println("JSONarray data " + array.toString());
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

