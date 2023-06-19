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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "point_collecte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PointCollecte.findAll", query = "SELECT p FROM PointCollecte p")
    ,
    @NamedQuery(name = "PointCollecte.dernordre", query = "SELECT COUNT(i) FROM PointCollecte i WHERE i.idPays = :idPays")
    ,
    @NamedQuery(name = "PointCollecte.findByIdPtcollecte", query = "SELECT p FROM PointCollecte p WHERE p.idPtcollecte = :idPtcollecte")
    ,
    @NamedQuery(name = "PointCollecte.findByLibPtcollecte", query = "SELECT p FROM PointCollecte p WHERE p.libPtcollecte = :libPtcollecte")
    ,
    @NamedQuery(name = "PointCollecte.findByCoordx", query = "SELECT p FROM PointCollecte p WHERE p.coordx = :coordx")
    ,
    @NamedQuery(name = "PointCollecte.findByCoordy", query = "SELECT p FROM PointCollecte p WHERE p.coordy = :coordy")})
public class PointCollecte implements Serializable {

//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 10)
//    @Column(name = "idPays")
//    private String idPays;
//    @Size(max = 50)
//    @Column(name = "posteid")
//    private String posteid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ptc")
    private boolean ptc;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "NaturePointCollecte")
    private String naturePointCollecte;

    /**
     * @return the posteid
     */
    public PosteDouane getPosteid() {
        return posteid;
    }

    /**
     * @param posteid the posteid to set
     */
    public void setPosteid(PosteDouane posteid) {
        this.posteid = posteid;
    }

    /**
     * @return the operateurList
     */
    public List<Operateur> getOperateurList() {
        return operateurList;
    }

    /**
     * @param operateurList the operateurList to set
     */
    public void setOperateurList(List<Operateur> operateurList) {
        this.operateurList = operateurList;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "id_ptcollecte")
    private String idPtcollecte;
    @Size(max = 150)
    @Column(name = "lib_ptcollecte")
    private String libPtcollecte;
    @Column(name = "coordx")
    private Float coordx;
    @Column(name = "coordy")
    private Float coordy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPtcollecte")
    private List<Collecte> collecteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPtcharge")
    private List<Collecte> collecteList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPtdecharge")
    private List<Collecte> collecteList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPtcollecte")
    private List<OperateurDetail> operateurDetailList;
    @JoinColumn(name = "idPays", referencedColumnName = "idPays")
    @ManyToOne(optional = false)
    private Pays idPays;
    @JoinColumn(name = "posteid", referencedColumnName = "posteid")
    @ManyToOne
    private PosteDouane posteid;

    @JoinTable(name = "operateur_detail", joinColumns = {
        @JoinColumn(name = "id_ptcollecte", referencedColumnName = "id_ptcollecte")}, inverseJoinColumns = {
        @JoinColumn(name = "codeoper", referencedColumnName = "CODEOPER")})
    @ManyToMany
    private List<Operateur> operateurList;

    public PointCollecte() {
    }

    public PointCollecte(String idPtcollecte) {
        this.idPtcollecte = idPtcollecte;
    }

    public String getIdPtcollecte() {
        return idPtcollecte;
    }

    public void setIdPtcollecte(String idPtcollecte) {
        this.idPtcollecte = idPtcollecte;
    }

    public String getLibPtcollecte() {
        return libPtcollecte;
    }

    public void setLibPtcollecte(String libPtcollecte) {
        this.libPtcollecte = libPtcollecte;
    }

    public Float getCoordx() {
        return coordx;
    }

    public void setCoordx(Float coordx) {
        this.coordx = coordx;
    }

    public Float getCoordy() {
        return coordy;
    }

    public void setCoordy(Float coordy) {
        this.coordy = coordy;
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
    public List<Collecte> getCollecteList1() {
        return collecteList1;
    }

    public void setCollecteList1(List<Collecte> collecteList1) {
        this.collecteList1 = collecteList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<Collecte> getCollecteList2() {
        return collecteList2;
    }

    public void setCollecteList2(List<Collecte> collecteList2) {
        this.collecteList2 = collecteList2;
    }

    @XmlTransient
    @JsonIgnore
    public List<OperateurDetail> getOperateurDetailList() {
        return operateurDetailList;
    }

    public void setOperateurDetailList(List<OperateurDetail> operateurDetailList) {
        this.operateurDetailList = operateurDetailList;
    }

    public Pays getIdPays() {
        return idPays;
    }

    public void setIdPays(Pays idPays) {
        this.idPays = idPays;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPtcollecte != null ? idPtcollecte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PointCollecte)) {
            return false;
        }
        PointCollecte other = (PointCollecte) object;
        if ((this.idPtcollecte == null && other.idPtcollecte != null) || (this.idPtcollecte != null && !this.idPtcollecte.equals(other.idPtcollecte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.PointCollecte[ idPtcollecte=" + idPtcollecte + " ]";
    }

    public String getNaturePointCollecte() {
        return naturePointCollecte;
    }

    public void setNaturePointCollecte(String naturePointCollecte) {
        this.naturePointCollecte = naturePointCollecte;
    }

//    public String getIdPays() {
//        return idPays;
//    }
//
//    public void setIdPays(String idPays) {
//        this.idPays = idPays;
//    }
//
//    public String getPosteid() {
//        return posteid;
//    }

//    public void setPosteid(String posteid) {
//        this.posteid = posteid;
//    }

    public boolean getPtc() {
        return ptc;
    }

    public void setPtc(boolean ptc) {
        this.ptc = ptc;
    }

}
