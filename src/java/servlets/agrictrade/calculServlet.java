/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Collecte.CollecteDAO;
import Offres.OffresDAO;
import entites.Collecte;
import entites.Offre;
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
 * @author Client
 */
public class calculServlet extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        int nbre = 0;
        int nbre1 = 0;
        int nbre2 = 0;
        
          Long time = new Date().getTime();
         Date date = new Date(time - time % (24 * 60 * 60 * 1000));
        
        List<Collecte> dataColl = new CollecteDAO().select_inter_collecte(date, new Date());
        
//         Calendar cal = Calendar.getInstance();
//         cal.add(Calendar.DAY_OF_MONTH,-1);
//         Date mdate = cal.getTime();
         
           List<Offre> offredata = new OffresDAO().selectAll(); 
           
         for(Offre off : offredata){
           if(off.getDatecre().after(date))
               nbre = nbre + off.getQteOffre();
       }        
                 for(Collecte itr : dataColl) {                    
                   if (itr.getTypeCollecte().equals("ANI")) {
                   nbre1 = nbre1 + itr.getNbreGros()+itr.getNbreMoyen()+itr.getNbrePetit();
               } else {
               nbre2 = nbre2 + itr.getNbreSacpan();                
            }    
          }   
        
         ArrayList<BigObjet> list = new ArrayList();
          BigObjet c= new BigObjet(String.valueOf(nbre1),String.valueOf(nbre2),String.valueOf(nbre));            
          
          list.add(c);
          JSONArray array = new JSONArray(list);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request,response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
