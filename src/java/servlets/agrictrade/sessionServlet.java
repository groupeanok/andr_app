/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Session.SessionDAO;
import User.UserDAO;
import entites.Operateur;
import entites.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Groupe Anok
 */
public class sessionServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");    
        String codeoper = request.getParameter("codeoper");
        String imei = request.getParameter("imei");
        String modelPhone = request.getParameter("modelPhone");
        String type = request.getParameter("type");
        
        
        SessionDAO SessionDao = new SessionDAO();
//        UserDAO userDao = new UserDAO();
        Session Session = new Session();
//        Operateur oper = userDao.selectFind(codeoper);
        
    if(type.equalsIgnoreCase("connexion")) {
        int nbr = 0;
           
//         ArrayList<BigObjet> userdata = new ArrayList<>(1);
       
         
          DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
          Date dateCol = new Date();
      
                    
         String idSession = codeoper + df.format(dateCol);
                 
        Session mk = SessionDao.selectFind(idSession);
        while(mk != null){
            nbr++;
            idSession += nbr;
            mk = SessionDao.selectFind(idSession);
        }
        
        Session.setIdSession(idSession);
        Session.setAdresseIp(imei);
        Session.setCodeoper(codeoper);
        Session.setIdMachine(imei);
        Session.setSystExploitation(modelPhone);
        Session.setDebSession(new Date());
        Session.setFinSession(dateCol);

        SessionDao.insert(Session);

//            userdata.add(new BigObjet("SUCCESS",""));
//            JSONArray array = new JSONArray(userdata);
//            PrintWriter pw = response.getWriter();
//            pw.write(array.toString());
            

         } else {
       
        List<Session> list = SessionDao.ChercheSession(imei);
        if(!list.isEmpty())
         Session = list.get(0);

        Session.setFinSession(new Date());

        SessionDao.update(Session);
        
//        System.out.println(Session);
                
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
