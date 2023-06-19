/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import classes.SQLutils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Groupe Anok
 */
public class retireptServlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        String codeoper = request.getParameter("codeoper");
        String ptcollecte = request.getParameter("ptcollecte");

      

            ArrayList<BigObjet> userdata = new ArrayList<>();
            try {
               
                Connection conn_imis = SQLutils.conn_at();
                
                  String sql = "DELETE FROM operateur_detail WHERE codeoper = ? AND id_ptcollecte = ? ";
                  
                  PreparedStatement preparedStmt = conn_imis.prepareStatement(sql);
                  
                  preparedStmt.setString(1, codeoper);
                  preparedStmt.setString(2, ptcollecte);
               
                  preparedStmt.executeUpdate();

                  conn_imis.clearWarnings();
                
                
                BigObjet b = new BigObjet(codeoper, ptcollecte, "SUCESS");
                userdata.add(b);
                JSONArray array = new JSONArray(userdata);
                PrintWriter pw = response.getWriter();
                pw.write(array.toString());
                
            } catch (SQLException e) {

                BigObjet b = new BigObjet("erreur", "erreur", "erreur");
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
