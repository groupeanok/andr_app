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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "specialite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Specialite.findAll", query = "SELECT s FROM Specialite s")
    , @NamedQuery(name = "Specialite.findByIdSpecialite", query = "SELECT s FROM Specialite s WHERE s.idSpecialite = :idSpecialite")
    , @NamedQuery(name = "Specialite.findByLibspeFR", query = "SELECT s FROM Specialite s WHERE s.libspeFR = :libspeFR")
    , @NamedQuery(name = "Specialite.findByLibspeEN", query = "SELECT s FROM Specialite s WHERE s.libspeEN = :libspeEN")
    , @NamedQuery(name = "Specialite.findByLibspePT", query = "SELECT s FROM Specialite s WHERE s.libspePT = :libspePT")
    , @NamedQuery(name = "Specialite.findByDescrSpeci", query = "SELECT s FROM Specialite s WHERE s.descrSpeci = :descrSpeci")})
public class Specialite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id_specialite")
    private String idSpecialite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_spe_FR")
    private String libspeFR;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_spe_EN")
    private String libspeEN;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_spe_PT")
    private String libspePT;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descr_speci")
    private String descrSpeci;
    
    @JoinTable(name = "actorspecialite", joinColumns = {
        @JoinColumn(name = "id_specialite", referencedColumnName = "id_specialite")}, inverseJoinColumns = {
        @JoinColumn(name = "id_acteur", referencedColumnName = "id_acteur")})
    @ManyToMany
    private List<Acteur> acteurList;

    public Specialite() {
    }

    public Specialite(String idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public Specialite(String idSpecialite, String libspeFR, String libspeEN, String libspePT, String descrSpeci) {
        this.idSpecialite = idSpecialite;
        this.libspeFR = libspeFR;
        this.libspeEN = libspeEN;
        this.libspePT = libspePT;
        this.descrSpeci = descrSpeci;
    }

    public String getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(String idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public String getLibspeFR() {
        return libspeFR;
    }

    public void setLibspeFR(String libspeFR) {
        this.libspeFR = libspeFR;
    }

    public String getLibspeEN() {
        return libspeEN;
    }

    public void setLibspeEN(String libspeEN) {
        this.libspeEN = libspeEN;
    }

    public String getLibspePT() {
        return libspePT;
    }

    public void setLibspePT(String libspePT) {
        this.libspePT = libspePT;
    }

    public String getDescrSpeci() {
        return descrSpeci;
    }

    public void setDescrSpeci(String descrSpeci) {
        this.descrSpeci = descrSpeci;
    }

    @XmlTransient
    @JsonIgnore
    public List<Acteur> getActeurList() {
        return acteurList;
    }

    public void setActeurList(List<Acteur> acteurList) {
        this.acteurList = acteurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSpecialite != null ? idSpecialite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specialite)) {
            return false;
        }
        Specialite other = (Specialite) object;
        if ((this.idSpecialite == null && other.idSpecialite != null) || (this.idSpecialite != null && !this.idSpecialite.equals(other.idSpecialite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Specialite[ idSpecialite=" + idSpecialite + " ]";
    }
    
}
