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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "bulletins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bulletins.findAll", query = "SELECT b FROM Bulletins b")
    , @NamedQuery(name = "Bulletins.findByBulletinID", query = "SELECT b FROM Bulletins b WHERE b.bulletinID = :bulletinID")
    , @NamedQuery(name = "Bulletins.findByAnnee", query = "SELECT b FROM Bulletins b WHERE b.annee = :annee")
    , @NamedQuery(name = "Bulletins.findByMois", query = "SELECT b FROM Bulletins b WHERE b.mois = :mois")
    , @NamedQuery(name = "Bulletins.findByBulletinType", query = "SELECT b FROM Bulletins b WHERE b.bulletinType = :bulletinType AND b.annee = :annee")
    , @NamedQuery(name = "Bulletins.findByFichier", query = "SELECT b FROM Bulletins b WHERE b.fichier = :fichier")
    , @NamedQuery(name = "Bulletins.findByImage", query = "SELECT b FROM Bulletins b WHERE b.image = :image")
    , @NamedQuery(name = "Bulletins.findByLien", query = "SELECT b FROM Bulletins b WHERE b.lien = :lien")
    , @NamedQuery(name = "Bulletins.findByDatecre", query = "SELECT b FROM Bulletins b WHERE b.datecre = :datecre")
    , @NamedQuery(name = "Bulletins.findByDatemaj", query = "SELECT b FROM Bulletins b WHERE b.datemaj = :datemaj")
    , @NamedQuery(name = "Bulletins.findByResume", query = "SELECT b FROM Bulletins b WHERE b.resume = :resume")})
public class Bulletins implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "bulletin_ID")
    private String bulletinID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "annee")
    private String annee;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "mois")
    private String mois;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "bulletin_type")
    private String bulletinType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "fichier")
    private String fichier;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "lien")
    private String lien;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATECRE")
    @Temporal(TemporalType.DATE)
    private Date datecre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEMAJ")
    @Temporal(TemporalType.DATE)
    private Date datemaj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "resume")
    private String resume;
    
    
        @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "titre_en")
    private String titreen;
        
//    @JoinColumn(name = "OPMAJ", referencedColumnName = "CODEOPER")
//    @ManyToOne(optional = false)
//    private Operateur opmaj;
//    @JoinColumn(name = "OPCRE", referencedColumnName = "CODEOPER")
//    @ManyToOne(optional = false)
//    private Operateur opcre;

    public Bulletins() {
    }

    public Bulletins(String bulletinID) {
        this.bulletinID = bulletinID;
    }

    public Bulletins(String bulletinID, String annee, String mois, String bulletinType, String fichier, String image, String lien, Date datecre, Date datemaj, String resume) {
        this.bulletinID = bulletinID;
        this.annee = annee;
        this.mois = mois;
        this.bulletinType = bulletinType;
        this.fichier = fichier;
        this.image = image;
        this.lien = lien;
        this.datecre = datecre;
        this.datemaj = datemaj;
        this.resume = resume;
    }

    public String getBulletinID() {
        return bulletinID;
    }

    public void setBulletinID(String bulletinID) {
        this.bulletinID = bulletinID;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getBulletinType() {
        return bulletinType;
    }

    public void setBulletinType(String bulletinType) {
        this.bulletinType = bulletinType;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public Date getDatecre() {
        return datecre;
    }

    public void setDatecre(Date datecre) {
        this.datecre = datecre;
    }

    public Date getDatemaj() {
        return datemaj;
    }

    public void setDatemaj(Date datemaj) {
        this.datemaj = datemaj;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
    
        /**
     * @return the titreen
     */
    public String getTitreen() {
        return titreen;
    }

    /**
     * @param titreen the titreen to set
     */
    public void setTitreen(String titreen) {
        this.titreen = titreen;
    }


//    public Operateur getOpmaj() {
//        return opmaj;
//    }
//
//    public void setOpmaj(Operateur opmaj) {
//        this.opmaj = opmaj;
//    }
//
//    public Operateur getOpcre() {
//        return opcre;
//    }
//
//    public void setOpcre(Operateur opcre) {
//        this.opcre = opcre;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bulletinID != null ? bulletinID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bulletins)) {
            return false;
        }
        Bulletins other = (Bulletins) object;
        if ((this.bulletinID == null && other.bulletinID != null) || (this.bulletinID != null && !this.bulletinID.equals(other.bulletinID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Bulletins[ bulletinID=" + bulletinID + " ]";
    }
    
}
