/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Acteur.ActeurDAO;
import Association.AssoDAO;
import Pays.PaysDAO;
import Specialite.SpecialiteDAO;
import User.UserDAO;
import entites.Acteur;
import entites.Association;
import entites.Operateur;
import entites.Pays;
import entites.Specialite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
public class newActeurServlet extends HttpServlet {
    
    
    List<Specialite> specialite = new ArrayList<>(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        
        ActeurDAO pDao = new ActeurDAO();
        Acteur acteur = new Acteur();
        Pays pays; // = new Pays();
        Association asso; // = new Association();
        PaysDAO paysDao = new PaysDAO();
        
        String nomUser = request.getParameter("nom");
        String prenomUser = request.getParameter("prenom");
        String nationalite = request.getParameter("nationnalite");
        String tel = request.getParameter("tel");
        String tel2 = request.getParameter("tel2");
        String tel3 = request.getParameter("tel3");
        String mailUser = request.getParameter("email");
        String sexe = request.getParameter("sexe");
        String assoc = request.getParameter("assoc");
        String codeOper = request.getParameter("codeoper");
        
        String coordx = request.getParameter("coordx");
        String coordy = request.getParameter("coordy");
        
        String specialite1 = request.getParameter("specialite1");
        String specialite2 = request.getParameter("specialite2");
        String specialite3 = request.getParameter("specialite3");
        
        String type = request.getParameter("type");
       
        
        SpecialiteDAO specialiteDao = new SpecialiteDAO();
        
        if(tel2.isEmpty())
          {tel2 ="Aucun";}
        
        if(tel3.isEmpty())
          {tel3 ="Aucun";}
        
        
  
            specialite.clear();
        
        if(!specialite1.isEmpty()){  
            Specialite specia = specialiteDao.selectFind(specialite1);
               specialite.add(specia);          
        }
        
          if(!specialite2.isEmpty()){  
            Specialite specia = specialiteDao.selectFind(specialite2);
               specialite.add(specia);          
        }
          if(!specialite3.isEmpty()){  
            Specialite specia = specialiteDao.selectFind(specialite3);
               specialite.add(specia);          
        }
    
        Operateur oper = new UserDAO().selectFind(codeOper);
        pays = paysDao.selectFind(nationalite);
        asso = new AssoDAO().selectFind(assoc);
        ArrayList<BigObjet> userdata = new ArrayList<>(1); 
        int der_ind = pDao.select_nb_col(pays);
        String id_col = pays.getIdPays() + der_ind; //mescodes.code_appui_cata(conv, pay, der_ind);
        Acteur ap_cata = pDao.selectFind(id_col);
        while (ap_cata != null) {
            der_ind++;
            id_col = pays.getIdPays() + der_ind;
            ap_cata = pDao.selectFind(id_col);
        }
        
try {
        acteur.setIdActeur(id_col);
        acteur.setIdAssoc(asso);
        acteur.setSexe(sexe);
        acteur.setNationalite(pays);
        acteur.setEmailActeur(mailUser);
        acteur.setNomActeur(nomUser);
        acteur.setPrenomActeur(prenomUser);
        acteur.setTelActeur(tel);
        acteur.setPhone2(tel2);
        acteur.setPhone3(tel3);
        acteur.setOpcre(oper);
        acteur.setOpmaj(oper);
        acteur.setCoordx(Float.valueOf(coordx));
        acteur.setCoordy(Float.valueOf(coordy));
        acteur.setSpecialiteList(specialite);
        acteur.setDatecre(new Date());
        acteur.setDatemaj(new Date());
        acteur.setSimType(type);
        pDao.insert(acteur);
        
        
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
