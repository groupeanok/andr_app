/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "offre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offre.findAll", query = "SELECT o FROM Offre o ORDER BY o.datecre DESC")
    ,
    @NamedQuery(name = "Offre.findByIdOffre", query = "SELECT o FROM Offre o WHERE o.idOffre = :idOffre")
    ,
        @NamedQuery(name = "Offre.nbre", query = "SELECT count(g) FROM Offre g where g.dateoffre = :dateCollecte AND g.typeOffre = :typeOffre")
    ,
    @NamedQuery(name = "Offre.findByDatecre", query = "SELECT o FROM Offre o WHERE o.datecre = :datecre")
    ,
    @NamedQuery(name = "Offre.findByDatemaj", query = "SELECT o FROM Offre o WHERE o.datemaj = :datemaj")
//    ,
//    @NamedQuery(name = "Offre.findByLibelleOffre", query = "SELECT o FROM Offre o WHERE o.libelleOffre = :libelleOffre")
    ,
    @NamedQuery(name = "Offre.findByTypeOffre", query = "SELECT o FROM Offre o WHERE o.typeOffre = :typeOffre ORDER BY o.datecre")
    ,
        @NamedQuery(name = "Offre.findByTypeOffrea", query = "SELECT o FROM Offre o WHERE o.typeOffre = :typeOffre")
    ,// AND o.idNatproduit = null"),
    @NamedQuery(name = "Offre.findByTypeOffrev", query = "SELECT o FROM Offre o WHERE o.typeOffre = :typeOffre")
    , // AND o.idTypeanis = null "),
  //      @NamedQuery(name = "Offre.findByTypeOffre", query = "SELECT o FROM Offre o WHERE o.typeOffre = :typeOffre"),
//    @NamedQuery(name = "Offre.findByTypeOffrea", query = "SELECT o FROM Offre o WHERE o.typeOffre = :typeOffre"),// AND o.idNatproduit = null"),
//    @NamedQuery(name = "Offre.findByTypeOffrev", query = "SELECT o FROM Offre o WHERE o.typeOffre = :typeOffre"), // AND o.idTypeanis = null "),
    @NamedQuery(name = "Offre.findByQteOffre", query = "SELECT o FROM Offre o WHERE o.qteOffre = :qteOffre")
    ,
    @NamedQuery(name = "Offre.findByPrixOffre", query = "SELECT o FROM Offre o WHERE o.prixOffre = :prixOffre")
    ,
    @NamedQuery(name = "Offre.findByValide", query = "SELECT o FROM Offre o WHERE o.valide = :valide")
    ,
    @NamedQuery(name = "Offre.findByConclus", query = "SELECT o FROM Offre o WHERE o.conclus = :conclus")
    ,
    @NamedQuery(name = "Offre.findByDateoffre", query = "SELECT o FROM Offre o WHERE o.dateoffre = :dateoffre")
    ,
    @NamedQuery(name = "Offre.findByDateVal", query = "SELECT o FROM Offre o WHERE o.dateVal = :dateVal")})
public class Offre implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "coordx")
    private float coordx;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coordy")
    private float coordy;

   @Size(max = 50)
    @Column(name = "photo")
    private String photo;
    @Size(max = 25)
    @Column(name = "codedev")
    private String codedev;
    @JoinColumn(name = "idunit", referencedColumnName = "unitid")
    @ManyToOne
    private Units idunit;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_offre")
    private String idOffre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATECRE")
    @Temporal(TemporalType.TIMESTAMP)
//    @Temporal(TemporalType.DATE)
    private java.util.Date datecre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEMAJ")
    @Temporal(TemporalType.DATE)
    private Date datemaj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "libelle_offre")
    private String libelleOffre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type_offre")
    private String typeOffre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qte_offre")
    private int qteOffre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_offre")
    private int prixOffre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valide")
    private boolean valide;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conclus")
    private boolean conclus;
    
    
    
//            @Basic(optional = false)
//    @NotNull
//    @Column(name = "conclus")
//    private boolean conclus;
//    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "statut")
    private String statut;
    
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateoffre")
    @Temporal(TemporalType.DATE)
    private Date dateoffre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_val")
    @Temporal(TemporalType.DATE)
    private Date dateVal;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "offre")
    private ValOffre valOffre;
    
    @JoinColumn(name = "id_acteur", referencedColumnName = "id_acteur")
    @ManyToOne(optional = false)
    private Acteur idActeur;
    
    @JoinColumn(name = "id_typean", referencedColumnName = "id_typean")
    @ManyToOne
    private TypeAnimal idTypean;
//    @JoinColumn(name = "id_natproduit", referencedColumnName = "id_natproduit")
//    @ManyToOne
//    private NatureProduit idNatproduit;

    @JoinColumn(name = "id_produit", referencedColumnName = "id_produit")
    @ManyToOne
    private Produit idProduit;

    @JoinColumn(name = "OPMAJ", referencedColumnName = "CODEOPER")
    @ManyToOne(optional = false)
    private Operateur opmaj;
    @JoinColumn(name = "OPCRE", referencedColumnName = "CODEOPER")
    @ManyToOne(optional = false)
    private Operateur opcre;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "offre")
//    private Offreconclus offreconclus;

    public Offre() {
    }

    public Offre(String idOffre) {
        this.idOffre = idOffre;
    }

    public Offre(String idOffre, Date datecre, Date datemaj, String libelleOffre, String typeOffre, int qteOffre, int prixOffre, boolean valide, boolean conclus, Date dateoffre, Date dateVal) {
        this.idOffre = idOffre;
        this.datecre = datecre;
        this.datemaj = datemaj;
        this.libelleOffre = libelleOffre;
        this.typeOffre = typeOffre;
        this.qteOffre = qteOffre;
        this.prixOffre = prixOffre;
        this.valide = valide;
        this.conclus = conclus;
        this.dateoffre = dateoffre;
        this.dateVal = dateVal;
    }

    public String getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public Date getDatecre() {
        return datecre;
    }

    public void setDatecre(Date datecre) {
        this.datecre = datecre;
    }

    public Date getDatemaj() {
        return datemaj;
    }

    public void setDatemaj(Date datemaj) {
        this.datemaj = datemaj;
    }

    public String getLibelleOffre() {
        return libelleOffre;
    }

    public void setLibelleOffre(String libelleOffre) {
        this.libelleOffre = libelleOffre;
    }

    public String getTypeOffre() {
        return typeOffre;
    }

    public void setTypeOffre(String typeOffre) {
        this.typeOffre = typeOffre;
    }

    public int getQteOffre() {
        return qteOffre;
    }

    public void setQteOffre(int qteOffre) {
        this.qteOffre = qteOffre;
    }

    public int getPrixOffre() {
        return prixOffre;
    }

    public void setPrixOffre(int prixOffre) {
        this.prixOffre = prixOffre;
    }

    public boolean getValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public boolean getConclus() {
        return conclus;
    }

    public void setConclus(boolean conclus) {
        this.conclus = conclus;
    }

    public Date getDateoffre() {
        return dateoffre;
    }

    public void setDateoffre(Date dateoffre) {
        this.dateoffre = dateoffre;
    }

    public Date getDateVal() {
        return dateVal;
    }

    public void setDateVal(Date dateVal) {
        this.dateVal = dateVal;
    }

    public ValOffre getValOffre() {
        return valOffre;
    }

    public void setValOffre(ValOffre valOffre) {
        this.valOffre = valOffre;
    }

    public Acteur getIdActeur() {
        return idActeur;
    }

    public void setIdActeur(Acteur idActeur) {
        this.idActeur = idActeur;
    }

    public TypeAnimal getIdTypean() {
        return idTypean;
    }

    public void setIdTypean(TypeAnimal idTypean) {
        this.idTypean = idTypean;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Produit idNatproduit) {
        this.idProduit = idNatproduit;
    }
//    
//    public NatureProduit getIdNatproduit() {
//        return idNatproduit;
//    }
//
//    public void setIdNatproduit(NatureProduit idNatproduit) {
//        this.idNatproduit = idNatproduit;
//    }

    public Operateur getOpmaj() {
        return opmaj;
    }

    public void setOpmaj(Operateur opmaj) {
        this.opmaj = opmaj;
    }

    public Operateur getOpcre() {
        return opcre;
    }

    public void setOpcre(Operateur opcre) {
        this.opcre = opcre;
    }

//    public Offreconclus getOffreconclus() {
//        return offreconclus;
//    }
//
//    public void setOffreconclus(Offreconclus offreconclus) {
//        this.offreconclus = offreconclus;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOffre != null ? idOffre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Offre)) {
            return false;
        }
        Offre other = (Offre) object;
        return !((this.idOffre == null && other.idOffre != null) || (this.idOffre != null && !this.idOffre.equals(other.idOffre)));
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Offre[ idOffre=" + idOffre + " ]";
    }

    /**
     * @return the statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * @param statut the statut to set
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCodedev() {
        return codedev;
    }

    public void setCodedev(String codedev) {
        this.codedev = codedev;
    }

    public Units getIdunit() {
        return idunit;
    }

    public void setIdunit(Units idunit) {
        this.idunit = idunit;
    }

    public float getCoordx() {
        return coordx;
    }

    public void setCoordx(float coordx) {
        this.coordx = coordx;
    }

    public float getCoordy() {
        return coordy;
    }

    public void setCoordy(float coordy) {
        this.coordy = coordy;
    }

}
