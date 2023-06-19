/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "type_animal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeAnimal.findAll", query = "SELECT t FROM TypeAnimal t")
    , @NamedQuery(name = "TypeAnimal.findByIdanimalType", query = "SELECT t FROM TypeAnimal t WHERE t.animalTypeID = :animalTypeID")
    , @NamedQuery(name = "TypeAnimal.findByIdTypean", query = "SELECT t FROM TypeAnimal t WHERE t.idTypean = :idTypean")
    , @NamedQuery(name = "TypeAnimal.findByLibtypeanEN", query = "SELECT t FROM TypeAnimal t WHERE t.libtypeanEN = :libtypeanEN")
    , @NamedQuery(name = "TypeAnimal.findByLibtypeanFR", query = "SELECT t FROM TypeAnimal t WHERE t.libtypeanFR = :libtypeanFR")
    , @NamedQuery(name = "TypeAnimal.findByLibtypeanPT", query = "SELECT t FROM TypeAnimal t WHERE t.libtypeanPT = :libtypeanPT")})
public class TypeAnimal implements Serializable {

    @Size(max = 50)
    @Column(name = "HS_code")
    private String hScode;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_typean")
    private String idTypean;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "lib_typean_EN")
    private String libtypeanEN;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_typean_FR")
    private String libtypeanFR;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_typean_PT")
    private String libtypeanPT;
    @OneToMany(mappedBy = "idTypean")
    private List<Offre> offreList;
    @JoinColumn(name = "AnimalTypeID", referencedColumnName = "AnimalTypeID")
    @ManyToOne(optional = false)
    private Animaltypes animalTypeID;

    public TypeAnimal() {
    }

    public TypeAnimal(String idTypean) {
        this.idTypean = idTypean;
    }

    public TypeAnimal(String idTypean, String libtypeanEN, String libtypeanFR, String libtypeanPT) {
        this.idTypean = idTypean;
        this.libtypeanEN = libtypeanEN;
        this.libtypeanFR = libtypeanFR;
        this.libtypeanPT = libtypeanPT;
    }

    public String getIdTypean() {
        return idTypean;
    }

    public void setIdTypean(String idTypean) {
        this.idTypean = idTypean;
    }

    public String getLibtypeanEN() {
        return libtypeanEN;
    }

    public void setLibtypeanEN(String libtypeanEN) {
        this.libtypeanEN = libtypeanEN;
    }

    public String getLibtypeanFR() {
        return libtypeanFR;
    }

    public void setLibtypeanFR(String libtypeanFR) {
        this.libtypeanFR = libtypeanFR;
    }

    public String getLibtypeanPT() {
        return libtypeanPT;
    }

    public void setLibtypeanPT(String libtypeanPT) {
        this.libtypeanPT = libtypeanPT;
    }

    @XmlTransient
    @JsonIgnore
    public List<Offre> getOffreList() {
        return offreList;
    }

    public void setOffreList(List<Offre> offreList) {
        this.offreList = offreList;
    }

    public Animaltypes getAnimalTypeID() {
        return animalTypeID;
    }

    public void setAnimalTypeID(Animaltypes animalTypeID) {
        this.animalTypeID = animalTypeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypean != null ? idTypean.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeAnimal)) {
            return false;
        }
        TypeAnimal other = (TypeAnimal) object;
        if ((this.idTypean == null && other.idTypean != null) || (this.idTypean != null && !this.idTypean.equals(other.idTypean))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.TypeAnimal[ idTypean=" + idTypean + " ]";
    }

    public String getHScode() {
        return hScode;
    }

    public void setHScode(String hScode) {
        this.hScode = hScode;
    }
    
}
