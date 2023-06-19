package servlets.agrictrade;

import Acteur.ActeurDAO;
import entites.Acteur;
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
 * @author DedOuena
 */
public class allActors extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");   
        List<Acteur> Userdt = new ActeurDAO().selectAll();
       ArrayList<BigObjet> list = new ArrayList();
        if (Userdt.isEmpty()) {
            list.add(null);
        } else {
            for (Acteur a : Userdt) {
                 if(a.getIdActeur().length()<=6 && a.getIdActeur().equals(a.getIdActeur().replaceAll(" ", "")))
                list.add(new BigObjet(a.getIdActeur(), a.getNomActeur() + " " + a.getPrenomActeur()));
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
        doGet(request, response);

    }

    @Override

    public String getServletInfo() {
        return "Short description";
    } // </editor-fold>

}
