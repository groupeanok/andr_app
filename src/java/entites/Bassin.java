/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Groupe Anok
 */
@Entity
@Table(name = "bassin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bassin.findAll", query = "SELECT b FROM Bassin b")
    , @NamedQuery(name = "Bassin.findByCodeoper", query = "SELECT b FROM Bassin b WHERE b.bassinPK.codeoper = :codeoper")
    , @NamedQuery(name = "Bassin.findByIdPays", query = "SELECT b FROM Bassin b WHERE b.bassinPK.idPays = :idPays")})
public class Bassin implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BassinPK bassinPK;

    public Bassin() {
    }

    public Bassin(BassinPK bassinPK) {
        this.bassinPK = bassinPK;
    }

    public Bassin(String codeoper, String idPays) {
        this.bassinPK = new BassinPK(codeoper, idPays);
    }

    public BassinPK getBassinPK() {
        return bassinPK;
    }

    public void setBassinPK(BassinPK bassinPK) {
        this.bassinPK = bassinPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bassinPK != null ? bassinPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bassin)) {
            return false;
        }
        Bassin other = (Bassin) object;
        if ((this.bassinPK == null && other.bassinPK != null) || (this.bassinPK != null && !this.bassinPK.equals(other.bassinPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Bassin[ bassinPK=" + bassinPK + " ]";
    }
    
}
