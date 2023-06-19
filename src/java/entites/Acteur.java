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
@Table(name = "acteur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acteur.findAll", query = "SELECT a FROM Acteur a")
    ,
    @NamedQuery(name = "Acteur.findByIdActeur", query = "SELECT a FROM Acteur a WHERE a.idActeur = :idActeur")
    ,
    @NamedQuery(name = "Acteur.findByIdPays", query = "SELECT g FROM Acteur g where g.nationalite=:nationalite")
    ,
    @NamedQuery(name = "Acteur.nbre", query = "SELECT count(g) FROM Acteur g where g.nationalite=:nationalite")
    ,
    @NamedQuery(name = "Acteur.findbypays", query = "SELECT a FROM Acteur a where a.nationalite=:nationalite")
   ,
    

    @NamedQuery(name = "Acteur.findByTelActeur", query = "SELECT a FROM Acteur a WHERE a.telActeur = :telActeur")
    ,
    @NamedQuery(name = "Acteur.findByEmailActeur", query = "SELECT a FROM Acteur a WHERE a.emailActeur = :emailActeur")
    ,
    @NamedQuery(name = "Acteur.findBySexe", query = "SELECT a FROM Acteur a WHERE a.sexe = :sexe")})
public class Acteur implements Serializable {

    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "sim_type")
    private String simType;

//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 5)
//    @Column(name = "nationalite")
//    private String nationalite;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 15)
//    @Column(name = "id_assoc")
//    private String idAssoc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "coordx")
    private float coordx;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coordy")
    private float coordy;
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
    
    @JoinColumn(name = "opmaj", referencedColumnName = "codeoper")
    @ManyToOne(optional = false)
    private Operateur opmaj;
    
    @JoinColumn(name = "opcre", referencedColumnName = "codeoper")
    @ManyToOne(optional = false)
    private Operateur opcre;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "id_acteur")
    private String idActeur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nom_acteur")
    private String nomActeur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "prenom_acteur")
    private String prenomActeur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tel_acteur")
    private String telActeur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email_acteur")
    private String emailActeur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "sexe")
    private String sexe;
    
        @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "phone3")
    private String phone3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "phone2")
    private String phone2;
    
     @JoinTable(name = "actorspecialite", joinColumns = {
        @JoinColumn(name = "id_acteur", referencedColumnName = "id_acteur")}, inverseJoinColumns = {
        @JoinColumn(name = "id_specialite", referencedColumnName = "id_specialite")})
        @ManyToMany
    private List<Specialite> specialiteList;
        
//    @JoinTable(name = "operateur_acto", joinColumns = {
//        @JoinColumn(name = "actorID", referencedColumnName = "id_acteur")}, inverseJoinColumns = {
//        @JoinColumn(name = "codeoper", referencedColumnName = "CODEOPER")})
//    @ManyToMany
//    private List<Operateur> operateurList;
    
    
    
//    @ManyToMany(mappedBy = "acteurList")
//    private List<Specialite> specialiteList;
    @JoinTable(name = "alertedrt", joinColumns = {
        @JoinColumn(name = "id_acteur", referencedColumnName = "id_acteur")}, inverseJoinColumns = {
        @JoinColumn(name = "codetypealrt", referencedColumnName = "codetypealrt")})
    @ManyToMany
    private List<Alertetype> alertetypeList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActeur")
    private List<Collecte> collecteList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActeur")
    private List<Offre> offreList;
    
    @JoinColumn(name = "id_assoc", referencedColumnName = "id_assoc")
    @ManyToOne(optional = false)    
    private Association idAssoc;
    
    @JoinColumn(name = "nationalite", referencedColumnName = "idPays")
    @ManyToOne(optional = false)
    private Pays nationalite;
    
    public Acteur() {
    }

    public Acteur(String idActeur) {
        this.idActeur = idActeur;
    }

    public Acteur(String idActeur, String nomActeur, String prenomActeur, String telActeur, String emailActeur, String sexe) {
        this.idActeur = idActeur;
        this.nomActeur = nomActeur;
        this.prenomActeur = prenomActeur;
        this.telActeur = telActeur;
        this.emailActeur = emailActeur;
        this.sexe = sexe;
    }

    public String getIdActeur() {
        return idActeur;
    }

    public void setIdActeur(String idActeur) {
        this.idActeur = idActeur;
    }

    public String getNomActeur() {
        return nomActeur;
    }

    public void setNomActeur(String nomActeur) {
        this.nomActeur = nomActeur;
    }

    public String getPrenomActeur() {
        return prenomActeur;
    }

    public void setPrenomActeur(String prenomActeur) {
        this.prenomActeur = prenomActeur;
    }

    public String getTelActeur() {
        return telActeur;
    }

    public void setTelActeur(String telActeur) {
        this.telActeur = telActeur;
    }

    public String getEmailActeur() {
        return emailActeur;
    }

    public void setEmailActeur(String emailActeur) {
        this.emailActeur = emailActeur;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @XmlTransient
    @JsonIgnore
    public List<Specialite> getSpecialiteList() {
        return specialiteList;
    }

    public void setSpecialiteList(List<Specialite> specialiteList) {
        this.specialiteList = specialiteList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Alertetype> getAlertetypeList() {
        return alertetypeList;
    }

    public void setAlertetypeList(List<Alertetype> alertetypeList) {
        this.alertetypeList = alertetypeList;
    }

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
    public List<Offre> getOffreList() {
        return offreList;
    }

    public void setOffreList(List<Offre> offreList) {
        this.offreList = offreList;
    }

    public Association getIdAssoc() {
        return idAssoc;
    }

    public void setIdAssoc(Association idAssoc) {
        this.idAssoc = idAssoc;
    }

    public Pays getNationalite() {
        return nationalite;
    }

    public void setNationalite(Pays nationalite) {
        this.nationalite = nationalite;
    }
    
    
        /**
     * @return the phone3
     */
    public String getPhone3() {
        return phone3;
    }

    /**
     * @param phone3 the phone3 to set
     */
    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    /**
     * @return the phone2
     */
    public String getPhone2() {
        return phone2;
    }

    /**
     * @param phone2 the phone2 to set
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActeur != null ? idActeur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acteur)) {
            return false;
        }
        Acteur other = (Acteur) object;
        if ((this.idActeur == null && other.idActeur != null) || (this.idActeur != null && !this.idActeur.equals(other.idActeur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Acteur[ idActeur=" + idActeur + " ]";
    }

//    public String getNationalite() {
//        return nationalite;
//    }
//
//    public void setNationalite(String nationalite) {
//        this.nationalite = nationalite;
//    }
//
//    public String getIdAssoc() {
//        return idAssoc;
//    }
//
//    public void setIdAssoc(String idAssoc) {
//        this.idAssoc = idAssoc;
//    }


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

    public String getSimType() {
        return simType;
    }

    public void setSimType(String simType) {
        this.simType = simType;
    }

}