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
@Table(name = "pays")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p")
    ,
    @NamedQuery(name = "Pays.findCurrency", query = "SELECT DISTINCT p.codedev FROM Pays p WHERE p.idPays IN ('BF', 'ML', 'NG','GH','NI','CV','TG','BN','GM','GB')")
    ,
    @NamedQuery(name = "Pays.findByCI", query = "SELECT p FROM Pays p WHERE p.dansCi = true")
    ,
    @NamedQuery(name = "Pays.findByIdPays", query = "SELECT p FROM Pays p WHERE p.idPays = :idPays")
    ,
    @NamedQuery(name = "Pays.findByNomPays", query = "SELECT p FROM Pays p WHERE p.nomPays = :nomPays")
    ,
    @NamedQuery(name = "Pays.findByCapitale", query = "SELECT p FROM Pays p WHERE p.capitale = :capitale")
    ,
    @NamedQuery(name = "Pays.findByIndicatif", query = "SELECT p FROM Pays p WHERE p.indicatif = :indicatif")
    ,
    @NamedQuery(name = "Pays.findByPopulation", query = "SELECT p FROM Pays p WHERE p.population = :population")
    ,
    @NamedQuery(name = "Pays.findBySuperficie", query = "SELECT p FROM Pays p WHERE p.superficie = :superficie")
        ,
    @NamedQuery(name = "Pays.findByDansCi2", query = "SELECT DISTINCT p.codedev FROM Pays p WHERE p.dansCi = true")
    ,
    @NamedQuery(name = "Pays.findByDansCi", query = "SELECT p FROM Pays p WHERE p.dansCi = :dansCi")})

public class Pays implements Serializable {

   

//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 5)
//    @Column(name = "codelang")
//    private String codelang;

  
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "idPays")
    private String idPays;
    @Size(max = 100)
    @Column(name = "nomPays")
    private String nomPays;
    @Size(max = 100)
    @Column(name = "capitale")
    private String capitale;
    @Column(name = "indicatif")
    private Integer indicatif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "population")
    private double population;
    @Basic(optional = false)
    @NotNull
    @Column(name = "superficie")
    private double superficie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dans_ci")
    private boolean dansCi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPays")
    private List<Collecte> collecteList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpays")
//    private List<Ville_1> villeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPays")
    private List<DaRegion> daRegionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nationalite")
    private List<Acteur> acteurList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nationalite")
    private List<Operateur> operateurList;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaysImma")
//    private List<TraCollecte_1> traCollecteList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNationalite")
//    private List<TraCollecte_1> traCollecteList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codepays")
    private List<Association> associationList;
    @JoinColumn(name = "codelang", referencedColumnName = "CODELANG")
    @ManyToOne(optional = false)
    private Langue codelang;
    @JoinColumn(name = "codedev", referencedColumnName = "codedev")
    @ManyToOne
    private Devise codedev;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPays")
    private List<PointCollecte> pointCollecteList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpays")
    private List<Ville> villeList;

    public Pays() {
    }

    public Pays(String idPays) {
        this.idPays = idPays;
    }

    public Pays(String idPays, double population, double superficie, boolean dansCi) {
        this.idPays = idPays;
        this.population = population;
        this.superficie = superficie;
        this.dansCi = dansCi;
    }

    public String getIdPays() {
        return idPays;
    }

    public void setIdPays(String idPays) {
        this.idPays = idPays;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public String getCapitale() {
        return capitale;
    }

    public void setCapitale(String capitale) {
        this.capitale = capitale;
    }

    public Integer getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(Integer indicatif) {
        this.indicatif = indicatif;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public boolean getDansCi() {
        return dansCi;
    }

    public void setDansCi(boolean dansCi) {
        this.dansCi = dansCi;
    }

    @XmlTransient
    @JsonIgnore
    public List<Collecte> getCollecteList() {
        return collecteList;
    }

    public void setCollecteList(List<Collecte> collecteList) {
        this.collecteList = collecteList;
    }

//    @XmlTransient
//    @JsonIgnore
//    public List<Ville_1> getVilleList() {
//        return villeList;
//    }
//
//    public void setVilleList(List<Ville_1> villeList) {
//        this.villeList = villeList;
//    }
    @XmlTransient
    @JsonIgnore
    public List<DaRegion> getDaRegionList() {
        return daRegionList;
    }

    public void setDaRegionList(List<DaRegion> daRegionList) {
        this.daRegionList = daRegionList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Acteur> getActeurList() {
        return acteurList;
    }

    public void setActeurList(List<Acteur> acteurList) {
        this.acteurList = acteurList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Operateur> getOperateurList() {
        return operateurList;
    }

    public void setOperateurList(List<Operateur> acteurList) {
        this.operateurList = acteurList;
    }

//    @XmlTransient
//    @JsonIgnore
//    public List<TraCollecte_1> getTraCollecteList() {
//        return traCollecteList;
//    }
//
//    public void setTraCollecteList(List<TraCollecte_1> traCollecteList) {
//        this.traCollecteList = traCollecteList;
//    }
//
//    @XmlTransient
//    @JsonIgnore
//    public List<TraCollecte_1> getTraCollecteList1() {
//        return traCollecteList1;
//    }
//
//    public void setTraCollecteList1(List<TraCollecte_1> traCollecteList1) {
//        this.traCollecteList1 = traCollecteList1;
//    }
    @XmlTransient
    @JsonIgnore
    public List<Association> getAssociationList() {
        return associationList;
    }

    public void setAssociationList(List<Association> associationList) {
        this.associationList = associationList;
    }

    public Langue getCodelang() {
        return codelang;
    }

    public void setCodelang(Langue codelang) {
        this.codelang = codelang;
    }

    public Devise getCodedev() {
        return codedev;
    }

    public void setCodedev(Devise codedev) {
        this.codedev = codedev;
    }

    @XmlTransient
    @JsonIgnore
    public List<PointCollecte> getPointCollecteList() {
        return pointCollecteList;
    }

    public void setPointCollecteList(List<PointCollecte> pointCollecteList) {
        this.pointCollecteList = pointCollecteList;
    }

//    
//       public Pays getNationalite() {
//        return nationalite;
//    }
//
//    public void setNationalite(Pays nationalite) {
//        this.nationalite = nationalite;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPays != null ? idPays.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.idPays == null && other.idPays != null) || (this.idPays != null && !this.idPays.equals(other.idPays))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Pays[ idPays=" + idPays + " ]";
    }

    /**
     * @return the villeList
     */
    public List<Ville> getVilleList() {
        return villeList;
    }

    /**
     * @param villeList the villeList to set
     */
    public void setVilleList(List<Ville> villeList) {
        this.villeList = villeList;
    }

//    public String getCodelang() {
//        return codelang;
//    }
//
//    public void setCodelang(String codelang) {
//        this.codelang = codelang;
//    }


}
