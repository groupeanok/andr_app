/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import User.UserDAO;
import entites.Operateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Groupe Anok
 */
public class allPointFocal extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        UserDAO pDao = new UserDAO();
        List<Operateur> list_oper = null;
         try {
             list_oper = pDao.select_bygroupe("PFOC");        
         } catch(NoResultException e){ 
             System.out.println(e.getMessage());
            // response = null;
         }
         ArrayList<BigObjet> list = new ArrayList<>();     
        if (list_oper != null) {
            BigObjet user;            
            for(Operateur op : list_oper){                
              user = new BigObjet(op.getCodeoper(),op.getNomoper(),op.getNationalite().getIdPays());
              
              list.add(user);           
        }
        
            
     } else {
            list.add(new BigObjet());
        }
        response.setCharacterEncoding("UTF-8");
      JSONArray array = new JSONArray(list);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
         // System.out.println("JSONarray data " + array.toString());        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
        
    }  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
