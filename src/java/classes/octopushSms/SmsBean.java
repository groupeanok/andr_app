/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.octopushSms;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Somda
 */
@ViewScoped
//@SessionScoped
@Named("SmsBean")
public class SmsBean implements Serializable {

//    private List<Anok_obj_1> data_pays;
//    private List<Anok_obj_1> data_pays_ci;
//    private List<Anok_obj_ppp> realisation;
    private String solde; 
    private String solde_std; 
    private String solde_pro; 
    public SmsBean() {
    }

    public String getSolde() {
//        data_pays = new ArrayList<>();
        SmsObject sms = new SmsObject();
        return sms.getBalance()+"\n";
//        return data_pays;
    }

    /**
     * @param solde the solde to set
     */
    public void setSolde(String solde) {
        this.solde = solde;
    }

    /**
     * @return the solde_std
     */
    public String getSolde_std() {
         SmsObject sms = new SmsObject();
        return sms.getBalanceSTD();
//        return solde_std;
    }

    /**
     * @param solde_std the solde_std to set
     */
    public void setSolde_std(String solde_std) {
        this.solde_std = solde_std;
    }

    /**
     * @return the solde_pro
     */
    public String getSolde_pro() {
//        return solde_pro;
         SmsObject sms = new SmsObject();
        return sms.getBalancePRO();
    }

    /**
     * @param solde_pro the solde_pro to set
     */
    public void setSolde_pro(String solde_pro) {
        this.solde_pro = solde_pro;
    }

}
