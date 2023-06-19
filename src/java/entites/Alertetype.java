/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "alertetype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alertetype.findAll", query = "SELECT a FROM Alertetype a")
    ,
    @NamedQuery(name = "Alertetype.findByCodetypealrt", query = "SELECT a FROM Alertetype a WHERE a.codetypealrt = :codetypealrt")
    ,
    @NamedQuery(name = "Alertetype.findByLitypealrt", query = "SELECT a FROM Alertetype a WHERE a.litypealrt = :litypealrt")
    ,
    @NamedQuery(name = "Alertetype.findByFrequence", query = "SELECT a FROM Alertetype a WHERE a.frequence = :frequence")
    ,
    @NamedQuery(name = "Alertetype.findByPrejour", query = "SELECT a FROM Alertetype a WHERE a.prejour = :prejour")
    ,
    @NamedQuery(name = "Alertetype.findByActif", query = "SELECT a FROM Alertetype a WHERE a.actif = :actif")
    ,
    @NamedQuery(name = "Alertetype.findBySms", query = "SELECT a FROM Alertetype a WHERE a.sms = :sms")
    ,
    @NamedQuery(name = "Alertetype.findByMel", query = "SELECT a FROM Alertetype a WHERE a.mel = :mel")})
public class Alertetype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "codetypealrt")
    private String codetypealrt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "Litypealrt")
    private String litypealrt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "frequence")
    private short frequence;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prejour")
    private short prejour;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actif")
    private boolean actif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sms")
    private boolean sms;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mel")
    private boolean mel;
    @ManyToMany(mappedBy = "alertetypeList")
    private List<Acteur> acteurList;

    public Alertetype() {
    }

    public Alertetype(String codetypealrt) {
        this.codetypealrt = codetypealrt;
    }

    public Alertetype(String codetypealrt, String litypealrt, short frequence, short prejour, boolean actif, boolean sms, boolean mel) {
        this.codetypealrt = codetypealrt;
        this.litypealrt = litypealrt;
        this.frequence = frequence;
        this.prejour = prejour;
        this.actif = actif;
        this.sms = sms;
        this.mel = mel;
    }

    public String getCodetypealrt() {
        return codetypealrt;
    }

    public void setCodetypealrt(String codetypealrt) {
        this.codetypealrt = codetypealrt;
    }

    public String getLitypealrt() {
        return litypealrt;
    }

    public void setLitypealrt(String litypealrt) {
        this.litypealrt = litypealrt;
    }

    public short getFrequence() {
        return frequence;
    }

    public void setFrequence(short frequence) {
        this.frequence = frequence;
    }

    public short getPrejour() {
        return prejour;
    }

    public void setPrejour(short prejour) {
        this.prejour = prejour;
    }

    public boolean getActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public boolean isSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }

    public boolean isMel() {
        return mel;
    }

    public void setMel(boolean mel) {
        this.mel = mel;
    }

    @XmlTransient
    @JsonIgnore
    public List<Acteur> getActeurList() {
        return acteurList;
    }

    public void setActeurList(List<Acteur> acteurList) {
        this.acteurList = acteurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codetypealrt != null ? codetypealrt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alertetype)) {
            return false;
        }
        Alertetype other = (Alertetype) object;
        if ((this.codetypealrt == null && other.codetypealrt != null) || (this.codetypealrt != null && !this.codetypealrt.equals(other.codetypealrt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Alertetype[ codetypealrt=" + codetypealrt + " ]";
    }

}
