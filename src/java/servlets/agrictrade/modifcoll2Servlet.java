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
import java.sql.Timestamp;
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
public class modifcoll2Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        String idColl = request.getParameter("idcoll");
        String codeoper = request.getParameter("codeoper");
        String numberpack = request.getParameter("numberpack");
        String weightpack = request.getParameter("weightpack");
        String unitpack = request.getParameter("unitpack");
        String other = request.getParameter("other");
        String decnumber = request.getParameter("decnumber");
        String cost = request.getParameter("cost");

//        Collecte op = null;
//
//        CollecteDAO opdao = new CollecteDAO();
//        op = opdao.selectFind(idColl);

        ArrayList<BigObjet> userdata = new ArrayList<>();


        Connection conn_imis = SQLutils.conn_at();
        Date date = new Date();

        String sql = "UPDATE `collecte` SET `nbre_sacpan` = '" + numberpack + "', `pdbysac` = '" + weightpack + "', `pdbyunite` = '" + unitpack + "'"
                + ", `otherfes` = '" + other + "', `prixtranspunit` = '" + cost + "',`dec_number` = '" + decnumber + "'"
                + ",`OPMAJ` = '" + codeoper + "',`valide` = '1',`DATEMAJ` = '" + new Timestamp(date.getTime()) + "' "
                + "WHERE `collecte`.`id_collecte` = '" + idColl + "' ";

//        if (op != null) {
//            op.setNbreSacpan(Integer.parseInt(numberpack));
//            op.setPdbysac(Integer.parseInt(weightpack));
//            op.setPdbyunite(Integer.parseInt(unitpack));
//            op.setOtherfes(Integer.parseInt(other));
//            op.setPrixtranspunit(Integer.parseInt(cost));
//            op.setDecNumber(decnumber);
//            op.setOpmaj(new UserDAO().selectFind(codeoper));
//            op.setDatemaj(new Date());
//            op.setValide(true);
        try {
//                   new CollecteDAO().update(op);

//            Statement statem = conn_imis.createStatement();
            Statement statem  = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statem.executeUpdate(sql);
            BigObjet b = new BigObjet("SUCCESS", "", "");
            userdata.add(b);
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());

        } catch (SQLException e) {
            BigObjet b = new BigObjet("erreur", "", "");
            userdata.add(b);
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());

            System.out.println(e.getMessage());
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
