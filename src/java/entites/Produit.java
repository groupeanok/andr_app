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
@Table(name = "produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findByIdProduit", query = "SELECT p FROM Produit p WHERE p.idProduit = :idProduit")
    , @NamedQuery(name = "Produit.findByIdNatproduit", query = "SELECT p FROM Produit p WHERE p.idNatproduit = :idNatproduit")
    , @NamedQuery(name = "Produit.findByHSCode", query = "SELECT p FROM Produit p WHERE p.hSCode = :hSCode")
    , @NamedQuery(name = "Produit.findByLibproduitEN", query = "SELECT p FROM Produit p WHERE p.libproduitEN = :libproduitEN")
    , @NamedQuery(name = "Produit.findByLibproduitFR", query = "SELECT p FROM Produit p WHERE p.libproduitFR = :libproduitFR")
    , @NamedQuery(name = "Produit.findByLibproduitPT", query = "SELECT p FROM Produit p WHERE p.libproduitPT = :libproduitPT")})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_produit")
    private String idProduit;
    
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
//    @Column(name = "id_natproduit")
//    private String idNatproduit;
    
        @JoinColumn(name = "id_natproduit", referencedColumnName = "id_natproduit")
    @ManyToOne(optional = false)
    private NatureProduit idNatproduit;
    
    
    
    
    @Size(max = 100)
    @Column(name = "HS_Code")
    private String hSCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "lib_produit_EN")
    private String libproduitEN;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_produit_FR")
    private String libproduitFR;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_produit_PT")
    private String libproduitPT;
    @OneToMany(mappedBy = "idProduit")
    private List<Offre> offreList;

    public Produit() {
    }

    public Produit(String idProduit) {
        this.idProduit = idProduit;
    }

    public Produit(String idProduit, NatureProduit idNatproduit, String libproduitEN, String libproduitFR, String libproduitPT) {
        this.idProduit = idProduit;
        this.idNatproduit = idNatproduit;
        this.libproduitEN = libproduitEN;
        this.libproduitFR = libproduitFR;
        this.libproduitPT = libproduitPT;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public NatureProduit getIdNatproduit() {
        return idNatproduit;
    }

    public void setIdNatproduit(NatureProduit idNatproduit) {
        this.idNatproduit = idNatproduit;
    }

    public String getHSCode() {
        return hSCode;
    }

    public void setHSCode(String hSCode) {
        this.hSCode = hSCode;
    }

    public String getLibproduitEN() {
        return libproduitEN;
    }

    public void setLibproduitEN(String libproduitEN) {
        this.libproduitEN = libproduitEN;
    }

    public String getLibproduitFR() {
        return libproduitFR;
    }

    public void setLibproduitFR(String libproduitFR) {
        this.libproduitFR = libproduitFR;
    }

    public String getLibproduitPT() {
        return libproduitPT;
    }

    public void setLibproduitPT(String libproduitPT) {
        this.libproduitPT = libproduitPT;
    }

    @XmlTransient
    @JsonIgnore
    public List<Offre> getOffreList() {
        return offreList;
    }

    public void setOffreList(List<Offre> offreList) {
        this.offreList = offreList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduit != null ? idProduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idProduit == null && other.idProduit != null) || (this.idProduit != null && !this.idProduit.equals(other.idProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Produit[ idProduit=" + idProduit + " ]";
    }
    
}
