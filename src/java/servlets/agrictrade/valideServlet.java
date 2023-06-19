/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;


import Offres.OffresDAO;
import ValOffre.ValOffreDAO;
import classes.SQLutils;
import entites.Offre;
import entites.ValOffre;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
public class valideServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        String idColl = request.getParameter("idcoll");
        String codeoper = request.getParameter("codeoper");
        String type = request.getParameter("type");
        String commentaire = request.getParameter("commentaire");

        Connection conn_imis = SQLutils.conn_at();

//        Collecte op;
        Offre of;

//        ValCollecte valcoll = new ValCollecte();

        ValOffre valoffre = new ValOffre();

        if (type.equalsIgnoreCase("COLLECTE")) {

//            CollecteDAO opdao = new CollecteDAO();
//            op = opdao.selectFind(idColl);

            ArrayList<BigObjet> userdata = new ArrayList<>();
//        if (op != null) {

//            valcoll.setIdCollecte(idColl);
//            valcoll.setDateVali(new Date());
//            valcoll.setIdPtfocal(codeoper);
//            valcoll.setObseVali(commentaire);
//            valcoll.setTypeOp(type.toUpperCase());

            String sql = "INSERT INTO `val_collecte` (`id_ptfocal`, `id_collecte`, `date_vali`, `obse_vali`, `type_op`) VALUES (?, ?, ?, ?, ?)";

            try {
//                   new ValCollecteDAO().insert(valcoll);

                SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                PreparedStatement preparedStmt = conn_imis.prepareStatement(sql);

                preparedStmt.setString(1, codeoper);
                preparedStmt.setString(2, idColl);
                preparedStmt.setString(3, df1.format(new Date()));
                preparedStmt.setString(4, commentaire);
                preparedStmt.setString(5, type.toUpperCase());

                // execute the preparedstatement
                preparedStmt.execute();

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
//            System.out.println("JSONarray data " + array.toString());
//          }
        } else if (type.equalsIgnoreCase("RejetB")) {
            
//            CollecteDAO opdao = new CollecteDAO();
//            op = opdao.selectFind(idColl);

            ArrayList<BigObjet> userdata = new ArrayList<>();
            
            String sql = "UPDATE `collecte` SET `valide` = '0' WHERE `collecte`.`id_collecte` = '" + idColl + "' ";
            
            
//            if (op != null) {
//                op.setValide(false);

                try {
//                    opdao.update(op);
                      Statement statem = conn_imis.createStatement();

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
//            System.out.println("JSONarray data " + array.toString());
//            }
        } else {
            OffresDAO opdao = new OffresDAO();
            of = opdao.selectFind(idColl);
            ArrayList<BigObjet> userdata = new ArrayList<>();
            if (of != null) {
                valoffre.setIdOffre(idColl);
                valoffre.setDateVali(new Date());
                valoffre.setIdPtfocal(codeoper);
                valoffre.setObseVali(commentaire);
                valoffre.setTypeOp(type.toUpperCase());

                try {
                    new ValOffreDAO().insert(valoffre);

                    BigObjet b = new BigObjet("SUCCESS", "", "");
                    userdata.add(b);
                    JSONArray array = new JSONArray(userdata);
                    PrintWriter pw = response.getWriter();
                    pw.write(array.toString());
                } catch (IOException e) {
//                    response = null;
                }
//            System.out.println("JSONarray data " + array.toString());
            }
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
