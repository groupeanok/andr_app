/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "tra_datatraca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TraDatatraca.findAll", query = "SELECT t FROM TraDatatraca t")
    ,
    @NamedQuery(name = "TraDatatraca.findByIdCheckpt", query = "SELECT t FROM TraDatatraca t WHERE t.traDatatracaPK.idCheckpt = :idCheckpt")
    ,
    @NamedQuery(name = "TraDatatraca.findByIdStructure", query = "SELECT t FROM TraDatatraca t WHERE t.traDatatracaPK.idStructure = :idStructure")
    ,
      @NamedQuery(name = "TraDatatraca.findByInterval", query = "SELECT t FROM TraDatatraca t WHERE t.traCollecte.dateCollecte >=:datedeb and t.traCollecte.dateCollecte <=:datefin")
    ,
    
    @NamedQuery(name = "TraDatatraca.findByIdCollecte", query = "SELECT t FROM TraDatatraca t WHERE t.traDatatracaPK.idCollecte = :idCollecte")
    ,
    @NamedQuery(name = "TraDatatraca.findCpByIdCollecte", query = "SELECT DISTINCT t.traCheckpoint FROM TraDatatraca t WHERE t.traDatatracaPK.idCollecte = :idCollecte")
    ,
    @NamedQuery(name = "TraDatatraca.findByMtPercu", query = "SELECT t FROM TraDatatraca t WHERE t.mtPercu = :mtPercu")
    ,
    @NamedQuery(name = "TraDatatraca.findByDureeEtape", query = "SELECT t FROM TraDatatraca t WHERE t.dureeEtape = :dureeEtape")})
public class TraDatatraca implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TraDatatracaPK traDatatracaPK;
    @Basic(optional = false)
    @Column(name = "mt_percu")
    private Long mtPercu;

    @Basic(optional = false)
    @Column(name = "duree_etape")
    private Long dureeEtape;

    @JoinColumn(name = "codedev", referencedColumnName = "codedev")
    @ManyToOne(optional = false)
    private Devise codedev;

    @JoinColumn(name = "id_collecte", referencedColumnName = "id_collecte", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TraCollecte traCollecte;

    @JoinColumn(name = "id_structure", referencedColumnName = "id_structure", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TraStructureCtrle traStructureCtrle;
    @JoinColumn(name = "id_checkpt", referencedColumnName = "id_checkpt", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TraCheckpoint traCheckpoint;

    public TraDatatraca() {
    }

    public TraDatatraca(TraDatatracaPK traDatatracaPK) {
        this.traDatatracaPK = traDatatracaPK;
    }

    public TraDatatraca(TraDatatracaPK traDatatracaPK, Long mtPercu, Long dureeEtape) {
        this.traDatatracaPK = traDatatracaPK;
        this.mtPercu = mtPercu;
        this.dureeEtape = dureeEtape;
    }

    public TraDatatraca(String idCheckpt, String idStructure, String idCollecte) {
        this.traDatatracaPK = new TraDatatracaPK(idCheckpt, idStructure, idCollecte);
    }

    public TraDatatracaPK getTraDatatracaPK() {
        return traDatatracaPK;
    }

    public void setTraDatatracaPK(TraDatatracaPK traDatatracaPK) {
        this.traDatatracaPK = traDatatracaPK;
    }

    public Long getMtPercu() {
        return mtPercu;
    }

    public void setMtPercu(Long mtPercu) {
        this.mtPercu = mtPercu;
    }

    public Long getDureeEtape() {
        return dureeEtape;
    }

    public void setDureeEtape(Long dureeEtape) {
        this.dureeEtape = dureeEtape;
    }

    public Devise getCodedev() {
        return codedev;
    }

    public void setCodedev(Devise codedev) {
        this.codedev = codedev;
    }

    public TraCollecte getTraCollecte() {
        return traCollecte;
    }

    public void setTraCollecte(TraCollecte traCollecte) {
        this.traCollecte = traCollecte;
    }

    public TraStructureCtrle getTraStructureCtrle() {
        return traStructureCtrle;
    }

    public void setTraStructureCtrle(TraStructureCtrle traStructureCtrle) {
        this.traStructureCtrle = traStructureCtrle;
    }

    public TraCheckpoint getTraCheckpoint() {
        return traCheckpoint;
    }

    public void setTraCheckpoint(TraCheckpoint traCheckpoint) {
        this.traCheckpoint = traCheckpoint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traDatatracaPK != null ? traDatatracaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraDatatraca)) {
            return false;
        }
        TraDatatraca other = (TraDatatraca) object;
        if ((this.traDatatracaPK == null && other.traDatatracaPK != null) || (this.traDatatracaPK != null && !this.traDatatracaPK.equals(other.traDatatracaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.TraDatatraca[ traDatatracaPK=" + traDatatracaPK + " ]";
    }

}
