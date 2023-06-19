/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Acteur.ActeurDAO;
import Alerte.AlerteDAO;
import entites.Acteur;
import entites.Alertetype;
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
 * @author Groupe Anok
 */
public class attribuerServlet extends HttpServlet {

    List<Alertetype> alertetypeList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        String idUser = request.getParameter("codeoper");
        String idAlerte = request.getParameter("alerte");

        ActeurDAO pDao = new ActeurDAO();
        Acteur acteur;
        AlerteDAO alertedao = new AlerteDAO();
        ArrayList<BigObjet> userdata = new ArrayList<>();

        alertetypeList.clear();

        alertetypeList.add(alertedao.selectFind(idAlerte));
        acteur = pDao.selectFind(idUser);
        boolean existe = false;
        for (int i = 0; i < acteur.getAlertetypeList().size(); i++) {
            if (acteur.getAlertetypeList().get(i).getCodetypealrt().equalsIgnoreCase(idAlerte)) {
                existe = true;
                break;
            }
        }
        if (existe) {
            BigObjet b = new BigObjet("erreur", "", "");
            userdata.add(b);
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
        } else {
            acteur.setAlertetypeList(alertetypeList);

            pDao.update(acteur);
            BigObjet b = new BigObjet("SUCCESS", "", "");
            userdata.add(b);
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
