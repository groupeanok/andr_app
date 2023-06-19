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
@Table(name = "tra_type_proprio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TraTypeProprio.findAll", query = "SELECT t FROM TraTypeProprio t")
    ,
    @NamedQuery(name = "TraTypeProprio.findByIdTypeProprio", query = "SELECT t FROM TraTypeProprio t WHERE t.idTypeProprio = :idTypeProprio")
    ,
    @NamedQuery(name = "TraTypeProprio.findByNomtypepro", query = "SELECT t FROM TraTypeProprio t WHERE t.nomtypepro = :nomtypepro")})
public class TraTypeProprio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "id_type_proprio")
    private String idTypeProprio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nomtypepro")
    private String nomtypepro;

    public TraTypeProprio() {
    }

    public TraTypeProprio(String idTypeProprio) {
        this.idTypeProprio = idTypeProprio;
    }

    public TraTypeProprio(String idTypeProprio, String nomtypepro) {
        this.idTypeProprio = idTypeProprio;
        this.nomtypepro = nomtypepro;
    }

    public String getIdTypeProprio() {
        return idTypeProprio;
    }

    public void setIdTypeProprio(String idTypeProprio) {
        this.idTypeProprio = idTypeProprio;
    }

    public String getNomtypepro() {
        return nomtypepro;
    }

    public void setNomtypepro(String nomtypepro) {
        this.nomtypepro = nomtypepro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeProprio != null ? idTypeProprio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraTypeProprio)) {
            return false;
        }
        TraTypeProprio other = (TraTypeProprio) object;
        if ((this.idTypeProprio == null && other.idTypeProprio != null) || (this.idTypeProprio != null && !this.idTypeProprio.equals(other.idTypeProprio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.TraTypeProprio[ idTypeProprio=" + idTypeProprio + " ]";
    }

}
