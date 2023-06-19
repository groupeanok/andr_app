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
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author DedOuena
 */
public class ChangeLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST"); 
        String login = request.getParameter("username");
        String pass = request.getParameter("password");
        String codeoper = request.getParameter("codeoper");
 
         Operateur op = null;
         try {
            op = new UserDAO().selectFind(codeoper);
         } catch(NoResultException e){          
             response = null;
         }
        
        ArrayList<BigObjet> userdata = new ArrayList<>();     
        if (op != null) {
            
            try {
               
                op.setMeloper(login);
                op.setPassword(pass);
                new UserDAO().update(op);
                BigObjet b = new BigObjet(login,pass,op.getCodeoper());
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

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
