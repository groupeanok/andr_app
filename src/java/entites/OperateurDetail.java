/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

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
@Table(name = "operateur_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OperateurDetail.findAll", query = "SELECT o FROM OperateurDetail o")
    ,
    @NamedQuery(name = "OperateurDetail.findByCodeoper", query = "SELECT o FROM OperateurDetail o WHERE o.codeoper = :codeoper")})
public class OperateurDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codeoper")
    private String codeoper;
    @JoinColumn(name = "id_ptcollecte", referencedColumnName = "id_ptcollecte")
    @ManyToOne(optional = false)
    private PointCollecte idPtcollecte;

    public OperateurDetail() {
    }

    public OperateurDetail(String codeoper) {
        this.codeoper = codeoper;
    }

    public String getCodeoper() {
        return codeoper;
    }

    public void setCodeoper(String codeoper) {
        this.codeoper = codeoper;
    }

    public PointCollecte getIdPtcollecte() {
        return idPtcollecte;
    }

    public void setIdPtcollecte(PointCollecte idPtcollecte) {
        this.idPtcollecte = idPtcollecte;
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
        if (!(object instanceof OperateurDetail)) {
            return false;
        }
        OperateurDetail other = (OperateurDetail) object;
        if ((this.codeoper == null && other.codeoper != null) || (this.codeoper != null && !this.codeoper.equals(other.codeoper))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.OperateurDetail[ codeoper=" + codeoper + " ]";
    }

}
