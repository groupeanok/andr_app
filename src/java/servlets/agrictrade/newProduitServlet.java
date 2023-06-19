/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import AnimalType.AnimalTypeDAO;
import Produits.ProduitDAO;
import entites.TypeAnimal;
import Nature_Produit.Nature_ProduitDAO;
import TypeAnimal.TypeAnimalDAO;
import entites.Produit;
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
public class newProduitServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");  
        String Nomfr = request.getParameter("nomfr");
        String Nomen = request.getParameter("nomen");
        String comoditieType = request.getParameter("typeprod");
        String Nompt = request.getParameter("nompt");
        String type = request.getParameter("type");
        
        if(type.equalsIgnoreCase("sima")){
//        Nature_ProduitDAO cmdTDao = new Nature_ProduitDAO();
        Produit cmd = new Produit();
        
      
         ArrayList<BigObjet> userdata = new ArrayList<>(1);
               int nbr = new ProduitDAO().der_ind_min(new Nature_ProduitDAO().selectFind(comoditieType));
               String idprod = comoditieType+nbr;
              
//                mk = null;
     Produit   mk = new ProduitDAO().selectFind(idprod);
        while(mk != null){
            nbr++;
//            mk = null;
            idprod = comoditieType+nbr;
            mk = new ProduitDAO().selectFind(idprod);
        }              
       try
       {       
           cmd.setIdNatproduit(new Nature_ProduitDAO().selectFind(comoditieType));
           cmd.setIdProduit(idprod.toUpperCase());
           cmd.setLibproduitEN(Nomen);
           cmd.setLibproduitFR(Nomfr);
           cmd.setLibproduitPT(Nompt);
           cmd.setHSCode("12554");
           new ProduitDAO().insert(cmd);
                
            userdata.add(new BigObjet("SUCCESS",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            
       }catch(IOException e){
            userdata.add(new BigObjet("ERREUR",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
         }       
      } else {
            
        TypeAnimalDAO cmdTDao = new TypeAnimalDAO();
        TypeAnimal cmd = new TypeAnimal();
            
         ArrayList<BigObjet> userdata = new ArrayList<>(1);
            int nbr = new TypeAnimalDAO().der_ind_min(new AnimalTypeDAO().selectFind(comoditieType));
               String idprod = comoditieType+nbr;
              
//                mk = null;
      TypeAnimal  mk = new TypeAnimalDAO().selectFind(idprod);
        while(mk != null){
            nbr++;
//            mk = null;
            idprod = comoditieType+nbr;
            mk = new TypeAnimalDAO().selectFind(idprod);
        }
              
       try
       {       
           
           
           cmd.setAnimalTypeID(new AnimalTypeDAO().selectFind(comoditieType));
           cmd.setIdTypean(idprod.toUpperCase());
           cmd.setLibtypeanFR(Nomfr);
           cmd.setLibtypeanEN(Nomen);
           cmd.setLibtypeanPT(Nompt);
           
                      
           cmdTDao.insert(cmd);
                
            userdata.add(new BigObjet("SUCCESS",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            
       }catch(IOException e){
            userdata.add(new BigObjet("ERREUR",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
         }
            
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
