/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Offres.OffresDAO;
import OffresConclus.OffresConclusDAO;
import User.UserDAO;
import entites.Offre;
import entites.Offreconclus;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class concluServlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        String idColl = request.getParameter("idcoll");
        String codeoper = request.getParameter("codeoper");
        String mtn = request.getParameter("montant");
        String clause = request.getParameter("clause");
        String commentaire = request.getParameter("commentaire");
        
        
//          of = null;
         
//         OffresConclusDAO valcoll = new OffresConclusDAO();
         Offreconclus  valoffre = new Offreconclus();
        
           OffresDAO opdao = new OffresDAO();
           Offre of = opdao.selectFind(idColl); 
            ArrayList<BigObjet> userdata = new ArrayList<>();     
        if (of != null) {           
            valoffre.setIdOffre(idColl);
            valoffre.setClause(clause);
            valoffre.setMtconc(Integer.parseInt(mtn));
            valoffre.setDateconc(new Date());
            valoffre.setCommentaire(commentaire);
            
            of.setConclus(true);
            of.setDatemaj(new Date());
            of.setOpmaj(new UserDAO().selectFind(codeoper));
                       
            try {             
                   new OffresConclusDAO().insert(valoffre);
                   new OffresDAO().update(of);
                
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}