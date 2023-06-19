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
@Table(name = "offreconclus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offreconclus.findAll", query = "SELECT o FROM Offreconclus o")
    ,
    @NamedQuery(name = "Offreconclus.findByIdOffre", query = "SELECT o FROM Offreconclus o WHERE o.idOffre = :idOffre")
    ,
    @NamedQuery(name = "Offreconclus.findByDateconc", query = "SELECT o FROM Offreconclus o WHERE o.dateconc = :dateconc")
    ,
    @NamedQuery(name = "Offreconclus.findByMtconc", query = "SELECT o FROM Offreconclus o WHERE o.mtconc = :mtconc")
    ,
    @NamedQuery(name = "Offreconclus.findByClause", query = "SELECT o FROM Offreconclus o WHERE o.clause = :clause")
    ,
    @NamedQuery(name = "Offreconclus.findByCommentaire", query = "SELECT o FROM Offreconclus o WHERE o.commentaire = :commentaire")})
public class Offreconclus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_offre")
    private String idOffre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateconc")
    @Temporal(TemporalType.DATE)
    private Date dateconc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mtconc")
    private int mtconc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "clause")
    private String clause;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "commentaire")
    private String commentaire;
    @JoinColumn(name = "id_offre", referencedColumnName = "id_offre", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Offre offre;

    public Offreconclus() {
    }

    public Offreconclus(String idOffre) {
        this.idOffre = idOffre;
    }

    public Offreconclus(String idOffre, Date dateconc, int mtconc, String clause, String commentaire) {
        this.idOffre = idOffre;
        this.dateconc = dateconc;
        this.mtconc = mtconc;
        this.clause = clause;
        this.commentaire = commentaire;
    }

    public String getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public Date getDateconc() {
        return dateconc;
    }

    public void setDateconc(Date dateconc) {
        this.dateconc = dateconc;
    }

    public int getMtconc() {
        return mtconc;
    }

    public void setMtconc(int mtconc) {
        this.mtconc = mtconc;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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
        if (!(object instanceof Offreconclus)) {
            return false;
        }
        Offreconclus other = (Offreconclus) object;
        if ((this.idOffre == null && other.idOffre != null) || (this.idOffre != null && !this.idOffre.equals(other.idOffre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Offreconclus[ idOffre=" + idOffre + " ]";
    }

}
