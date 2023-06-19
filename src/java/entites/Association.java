/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "association")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Association.findAll", query = "SELECT a FROM Association a")
    ,
    @NamedQuery(name = "Association.findByIdAssoc", query = "SELECT a FROM Association a WHERE a.idAssoc = :idAssoc")
    ,
    @NamedQuery(name = "Association.nbre", query = "SELECT count(g) FROM Association g where g.codepays =:codepays")
    ,
    @NamedQuery(name = "Association.findByCigle", query = "SELECT a FROM Association a WHERE a.cigle = :cigle")
    ,
    @NamedQuery(name = "Association.findByNomAssoc", query = "SELECT a FROM Association a WHERE a.nomAssoc = :nomAssoc")
    ,
    @NamedQuery(name = "Association.findByResponsable", query = "SELECT a FROM Association a WHERE a.responsable = :responsable")
    ,
    @NamedQuery(name = "Association.findByTelephone", query = "SELECT a FROM Association a WHERE a.telephone = :telephone")
    ,
    @NamedQuery(name = "Association.findByEmail", query = "SELECT a FROM Association a WHERE a.email = :email")})
public class Association implements Serializable {

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
    @JoinColumn(name = "OPCRE", referencedColumnName = "CODEOPER")
    @ManyToOne(optional = false)
    private Operateur opcre;
    @JoinColumn(name = "OPMAJ", referencedColumnName = "CODEOPER")
    @ManyToOne(optional = false)
    private Operateur opmaj;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "id_assoc")
    private String idAssoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cigle")
    private String cigle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nom_assoc")
    private String nomAssoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "responsable")
    private String responsable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telephone")
    private String telephone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAssoc")
    private List<Acteur> acteurList;
    @JoinColumn(name = "codepays", referencedColumnName = "idPays")
    @ManyToOne(optional = false)
    private Pays codepays;

    public Association() {
    }

    public Association(String idAssoc) {
        this.idAssoc = idAssoc;
    }

    public Association(String idAssoc, String cigle, String nomAssoc, String responsable, String telephone, String email) {
        this.idAssoc = idAssoc;
        this.cigle = cigle;
        this.nomAssoc = nomAssoc;
        this.responsable = responsable;
        this.telephone = telephone;
        this.email = email;
    }

    public String getIdAssoc() {
        return idAssoc;
    }

    public void setIdAssoc(String idAssoc) {
        this.idAssoc = idAssoc;
    }

    public String getCigle() {
        return cigle;
    }

    public void setCigle(String cigle) {
        this.cigle = cigle;
    }

    public String getNomAssoc() {
        return nomAssoc;
    }

    public void setNomAssoc(String nomAssoc) {
        this.nomAssoc = nomAssoc;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    @JsonIgnore
    public List<Acteur> getActeurList() {
        return acteurList;
    }

    public void setActeurList(List<Acteur> acteurList) {
        this.acteurList = acteurList;
    }

    public Pays getCodepays() {
        return codepays;
    }

    public void setCodepays(Pays codepays) {
        this.codepays = codepays;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAssoc != null ? idAssoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Association)) {
            return false;
        }
        Association other = (Association) object;
        if ((this.idAssoc == null && other.idAssoc != null) || (this.idAssoc != null && !this.idAssoc.equals(other.idAssoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Association[ idAssoc=" + idAssoc + " ]";
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

    public Operateur getOpcre() {
        return opcre;
    }

    public void setOpcre(Operateur opcre) {
        this.opcre = opcre;
    }

    public Operateur getOpmaj() {
        return opmaj;
    }

    public void setOpmaj(Operateur opmaj) {
        this.opmaj = opmaj;
    }

}
