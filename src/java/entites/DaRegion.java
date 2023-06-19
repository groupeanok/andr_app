/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "da_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DaRegion.findAll", query = "SELECT d FROM DaRegion d")
    ,
        @NamedQuery(name = "DaRegion.dernordre", query = "SELECT COUNT(i) FROM DaRegion i WHERE i.idPays = :idPays")
    ,
    @NamedQuery(name = "DaRegion.findByIdRegion", query = "SELECT d FROM DaRegion d WHERE d.idRegion = :idRegion")
    ,
    @NamedQuery(name = "DaRegion.findByNomRegion", query = "SELECT d FROM DaRegion d WHERE d.nomRegion = :nomRegion")
    ,
    @NamedQuery(name = "DaRegion.findByCoordx", query = "SELECT d FROM DaRegion d WHERE d.coordx = :coordx")
    ,
    @NamedQuery(name = "DaRegion.findByCoordy", query = "SELECT d FROM DaRegion d WHERE d.coordy = :coordy")})
public class DaRegion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "id_region")
    private String idRegion;
    @Size(max = 150)
    @Column(name = "nom_region")
    private String nomRegion;
    @Column(name = "coordx")
    private BigInteger coordx;
    @Column(name = "coordy")
    private BigInteger coordy;
    @JoinColumn(name = "idPays", referencedColumnName = "idPays")
    @ManyToOne(optional = false)
    private Pays idPays;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegion")
    private List<DaDistrict> daDistrictList;

    public DaRegion() {
    }

    public DaRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    public String getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public BigInteger getCoordx() {
        return coordx;
    }

    public void setCoordx(BigInteger coordx) {
        this.coordx = coordx;
    }

    public BigInteger getCoordy() {
        return coordy;
    }

    public void setCoordy(BigInteger coordy) {
        this.coordy = coordy;
    }

    public Pays getIdPays() {
        return idPays;
    }

    public void setIdPays(Pays idPays) {
        this.idPays = idPays;
    }

    @XmlTransient
    @JsonIgnore
    public List<DaDistrict> getDaDistrictList() {
        return daDistrictList;
    }

    public void setDaDistrictList(List<DaDistrict> daDistrictList) {
        this.daDistrictList = daDistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegion != null ? idRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DaRegion)) {
            return false;
        }
        DaRegion other = (DaRegion) object;
        if ((this.idRegion == null && other.idRegion != null) || (this.idRegion != null && !this.idRegion.equals(other.idRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.DaRegion[ idRegion=" + idRegion + " ]";
    }

}
