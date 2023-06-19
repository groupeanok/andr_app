/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author user
 */
@Entity
@Table(name = "langue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Langue.findAll", query = "SELECT l FROM Langue l")
    ,
    @NamedQuery(name = "Langue.findByCodelang", query = "SELECT l FROM Langue l WHERE l.codelang = :codelang")
    ,
    @NamedQuery(name = "Langue.findByLibellelang", query = "SELECT l FROM Langue l WHERE l.libellelang = :libellelang")})
public class Langue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CODELANG")
    private String codelang;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LIBELLELANG")
    private String libellelang;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codelang")
    private List<Pays> paysList;

    public Langue() {
    }

    public Langue(String codelang) {
        this.codelang = codelang;
    }

    public Langue(String codelang, String libellelang) {
        this.codelang = codelang;
        this.libellelang = libellelang;
    }

    public String getCodelang() {
        return codelang;
    }

    public void setCodelang(String codelang) {
        this.codelang = codelang;
    }

    public String getLibellelang() {
        return libellelang;
    }

    public void setLibellelang(String libellelang) {
        this.libellelang = libellelang;
    }

    @XmlTransient
    @JsonIgnore
    public List<Pays> getPaysList() {
        return paysList;
    }

    public void setPaysList(List<Pays> paysList) {
        this.paysList = paysList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codelang != null ? codelang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Langue)) {
            return false;
        }
        Langue other = (Langue) object;
        if ((this.codelang == null && other.codelang != null) || (this.codelang != null && !this.codelang.equals(other.codelang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Langue[ codelang=" + codelang + " ]";
    }

}
