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
@Table(name = "tra_structure_ctrle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TraStructureCtrle.findAll", query = "SELECT t FROM TraStructureCtrle t")
    ,
    @NamedQuery(name = "TraStructureCtrle.findByIdStructure", query = "SELECT t FROM TraStructureCtrle t WHERE t.idStructure = :idStructure")
    ,
    @NamedQuery(name = "TraStructureCtrle.findByNomStructure", query = "SELECT t FROM TraStructureCtrle t WHERE t.nomStructure = :nomStructure")})
public class TraStructureCtrle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "id_structure")
    private String idStructure;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom_structure")
    private String nomStructure;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStructure")
//    private List<TraDatatraca> traDatatracaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "traStructureCtrle")
    private List<TraDatatraca> traDatatracaList;

    public TraStructureCtrle() {
    }

    public TraStructureCtrle(String idStructure) {
        this.idStructure = idStructure;
    }

    public TraStructureCtrle(String idStructure, String nomStructure) {
        this.idStructure = idStructure;
        this.nomStructure = nomStructure;
    }

    public String getIdStructure() {
        return idStructure;
    }

    public void setIdStructure(String idStructure) {
        this.idStructure = idStructure;
    }

    public String getNomStructure() {
        return nomStructure;
    }

    public void setNomStructure(String nomStructure) {
        this.nomStructure = nomStructure;
    }

    @XmlTransient
    @JsonIgnore
    public List<TraDatatraca> getTraDatatracaList() {
        return traDatatracaList;
    }

    public void setTraDatatracaList(List<TraDatatraca> traDatatracaList) {
        this.traDatatracaList = traDatatracaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStructure != null ? idStructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraStructureCtrle)) {
            return false;
        }
        TraStructureCtrle other = (TraStructureCtrle) object;
        if ((this.idStructure == null && other.idStructure != null) || (this.idStructure != null && !this.idStructure.equals(other.idStructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.TraStructureCtrle[ idStructure=" + idStructure + " ]";
    }

}
