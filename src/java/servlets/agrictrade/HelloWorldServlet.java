package servlets.agrictrade;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class HelloWorldServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
         ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        BigObjet c; 
        ArrayList<BigObjet> list = new ArrayList();
        try {
//        List<Collecte> dataColl = new CollecteDAO().selectAll();
          
           
           c  = new BigObjet("SUCCES","","");
          
           list.add(c);
           
          JSONArray array = new JSONArray(list);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            
        }catch(IOException e){ 
           c  = new BigObjet("ERREUR","","");
           list.add(c);
           JSONArray array = new JSONArray(list);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
            
        }
       
    }
    
    @Override
     public  void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {	
		doGet(request, response) ;
	}
    
}
