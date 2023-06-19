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
@Table(name = "val_offre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValOffre.findAll", query = "SELECT v FROM ValOffre v")
    ,
    @NamedQuery(name = "ValOffre.findByIdPtfocal", query = "SELECT v FROM ValOffre v WHERE v.idPtfocal = :idPtfocal")
    ,
    @NamedQuery(name = "ValOffre.findByIdOffre", query = "SELECT v FROM ValOffre v WHERE v.idOffre = :idOffre")
    ,
    @NamedQuery(name = "ValOffre.findByDateVali", query = "SELECT v FROM ValOffre v WHERE v.dateVali = :dateVali")
    ,
    @NamedQuery(name = "ValOffre.findByObseVali", query = "SELECT v FROM ValOffre v WHERE v.obseVali = :obseVali")
    ,
    @NamedQuery(name = "ValOffre.findByTypeOp", query = "SELECT v FROM ValOffre v WHERE v.typeOp = :typeOp")})
public class ValOffre implements Serializable {

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
    @Column(name = "id_offre")
    private String idOffre;
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
    @JoinColumn(name = "id_offre", referencedColumnName = "id_offre", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Offre offre;

    public ValOffre() {
    }

    public ValOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public ValOffre(String idOffre, String idPtfocal, Date dateVali, String obseVali, String typeOp) {
        this.idOffre = idOffre;
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

    public String getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
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

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOffre != null ? idOffre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValOffre)) {
            return false;
        }
        ValOffre other = (ValOffre) object;
        if ((this.idOffre == null && other.idOffre != null) || (this.idOffre != null && !this.idOffre.equals(other.idOffre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.ValOffre[ idOffre=" + idOffre + " ]";
    }

}
