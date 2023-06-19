/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import Offres.OffresDAO;
import User.UserDAO;
import entites.Offre;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Groupe Anok
 */
public class motifOffreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        String idColl = request.getParameter("idcoll");
        String codeoper = request.getParameter("codeoper");
        String nbbig = request.getParameter("quantite");
        String prixbig = request.getParameter("prix");
        
//         op = null;

        OffresDAO opdao = new OffresDAO();
       Offre op = opdao.selectFind(idColl);

        ArrayList<BigObjet> userdata = new ArrayList<>();
        if (op != null) {
            op.setQteOffre(Integer.parseInt(nbbig));
            op.setPrixOffre(Integer.parseInt(prixbig));
            op.setOpmaj(new UserDAO().selectFind(codeoper));
            op.setDatemaj(new Date());

            //  try {             
            new OffresDAO().update(op);

            BigObjet b = new BigObjet("SUCCESS", "", "");
            userdata.add(b);
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            //       }
            //    catch (IOException e) {
            //         response = null;
            //     }            
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
