package servlets.agrictrade;


import classes.SQLutils;
import entites.PointCollecte;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class assignServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
//        String login = request.getParameter("mailoper");
        String codeoper = request.getParameter("codeoper");
        String idfocal = request.getParameter("pointfocal");
        String ptcoll = request.getParameter("pointcoll");
        String opermaj = request.getParameter("opermaj");

//        Operateur op = null;
//        Operateur op1 = null;
//        PointCollecte idPtColl = null;

//        OperateurDetail opdetail = new OperateurDetail();
//        OperateurDetailDAO opdetaildao = new OperateurDetailDAO();
//        if (!ptcoll.equalsIgnoreCase(" ")) {
//            idPtColl = new Point_CollecteDAO().selectFind(ptcoll.trim());
//        }
//        try {
//            op = new UserDAO().selectFind(codeoper);
//            op1 = new UserDAO().selectFind(opermaj);
//        } catch (NoResultException e) {
//            response = null;
//        }
        ArrayList<BigObjet> userdata = new ArrayList<>();

        Connection conn_imis = SQLutils.conn_at();

//        if (op != null) {
        try {
            if (!idfocal.equalsIgnoreCase(" ")) {

                String sql = "UPDATE `operateur` SET `OPMAJ` = '" + opermaj + "', `id_pfoc` = '" + idfocal + "' WHERE `operateur`.`CODEOPER` = '" + codeoper + "'";

//                    op.setIdPfoc(idfocal);
//                    op.setOpmaj(op1);
//                    
//                    new UserDAO().update(op);
                Statement statem = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                statem.executeUpdate(sql);

                BigObjet b = new BigObjet("SUCCESS", "", "");
                userdata.add(b);

                JSONArray array = new JSONArray(userdata);
                PrintWriter pw = response.getWriter();
                pw.write(array.toString());

                System.out.println(array.toString());
                conn_imis.close();
            }
        } catch (IOException | SQLException e) {
            BigObjet b = new BigObjet("erreur", "", "");
            userdata.add(b);
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());

            System.out.println(array.toString());
        }

        try {
            if (!ptcoll.equalsIgnoreCase(" ")) {

//                    List<OperateurDetail> list = opdetaildao.selectAll();
                String sql1 = "SELECT * FROM `operateur_detail` WHERE codeoper ='" + codeoper + "' AND id_ptcollecte ='" + ptcoll + "'";

                boolean exist = false;

                ResultSet rs1;

                Statement statem1 = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs1 = statem1.executeQuery(sql1);

                int nbre_rel1 = 0;

                if (rs1 != null) {
                    rs1.last();
                    nbre_rel1 = rs1.getRow();
                }

                if (nbre_rel1 > 0) {
                    exist = true;
                }

//                    for (OperateurDetail lis : list) {
//                        if (lis.getCodeoper().equals(codeoper)) {
//                            if (lis.getIdPtcollecte().getIdPtcollecte().equalsIgnoreCase(idPtColl.getIdPtcollecte())) {
//                                exist = true;
//                                break;
//                            }
//                        }
//                    }
                if (exist) {

                    BigObjet b = new BigObjet("erreur1", "", "");
                    userdata.add(b);
                    JSONArray array = new JSONArray(userdata);
                    PrintWriter pw = response.getWriter();
                    pw.write(array.toString());

//                    System.out.println(array.toString());

                    conn_imis.close();

                } else {

//                        opdetail.setCodeoper(codeoper);
//                        opdetail.setIdPtcollecte(idPtColl);
//                        new OperateurDetailDAO().insert(opdetail);
                    String query = "insert into operateur_detail (codeoper, id_ptcollecte) values (?, ?)";

                    PreparedStatement preparedStmt = conn_imis.prepareStatement(query);
                    preparedStmt.setString(1, codeoper);
                    preparedStmt.setString(2, ptcoll);

                    preparedStmt.execute();

                    BigObjet b = new BigObjet("SUCCESS", "", "");
                    userdata.add(b);
                    JSONArray array = new JSONArray(userdata);
                    PrintWriter pw = response.getWriter();
                    pw.write(array.toString());

                    System.out.println(array.toString());

                    conn_imis.close();
                }
            }
        } catch (IOException | SQLException e) {
            BigObjet b = new BigObjet("erreur", "", "");
            userdata.add(b);
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());

            System.out.println(array.toString());

        }
//            System.out.println("JSONarray data " + array.toString());
//        }

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
