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
@Table(name = "alerte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alerte.findAll", query = "SELECT a FROM Alerte a")
    ,
    @NamedQuery(name = "Alerte.findByIdAlerte", query = "SELECT a FROM Alerte a WHERE a.idAlerte = :idAlerte")
    ,
    @NamedQuery(name = "Alerte.findByLibAlerte", query = "SELECT a FROM Alerte a WHERE a.libAlerte = :libAlerte")
    ,
    @NamedQuery(name = "Alerte.findByTypeAlerte", query = "SELECT a FROM Alerte a WHERE a.typeAlerte = :typeAlerte")
    ,
    @NamedQuery(name = "Alerte.findByActif", query = "SELECT a FROM Alerte a WHERE a.actif = :actif")})
public class Alerte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ID_ALERTE")
    private String idAlerte;
    @Size(max = 40)
    @Column(name = "LIB_ALERTE")
    private String libAlerte;
    @Size(max = 20)
    @Column(name = "TYPE_ALERTE")
    private String typeAlerte;
    @Column(name = "ACTIF")
    private Boolean actif;

    public Alerte() {
    }

    public Alerte(String idAlerte) {
        this.idAlerte = idAlerte;
    }

    public String getIdAlerte() {
        return idAlerte;
    }

    public void setIdAlerte(String idAlerte) {
        this.idAlerte = idAlerte;
    }

    public String getLibAlerte() {
        return libAlerte;
    }

    public void setLibAlerte(String libAlerte) {
        this.libAlerte = libAlerte;
    }

    public String getTypeAlerte() {
        return typeAlerte;
    }

    public void setTypeAlerte(String typeAlerte) {
        this.typeAlerte = typeAlerte;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlerte != null ? idAlerte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alerte)) {
            return false;
        }
        Alerte other = (Alerte) object;
        if ((this.idAlerte == null && other.idAlerte != null) || (this.idAlerte != null && !this.idAlerte.equals(other.idAlerte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Alerte[ idAlerte=" + idAlerte + " ]";
    }

}
