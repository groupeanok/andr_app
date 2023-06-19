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
import java.text.SimpleDateFormat;
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
 * @author DedOuena
 */
public class userInviteServlet extends HttpServlet {

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");  
         List<Operateur> op = null;
         try {
            op = new UserDAO().selectAlltrie();
         } catch(NoResultException e){          
             response = null;
         }
        BigObjet b;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<BigObjet> userdata = new ArrayList<>();     
        for(Operateur op1 : op) {
            if(!op1.getIdgroupe().getIdgroupe().equals("PRO")){
            }else{
             b = new BigObjet(op1.getCodeoper(),op1.getNomoper(),op1.getFonction());
//            b.setCh3(op.getCodeoper());
            b.setCh4(op1.getNationalite().getNomPays());
            b.setCh5(op1.getNumero());
            b.setCh6(op1.getSexe());
            b.setCh7(df.format(op1.getDateNaissance()));
            b.setCh9(op1.getMeloper());
            b.setCh10(df.format(op1.getDatecre()));
            b.setCh11(op1.getIdgroupe().getGroupe());
            userdata.add(b);
             } 
            }
        response.setCharacterEncoding("UTF-8");
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
//            System.out.println("JSONarray data " + array.toString());
            }
                
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
//      LoginServlet
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    } // </editor-fold>
   
}
