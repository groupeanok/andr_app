/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Association.AssoDAO;
import Pays.PaysDAO;
import PosteDouane.PosteDouaneDAO;
import entites.Association;
import entites.Pays;
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
public class newPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
       
        PosteDouaneDAO postdao = new PosteDouaneDAO();
       
        PosteDouane post = new PosteDouane();
       
        
        String nomPost = request.getParameter("nom");
        String nationalite = request.getParameter("nationnalite");
        String coordx = request.getParameter("coordx");
        String coordy = request.getParameter("coordy");
            
        
        ArrayList<BigObjet> userdata = new ArrayList<>(1); 
        
         int der_ind = postdao.select_bypays(nationalite).size();
        String id_post = nationalite+ der_ind; //mescodes.code_appui_cata(conv, pay, der_ind);
        PosteDouane ap_cata = postdao.selectFind(id_post);
        while (ap_cata != null) {
            der_ind++;
            id_post = nationalite + der_ind;
            ap_cata = postdao.selectFind(id_post);
        }
        
//try {
       post.setPosteid(id_post);
       post.setPosteName(nomPost);
       post.setCoordx(Float.valueOf(coordx));
       post.setCoordy(Float.valueOf(coordy));
       post.setIdPays(nationalite);
       post.setOldId(id_post);
       
       
       postdao.insert(post);
        
         userdata.add(new BigObjet("SUCCESS",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
         //  System.out.println("JSONarray data " + specialite.toString());
               
    // } catch (IOException hx) {

//            userdata.add(new BigObjet("ERREUR",""));
//            JSONArray array = new JSONArray(userdata);
//            PrintWriter pw = response.getWriter();
//            pw.write(array.toString());
//            
//        } catch(NullPointerException e) {
//            userdata.add(new BigObjet("ERREUR",""));
//            JSONArray array = new JSONArray(userdata);
//            PrintWriter pw = response.getWriter();
//            pw.write(array.toString());       
//        }
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
