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
 * @author Groupe Anok
 */
@Entity
@Table(name = "session")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Session.findAll", query = "SELECT s FROM Session s")
    , @NamedQuery(name = "Session.findByIdSession", query = "SELECT s FROM Session s WHERE s.idSession = :idSession")
    , @NamedQuery(name = "Session.findByDebSession", query = "SELECT s FROM Session s WHERE s.debSession = :debSession")
    , @NamedQuery(name = "Session.findByFinSession", query = "SELECT s FROM Session s WHERE s.finSession = :finSession")
    , @NamedQuery(name = "Session.findByIdMachine", query = "SELECT s FROM Session s WHERE s.idMachine = :idMachine")
    , @NamedQuery(name = "Session.findByAdresseIp", query = "SELECT s FROM Session s WHERE s.adresseIp = :adresseIp")
    , @NamedQuery(name = "Session.findBySystExploitation", query = "SELECT s FROM Session s WHERE s.systExploitation = :systExploitation")
    , @NamedQuery(name = "Session.findByCodeoper", query = "SELECT s FROM Session s WHERE s.codeoper = :codeoper")
    , @NamedQuery(name = "Session.findByActive", query = "SELECT s FROM Session s WHERE s.active = :active")})

public class Session implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_session")
    private String idSession;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deb_session")
    @Temporal(TemporalType.TIMESTAMP)
    private Date debSession;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fin_session")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finSession;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_machine")
    private String idMachine;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "adresse_ip")
    private String adresseIp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "syst_exploitation")
    private String systExploitation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codeoper")
    private String codeoper;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;

    public Session() {
    }

    public Session(String idSession) {
        this.idSession = idSession;
    }

    public Session(String idSession, Date debSession, Date finSession, String idMachine, String adresseIp, String systExploitation, String codeoper, boolean active) {
        this.idSession = idSession;
        this.debSession = debSession;
        this.finSession = finSession;
        this.idMachine = idMachine;
        this.adresseIp = adresseIp;
        this.systExploitation = systExploitation;
        this.codeoper = codeoper;
        this.active = active;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public Date getDebSession() {
        return debSession;
    }

    public void setDebSession(Date debSession) {
        this.debSession = debSession;
    }

    public Date getFinSession() {
        return finSession;
    }

    public void setFinSession(Date finSession) {
        this.finSession = finSession;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }

    public String getAdresseIp() {
        return adresseIp;
    }

    public void setAdresseIp(String adresseIp) {
        this.adresseIp = adresseIp;
    }

    public String getSystExploitation() {
        return systExploitation;
    }

    public void setSystExploitation(String systExploitation) {
        this.systExploitation = systExploitation;
    }

    public String getCodeoper() {
        return codeoper;
    }

    public void setCodeoper(String codeoper) {
        this.codeoper = codeoper;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSession != null ? idSession.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Session)) {
            return false;
        }
        Session other = (Session) object;
        if ((this.idSession == null && other.idSession != null) || (this.idSession != null && !this.idSession.equals(other.idSession))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Session[ idSession=" + idSession + " ]";
    }
    
}
