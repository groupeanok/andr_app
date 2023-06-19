/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.agrictrade;

import TypeAnimal.TypeAnimalDAO;
import entites.TypeAnimal;
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
 * @author Client
 */
public class allAnimaloffline extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");   
        List<TypeAnimal> userdata1 = new TypeAnimalDAO().selectAll();
        ArrayList<BigObjet> list = new ArrayList();

        if (userdata1.isEmpty()) {
            list.add(null);
        } else {
            for (TypeAnimal userdata : userdata1) {             
                BigObjet b = new BigObjet(userdata.getIdTypean(),userdata.getLibtypeanEN(),userdata.getAnimalTypeID().getAnimalTypeID());
                b.setCh4(userdata.getLibtypeanFR());
                b.setCh5(userdata.getLibtypeanPT());
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
