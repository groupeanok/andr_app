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
@Table(name = "nature_produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NatureProduit.findAll", query = "SELECT n FROM NatureProduit n")
    ,
    @NamedQuery(name = "NatureProduit.findByIdNatproduit", query = "SELECT n FROM NatureProduit n WHERE n.idNatproduit = :idNatproduit")
    ,
    @NamedQuery(name = "NatureProduit.findByLibNatproduit", query = "SELECT n FROM NatureProduit n WHERE n.libNatproduit = :libNatproduit")})
public class NatureProduit implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_natproduit_En")
    private String libnatproduitEn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lib_natproduit_Pt")
    private String libnatproduitPt;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id_natproduit")
    private String idNatproduit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "lib_natproduit")
    private String libNatproduit;
//    @OneToMany(mappedBy = "idProduit")
//    private List<Collecte> collecteList;
//    @OneToMany(mappedBy = "idProduit")
//    private List<Offre> offreList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNatproduit")
    private List<Produit> produitList;

    public NatureProduit() {
    }

    public NatureProduit(String idNatproduit) {
        this.idNatproduit = idNatproduit;
    }

    public NatureProduit(String idNatproduit, String libNatproduit) {
        this.idNatproduit = idNatproduit;
        this.libNatproduit = libNatproduit;
    }

    public String getIdNatproduit() {
        return idNatproduit;
    }

    public void setIdNatproduit(String idNatproduit) {
        this.idNatproduit = idNatproduit;
    }

    public String getLibNatproduit() {
        return libNatproduit;
    }

    public void setLibNatproduit(String libNatproduit) {
        this.libNatproduit = libNatproduit;
    }

//    @XmlTransient
//    @JsonIgnore
//    public List<Collecte> getCollecteList() {
//        return collecteList;
//    }
//
//    public void setCollecteList(List<Collecte> collecteList) {
//        this.collecteList = collecteList;
//    }
//
//    @XmlTransient
//    @JsonIgnore
//    public List<Offre> getOffreList() {
//        return offreList;
//    }
//
//    public void setOffreList(List<Offre> offreList) {
//        this.offreList = offreList;
//    }
    @XmlTransient
    @JsonIgnore
    public List<Produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        this.produitList = produitList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNatproduit != null ? idNatproduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NatureProduit)) {
            return false;
        }
        NatureProduit other = (NatureProduit) object;
        if ((this.idNatproduit == null && other.idNatproduit != null) || (this.idNatproduit != null && !this.idNatproduit.equals(other.idNatproduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.NatureProduit[ idNatproduit=" + idNatproduit + " ]";
    }

    public String getLibnatproduitEn() {
        return libnatproduitEn;
    }

    public void setLibnatproduitEn(String libnatproduitEn) {
        this.libnatproduitEn = libnatproduitEn;
    }

    public String getLibnatproduitPt() {
        return libnatproduitPt;
    }

    public void setLibnatproduitPt(String libnatproduitPt) {
        this.libnatproduitPt = libnatproduitPt;
    }

}
