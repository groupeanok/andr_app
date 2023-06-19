/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;


import Pays.PaysDAO;
import Point_Collecte.Point_CollecteDAO;
import PosteDouane.PosteDouaneDAO;
import entites.Pays;
import entites.PointCollecte;
import entites.PosteDouane;
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
public class newfocalPtServlet extends HttpServlet {

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        
       Point_CollecteDAO pDao = new Point_CollecteDAO();
       
//         pays = new Pays();
        PaysDAO paysDao = new PaysDAO();
        PointCollecte ptColl = new PointCollecte();
//         poste = null;
        
        String nomptColl = request.getParameter("nom");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String nationalite = request.getParameter("pays");
        String post = request.getParameter("post"); 
        String natureptc = request.getParameter("natureptc");
        String ptc = request.getParameter("ptc");
        
       Pays pays = paysDao.selectFind(nationalite);
       PosteDouane poste = new PosteDouaneDAO().selectFind(post);
       
        ArrayList<BigObjet> userdata = new ArrayList<>(1); 
        int der_ind = pDao.der_ind_min(pays);
        String id_col = pays.getIdPays() + der_ind; //mescodes.code_appui_cata(conv, pay, der_ind);
        PointCollecte ap_cata = pDao.selectFind(id_col);
        
        while (ap_cata != null) {
            der_ind++;
            id_col = pays.getIdPays() + der_ind;
            ap_cata = pDao.selectFind(id_col);
        }
        
  try {
        ptColl.setIdPtcollecte(id_col);
        ptColl.setLibPtcollecte(nomptColl);
        ptColl.setIdPays(pays);
        ptColl.setCoordx(Float.parseFloat(latitude));
        ptColl.setCoordy(Float.parseFloat(longitude));
        ptColl.setNaturePointCollecte(natureptc);
        
        if(ptc.equalsIgnoreCase("yes"))
           ptColl.setPtc(true);
        else
            ptColl.setPtc(false);
        
        
        if(poste != null){
        ptColl.setPosteid(poste);
        }      
        pDao.insert(ptColl);

         userdata.add(new BigObjet("SUCCESS",id_col));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
         //  System.out.println("JSONarray data " + specialite.toString());
               
     } catch (IOException | NullPointerException hx) {

            userdata.add(new BigObjet("ERREUR",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            
           // System.out.println("JSONarray data " + array.toString());
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
    }// </editor-fold>

}
