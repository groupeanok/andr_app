/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "operateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operateur.findAll", query = "SELECT o FROM Operateur o")
    ,@NamedQuery(name = "Operateur.findAllorder", query = "SELECT o FROM Operateur o ORDER BY o.nomoper")
    ,@NamedQuery(name = "Operateur.findAll1", query = "SELECT o FROM Operateur o ORDER BY o.datecre desc")
    ,@NamedQuery(name = "Operateur.findbygrp", query = "SELECT o FROM Operateur o WHERE o.idgroupe.idgroupe = :idgroupe")
    ,  
    @NamedQuery(name = "Operateur.findbygrppa", query = "SELECT o FROM Operateur o WHERE o.idgroupe.idgroupe = :idgroupe AND o.nationalite = :nationalite")
    , 
    @NamedQuery(name = "Operateur.Connect", query = "SELECT t FROM Operateur t WHERE t.codeoper = :codeoper AND t.password = :password")
    ,
    @NamedQuery(name = "Operateur.Connectvm", query = "SELECT t FROM Operateur t WHERE t.meloper = :meloper AND t.password = :password")
    ,
    @NamedQuery(name = "Operateur.findByCodeoper", query = "SELECT o FROM Operateur o WHERE o.codeoper = :codeoper")
    ,
    @NamedQuery(name = "Operateur.findBynotpfcol", query = "SELECT o FROM Operateur o WHERE o.idgroupe.idgroupe<>'COLL' and o.idgroupe.idgroupe<>'PFOC'")
    ,
//    @NamedQuery(name = "Operateur.findByFonction", query = "SELECT o FROM Operateur o WHERE o.fonction = :fonction")
//    ,
    @NamedQuery(name = "Operateur.findByPassword", query = "SELECT o FROM Operateur o WHERE o.password = :password")
    ,
//    @NamedQuery(name = "Operateur.findByDatecre", query = "SELECT o FROM Operateur o WHERE o.datecre = :datecre")
//    ,
//    @NamedQuery(name = "Operateur.findByDatemaj", query = "SELECT o FROM Operateur o WHERE o.datemaj = :datemaj")
//    ,
    @NamedQuery(name = "Operateur.findByMeloper", query = "SELECT o FROM Operateur o WHERE o.meloper = :meloper")
    ,
    @NamedQuery(name = "Operateur.findByNumero", query = "SELECT o FROM Operateur o WHERE o.numero = :numero")
    ,
    @NamedQuery(name = "Operateur.findByNationalite", query = "SELECT o FROM Operateur o WHERE o.nationalite = :nationalite")
 ,
    @NamedQuery(name = "Operateur.findCollByPfoc", query = "SELECT o FROM Operateur o WHERE o.idPfoc = :idPfoc")
        ,
//    ,
    @NamedQuery(name = "Operateur.findByispro", query = "SELECT o FROM Operateur o WHERE o.idgroupe.idgroupe = 'PRO'")

    // LES REQUETES 
////        
////        ,
////    @NamedQuery(name = "Operateur.nation_BF", query = "SELECT COUNT(o) FROM Operateur o WHERE o.nationalite = 'BF' ")
////    ,
////    
////    @NamedQuery(name = "Operateur.nation_CI", query = "SELECT COUNT(o) FROM Operateur o WHERE o.nationalite = 'CI' ")
////    ,
////    @NamedQuery(name = "Operateur.nation_BN", query = "SELECT COUNT(o) FROM Operateur o WHERE o.nationalite = 'BN' ")
////    ,
////    @NamedQuery(name = "Operateur.nation_GH", query = "SELECT COUNT(o) FROM Operateur o WHERE o.nationalite = 'GH' ")
////    ,
////    @NamedQuery(name = "Operateur.nation_ML", query = "SELECT COUNT(o) FROM Operateur o WHERE o.nationalite = 'ML' ")
////    ,
////    @NamedQuery(name = "Operateur.nation_NE", query = "SELECT COUNT(o) FROM Operateur o WHERE o.nationalite = 'NE' ")
////    ,
////    @NamedQuery(name = "Operateur.nation_SN", query = "SELECT COUNT(o) FROM Operateur o WHERE o.nationalite = 'SN' ")
////    ,
////    @NamedQuery(name = "Operateur.nation_NG", query = "SELECT COUNT(o) FROM Operateur o WHERE o.nationalite = 'NG' ")
////    ,
////    @NamedQuery(name = "Operateur.nation_TG", query = "SELECT COUNT(o) FROM Operateur o WHERE o.nationalite = 'TG' ")
////    ,
////    @NamedQuery(name = "Operateur.nation_TCD", query = "SELECT COUNT(o) FROM Operateur o WHERE o.nationalite = 'TCD' ")
    ,
    
     // FIN DES REQUETES 
    @NamedQuery(name = "Operateur.findBySexe", query = "SELECT o FROM Operateur o WHERE o.sexe = :sexe")
    ,
    @NamedQuery(name = "Operateur.findByDateNaissance", query = "SELECT o FROM Operateur o WHERE o.dateNaissance = :dateNaissance")
    ,
    @NamedQuery(name = "Operateur.findByPhoto", query = "SELECT o FROM Operateur o WHERE o.photo = :photo")
    ,
    @NamedQuery(name = "Operateur.findByIdAssoc", query = "SELECT o FROM Operateur o WHERE o.association = :association")})
public class Operateur implements Serializable {

    
   
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcre")
//    private Collection<Association> associationCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opmaj")
//    private Collection<Association> associationCollection1;

    
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 20)
//    @Column(name = "OPCRE")
//    private String opcre;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 20)
//    @Column(name = "OPMAJ")
//    private String opmaj;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
//    @Column(name = "nationalite")
//    private String nationalite;
//    @Size(max = 20)
//    @Column(name = "id_assoc")
//    private String idAssoc;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 20)
//    @Column(name = "ID_ENT")
//    private String idEnt;
//    @ManyToMany(mappedBy = "operateurCollection")
//    private Collection<Acteur> acteurCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODEOPER")
    private String codeoper;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMOPER")
    private String nomoper;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FONCTION")
    private String fonction;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATECRE")
    @Temporal(TemporalType.DATE)
    private Date datecre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEMAJ")
    @Temporal(TemporalType.DATE)
    private Date datemaj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "meloper")
    private String meloper;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "numero")
    private String numero;

    @JoinColumn(name = "nationalite", referencedColumnName = "idPays")
    @ManyToOne(optional = false)
    private Pays nationalite;

//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
//    @Column(name = "nationalite")
//    private Pays nationalite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "Sexe")
    private String sexe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateNaissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "photo")
    private String photo;
    
    @JoinColumn(name = "id_assoc", referencedColumnName = "id_assoc")
    @ManyToOne(optional = false)
    private Association association;
//    
//    @Size(max = 20)
//    @Column(name = "id_assoc")
//    private String idAssoc;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opmaj")
    private List<Collecte> collecteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcre")
    private List<Collecte> collecteList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opmaj")
    private List<Offre> offreList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcre")
    private List<Offre> offreList1;
    @JoinColumn(name = "idgroupe", referencedColumnName = "idgroupe")
    @ManyToOne(optional = false)
    private Groupe idgroupe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcre")
    private List<Operateur> operateurList;
    @JoinColumn(name = "OPCRE", referencedColumnName = "CODEOPER")
    @ManyToOne(optional = false)
    private Operateur opcre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opmaj")
    private List<Operateur> operateurList1;
    @JoinColumn(name = "OPMAJ", referencedColumnName = "CODEOPER")
    @ManyToOne(optional = false)
    private Operateur opmaj;

    
        @Basic(optional = false)
   
    @Size(min = 1, max = 20)
    @Column(name = "id_pfoc")
    private String idPfoc;
    @ManyToMany(mappedBy = "operateurList")
    private List<PointCollecte> pointCollecteList;
    
    @JoinTable(name = "operateur_actor", joinColumns = {
        @JoinColumn(name = "codeoper", referencedColumnName = "CODEOPER")}, inverseJoinColumns = {
        @JoinColumn(name = "actorID", referencedColumnName = "id_acteur")})
    @ManyToMany
    private List<Acteur> acteurList;
    @JoinTable(name = "operateur_assoc", joinColumns = {
        @JoinColumn(name = "codeoper", referencedColumnName = "CODEOPER")}, inverseJoinColumns = {
        @JoinColumn(name = "assocID", referencedColumnName = "id_assoc")})
    @ManyToMany
    private List<Association> associationList;
    
      
    
    @JoinColumn(name = "ID_ENT", referencedColumnName = "ID_ENT")
    @ManyToOne(optional = false)
    private Entreprise idEnt;

    public Operateur() {
    }

    public Operateur(String codeoper) {
        this.codeoper = codeoper;
    }

    public Operateur(String codeoper, String nomoper, String fonction, String password, Date datecre, Date datemaj, String meloper, String numero, Pays nationalite, String sexe, Date dateNaissance, String photo) {
        this.codeoper = codeoper;
        this.nomoper = nomoper;
        this.fonction = fonction;
        this.password = password;
        this.datecre = datecre;
        this.datemaj = datemaj;
        this.meloper = meloper;
        this.numero = numero;
        this.nationalite = nationalite;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.photo = photo;
    }

    public String getCodeoper() {
        return codeoper;
    }

    public void setCodeoper(String codeoper) {
        this.codeoper = codeoper;
    }

    public String getNomoper() {
        return nomoper;
    }

    public void setNomoper(String nomoper) {
        this.nomoper = nomoper;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getMeloper() {
        return meloper;
    }

    public void setMeloper(String meloper) {
        this.meloper = meloper;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Pays getNationalite() {
        return nationalite;
    }

    public void setNationalite(Pays nationalite) {
        this.nationalite = nationalite;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
//
//    public String getIdAssoc() {
//        return idAssoc;
//    }
//
//    public void setIdAssoc(String idAssoc) {
//        this.idAssoc = idAssoc;
//    }

    @XmlTransient
    @JsonIgnore
    public List<Collecte> getCollecteList() {
        return collecteList;
    }

    public void setCollecteList(List<Collecte> collecteList) {
        this.collecteList = collecteList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Collecte> getCollecteList1() {
        return collecteList1;
    }

    public void setCollecteList1(List<Collecte> collecteList1) {
        this.collecteList1 = collecteList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Offre> getOffreList() {
        return offreList;
    }

    public void setOffreList(List<Offre> offreList) {
        this.offreList = offreList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Offre> getOffreList1() {
        return offreList1;
    }

    public void setOffreList1(List<Offre> offreList1) {
        this.offreList1 = offreList1;
    }

    public Groupe getIdgroupe() {
        return idgroupe;
    }

    public void setIdgroupe(Groupe idgroupe) {
        this.idgroupe = idgroupe;
    }

    @XmlTransient
    @JsonIgnore
    public List<Operateur> getOperateurList() {
        return operateurList;
    }

    public void setOperateurList(List<Operateur> operateurList) {
        this.operateurList = operateurList;
    }

    public Operateur getOpcre() {
        return opcre;
    }

    public void setOpcre(Operateur opcre) {
        this.opcre = opcre;
    }

    @XmlTransient
    @JsonIgnore
    public List<Operateur> getOperateurList1() {
        return operateurList1;
    }

    public void setOperateurList1(List<Operateur> operateurList1) {
        this.operateurList1 = operateurList1;
    }

    public Operateur getOpmaj() {
        return opmaj;
    }

    public void setOpmaj(Operateur opmaj) {
        this.opmaj = opmaj;
    }
    
    
        /**
     * @return the idPfoc
     */
    public String getIdPfoc() {
        return idPfoc;
    }

    /**
     * @param idPfoc the idPfoc to set
     */
    public void setIdPfoc(String idPfoc) {
        this.idPfoc = idPfoc;
    }

    /**
     * @return the pointCollecteList
     */
    public List<PointCollecte> getPointCollecteList() {
        return pointCollecteList;
    }

    /**
     * @param pointCollecteList the pointCollecteList to set
     */
    public void setPointCollecteList(List<PointCollecte> pointCollecteList) {
        this.pointCollecteList = pointCollecteList;
    }

    /**
     * @return the acteurList
     */
    public List<Acteur> getActeurList() {
        return acteurList;
    }

    /**
     * @param acteurList the acteurList to set
     */
    public void setActeurList(List<Acteur> acteurList) {
        this.acteurList = acteurList;
    }

    /**
     * @return the associationList
     */
    public List<Association> getAssociationList() {
        return associationList;
    }

    /**
     * @param associationList the associationList to set
     */
    public void setAssociationList(List<Association> associationList) {
        this.associationList = associationList;
    }

    /**
     * @return the idEnt
     */
    public Entreprise getIdEnt() {
        return idEnt;
    }

    /**
     * @param idEnt the idEnt to set
     */
    public void setIdEnt(Entreprise idEnt) {
        this.idEnt = idEnt;
    }
    /**
     * @return the association
     */
    public Association getAssociation() {
        return association;
    }

    /**
     * @param association the association to set
     */
    public void setAssociation(Association association) {
        this.association = association;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeoper != null ? codeoper.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operateur)) {
            return false;
        }
        Operateur other = (Operateur) object;
        if ((this.codeoper == null && other.codeoper != null) || (this.codeoper != null && !this.codeoper.equals(other.codeoper))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Operateur[ codeoper=" + codeoper + " ]";
    }

}
