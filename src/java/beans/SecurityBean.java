/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

//import User.UserBean;
import User.UserConnBean;
//import entites.Operateur;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Somda
 */
@ManagedBean(name = "securityMB")
@RequestScoped
public class SecurityBean {
  @ManagedProperty(value = "#{userManager}")
  private UserConnBean userManager;
  String loginURL = "faces/Authentification.xhtml";
  public void checkLogIn() {
  if (getUserManager() == null || !userManager.isLoggedIn()) {
      try {
          FacesContext.getCurrentInstance().getExternalContext().redirect(loginURL);
      } catch (IOException ex) {
          System.out.println(ex.getMessage());
//          Logger.getLogger(SecurityBean.class.getName()).log(Level.SEVERE, null, ex);
      }
  }}

    /**
     * @return the userManager
     */
    public UserConnBean getUserManager() {
        return userManager;
    }

    /**
     * @param userManager the userManager to set
     */
    public void setUserManager(UserConnBean userManager) {
        this.userManager = userManager;
    }
}