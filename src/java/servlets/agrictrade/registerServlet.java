
package servlets.agrictrade;

import Association.AssoDAO;
import Groupe.GroupeDAO;
import Pays.PaysDAO;
import User.UserDAO;
import entites.Association;
import entites.Groupe;
import entites.Operateur;
import entites.Pays;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author DedOuena
 */

public class registerServlet extends HttpServlet {
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");   
           UserDAO pDao = new UserDAO();
//           UserDAO pDaos = new UserDAO();         
           Operateur user = new Operateur();
           Operateur oper;
//           Operateur opersim = new Operateur();
           Groupe groupe;
           Pays pays;
    
        String nomUser = request.getParameter("nom");
        String codeUser = request.getParameter("code");
        String fonctionUser = request.getParameter("fonction");
        String mailUser = request.getParameter("email");
        String dateNaisUser = request.getParameter("age");
        String password = request.getParameter("password");
        String nationalite = request.getParameter("nationnalite"); 
        String numero = request.getParameter("numero");
        String sexe = request.getParameter("sexe"); 
        // String sim = request.getParameter("sim");
        
        ArrayList<BigObjet> userdata = new ArrayList<>(1); 
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date dateNais = null ;
        try {
            dateNais = (Date) format.parse(dateNaisUser);
        } catch (ParseException ex) {
           // Logger.getLogger(CollecteServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
 boolean codeok = false;
        int codecpt = 0;
        String codeoperori = codeUser;
         List<Operateur> list = pDao.select_bycode(codeUser);
        if (!list.isEmpty() && list.get(0).getMeloper().equalsIgnoreCase(mailUser)
                && list.get(0).getNomoper().equalsIgnoreCase(nomUser)
                && list.get(0).getNationalite().getIdPays().trim().equalsIgnoreCase(nationalite)
                && list.get(0).getFonction().equalsIgnoreCase(fonctionUser)
                && list.get(0).getPassword().equalsIgnoreCase(password)
                && list.get(0).getDateNaissance().equals(dateNais)) {
             userdata.add(new BigObjet("SUCCESS", ""));
                JSONArray array = new JSONArray(userdata);
                PrintWriter pw = response.getWriter();
                pw.write(array.toString());

        } else {

        while(!codeok) {
        if(!pDao.select_bycode(codeUser).isEmpty()){
//            userdata.add(new BigObjet("erreurcode",""));
//            JSONArray array = new JSONArray(userdata);
//            PrintWriter pw = response.getWriter();
//            pw.write(array.toString());
           codeUser = codeoperori + String.valueOf(codecpt++);           
        } else {
            codeok = true;
        }
     } 
        if(!pDao.select_bymail(mailUser).isEmpty()){
            userdata.add(new BigObjet("erreurmail",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
 }
 else{    
       try  {    
        oper = pDao.selectFind("ADMIN"); 
        groupe = new GroupeDAO().selectFind("PRO");
        pays = new PaysDAO().selectFind(nationalite);
        
            user.setDatecre(new Date());
            user.setDatemaj(new Date());
            user.setNationalite(pays);
            user.setDateNaissance(dateNais);
            user.setNomoper(nomUser);
            user.setCodeoper(codeUser);
            user.setMeloper(mailUser);
            user.setFonction(fonctionUser);
            user.setPassword(password);
            user.setNumero(numero);   
            user.setOpcre(oper);
            user.setOpmaj(oper);
            user.setIdgroupe(groupe);
            user.setSexe(sexe);
            user.setIdPfoc(" ");
//            user.setCompte("invite");

            Association asso = new AssoDAO().selectFind("01");
            if(asso != null) {
              user.setAssociation(asso);
            }else {
              asso =  new AssoDAO().selectAll().get(0);
              user.setAssociation(asso);
            }
            
           user.setIdEnt(oper.getIdEnt());
            user.setPhoto("-");
            pDao.insert(user);
                     
            userdata.add(new BigObjet("SUCCESS",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
           // System.out.println("JSONarray data " + array.toString());
           
        } catch (IOException | NullPointerException hx) {

            userdata.add(new BigObjet("ERREUR",""));
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            
           // System.out.println("JSONarray data " + array.toString());
        }
       }
    }
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
