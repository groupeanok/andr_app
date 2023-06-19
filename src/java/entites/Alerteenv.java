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
@Table(name = "alerteenv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alerteenv.findAll", query = "SELECT a FROM Alerteenv a")
    ,
    @NamedQuery(name = "Alerteenv.findByDtenvoie", query = "SELECT a FROM Alerteenv a WHERE a.dtenvoie = :dtenvoie")
    ,
    @NamedQuery(name = "Alerteenv.findByOperenvoie", query = "SELECT a FROM Alerteenv a WHERE a.operenvoie = :operenvoie")})
public class Alerteenv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtenvoie")
    @Temporal(TemporalType.DATE)
    private Date dtenvoie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "operenvoie")
    private String operenvoie;

    public Alerteenv() {
    }

    public Alerteenv(Date dtenvoie) {
        this.dtenvoie = dtenvoie;
    }

    public Alerteenv(Date dtenvoie, String operenvoie) {
        this.dtenvoie = dtenvoie;
        this.operenvoie = operenvoie;
    }

    public Date getDtenvoie() {
        return dtenvoie;
    }

    public void setDtenvoie(Date dtenvoie) {
        this.dtenvoie = dtenvoie;
    }

    public String getOperenvoie() {
        return operenvoie;
    }

    public void setOperenvoie(String operenvoie) {
        this.operenvoie = operenvoie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtenvoie != null ? dtenvoie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alerteenv)) {
            return false;
        }
        Alerteenv other = (Alerteenv) object;
        if ((this.dtenvoie == null && other.dtenvoie != null) || (this.dtenvoie != null && !this.dtenvoie.equals(other.dtenvoie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Alerteenv[ dtenvoie=" + dtenvoie + " ]";
    }

}
