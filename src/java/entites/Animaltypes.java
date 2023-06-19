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
@Table(name = "animaltypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Animaltypes.findAll", query = "SELECT a FROM Animaltypes a")
    , @NamedQuery(name = "Animaltypes.findByAnimalTypeID", query = "SELECT a FROM Animaltypes a WHERE a.animalTypeID = :animalTypeID")
    , @NamedQuery(name = "Animaltypes.findByNameEN", query = "SELECT a FROM Animaltypes a WHERE a.nameEN = :nameEN")
    , @NamedQuery(name = "Animaltypes.findByNameFR", query = "SELECT a FROM Animaltypes a WHERE a.nameFR = :nameFR")
    , @NamedQuery(name = "Animaltypes.findByNamePT", query = "SELECT a FROM Animaltypes a WHERE a.namePT = :namePT")
    , @NamedQuery(name = "Animaltypes.findByActive", query = "SELECT a FROM Animaltypes a WHERE a.active = :active")})
public class Animaltypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "AnimalTypeID")
    private String animalTypeID;
    @Size(max = 150)
    @Column(name = "Name_EN")
    private String nameEN;
    @Size(max = 150)
    @Column(name = "Name_FR")
    private String nameFR;
    @Size(max = 150)
    @Column(name = "Name_PT")
    private String namePT;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Active")
    private boolean active;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animalTypeID")
//    private Collection<TypeAnimal> typeAnimalCollection;
    
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "animalTypeID")
    private List<TypeAnimal> animalsList;

    public Animaltypes() {
    }

    public Animaltypes(String animalTypeID) {
        this.animalTypeID = animalTypeID;
    }

    public Animaltypes(String animalTypeID, boolean active) {
        this.animalTypeID = animalTypeID;
        this.active = active;
    }

    public String getAnimalTypeID() {
        return animalTypeID;
    }

    public void setAnimalTypeID(String animalTypeID) {
        this.animalTypeID = animalTypeID;
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    @JsonIgnore
    public List<TypeAnimal> getAnimalsList() {
        return animalsList;
    }

    public void setAnimalsList(List<TypeAnimal> typeAnimalCollection) {
        this.animalsList = typeAnimalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (animalTypeID != null ? animalTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animaltypes)) {
            return false;
        }
        Animaltypes other = (Animaltypes) object;
        if ((this.animalTypeID == null && other.animalTypeID != null) || (this.animalTypeID != null && !this.animalTypeID.equals(other.animalTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Animaltypes[ animalTypeID=" + animalTypeID + " ]";
    }
    
}
