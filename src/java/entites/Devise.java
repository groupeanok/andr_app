/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "devise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Devise.findAll", query = "SELECT d FROM Devise d")
    ,
    @NamedQuery(name = "Devise.findByCodedev", query = "SELECT d FROM Devise d WHERE d.codedev = :codedev")
    ,
    @NamedQuery(name = "Devise.findByLibelleDev", query = "SELECT d FROM Devise d WHERE d.libelleDev = :libelleDev")})
public class Devise implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codedev")
    private Collection<Collecte> collecteCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codedev")
    private String codedev;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LibelleDev")
    private String libelleDev;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codedev")
//    private List<TraDatatraca_1> traDatatracaList;
    @OneToMany(mappedBy = "codedev")
    private List<Pays> paysList;

    public Devise() {
    }

    public Devise(String codedev) {
        this.codedev = codedev;
    }

    public Devise(String codedev, String libelleDev) {
        this.codedev = codedev;
        this.libelleDev = libelleDev;
    }

    public String getCodedev() {
        return codedev;
    }

    public void setCodedev(String codedev) {
        this.codedev = codedev;
    }

    public String getLibelleDev() {
        return libelleDev;
    }

    public void setLibelleDev(String libelleDev) {
        this.libelleDev = libelleDev;
    }

//    @XmlTransient
//    @JsonIgnore
//    public List<TraDatatraca_1> getTraDatatracaList() {
//        return traDatatracaList;
//    }
//
//    public void setTraDatatracaList(List<TraDatatraca_1> traDatatracaList) {
//        this.traDatatracaList = traDatatracaList;
//    }
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
        hash += (codedev != null ? codedev.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devise)) {
            return false;
        }
        Devise other = (Devise) object;
        if ((this.codedev == null && other.codedev != null) || (this.codedev != null && !this.codedev.equals(other.codedev))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Devise[ codedev=" + codedev + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Collecte> getCollecteCollection() {
        return collecteCollection;
    }

    public void setCollecteCollection(Collection<Collecte> collecteCollection) {
        this.collecteCollection = collecteCollection;
    }

}
