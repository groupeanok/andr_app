package servlets.agrictrade;

import Collecte.CollecteDAO;
import ValCollecte.ValCollecteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import entites.Collecte;
import entites.ValCollecte;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
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
//@WebServlet(name = "AllCollecteServlet", urlPatterns = {"/AllCollecteServlet"})
public class AllCollecteServletDay extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        Long time = new Date().getTime();
        Date date = new Date(time - time % (24 * 60 * 60 * 1000));

        List<Collecte> dataColl = new CollecteDAO().select_inter_collecte(date, new Date());
        ArrayList<BigObjet> list = new ArrayList();
        BigObjet c;

        ValCollecteDAO valcollDao = new ValCollecteDAO();
        ValCollecte valcoll;//= null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String encodedfile;
        for (Collecte itr : dataColl) {
//             = null;
            c = new BigObjet(itr.getIdCollecte(), itr.getIdMoyTrans().getLibmtEN(), df.format(itr.getDatecre()));
            c.setCh4(itr.getIdPtcharge().getLibPtcollecte());
            c.setCh13(itr.getOpcre().getNomoper());
            c.setCh17(itr.getIdActeur().getNomActeur());
            c.setCh18(itr.getIdPtdecharge().getLibPtcollecte());
            c.setCh20(String.valueOf(itr.getOtherfes()));
            c.setCh21(itr.getDecNumber());
            c.setCh22(String.valueOf(itr.getPrixtranspunit()));
            c.setCh5(String.valueOf(itr.getPrixtranspunit()));
            c.setCh23(itr.getIdMoyTrans().getLibmtFR());
            c.setCh25(itr.getOpcre().getCodeoper());
            c.setCh27(itr.getOpcre().getNationalite().getIdPays());
            c.setCh29(itr.getCodedev().getCodedev());
           

            File file = new File("ImgAndroid/" + itr.getPhoto());
//              ="";
            if (file.exists()) {
                FileInputStream fileInputStreamReader = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                fileInputStreamReader.read(bytes);
                encodedfile = Base64.getEncoder().encodeToString(bytes);
                c.setCh26(encodedfile);
            } else {
                c.setCh26("");
            }
            valcoll = valcollDao.selectFind(itr.getIdCollecte());
            if (valcoll != null) {
                c.setCh19(itr.getIdCollecte());
            } else {
                c.setCh19(" ");
            }
            c.setCh26("");

            if (itr.getValide()) {
                c.setCh30(" ");
            } else {
                c.setCh30("ok");
            }
            if (itr.getTypeCollecte().equals("ANI")) {
                c.setCh6(itr.getIdTypean().getAnimalTypeID().getNameEN() + " : " + itr.getIdTypean().getLibtypeanEN());
                c.setCh24(itr.getIdTypean().getAnimalTypeID().getNameFR() + " : " + itr.getIdTypean().getLibtypeanFR());
                c.setCh7(String.valueOf(itr.getNbreGros()));
                c.setCh8(String.valueOf(itr.getNbreMoyen()));
                c.setCh9(String.valueOf(itr.getNbrePetit()));
                c.setCh10(String.valueOf(itr.getPrixGros()));
                c.setCh11(String.valueOf(itr.getPrixPetit()));
                c.setCh12(String.valueOf(itr.getPrixMoyen()));
//                c.setCh5(String.valueOf(itr.getPrixtranspunit()));
                c.setCh14("0");
                c.setCh15("0");
                c.setCh16("0");
            } else {
                c.setCh6(itr.getIdProduit().getIdNatproduit().getLibNatproduit() + " : " + itr.getIdProduit().getLibproduitEN());
                c.setCh24(itr.getIdProduit().getIdNatproduit().getLibNatproduit() + " : " + itr.getIdProduit().getLibproduitFR());
//                c.setCh5(String.valueOf(itr.getPrixtranspunit()));
                c.setCh14(String.valueOf(itr.getNbreSacpan()));
                c.setCh15(String.valueOf(itr.getPdbyunite()));
                c.setCh16(String.valueOf(itr.getPdbysac()));                
                c.setCh7("0");
                c.setCh8("0");
                c.setCh9("0");
                c.setCh10("0");
                c.setCh11("0");
                c.setCh12("0");
            }
            c.setCh31(itr.getTypeCollecte());

            list.add(c);
        }
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
