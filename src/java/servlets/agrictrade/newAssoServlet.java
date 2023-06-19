/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Association.AssoDAO;
import Pays.PaysDAO;
import User.UserDAO;
import entites.Association;
import entites.Operateur;
import entites.Pays;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Groupe Anok
 */
public class newAssoServlet extends HttpServlet {

    
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        AssoDAO assodao = new AssoDAO();
       
        Association asso = new Association();
       
        
        String nomAsso = request.getParameter("nom");
        String cicle = request.getParameter("cicle");
        String nationalite = request.getParameter("nationnalite");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String responsable = request.getParameter("responsable"); 
        String oper =  request.getParameter("codeoper");
        
        ArrayList<BigObjet> userdata = new ArrayList<>(1); 
//        int der_ind = assodao.selectAll().size();
       Pays pays = new PaysDAO().selectFind(nationalite);
       
       Operateur op = new UserDAO().selectFind(oper);
        
    String idAsso = cicle.replaceAll(" ", "");
       
       if(idAsso.length()> 8) {
           idAsso = idAsso.substring(0,8);
       }
        
   try {
       asso.setIdAssoc(idAsso.toUpperCase());

       asso.setNomAssoc(nomAsso);
       asso.setCigle(cicle);
       asso.setEmail(email);
       asso.setResponsable(responsable);
       asso.setTelephone(tel);
       asso.setCodepays(pays);
       asso.setDatecre(new Date());
       asso.setDatemaj(new Date());
       
       if(op != null ) {
         asso.setOpcre(op);
         asso.setOpmaj(op);
       }
       try {
       
       assodao.insert(asso);
        
       } catch(PersistenceException pe) {
           userdata.add(new BigObjet("ERREUR1",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            System.out.println(pe.getMessage());
       } 
        
            userdata.add(new BigObjet("SUCCESS",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
         //  System.out.println("JSONarray data " + specialite.toString());
               
     } catch (IOException | NullPointerException hx) {

            userdata.add(new BigObjet("ERREUR",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            
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
