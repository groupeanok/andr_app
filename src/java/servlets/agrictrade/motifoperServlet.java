/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;


import Association.AssoDAO;
import Pays.PaysDAO;
import User.UserDAO;
import classes.SQLutils;
import entites.Association;
import entites.Operateur;
import entites.Pays;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONArray;

/**
 *
 * @author Groupe Anok
 */
public class motifoperServlet extends HttpServlet {

     
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");    
        
        String codeoper = request.getParameter("codeoper");
        String login = request.getParameter("email");
        String nom = request.getParameter("nom");
        String fonction = request.getParameter("fonction");
        String password = request.getParameter("password");
        String numero = request.getParameter("numero");
        String naissance = request.getParameter("datenaiss");
        String pays = request.getParameter("pays");
        String association = request.getParameter("association");
        String photoprofil = request.getParameter("photo");
        
        String login2 = request.getParameter("email_old");
         
//         System.out.println(codeoper);
//         System.out.println(login);
//         System.out.println(nom);
//         System.out.println(fonction);
//         System.out.println(password);
//         System.out.println(numero);
//         System.out.println(naissance);
//         System.out.println("pays : " + pays);
//         System.out.println("association : " + association);
//         System.out.println("photoprofil : " +photoprofil);
        
 
         Operateur op = null;
         
         UserDAO userDao = new UserDAO();
          ArrayList<BigObjet> userdata = new ArrayList<>();
          
         Connection conn_imis = SQLutils.conn_at();

        String sql = "SELECT * FROM operateur WHERE meloper ='" + login + "' ";

        ResultSet rs;
        int nbre_rel1 = 0;

        try (Statement statem  = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            rs = statem.executeQuery(sql);
            if (rs != null) {
                rs.last();
                nbre_rel1 = rs.getRow();
            }
        } catch (SQLException | NullPointerException e) {

        }
        
        if(login.equalsIgnoreCase(login2)) {
            nbre_rel1 = 0;
        } else {
            
        }

        if(nbre_rel1>0) {
                BigObjet b = new BigObjet("erreurmail", "erreurmail");
                userdata.add(b);
                JSONArray array = new JSONArray(userdata);
                PrintWriter pw = response.getWriter();
                pw.write(array.toString());
        } else {
          
         try {
            op = userDao.selectFind(codeoper);
         } catch(Exception e){          
             response = null;
             System.out.println(e.getMessage());
         }
        
       
        
        if (op != null) {  
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date dateNais = null ;
        try {
            dateNais = (Date) format.parse(naissance);
        } catch (ParseException ex) {
           // Logger.getLogger(CollecteServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
            
//            try {
              
                op.setMeloper(login);
                op.setNumero(numero);
                op.setFonction(fonction);
                op.setPassword(password);
                op.setNomoper(nom);
                op.setOpmaj(op);
                
                if(!pays.isEmpty()){
                    Pays pa = new PaysDAO().selectFind(pays);
                    op.setNationalite(pa);
                }
                
                if(!association.isEmpty()){
                    Association asso = new AssoDAO().selectFind(association);
                    op.setAssociation(asso);
                }
                
                op.setDatemaj(new Date());
                op.setDateNaissance(dateNais);
                
              
               
                if(photoprofil.isEmpty() || photoprofil.equalsIgnoreCase("")){
                    
                } else {                                 
                   byte[] data = DatatypeConverter.parseBase64Binary(photoprofil);
                    String path = op.getCodeoper()+".png";
                    File file = new File("ProfilUserAndroid");
                    if(!file.exists()){
                        file.mkdir();
                    }
                    File file1 = new File("ProfilUserAndroid/"+path);
                    
                    try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file1))) {
                        outputStream.write(data);
                    } catch (IOException e) {
//                        e.printStackTrace();
                    }                 
                    op.setPhoto(path);
                  }             
                 
                userDao.update(op);
                
                BigObjet b = new BigObjet("SUCCESS","","SUCCESS");
                userdata.add(b);
                JSONArray array = new JSONArray(userdata);
              PrintWriter pw = response.getWriter();
              pw.write(array.toString());
//            }
//            catch (IOException e) {
//                response = null;
//            }            
//            System.out.println("JSONarray data " + array.toString());
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
