/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import entites.Pays;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "ville")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ville.findAll", query = "SELECT v FROM Ville v")
    ,
    @NamedQuery(name = "Ville.dernordre", query = "SELECT COUNT(i) FROM Ville i WHERE i.idpays = :idPays")
    ,
    @NamedQuery(name = "Ville.findByCodeville", query = "SELECT v FROM Ville v WHERE v.codeville = :codeville")
    ,
    @NamedQuery(name = "Ville.findByNomVille", query = "SELECT v FROM Ville v WHERE v.nomVille = :nomVille")})
public class Ville implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "codeville")
    private String codeville;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom_ville")
    private String nomVille;
    @JoinColumn(name = "idpays", referencedColumnName = "idPays")
    @ManyToOne(optional = false)
    private Pays idpays;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVilleDest")
//    private List<TraCollecte> traCollecteList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVilleSrc")
//    private List<TraCollecte> traCollecteList1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVilleDep")
//    private List<TraCorridor> traCorridorList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVilleArr")
//    private List<TraCorridor> traCorridorList1;

    public Ville() {
    }

    public Ville(String codeville) {
        this.codeville = codeville;
    }

    public Ville(String codeville, String nomVille) {
        this.codeville = codeville;
        this.nomVille = nomVille;
    }

    public String getCodeville() {
        return codeville;
    }

    public void setCodeville(String codeville) {
        this.codeville = codeville;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public Pays getIdpays() {
        return idpays;
    }

    public void setIdpays(Pays idpays) {
        this.idpays = idpays;
    }

//    @XmlTransient
//    @JsonIgnore
//    public List<TraCollecte> getTraCollecteList() {
//        return traCollecteList;
//    }
//
//    public void setTraCollecteList(List<TraCollecte> traCollecteList) {
//        this.traCollecteList = traCollecteList;
//    }
//
//    @XmlTransient
//    @JsonIgnore
//    public List<TraCollecte> getTraCollecteList1() {
//        return traCollecteList1;
//    }
//
//    public void setTraCollecteList1(List<TraCollecte> traCollecteList1) {
//        this.traCollecteList1 = traCollecteList1;
//    }
//
//    @XmlTransient
//    @JsonIgnore
//    public List<TraCorridor> getTraCorridorList() {
//        return traCorridorList;
//    }
//
//    public void setTraCorridorList(List<TraCorridor> traCorridorList) {
//        this.traCorridorList = traCorridorList;
//    }
//
//    @XmlTransient
//    @JsonIgnore
//    public List<TraCorridor> getTraCorridorList1() {
//        return traCorridorList1;
//    }
//
//    public void setTraCorridorList1(List<TraCorridor> traCorridorList1) {
//        this.traCorridorList1 = traCorridorList1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeville != null ? codeville.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ville)) {
            return false;
        }
        Ville other = (Ville) object;
        if ((this.codeville == null && other.codeville != null) || (this.codeville != null && !this.codeville.equals(other.codeville))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Ville[ codeville=" + codeville + " ]";
    }

}
