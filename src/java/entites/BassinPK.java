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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Groupe Anok
 */
@Embeddable
public class BassinPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODEOPER")
    private String codeoper;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "idPays")
    private String idPays;

    public BassinPK() {
    }

    public BassinPK(String codeoper, String idPays) {
        this.codeoper = codeoper;
        this.idPays = idPays;
    }

    public String getCodeoper() {
        return codeoper;
    }

    public void setCodeoper(String codeoper) {
        this.codeoper = codeoper;
    }

    public String getIdPays() {
        return idPays;
    }

    public void setIdPays(String idPays) {
        this.idPays = idPays;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeoper != null ? codeoper.hashCode() : 0);
        hash += (idPays != null ? idPays.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BassinPK)) {
            return false;
        }
        BassinPK other = (BassinPK) object;
        if ((this.codeoper == null && other.codeoper != null) || (this.codeoper != null && !this.codeoper.equals(other.codeoper))) {
            return false;
        }
        if ((this.idPays == null && other.idPays != null) || (this.idPays != null && !this.idPays.equals(other.idPays))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.BassinPK[ codeoper=" + codeoper + ", idPays=" + idPays + " ]";
    }
    
}
