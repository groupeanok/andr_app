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
@Table(name = "tra_niveausco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TraNiveausco.findAll", query = "SELECT t FROM TraNiveausco t")
    ,
    @NamedQuery(name = "TraNiveausco.findByIdNiveau", query = "SELECT t FROM TraNiveausco t WHERE t.idNiveau = :idNiveau")
    ,
    @NamedQuery(name = "TraNiveausco.findByLibelle", query = "SELECT t FROM TraNiveausco t WHERE t.libelle = :libelle")})
public class TraNiveausco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "id_niveau")
    private String idNiveau;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNiveau")
    private List<TraCollecte> traCollecteList;

    public TraNiveausco() {
    }

    public TraNiveausco(String idNiveau) {
        this.idNiveau = idNiveau;
    }

    public TraNiveausco(String idNiveau, String libelle) {
        this.idNiveau = idNiveau;
        this.libelle = libelle;
    }

    public String getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(String idNiveau) {
        this.idNiveau = idNiveau;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    @JsonIgnore
    public List<TraCollecte> getTraCollecteList() {
        return traCollecteList;
    }

    public void setTraCollecteList(List<TraCollecte> traCollecteList) {
        this.traCollecteList = traCollecteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNiveau != null ? idNiveau.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraNiveausco)) {
            return false;
        }
        TraNiveausco other = (TraNiveausco) object;
        if ((this.idNiveau == null && other.idNiveau != null) || (this.idNiveau != null && !this.idNiveau.equals(other.idNiveau))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.TraNiveausco[ idNiveau=" + idNiveau + " ]";
    }

}
