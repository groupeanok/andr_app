package servlets.agrictrade;

import Collecte.CollecteDAO;
import classes.DateHelper;
import java.io.IOException;
import java.io.PrintWriter;
import entites.Collecte;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author KONOMBO
 */
//@WebServlet(name = "AllCollecteFlux", urlPatterns = {"/AllCollecteServlet"})
public class AllCollecteFlux extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
//        List<Collecte> dataColl = mCollecteDAO.selectAll();
        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DAY_OF_MONTH, -365);
//        Date mdate = cal.getTime();   
//        int year = cal.get(Calendar.YEAR)-10;
//        int year = cal.get(Calendar.YEAR)-3;
        int year = cal.get(Calendar.YEAR);
        List<Collecte> dataColl;
        dataColl = new CollecteDAO().select_inter_collecte(DateHelper.premdayofyear(year), new Date());
        ArrayList<BigObjet> list = new ArrayList();
        BigObjet c;
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        ValCollecteDAO valcollDao = new ValCollecteDAO();
//        ValCollecte valcoll; // = null;
//        File file;
//        String encodedfile;
        for (Collecte itr : dataColl) {
//            valcoll = null;
            c = new BigObjet(itr.getIdCollecte(), itr.getTypeCollecte(), df.format(itr.getDateCollecte()));
            // Ok pour 1,2,3 qui sontt - code collecte, type de collecte (VI ou AN), date saisie
//            c.setCh4(itr.getIdMoyTrans().getLibmtFR());
            if (dateFormat.format(itr.getDatecre()).contains("00:00:00")) {
                c.setCh4(df.format(itr.getDatecre()));
            } else {
                c.setCh4(dateFormat.format(itr.getDatecre()));
//                    System.out.println(dateFormat.format(rs.getDate("datecre")));
            }
            c.setCh5(itr.getIdActeur().getNomActeur());
            c.setCh17("-");//Unité de messure initialisé
            c.setCh18(itr.getCodedev().getCodedev());
            if (itr.getCodedev().getCodedev().equals("$")) {
                if (itr.getCoursAvecDollard() == 1f) {
                    c.setCh19("-");
                } else {
                    c.setCh19(String.valueOf(itr.getCoursAvecDollard()));
                }
            } else {
                c.setCh19(String.valueOf(itr.getCoursAvecDollard()));
            }
              
            if (itr.isPassageDouane()) {
                c.setCh20("OUI");
                if (itr.getDecNumber().isEmpty()) {
                    c.setCh21("--");
                } else {
                    c.setCh21(itr.getDecNumber());
                }
            } else {
                c.setCh20("NON");
                c.setCh21("--");
            }
//            c.setCh20(String.valueOf(itr.getOtherfes()));           
//            c.setCh21(itr.getDecNumber());
            c.setCh22(itr.getIdPtcharge().getLibPtcollecte()); // Le point de chargement
            c.setCh23(itr.getIdPtdecharge().getLibPtcollecte()); // Le point de dechargement
            c.setCh24(itr.getIdPtcollecte().getLibPtcollecte()); // Le point de collecte
            c.setCh25(itr.getIdPtcharge().getIdPays().getNomPays()); // Le pays du point de collecte
            c.setCh26(itr.getIdPtdecharge().getIdPays().getNomPays()); // Le pays du point de collecte
            c.setCh27(itr.getIdPtcollecte().getIdPays().getNomPays()); // Le pays du point de collecte                       
            c.setCh28(itr.getOpcre().getNomoper()); // l'enquetteur de la collecte           
            c.setCh29(itr.getIdActeur().getIdAssoc().getCigle()); // La nationalite de l'enquetteur
//            c.setCh28(itr.getCodedev().getCodedev());          
//            else
//            c.setVal1(itr.getCoursAvecDollard()); 
            if (itr.getValide()) {
                c.setCh30(" ");
            } else {
                c.setCh30("ok");
            }
            c.setCh31(itr.getCpcRegime()); // CPC Regime (import ou export  
            c.setCh32(itr.getIdMoyTrans().getLibmtFR()); 
            c.setCh33(itr.getIdMoyTrans().getLibmtEN()); 

// Le moyen de transport
//            if (dateFormat.format(itr.getDatecre()).contains("00:00:00")) {
//                c.setCh34(df.format(itr.getDatecre()));
//            } else {
//                c.setCh34(dateFormat.format(itr.getDatecre()));
////                    System.out.println(dateFormat.format(rs.getDate("datecre")));
//            }
//            c.setCh35("-");                     
            if (itr.getTypeCollecte().equals("ANI")) {
                c.setCh6(itr.getIdTypean().getAnimalTypeID().getNameFR() + " : " + itr.getIdTypean().getLibtypeanFR());
                c.setCh34(itr.getIdTypean().getAnimalTypeID().getNameEN()+" : "+ itr.getIdTypean().getLibtypeanEN());
                c.setCh7(String.valueOf(itr.getNbreGros()));
                c.setCh8(String.valueOf(itr.getNbreMoyen()));
                c.setCh9(String.valueOf(itr.getNbrePetit()));
                c.setCh10(String.valueOf(itr.getPrixGros()));
                c.setCh11(String.valueOf(itr.getPrixMoyen()));
                c.setCh12(String.valueOf(itr.getPrixPetit()));
//                c.setCh5(String.valueOf(itr.getPrixtranspunit())); // revoir son cas, repris dans CH22
                c.setCh13("0");
                c.setCh14("0");
                c.setCh15("0");
                c.setCh16(itr.getIdTypean().getHScode());
            } else {
                c.setCh6(itr.getIdProduit().getIdNatproduit().getLibNatproduit() + " : " + itr.getIdProduit().getLibproduitFR());
                c.setCh34(itr.getIdProduit().getIdNatproduit().getLibNatproduit()+" : "+ itr.getIdProduit().getLibproduitEN());  
//                c.setCh5(String.valueOf(itr.getPrixtranspunit())); // revoir son cas, repris dans CH22
                c.setCh7("0");
                c.setCh8("0");
                c.setCh9("0");
                c.setCh10("0");
                c.setCh11("0");
                c.setCh12("0");
                c.setCh13(String.valueOf(itr.getNbreSacpan()));
                c.setCh14(String.valueOf(itr.getPdbyunite()));
                c.setCh15(String.valueOf(itr.getPdbysac()));
                c.setCh16(itr.getIdProduit().getHSCode());
                if (itr.getUnitid() != null) {
                    c.setCh17(itr.getUnitid().getUnitid());
                }
            }  
//             c.setCh32(itr.getIdMoyTrans().getLibmtFR());
            // 33, 34, 35
            c.setVal1(Float.valueOf(itr.getPrixtranspunit()));
            c.setVal2(Float.valueOf(itr.getOtherfes()));
            list.add(c);
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
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
