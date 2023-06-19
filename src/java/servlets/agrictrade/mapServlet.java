package servlets.agrictrade;

import Point_Collecte.Point_CollecteDAO;
import User.UserDAO;
import entites.Operateur;
import entites.OperateurDetail;
import entites.PointCollecte;
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
public class mapServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");

//         Point_CollecteDAO operDao = new Point_CollecteDAO();
        List<PointCollecte> pt_list = new Point_CollecteDAO().selectAll();
        UserDAO pDao = new UserDAO();
        ArrayList<BigObjet> list = new ArrayList();
        if (pt_list.isEmpty()) {
            list.add(null);
        } else {

            List<OperateurDetail> op_det;
            BigObjet elemMap;

            Operateur operr;

            for (PointCollecte pt : pt_list) {
                elemMap = new BigObjet(pt.getLibPtcollecte(), "", "");
                elemMap.setVal1(pt.getCoordx());
                elemMap.setVal2(pt.getCoordy());
                op_det = pt.getOperateurDetailList();
                if (op_det.isEmpty()) {
                    continue;
                } else {
                    try {
                        operr = pDao.selectFind(op_det.get(0).getCodeoper());
                        elemMap.setCh2(operr.getNomoper());
                        elemMap.setCh3(operr.getNumero());
                        elemMap.setCh4(operr.getMeloper());
                    } catch (NullPointerException e) {
                        elemMap.setCh2("Inconnu");
                        elemMap.setCh3("aucun");
                        elemMap.setCh4("aucun");
                    }
                }
                if (elemMap.getVal1() != 0 || elemMap.getVal2() != 0) {
                    list.add(elemMap);
                }
            }
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
    } // </editor-fold>
}
