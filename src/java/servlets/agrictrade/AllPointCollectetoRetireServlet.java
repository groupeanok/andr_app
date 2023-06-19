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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class AllPointCollectetoRetireServlet extends HttpServlet {

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        BigObjet user;
        ArrayList<BigObjet> list = new ArrayList<>();

        Connection conn_imis = SQLutils.conn_at();
//        = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      

        String sql = "SELECT * FROM operateur_detail opd, operateur op, point_collecte pt, pays pa WHERE opd.codeoper = op.CODEOPER AND pt.id_ptcollecte = opd.id_ptcollecte"
                + " AND pa.idPays = op.nationalite ORDER by NOMOPER";

        ResultSet rs;

        try (Statement statem = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            rs = statem.executeQuery(sql);            
            while (rs.next()) {
                user = new BigObjet(rs.getString("CODEOPER"), rs.getString("id_ptcollecte"), rs.getString("NOMOPER"));
                user.setCh4(rs.getString("meloper"));
                user.setCh5(rs.getString("FONCTION"));                          
                user.setCh8(rs.getString("lib_ptcollecte"));
                user.setCh9(rs.getString("nomPays"));
              
     
                list.add(user);
            }            
            conn_imis.close();
            
        } catch (SQLException | NullPointerException e) {
             System.out.println(e.getMessage());     
        }

        response.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(list);
        PrintWriter pw = response.getWriter();
        pw.write(array.toString());
//        System.out.println("JSONarray data " + list.size());        
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