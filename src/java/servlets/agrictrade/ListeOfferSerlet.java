/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Offres.OffresDAO;
import ValOffre.ValOffreDAO;
import entites.Offre;
import entites.ValOffre;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;


/**
 *
 * @author DedOuena
 */
public class ListeOfferSerlet extends HttpServlet {
 
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");       
        Calendar cal = Calendar.getInstance();
         cal.add(Calendar.DAY_OF_MONTH,-7);
         Date mdate = cal.getTime();
         
       List<Offre> offredata = new OffresDAO().selectAll();
       
        ArrayList<BigObjet> list = new ArrayList();
           BigObjet  offreInfo;
           
            ValOffreDAO valoffreDao = new ValOffreDAO();
            ValOffre  valoffre ;//= null;
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
       for(Offre off : offredata){
           if(off.getDatecre().after(mdate)){
//             valoffre= null;   
           offreInfo = new BigObjet(off.getIdActeur().getNomActeur()+"  "+ off.getIdActeur().getPrenomActeur(),df.format(off.getDatecre()),df.format(off.getDateVal()));
           offreInfo.setCh4(off.getLibelleOffre());
           offreInfo.setCh5(String.valueOf(off.getPrixOffre()));
           offreInfo.setCh6(String.valueOf(off.getQteOffre()));
           offreInfo.setCh7(off.getTypeOffre());
           offreInfo.setCh8(off.getIdOffre());
           offreInfo.setCh9(off.getIdActeur().getTelActeur() +"  "+ off.getIdActeur().getEmailActeur());
           offreInfo.setCh18(off.getIdOffre());
           offreInfo.setCh20(off.getOpcre().getCodeoper());
           offreInfo.setCh29(off.getCodedev());
           if(off.getConclus()){
               offreInfo.setCh28("OK");
           }else{
             offreInfo.setCh28(" ");  
           }
            
            File file = new File("ImgAndroid/"+off.getPhoto());
             String encodedfile ;//="";
            if(file.exists()){ 
                FileInputStream  fileInputStreamReader = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                fileInputStreamReader.read(bytes);
                encodedfile = Base64.getEncoder().encodeToString(bytes);                
                offreInfo.setCh26(encodedfile);                
            }  else {
                offreInfo.setCh26("");
            }
            
           
           valoffre = valoffreDao.selectFind(off.getIdOffre());
            if(valoffre != null){
                offreInfo.setCh19(valoffre.getIdOffre());
            }else {
                offreInfo.setCh19(" ");
            }

          try{
                offreInfo.setCh10(off.getIdProduit().getIdNatproduit().getLibNatproduit() +" : "+ off.getIdProduit().getLibproduitEN()); 
                offreInfo.setCh11("");
                offreInfo.setCh14(off.getIdProduit().getIdNatproduit().getLibNatproduit() +" : "+off.getIdProduit().getLibproduitFR());
                offreInfo.setCh15("");
            } catch(NullPointerException e){
                offreInfo.setCh11(off.getIdTypean().getAnimalTypeID().getNameEN() +" : "+ off.getIdTypean().getLibtypeanEN());
                offreInfo.setCh10("");
                offreInfo.setCh14("");
                offreInfo.setCh15(off.getIdTypean().getAnimalTypeID().getNameEN() +" : "+ off.getIdTypean().getLibtypeanFR());
            }             
           list.add(offreInfo);
        }
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
    }// </editor-fold>

}
