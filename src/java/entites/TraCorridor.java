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

/**
 *
 * @author user
 */
@Entity
@Table(name = "tra_corridor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TraCorridor.findAll", query = "SELECT t FROM TraCorridor t")
    ,
    @NamedQuery(name = "TraCorridor.dernordre", query = "SELECT COUNT(i) FROM TraCorridor i")
    ,
    @NamedQuery(name = "TraCorridor.pays", query = "SELECT t FROM TraCorridor t WHERE t.idVilleDep.idpays = :idPays or t.idVilleArr.idpays= :idPays")
    ,
    /*@NamedQuery(name = "TraCorridor.fndPays", query = "SELECT t. FROM TraCorridor t WHERE t.idVilleDep.idpays = :idPays or t.idVilleArr.idpays= :idPays"),*/

    @NamedQuery(name = "TraCorridor.findByIdCorridor", query = "SELECT t FROM TraCorridor t WHERE t.idCorridor = :idCorridor")
    ,

    @NamedQuery(name = "TraCorridor.findByLibelle", query = "SELECT t FROM TraCorridor t WHERE t.libelle = :libelle")
    ,
    @NamedQuery(name = "TraCorridor.findByDescription", query = "SELECT t FROM TraCorridor t WHERE t.description = :description")
    ,
    @NamedQuery(name = "TraCorridor.findByDistance", query = "SELECT t FROM TraCorridor t WHERE t.distance = :distance")})
public class TraCorridor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "id_corridor")
    private String idCorridor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "libelle")
    private String libelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "distance")
    private int distance;
    @JoinColumn(name = "id_ville_dep", referencedColumnName = "codeville")
    @ManyToOne(optional = false)
    private Ville idVilleDep;
    @JoinColumn(name = "id_ville_arr", referencedColumnName = "codeville")
    @ManyToOne(optional = false)
    private Ville idVilleArr;

    @JoinColumn(name = "id_produit", referencedColumnName = "id_produit")
    @ManyToOne(optional = false)
    private Produit idProduit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCorridor")
    private List<TraCheckpoint> traCheckpointList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCorridor")
    private List<TraCollecte> traCollecteList;

    public TraCorridor() {
    }

    public TraCorridor(String idCorridor) {
        this.idCorridor = idCorridor;
    }

    public TraCorridor(String idCorridor, String libelle, String description, int distance) {
        this.idCorridor = idCorridor;
        this.libelle = libelle;
        this.description = description;
        this.distance = distance;
    }

    public String getIdCorridor() {
        return idCorridor;
    }

    public void setIdCorridor(String idCorridor) {
        this.idCorridor = idCorridor;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Ville getIdVilleDep() {
        return idVilleDep;
    }

    public void setIdVilleDep(Ville idVilleDep) {
        this.idVilleDep = idVilleDep;
    }

    public Ville getIdVilleArr() {
        return idVilleArr;
    }

    public void setIdVilleArr(Ville idVilleArr) {
        this.idVilleArr = idVilleArr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCorridor != null ? idCorridor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraCorridor)) {
            return false;
        }
        TraCorridor other = (TraCorridor) object;
        if ((this.idCorridor == null && other.idCorridor != null) || (this.idCorridor != null && !this.idCorridor.equals(other.idCorridor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.TraCorridor[ idCorridor=" + idCorridor + " ]";
    }

    /**
     * @return the traCollecteList
     */
    public List<TraCollecte> getTraCollecteList() {
        return traCollecteList;
    }

    /**
     * @param traCollecteList the traCollecteList to set
     */
    public void setTraCollecteList(List<TraCollecte> traCollecteList) {
        this.traCollecteList = traCollecteList;
    }

    /**
     * @return the idProduit
     */
    public Produit getIdProduit() {
        return idProduit;
    }

    /**
     * @param idProduit the idProduit to set
     */
    public void setIdProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

//    /**
//     * @return the codedev
//     */
//    public Devise getCodedev() {
//        return codedev;
//    }
//
//    /**
//     * @param codedev the codedev to set
//     */
//    public void setCodedev(Devise codedev) {
//        this.codedev = codedev;
//    }
    /**
     * @return the traCheckpointList
     */
    public List<TraCheckpoint> getTraCheckpointList() {
        return traCheckpointList;
    }

    /**
     * @param traCheckpointList the traCheckpointList to set
     */
    public void setTraCheckpointList(List<TraCheckpoint> traCheckpointList) {
        this.traCheckpointList = traCheckpointList;
    }

}
