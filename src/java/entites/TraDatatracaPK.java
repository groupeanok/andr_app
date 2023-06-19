/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author user
 */
@Embeddable
public class TraDatatracaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_checkpt")
    private String idCheckpt;
    @Basic(optional = false)
    @Column(name = "id_structure")
    private String idStructure;
    @Basic(optional = false)
    @Column(name = "id_collecte")
    private String idCollecte;

    public TraDatatracaPK() {
    }

    public TraDatatracaPK(String idCheckpt, String idStructure, String idCollecte) {
        this.idCheckpt = idCheckpt;
        this.idStructure = idStructure;
        this.idCollecte = idCollecte;
    }

    public String getIdCheckpt() {
        return idCheckpt;
    }

    public void setIdCheckpt(String idCheckpt) {
        this.idCheckpt = idCheckpt;
    }

    public String getIdStructure() {
        return idStructure;
    }

    public void setIdStructure(String idStructure) {
        this.idStructure = idStructure;
    }

    public String getIdCollecte() {
        return idCollecte;
    }

    public void setIdCollecte(String idCollecte) {
        this.idCollecte = idCollecte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCheckpt != null ? idCheckpt.hashCode() : 0);
        hash += (idStructure != null ? idStructure.hashCode() : 0);
        hash += (idCollecte != null ? idCollecte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraDatatracaPK)) {
            return false;
        }
        TraDatatracaPK other = (TraDatatracaPK) object;
        if ((this.idCheckpt == null && other.idCheckpt != null) || (this.idCheckpt != null && !this.idCheckpt.equals(other.idCheckpt))) {
            return false;
        }
        if ((this.idStructure == null && other.idStructure != null) || (this.idStructure != null && !this.idStructure.equals(other.idStructure))) {
            return false;
        }
        if ((this.idCollecte == null && other.idCollecte != null) || (this.idCollecte != null && !this.idCollecte.equals(other.idCollecte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.TraDatatracaPK[ idCheckpt=" + idCheckpt + ", idStructure=" + idStructure + ", idCollecte=" + idCollecte + " ]";
    }

}
