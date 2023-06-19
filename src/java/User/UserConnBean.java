package User;


import entites.Operateur;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class UserConnBean implements Serializable {

    
    private String lbl_nom_op ="Username";
    private String lbl_pwd_op ="Password";
    private String btn_connecte ="Login";       
    private String langue;
    private Operateur user;
    private boolean loggedIn;
    String loginURL_fr = "index_fr.xhtml";
    String loginURL_pt = "index_pt.xhtml";
    String originalURL= "index.xhtml";
    private final UserDAO pDao = new UserDAO();

 /*   @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/index.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);
            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }*/


    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        try {
            System.out.println(langue);
            System.out.println(user.getMeloper() + " - "+  user.getPassword());
//            Operateur op = pDao.SeConnecterViaMel(user.getCodeoper(), user.getPassword());
            Operateur op = pDao.SeConnecterViaMel(user.getMeloper(), user.getPassword());
            if (op == null) {
                throw new Exception("Invalid user/password");
            }
            Object put = externalContext.getSessionMap().put("user", op);
            
            switch (langue) {
                case "FR":
                    externalContext.redirect(loginURL_fr);
                    break;
                case "PT":
                    externalContext.redirect(loginURL_pt);
                    break;
                default:
                    externalContext.redirect(originalURL);
//                    break;
            }
//            createTimer(externalContext, op).start();
        } catch (Exception e) {
//            context.addMessage(null, new FacesMessage(e.getMessage()));
            System.out.println(e.getMessage());
        }
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public UserConnBean() {
        user = new Operateur();
    }
//
//    public Operateur oper_conn() {
//        ExternalContext tmpEC;
//        Map sMap;
//        tmpEC = FacesContext.getCurrentInstance().getExternalContext();
//        sMap = tmpEC.getSessionMap();
//        return (Operateur) sMap.get("operateur");
//    }

    public Operateur getUser() {
        return user;
    }

    public void setUser(Operateur serv) {
        this.user = serv;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void checkLogIn() {
        if (user == null || !this.isLoggedIn()) {
            try {
//                System.out.println("Session bien en cours");
//                FacesContext.getCurrentInstance().getExternalContext().redirect(loginURL);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
//                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * @return the langue
     */
    public String getLangue() {
        return langue;
    }

    /**
     * @param langue the langue to set
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    public static String getJasperFilePath(ServletContext context, String compileDir, String jasperFile) {
        return context.getRealPath(compileDir + jasperFile);
    }

    public String getLbl_nom_op() {
        return lbl_nom_op;
    }

    /**
     * @param lbl_nom_op the lbl_nom_op to set
     */
    public void setLbl_nom_op(String lbl_nom_op) {
        this.lbl_nom_op = lbl_nom_op;
    }

    /**
     * @return the lbl_pwd_op
     */
    public String getLbl_pwd_op() {
        return lbl_pwd_op;
    }

    /**
     * @param lbl_pwd_op the lbl_pwd_op to set
     */
    public void setLbl_pwd_op(String lbl_pwd_op) {
        this.lbl_pwd_op = lbl_pwd_op;
    }

    /**
     * @return the btn_connecte
     */
    public String getBtn_connecte() {
        return btn_connecte;
    }

    /**
     * @param btn_connecte the btn_connecte to set
     */
    public void setBtn_connecte(String btn_connecte) {
        this.btn_connecte = btn_connecte;
    }

}
