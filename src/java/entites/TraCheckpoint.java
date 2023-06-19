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
@Table(name = "tra_checkpoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TraCheckpoint.findAll", query = "SELECT t FROM TraCheckpoint t")
    ,
    @NamedQuery(name = "TraCheckpoint.findByIdCheckpt", query = "SELECT t FROM TraCheckpoint t WHERE t.idCheckpt = :idCheckpt")
    ,
    @NamedQuery(name = "TraCheckpoint.dernordre", query = "SELECT COUNT(i) FROM TraCheckpoint i WHERE i.idCorridor = :idCorridor")
    ,
    @NamedQuery(name = "TraCheckpoint.findByNomckp", query = "SELECT t FROM TraCheckpoint t WHERE t.nomckp = :nomckp")
    ,
    @NamedQuery(name = "TraCheckpoint.findByDistance", query = "SELECT t FROM TraCheckpoint t WHERE t.distance = :distance")
    ,
    @NamedQuery(name = "TraCheckpoint.findByIdCorridor", query = "SELECT t FROM TraCheckpoint t WHERE t.idCorridor = :idCorridor")
    ,
    @NamedQuery(name = "TraCheckpoint.findByIdPays", query = "SELECT t FROM TraCheckpoint t WHERE t.idPays = :idPays")})
public class TraCheckpoint implements Serializable, Comparable<TraCheckpoint> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "id_checkpt")
    private String idCheckpt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nomckp")
    private String nomckp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "distance")
    private int distance;

    @JoinColumn(name = "id_corridor", referencedColumnName = "id_corridor")
    @ManyToOne(optional = false)
    private TraCorridor idCorridor;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PO")
    private boolean po;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DO")
    private boolean do1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GE")
    private boolean ge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PH")
    private boolean ph;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SY")
    private boolean sy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SA")
    private boolean sa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AU")
    private boolean au;

//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 5)
//    @Column(name = "id_corridor")
//    private String idCorridor;
//    
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 5)
//    @Column(name = "id_pays")
//    private String idPays;
    @JoinColumn(name = "id_pays", referencedColumnName = "idPays")
    @ManyToOne(optional = false)
    private Pays idPays;

    @Basic(optional = false)
    @NotNull
    @Column(name = "coordx")
    private long coordx;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coordy")
    private long coordy;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "traCheckpoint")
//    private TraDatatraca traDatatraca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "traCheckpoint")
    private List<TraDatatraca> traDatatracaList;

    public TraCheckpoint() {
    }

    public TraCheckpoint(String idCheckpt) {
        this.idCheckpt = idCheckpt;
    }

    public TraCheckpoint(String idCheckpt, String nomckp, int distance, TraCorridor idCorridor, Pays idPays) {
        this.idCheckpt = idCheckpt;
        this.nomckp = nomckp;
        this.distance = distance;
        this.idCorridor = idCorridor;
        this.idPays = idPays;
    }

    public String getIdCheckpt() {
        return idCheckpt;
    }

    public void setIdCheckpt(String idCheckpt) {
        this.idCheckpt = idCheckpt;
    }

    public String getNomckp() {
        return nomckp;
    }

    public void setNomckp(String nomckp) {
        this.nomckp = nomckp;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public TraCorridor getIdCorridor() {
        return idCorridor;
    }

    public void setIdCorridor(TraCorridor idCorridor) {
        this.idCorridor = idCorridor;
    }

    public Pays getIdPays() {
        return idPays;
    }

    public void setIdPays(Pays idPays) {
        this.idPays = idPays;
    }

//    public TraDatatraca getTraDatatraca() {
//        return traDatatraca;
//    }
//
//    public void setTraDatatraca(TraDatatraca traDatatraca) {
//        this.traDatatraca = traDatatraca;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCheckpt != null ? idCheckpt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraCheckpoint)) {
            return false;
        }
        TraCheckpoint other = (TraCheckpoint) object;
        if ((this.idCheckpt == null && other.idCheckpt != null) || (this.idCheckpt != null && !this.idCheckpt.equals(other.idCheckpt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.TraCheckpoint[ idCheckpt=" + idCheckpt + " ]";
    }

    /**
     * @return the traDatatracaList
     */
    public List<TraDatatraca> getTraDatatracaList() {
        return traDatatracaList;
    }

    /**
     * @param traDatatracaList the traDatatracaList to set
     */
    public void setTraDatatracaList(List<TraDatatraca> traDatatracaList) {
        this.traDatatracaList = traDatatracaList;
    }

    /**
     * @return the coordx
     */
    public long getCoordx() {
        return coordx;
    }

    /**
     * @param coordx the coordx to set
     */
    public void setCoordx(long coordx) {
        this.coordx = coordx;
    }

    /**
     * @return the coordy
     */
    public long getCoordy() {
        return coordy;
    }

    /**
     * @param coordy the coordy to set
     */
    public void setCoordy(long coordy) {
        this.coordy = coordy;
    }

    @Override
    public int compareTo(TraCheckpoint other) {
        return Integer.compare(this.distance, other.distance);
    }

    /**
     * @return the po
     */
    public boolean isPo() {
        return po;
    }

    /**
     * @param po the po to set
     */
    public void setPo(boolean po) {
        this.po = po;
    }

    /**
     * @return the do1
     */
    public boolean isDo1() {
        return do1;
    }

    /**
     * @param do1 the do1 to set
     */
    public void setDo1(boolean do1) {
        this.do1 = do1;
    }

    /**
     * @return the ge
     */
    public boolean isGe() {
        return ge;
    }

    /**
     * @param ge the ge to set
     */
    public void setGe(boolean ge) {
        this.ge = ge;
    }

    /**
     * @return the ph
     */
    public boolean isPh() {
        return ph;
    }

    /**
     * @param ph the ph to set
     */
    public void setPh(boolean ph) {
        this.ph = ph;
    }

    /**
     * @return the sy
     */
    public boolean isSy() {
        return sy;
    }

    /**
     * @param sy the sy to set
     */
    public void setSy(boolean sy) {
        this.sy = sy;
    }

    /**
     * @return the sa
     */
    public boolean isSa() {
        return sa;
    }

    /**
     * @param sa the sa to set
     */
    public void setSa(boolean sa) {
        this.sa = sa;
    }

    /**
     * @return the au
     */
    public boolean isAu() {
        return au;
    }

    /**
     * @param au the au to set
     */
    public void setAu(boolean au) {
        this.au = au;
    }

}
