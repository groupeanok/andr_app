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
@Table(name = "units")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Units.findAll", query = "SELECT u FROM Units u")
    , @NamedQuery(name = "Units.findByUnitid", query = "SELECT u FROM Units u WHERE u.unitid = :unitid")
    , @NamedQuery(name = "Units.findByNameEN", query = "SELECT u FROM Units u WHERE u.nameEN = :nameEN")
    , @NamedQuery(name = "Units.findByNameFR", query = "SELECT u FROM Units u WHERE u.nameFR = :nameFR")
    , @NamedQuery(name = "Units.findByNamePT", query = "SELECT u FROM Units u WHERE u.namePT = :namePT")
    , @NamedQuery(name = "Units.findByAbbrev", query = "SELECT u FROM Units u WHERE u.abbrev = :abbrev")})
public class Units implements Serializable {

    @OneToMany(mappedBy = "idunit")
    private Collection<Offre> offreCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "unitid")
    private String unitid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name_EN")
    private String nameEN;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name_FR")
    private String nameFR;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name_PT")
    private String namePT;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "abbrev")
    private String abbrev;
    @OneToMany(mappedBy = "unitid")
    private List<Collecte> collecteList;

    public Units() {
    }

    public Units(String unitid) {
        this.unitid = unitid;
    }

    public Units(String unitid, String nameEN, String nameFR, String namePT, String abbrev) {
        this.unitid = unitid;
        this.nameEN = nameEN;
        this.nameFR = nameFR;
        this.namePT = namePT;
        this.abbrev = abbrev;
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameFR() {
        return nameFR;
    }

    public void setNameFR(String nameFR) {
        this.nameFR = nameFR;
    }

    public String getNamePT() {
        return namePT;
    }

    public void setNamePT(String namePT) {
        this.namePT = namePT;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    @XmlTransient
    @JsonIgnore
    public List<Collecte> getCollecteList() {
        return collecteList;
    }

    public void setCollecteList(List<Collecte> collecteList) {
        this.collecteList = collecteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unitid != null ? unitid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Units)) {
            return false;
        }
        Units other = (Units) object;
        if ((this.unitid == null && other.unitid != null) || (this.unitid != null && !this.unitid.equals(other.unitid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Units[ unitid=" + unitid + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Offre> getOffreCollection() {
        return offreCollection;
    }

    public void setOffreCollection(Collection<Offre> offreCollection) {
        this.offreCollection = offreCollection;
    }
    
}
