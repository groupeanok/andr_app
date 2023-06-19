/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;


import Groupe.GroupeDAO;
import entites.Groupe;
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
 * @author Groupe Anok
 */
public class groupeServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");   
        
//          gr = null;
        
         List<Groupe>   gr = new GroupeDAO().selectAll();
        BigObjet b;
        ArrayList<BigObjet> userdata = new ArrayList<>();    
//         File file = new File("ImgAndroid");
        for(Groupe grp : gr) {
            if(grp.getIdgroupe().equals("PRO")){ 
                
               }
            else{
             b = new BigObjet(grp.getIdgroupe(),grp.getGroupe());
               userdata.add(b);
               } 
            }
        response.setCharacterEncoding("UTF-8");
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
        
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
