/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "val_collecte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValCollecte.findAll", query = "SELECT v FROM ValCollecte v")
    ,
    @NamedQuery(name = "ValCollecte.findByIdPtfocal", query = "SELECT v FROM ValCollecte v WHERE v.idPtfocal = :idPtfocal")
    ,
    @NamedQuery(name = "ValCollecte.findByIdCollecte", query = "SELECT v FROM ValCollecte v WHERE v.idCollecte = :idCollecte")
    ,
    @NamedQuery(name = "ValCollecte.findByDateVali", query = "SELECT v FROM ValCollecte v WHERE v.dateVali = :dateVali")
    ,
    @NamedQuery(name = "ValCollecte.findByObseVali", query = "SELECT v FROM ValCollecte v WHERE v.obseVali = :obseVali")
    ,
    @NamedQuery(name = "ValCollecte.findByTypeOp", query = "SELECT v FROM ValCollecte v WHERE v.typeOp = :typeOp")})

public class ValCollecte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_ptfocal")
    private String idPtfocal;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_collecte")
    private String idCollecte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_vali")
    @Temporal(TemporalType.DATE)
    private Date dateVali;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "obse_vali")
    private String obseVali;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "type_op")
    private String typeOp;
    @JoinColumn(name = "id_collecte", referencedColumnName = "id_collecte", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Collecte collecte;

    public ValCollecte() {
    }

    public ValCollecte(String idCollecte) {
        this.idCollecte = idCollecte;
    }

    public ValCollecte(String idCollecte, String idPtfocal, Date dateVali, String obseVali, String typeOp) {
        this.idCollecte = idCollecte;
        this.idPtfocal = idPtfocal;
        this.dateVali = dateVali;
        this.obseVali = obseVali;
        this.typeOp = typeOp;
    }

    public String getIdPtfocal() {
        return idPtfocal;
    }

    public void setIdPtfocal(String idPtfocal) {
        this.idPtfocal = idPtfocal;
    }

    public String getIdCollecte() {
        return idCollecte;
    }

    public void setIdCollecte(String idCollecte) {
        this.idCollecte = idCollecte;
    }

    public Date getDateVali() {
        return dateVali;
    }

    public void setDateVali(Date dateVali) {
        this.dateVali = dateVali;
    }

    public String getObseVali() {
        return obseVali;
    }

    public void setObseVali(String obseVali) {
        this.obseVali = obseVali;
    }

    public String getTypeOp() {
        return typeOp;
    }

    public void setTypeOp(String typeOp) {
        this.typeOp = typeOp;
    }

    public Collecte getCollecte() {
        return collecte;
    }

    public void setCollecte(Collecte collecte) {
        this.collecte = collecte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCollecte != null ? idCollecte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValCollecte)) {
            return false;
        }
        ValCollecte other = (ValCollecte) object;
        if ((this.idCollecte == null && other.idCollecte != null) || (this.idCollecte != null && !this.idCollecte.equals(other.idCollecte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.ValCollecte[ idCollecte=" + idCollecte + " ]";
    }

}
