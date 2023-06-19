/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;


import AnimalType.AnimalTypeDAO;
import Nature_Produit.Nature_ProduitDAO;
import entites.Animaltypes;
import entites.NatureProduit;
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
public class newProduitTypeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");    
        String Nomfr = request.getParameter("nomfr");
        String Nomen = request.getParameter("nomen");
        String Nompt = request.getParameter("nompt");
        String type = request.getParameter("type");
        
         if(type.equalsIgnoreCase("sima")){
             
        Nature_ProduitDAO cmdTDao = new Nature_ProduitDAO();
        NatureProduit cmd = new NatureProduit();
        
      
         ArrayList<BigObjet> userdata = new ArrayList<>(1);
         
               int nbr = 3;               
               NatureProduit mk;
               String nom = Nomfr;
               String id = nom.substring(0, nbr).toUpperCase();
        mk = new Nature_ProduitDAO().selectFind(id);
        while(mk != null){
            nbr++;
//            mk = null;
            nom = Nomfr;
            id = nom.substring(0, nbr).toUpperCase();
            mk = new Nature_ProduitDAO().selectFind(id);
        }               
       try           
         {              
           cmd.setIdNatproduit(id.toUpperCase());
           cmd.setLibNatproduit(Nomfr);
           cmd.setLibnatproduitEn(Nomen);
           cmd.setLibnatproduitPt(Nompt);
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
         } else{
             AnimalTypeDAO cmdTDao = new AnimalTypeDAO();
        Animaltypes cmd = new Animaltypes();
        
      
         ArrayList<BigObjet> userdata = new ArrayList<>(1);
               int nbr = 3;               
               Animaltypes mk;
               String nom = Nomfr;
               String id = nom.substring(0, nbr).toUpperCase();
        mk = new AnimalTypeDAO().selectFind(id);
        while(mk != null){
            nbr++;
//            mk = null;
            nom = Nomfr;
            id = nom.substring(0, nbr).toUpperCase();
            mk = new AnimalTypeDAO().selectFind(id);
        }               
              
       try
       {    
         
           
           cmd.setAnimalTypeID(id.toUpperCase());
           cmd.setNameFR(Nomfr);
           cmd.setNameEN(Nomen);
           cmd.setNamePT(Nompt);
           cmd.setActive(true);
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
