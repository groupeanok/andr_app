/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "parametres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametres.findAll", query = "SELECT p FROM Parametres p")
    ,
    @NamedQuery(name = "Parametres.findByCodeParam", query = "SELECT p FROM Parametres p WHERE p.codeParam = :codeParam")
    ,
    @NamedQuery(name = "Parametres.findByDebPremJob", query = "SELECT p FROM Parametres p WHERE p.debPremJob = :debPremJob")
    ,
    @NamedQuery(name = "Parametres.findBySite", query = "SELECT p FROM Parametres p WHERE p.site = :site")
    ,
    @NamedQuery(name = "Parametres.findByLangue", query = "SELECT p FROM Parametres p WHERE p.langue = :langue")
    ,
    @NamedQuery(name = "Parametres.findByIdApp", query = "SELECT p FROM Parametres p WHERE p.idApp = :idApp")
    ,
    @NamedQuery(name = "Parametres.findByNomApp", query = "SELECT p FROM Parametres p WHERE p.nomApp = :nomApp")
    ,
    @NamedQuery(name = "Parametres.findByNbreExoSim", query = "SELECT p FROM Parametres p WHERE p.nbreExoSim = :nbreExoSim")
    ,
    @NamedQuery(name = "Parametres.findByDbDrivers", query = "SELECT p FROM Parametres p WHERE p.dbDrivers = :dbDrivers")
    ,
    @NamedQuery(name = "Parametres.findByDbUrlPreacilss", query = "SELECT p FROM Parametres p WHERE p.dbUrlPreacilss = :dbUrlPreacilss")
    ,
    @NamedQuery(name = "Parametres.findByDbUrlImis", query = "SELECT p FROM Parametres p WHERE p.dbUrlImis = :dbUrlImis")
    ,
    @NamedQuery(name = "Parametres.findByDbUrlImmo", query = "SELECT p FROM Parametres p WHERE p.dbUrlImmo = :dbUrlImmo")
    ,
    @NamedQuery(name = "Parametres.findByIpServImis", query = "SELECT p FROM Parametres p WHERE p.ipServImis = :ipServImis")
    ,
    @NamedQuery(name = "Parametres.findByIpServImmo", query = "SELECT p FROM Parametres p WHERE p.ipServImmo = :ipServImmo")
    ,
    @NamedQuery(name = "Parametres.findByUsername", query = "SELECT p FROM Parametres p WHERE p.username = :username")
    ,
    @NamedQuery(name = "Parametres.findByPassword", query = "SELECT p FROM Parametres p WHERE p.password = :password")
    ,
    @NamedQuery(name = "Parametres.findBySignPrinc", query = "SELECT p FROM Parametres p WHERE p.signPrinc = :signPrinc")
    ,
    @NamedQuery(name = "Parametres.findBySignPrincTitre", query = "SELECT p FROM Parametres p WHERE p.signPrincTitre = :signPrincTitre")
    ,
    @NamedQuery(name = "Parametres.findBySignOtherResp", query = "SELECT p FROM Parametres p WHERE p.signOtherResp = :signOtherResp")
    ,
    @NamedQuery(name = "Parametres.findBySignOtherRespTitre", query = "SELECT p FROM Parametres p WHERE p.signOtherRespTitre = :signOtherRespTitre")
    ,
    @NamedQuery(name = "Parametres.findByResptechNom", query = "SELECT p FROM Parametres p WHERE p.resptechNom = :resptechNom")
    ,
    @NamedQuery(name = "Parametres.findByResptechEmail", query = "SELECT p FROM Parametres p WHERE p.resptechEmail = :resptechEmail")
    ,
    @NamedQuery(name = "Parametres.findByChemEtat", query = "SELECT p FROM Parametres p WHERE p.chemEtat = :chemEtat")
    ,
    @NamedQuery(name = "Parametres.findByChemAide", query = "SELECT p FROM Parametres p WHERE p.chemAide = :chemAide")
    ,
    @NamedQuery(name = "Parametres.findByChemImage", query = "SELECT p FROM Parametres p WHERE p.chemImage = :chemImage")
    ,
    @NamedQuery(name = "Parametres.findByChemMysql", query = "SELECT p FROM Parametres p WHERE p.chemMysql = :chemMysql")
    ,
    @NamedQuery(name = "Parametres.findByChemExp", query = "SELECT p FROM Parametres p WHERE p.chemExp = :chemExp")
    ,
    @NamedQuery(name = "Parametres.findByImgFond", query = "SELECT p FROM Parametres p WHERE p.imgFond = :imgFond")
    ,
    @NamedQuery(name = "Parametres.findByImgConn", query = "SELECT p FROM Parametres p WHERE p.imgConn = :imgConn")
    ,
    @NamedQuery(name = "Parametres.findByImgLogo", query = "SELECT p FROM Parametres p WHERE p.imgLogo = :imgLogo")
    ,
    @NamedQuery(name = "Parametres.findBySmtpServer", query = "SELECT p FROM Parametres p WHERE p.smtpServer = :smtpServer")
    ,
    @NamedQuery(name = "Parametres.findByEmailExp", query = "SELECT p FROM Parametres p WHERE p.emailExp = :emailExp")
    ,
    @NamedQuery(name = "Parametres.findByUserExp", query = "SELECT p FROM Parametres p WHERE p.userExp = :userExp")
    ,
    @NamedQuery(name = "Parametres.findByMdpExp", query = "SELECT p FROM Parametres p WHERE p.mdpExp = :mdpExp")
    ,
//    @NamedQuery(name = "Parametres.findByActif", query = "SELECT p FROM Parametres p WHERE p.actif = :actif"),
    @NamedQuery(name = "Parametres.findByActif", query = "SELECT p FROM Parametres p WHERE p.actif = true")
    ,
    @NamedQuery(name = "Parametres.findByCodebarre", query = "SELECT p FROM Parametres p WHERE p.codebarre = :codebarre")})
public class Parametres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "code_param")
    private String codeParam;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deb_prem_job")
    @Temporal(TemporalType.DATE)
    private Date debPremJob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "site")
    private String site;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "langue")
    private String langue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "idApp")
    private String idApp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nomApp")
    private String nomApp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nbre_exo_sim")
    private int nbreExoSim;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "dbDrivers")
    private String dbDrivers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "db_url_preacilss")
    private String dbUrlPreacilss;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "db_url_imis")
    private String dbUrlImis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "db_url_immo")
    private String dbUrlImmo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ip_serv_imis")
    private String ipServImis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ip_serv_immo")
    private String ipServImmo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sign_princ")
    private String signPrinc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sign_princ_titre")
    private String signPrincTitre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sign_other_resp")
    private String signOtherResp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sign_other_resp_titre")
    private String signOtherRespTitre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "resptech_nom")
    private String resptechNom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "resptech_email")
    private String resptechEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "chem_etat")
    private String chemEtat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "chem_aide")
    private String chemAide;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "chem_image")
    private String chemImage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "chem_mysql")
    private String chemMysql;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "chem_exp")
    private String chemExp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "img_fond")
    private String imgFond;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "img_conn")
    private String imgConn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "img_logo")
    private String imgLogo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "smtp_server")
    private String smtpServer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email_exp")
    private String emailExp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "user_exp")
    private String userExp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "mdp_exp")
    private String mdpExp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actif")
    private boolean actif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "codebarre")
    private String codebarre;

    public Parametres() {
    }

    public Parametres(String codeParam) {
        this.codeParam = codeParam;
    }

    public Parametres(String codeParam, Date debPremJob, String site, String langue, String idApp, String nomApp, int nbreExoSim, String dbDrivers, String dbUrlPreacilss, String dbUrlImis, String dbUrlImmo, String ipServImis, String ipServImmo, String username, String password, String signPrinc, String signPrincTitre, String signOtherResp, String signOtherRespTitre, String resptechNom, String resptechEmail, String chemEtat, String chemAide, String chemImage, String chemMysql, String chemExp, String imgFond, String imgConn, String imgLogo, String smtpServer, String emailExp, String userExp, String mdpExp, boolean actif, String codebarre) {
        this.codeParam = codeParam;
        this.debPremJob = debPremJob;
        this.site = site;
        this.langue = langue;
        this.idApp = idApp;
        this.nomApp = nomApp;
        this.nbreExoSim = nbreExoSim;
        this.dbDrivers = dbDrivers;
        this.dbUrlPreacilss = dbUrlPreacilss;
        this.dbUrlImis = dbUrlImis;
        this.dbUrlImmo = dbUrlImmo;
        this.ipServImis = ipServImis;
        this.ipServImmo = ipServImmo;
        this.username = username;
        this.password = password;
        this.signPrinc = signPrinc;
        this.signPrincTitre = signPrincTitre;
        this.signOtherResp = signOtherResp;
        this.signOtherRespTitre = signOtherRespTitre;
        this.resptechNom = resptechNom;
        this.resptechEmail = resptechEmail;
        this.chemEtat = chemEtat;
        this.chemAide = chemAide;
        this.chemImage = chemImage;
        this.chemMysql = chemMysql;
        this.chemExp = chemExp;
        this.imgFond = imgFond;
        this.imgConn = imgConn;
        this.imgLogo = imgLogo;
        this.smtpServer = smtpServer;
        this.emailExp = emailExp;
        this.userExp = userExp;
        this.mdpExp = mdpExp;
        this.actif = actif;
        this.codebarre = codebarre;
    }

    public String getCodeParam() {
        return codeParam;
    }

    public void setCodeParam(String codeParam) {
        this.codeParam = codeParam;
    }

    public Date getDebPremJob() {
        return debPremJob;
    }

    public void setDebPremJob(Date debPremJob) {
        this.debPremJob = debPremJob;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getIdApp() {
        return idApp;
    }

    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }

    public String getNomApp() {
        return nomApp;
    }

    public void setNomApp(String nomApp) {
        this.nomApp = nomApp;
    }

    public int getNbreExoSim() {
        return nbreExoSim;
    }

    public void setNbreExoSim(int nbreExoSim) {
        this.nbreExoSim = nbreExoSim;
    }

    public String getDbDrivers() {
        return dbDrivers;
    }

    public void setDbDrivers(String dbDrivers) {
        this.dbDrivers = dbDrivers;
    }

    public String getDbUrlPreacilss() {
        return dbUrlPreacilss;
    }

    public void setDbUrlPreacilss(String dbUrlPreacilss) {
        this.dbUrlPreacilss = dbUrlPreacilss;
    }

    public String getDbUrlImis() {
        return dbUrlImis;
    }

    public void setDbUrlImis(String dbUrlImis) {
        this.dbUrlImis = dbUrlImis;
    }

    public String getDbUrlImmo() {
        return dbUrlImmo;
    }

    public void setDbUrlImmo(String dbUrlImmo) {
        this.dbUrlImmo = dbUrlImmo;
    }

    public String getIpServImis() {
        return ipServImis;
    }

    public void setIpServImis(String ipServImis) {
        this.ipServImis = ipServImis;
    }

    public String getIpServImmo() {
        return ipServImmo;
    }

    public void setIpServImmo(String ipServImmo) {
        this.ipServImmo = ipServImmo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignPrinc() {
        return signPrinc;
    }

    public void setSignPrinc(String signPrinc) {
        this.signPrinc = signPrinc;
    }

    public String getSignPrincTitre() {
        return signPrincTitre;
    }

    public void setSignPrincTitre(String signPrincTitre) {
        this.signPrincTitre = signPrincTitre;
    }

    public String getSignOtherResp() {
        return signOtherResp;
    }

    public void setSignOtherResp(String signOtherResp) {
        this.signOtherResp = signOtherResp;
    }

    public String getSignOtherRespTitre() {
        return signOtherRespTitre;
    }

    public void setSignOtherRespTitre(String signOtherRespTitre) {
        this.signOtherRespTitre = signOtherRespTitre;
    }

    public String getResptechNom() {
        return resptechNom;
    }

    public void setResptechNom(String resptechNom) {
        this.resptechNom = resptechNom;
    }

    public String getResptechEmail() {
        return resptechEmail;
    }

    public void setResptechEmail(String resptechEmail) {
        this.resptechEmail = resptechEmail;
    }

    public String getChemEtat() {
        return chemEtat;
    }

    public void setChemEtat(String chemEtat) {
        this.chemEtat = chemEtat;
    }

    public String getChemAide() {
        return chemAide;
    }

    public void setChemAide(String chemAide) {
        this.chemAide = chemAide;
    }

    public String getChemImage() {
        return chemImage;
    }

    public void setChemImage(String chemImage) {
        this.chemImage = chemImage;
    }

    public String getChemMysql() {
        return chemMysql;
    }

    public void setChemMysql(String chemMysql) {
        this.chemMysql = chemMysql;
    }

    public String getChemExp() {
        return chemExp;
    }

    public void setChemExp(String chemExp) {
        this.chemExp = chemExp;
    }

    public String getImgFond() {
        return imgFond;
    }

    public void setImgFond(String imgFond) {
        this.imgFond = imgFond;
    }

    public String getImgConn() {
        return imgConn;
    }

    public void setImgConn(String imgConn) {
        this.imgConn = imgConn;
    }

    public String getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(String imgLogo) {
        this.imgLogo = imgLogo;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getEmailExp() {
        return emailExp;
    }

    public void setEmailExp(String emailExp) {
        this.emailExp = emailExp;
    }

    public String getUserExp() {
        return userExp;
    }

    public void setUserExp(String userExp) {
        this.userExp = userExp;
    }

    public String getMdpExp() {
        return mdpExp;
    }

    public void setMdpExp(String mdpExp) {
        this.mdpExp = mdpExp;
    }

    public boolean getActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getCodebarre() {
        return codebarre;
    }

    public void setCodebarre(String codebarre) {
        this.codebarre = codebarre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeParam != null ? codeParam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametres)) {
            return false;
        }
        Parametres other = (Parametres) object;
        if ((this.codeParam == null && other.codeParam != null) || (this.codeParam != null && !this.codeParam.equals(other.codeParam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Parametres[ codeParam=" + codeParam + " ]";
    }

}
