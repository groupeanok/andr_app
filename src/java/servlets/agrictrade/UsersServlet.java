package servlets.agrictrade;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import User.UserDAO;
import OperateurDetail.OperateurDetailDAO;
import entites.Operateur;
import entites.OperateurDetail;
import entites.PointCollecte;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.json.JSONArray;

/**
 *
 * @author KONOMBO
 */
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");

        UserDAO pDao = new UserDAO();
//        AssoDAO aDao = new AssoDAO();
        OperateurDetailDAO opdao = new OperateurDetailDAO();
//        OperateurDetail opd;//= null;
        List<OperateurDetail> opd;
        List<Operateur> list_oper = null;
        try {
            list_oper = pDao.select_bygroupe("COLL");
        } catch (NoResultException e) {
//             System.out.println(e.getMessage());
            // response = null;
        }
        ArrayList<BigObjet> list = new ArrayList<>();
        if (list_oper != null) {
            BigObjet user;
            for (Operateur op : list_oper) {
                user = new BigObjet(op.getNomoper(), op.getNumero(), op.getNationalite().getNomPays());
                user.setCh4(op.getMeloper());
                user.setCh5(op.getFonction());
                user.setCh6(op.getDateNaissance().toString());
                user.setCh7(op.getCodeoper());
                user.setCh9(" ");
                user.setCh10(op.getNationalite().getIdPays());
                opd = opdao.selectcpof_user(op.getCodeoper());
                // Attention cela peut donner plusieurs points de collectes
                // pour l'instant on ne choisit que le premier
                if (opd != null) {
                    if (!opd.isEmpty()) {
                        user.setCh8(opd.get(0).getIdPtcollecte().getLibPtcollecte());
                    }
                } else {
                    user.setCh8(" ");
                }
                
                
                
                
                Operateur opp;

                String ptCollecte = "";

                for (PointCollecte mk : op.getPointCollecteList()) {
                    ptCollecte += mk.getLibPtcollecte() + ", ";
                }
                user.setCh11(ptCollecte);

                try {
                    if (!op.getIdPfoc().isEmpty()) {
                        opp = pDao.selectFind(op.getIdPfoc());
                        if (opp != null) {
                            user.setCh9(opp.getNomoper());
                        }
                    } else {
                        user.setCh9(" ");
                    }
                } catch (NullPointerException e) {
                    user.setCh9(" ");
                }
                list.add(user);
            }

        } else {
            list.add(new BigObjet());
        }
        response.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(list);
        PrintWriter pw = response.getWriter();
        pw.write(array.toString());
        //       System.out.println("JSONarray data " + array.toString());        
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
