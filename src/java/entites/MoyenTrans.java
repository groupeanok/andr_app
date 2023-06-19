/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "moyen_trans")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MoyenTrans.findAll", query = "SELECT m FROM MoyenTrans m")
    , @NamedQuery(name = "MoyenTrans.findByIdMoyTrans", query = "SELECT m FROM MoyenTrans m WHERE m.idMoyTrans = :idMoyTrans")
    , @NamedQuery(name = "MoyenTrans.findByLibmtEN", query = "SELECT m FROM MoyenTrans m WHERE m.libmtEN = :libmtEN")
    , @NamedQuery(name = "MoyenTrans.findByLibmtFR", query = "SELECT m FROM MoyenTrans m WHERE m.libmtFR = :libmtFR")
    , @NamedQuery(name = "MoyenTrans.findByLibmtPT", query = "SELECT m FROM MoyenTrans m WHERE m.libmtPT = :libmtPT")})
public class MoyenTrans implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id_moy_trans")
    private String idMoyTrans;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_mt_EN")
    private String libmtEN;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_mt_FR")
    private String libmtFR;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_mt_PT")
    private String libmtPT;

    public MoyenTrans() {
    }

    public MoyenTrans(String idMoyTrans) {
        this.idMoyTrans = idMoyTrans;
    }

    public MoyenTrans(String idMoyTrans, String libmtEN, String libmtFR, String libmtPT) {
        this.idMoyTrans = idMoyTrans;
        this.libmtEN = libmtEN;
        this.libmtFR = libmtFR;
        this.libmtPT = libmtPT;
    }

    public String getIdMoyTrans() {
        return idMoyTrans;
    }

    public void setIdMoyTrans(String idMoyTrans) {
        this.idMoyTrans = idMoyTrans;
    }

    public String getLibmtEN() {
        return libmtEN;
    }

    public void setLibmtEN(String libmtEN) {
        this.libmtEN = libmtEN;
    }

    public String getLibmtFR() {
        return libmtFR;
    }

    public void setLibmtFR(String libmtFR) {
        this.libmtFR = libmtFR;
    }

    public String getLibmtPT() {
        return libmtPT;
    }

    public void setLibmtPT(String libmtPT) {
        this.libmtPT = libmtPT;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMoyTrans != null ? idMoyTrans.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MoyenTrans)) {
            return false;
        }
        MoyenTrans other = (MoyenTrans) object;
        if ((this.idMoyTrans == null && other.idMoyTrans != null) || (this.idMoyTrans != null && !this.idMoyTrans.equals(other.idMoyTrans))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.MoyenTrans[ idMoyTrans=" + idMoyTrans + " ]";
    }
    
}
