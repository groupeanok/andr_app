/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import AnimalType.AnimalTypeDAO;
import entites.Animaltypes;
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
public class allAnimalType extends HttpServlet {

     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");   
        List<Animaltypes> userdata1 = new AnimalTypeDAO().selectAll();
        ArrayList<BigObjet> list = new ArrayList();

        if (userdata1.isEmpty()) {
            list.add(null);
        } else {
            for (Animaltypes userdata : userdata1) {
                BigObjet b = new BigObjet(userdata.getAnimalTypeID(),userdata.getNameEN(),userdata.getNameFR());
                b.setCh4(userdata.getNamePT());
                list.add(b);
            }
        }
        response.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray(list);
        PrintWriter pw = response.getWriter();
        pw.write(array.toString());
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
