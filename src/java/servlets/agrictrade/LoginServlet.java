package servlets.agrictrade;
 
import User.UserDAO;
import classes.SQLutils;
import entites.Operateur;
import entites.PointCollecte;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.persistence.NoResultException;
import org.json.JSONArray;

/**
 *
 * @author DedOuena
 */
public class LoginServlet extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        String login = request.getParameter("username");
        String pass = request.getParameter("password");
        
      
 
         Operateur op = null;
         
          File file;
          FileInputStream  fileInputStreamReader;
          byte[] bytes;
         
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         
         Connection conn_imis = SQLutils.conn_at();
         
         try {
            op = new UserDAO().SeConnecterViaMel(login, pass);
         } catch(NoResultException e){          
             response = null;
         }
        
        ArrayList<BigObjet> userdata = new ArrayList<>();     
        if (op != null) {
            if(op.getIdgroupe().getIdgroupe().equals("PRO")){
                BigObjet b = new BigObjet("invite","","");
                 userdata.add(b);
                  JSONArray array = new JSONArray(userdata);
                  PrintWriter pw = response.getWriter();
                  pw.write(array.toString());
            }else {
            BigObjet b = new BigObjet(login,pass,op.getCodeoper());
//            b.setCh3(op.getCodeoper());
            b.setCh4(op.getNomoper());
            b.setCh5(op.getFonction());
            b.setCh6(op.getNationalite().getIdPays());
            b.setCh7(op.getIdgroupe().getIdgroupe());
            b.setCh8(op.getNumero());
            b.setCh9(df.format(op.getDateNaissance()));
            
            if(op.getIdgroupe().getIdgroupe().equals("PFOC")){
                List<Operateur> op1 = new UserDAO().select_bypfoc(op.getCodeoper());
                String list ="";
                for(Operateur allColl : op1) {
                  list += allColl.getCodeoper()+ " ";
                }
                b.setCh10(list);               
            } else {
                b.setCh10(""); 
            }
            String listPcoll="";
            for(PointCollecte pcol : op.getPointCollecteList()){
                 listPcoll += pcol.getIdPtcollecte() + "::" + pcol.getIdPays().getIdPays() + "##";
            }
            
            b.setCh11(listPcoll);
            
              try {
                if(op.getPhoto().equalsIgnoreCase("-")) {
                b.setCh13("");
                }else {
                  file = new File("ProfilUserAndroid/"+op.getPhoto());
                  String encodedfile; // ="";
                  if(file.exists()){ 
                fileInputStreamReader = new FileInputStream(file);
                bytes = new byte[(int) file.length()];
                fileInputStreamReader.read(bytes);
                encodedfile = Base64.getEncoder().encodeToString(bytes);
                
                b.setCh13(encodedfile);
                
            }  else {
                b.setCh13("");
            }
                }
            }catch(NullPointerException e ) {
                b.setCh13("");                
            }   
              
               String sql2 = "SELECT * FROM `bassin` WHERE CODEOPER  ='" + op.getCodeoper() + "'";

            ResultSet rs;
            Statement statem;
            String bassin_pays ="";
                try {
                    statem = conn_imis.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);;
                    rs = statem.executeQuery(sql2);
                    
                    while(rs.next()) {
                        bassin_pays += rs.getString("idPays")+"::";
                    }
                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            
              b.setCh14(bassin_pays);
              
            
            userdata.add(b);
            JSONArray array = new JSONArray(userdata);
            PrintWriter pw = response.getWriter();
            pw.write(array.toString());
//            System.out.println("JSONarray data " + array.toString());
            }
        }      
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
//      LoginServlet
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    } // </editor-fold>
}
