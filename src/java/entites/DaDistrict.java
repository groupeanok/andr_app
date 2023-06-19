/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "da_district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DaDistrict.findAll", query = "SELECT d FROM DaDistrict d")
    ,
    @NamedQuery(name = "DaDistrict.dernordre", query = "SELECT COUNT(i) FROM DaDistrict i WHERE i.idRegion = :idRegion")
    ,
    @NamedQuery(name = "DaDistrict.findByIdDistrict", query = "SELECT d FROM DaDistrict d WHERE d.idDistrict = :idDistrict")
    ,
    @NamedQuery(name = "DaDistrict.findByNomDistrict", query = "SELECT d FROM DaDistrict d WHERE d.nomDistrict = :nomDistrict")
    ,
    @NamedQuery(name = "DaDistrict.findByCoordx", query = "SELECT d FROM DaDistrict d WHERE d.coordx = :coordx")
    ,
    @NamedQuery(name = "DaDistrict.findByCoordy", query = "SELECT d FROM DaDistrict d WHERE d.coordy = :coordy")})
public class DaDistrict implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "id_district")
    private String idDistrict;
    @Size(max = 150)
    @Column(name = "nom_district")
    private String nomDistrict;
    @Column(name = "coordx")
    private BigInteger coordx;
    @Column(name = "coordy")
    private BigInteger coordy;
    @JoinColumn(name = "id_region", referencedColumnName = "id_region")
    @ManyToOne(optional = false)
    private DaRegion idRegion;

    public DaDistrict() {
    }

    public DaDistrict(String idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(String idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getNomDistrict() {
        return nomDistrict;
    }

    public void setNomDistrict(String nomDistrict) {
        this.nomDistrict = nomDistrict;
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

    public DaRegion getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(DaRegion idRegion) {
        this.idRegion = idRegion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDistrict != null ? idDistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DaDistrict)) {
            return false;
        }
        DaDistrict other = (DaDistrict) object;
        if ((this.idDistrict == null && other.idDistrict != null) || (this.idDistrict != null && !this.idDistrict.equals(other.idDistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.DaDistrict[ idDistrict=" + idDistrict + " ]";
    }

}
