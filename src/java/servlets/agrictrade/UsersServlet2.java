package servlets.agrictrade;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import User.UserDAO;
import entites.Operateur;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.json.JSONArray;

/**
 *
 * @author DedOuena
 */
public class UsersServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        UserDAO pDao = new UserDAO();
//        List<Operateur> list_oper = null;
        List<Operateur> list_oper = new ArrayList<>();
        try {
            list_oper = pDao.select_bygroupe("PFOC");
        } catch (NoResultException e) {
//             System.out.println(e.getMessage());
            // response = null;
        }
        ArrayList<BigObjet> list = new ArrayList<>();
        if (list_oper.isEmpty()) {
            list.add(new BigObjet());
        } else {
            BigObjet user;
            for (Operateur op : list_oper) {
                user = new BigObjet(op.getNomoper(), op.getNumero(), op.getNationalite().getNomPays());
                user.setCh4(op.getMeloper());
                user.setCh5(op.getFonction());
                user.setCh6(op.getDateNaissance().toString());
                List<Operateur> op1 = new UserDAO().select_bypfoc(op.getCodeoper());
                String collecteur = "";
                for (Operateur allColl : op1) {
                    collecteur += allColl.getNomoper() + ", ";
                }
                user.setCh11(collecteur);
                list.add(user);
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
    }

}
