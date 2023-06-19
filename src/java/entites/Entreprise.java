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
@Table(name = "entreprise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entreprise.findAll", query = "SELECT e FROM Entreprise e")
    , @NamedQuery(name = "Entreprise.findByIdEnt", query = "SELECT e FROM Entreprise e WHERE e.idEnt = :idEnt")
    , @NamedQuery(name = "Entreprise.findByRaisonSoc", query = "SELECT e FROM Entreprise e WHERE e.raisonSoc = :raisonSoc")
    , @NamedQuery(name = "Entreprise.findByCapitale", query = "SELECT e FROM Entreprise e WHERE e.capitale = :capitale")
    , @NamedQuery(name = "Entreprise.findByRccm", query = "SELECT e FROM Entreprise e WHERE e.rccm = :rccm")
    , @NamedQuery(name = "Entreprise.findByIfu", query = "SELECT e FROM Entreprise e WHERE e.ifu = :ifu")
    , @NamedQuery(name = "Entreprise.findByCnss", query = "SELECT e FROM Entreprise e WHERE e.cnss = :cnss")
    , @NamedQuery(name = "Entreprise.findByTelephone", query = "SELECT e FROM Entreprise e WHERE e.telephone = :telephone")
    , @NamedQuery(name = "Entreprise.findByTelMobile", query = "SELECT e FROM Entreprise e WHERE e.telMobile = :telMobile")
    , @NamedQuery(name = "Entreprise.findByFax", query = "SELECT e FROM Entreprise e WHERE e.fax = :fax")
    , @NamedQuery(name = "Entreprise.findByEmail", query = "SELECT e FROM Entreprise e WHERE e.email = :email")
    , @NamedQuery(name = "Entreprise.findBySiteweb", query = "SELECT e FROM Entreprise e WHERE e.siteweb = :siteweb")
    , @NamedQuery(name = "Entreprise.findByResponsable", query = "SELECT e FROM Entreprise e WHERE e.responsable = :responsable")
    , @NamedQuery(name = "Entreprise.findByLogo", query = "SELECT e FROM Entreprise e WHERE e.logo = :logo")
    , @NamedQuery(name = "Entreprise.findByCommentaire", query = "SELECT e FROM Entreprise e WHERE e.commentaire = :commentaire")})
public class Entreprise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ID_ENT")
    private String idEnt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "Raison_Soc")
    private String raisonSoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Capitale")
    private long capitale;
    @Size(max = 20)
    @Column(name = "rccm")
    private String rccm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ifu")
    private String ifu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cnss")
    private String cnss;
    @Size(max = 20)
    @Column(name = "telephone")
    private String telephone;
    @Size(max = 30)
    @Column(name = "tel_mobile")
    private String telMobile;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "fax")
    private String fax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "siteweb")
    private String siteweb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "responsable")
    private String responsable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "logo")
    private String logo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "commentaire")
    private String commentaire;
//    @JoinColumn(name = "id_FormeJ", referencedColumnName = "id_FormeJ")
//    @ManyToOne(optional = false)
//    private FormeJur idFormeJ;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entreprise")
//    private List<DrtEntre> drtEntreList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEnt")
//    private List<Operateur> operateurList;

    public Entreprise() {
    }

    public Entreprise(String idEnt) {
        this.idEnt = idEnt;
    }

    public Entreprise(String idEnt, String raisonSoc, long capitale, String ifu, String cnss, String fax, String email, String siteweb, String responsable, String logo, String commentaire) {
        this.idEnt = idEnt;
        this.raisonSoc = raisonSoc;
        this.capitale = capitale;
        this.ifu = ifu;
        this.cnss = cnss;
        this.fax = fax;
        this.email = email;
        this.siteweb = siteweb;
        this.responsable = responsable;
        this.logo = logo;
        this.commentaire = commentaire;
    }

    public String getIdEnt() {
        return idEnt;
    }

    public void setIdEnt(String idEnt) {
        this.idEnt = idEnt;
    }

    public String getRaisonSoc() {
        return raisonSoc;
    }

    public void setRaisonSoc(String raisonSoc) {
        this.raisonSoc = raisonSoc;
    }

    public long getCapitale() {
        return capitale;
    }

    public void setCapitale(long capitale) {
        this.capitale = capitale;
    }

    public String getRccm() {
        return rccm;
    }

    public void setRccm(String rccm) {
        this.rccm = rccm;
    }

    public String getIfu() {
        return ifu;
    }

    public void setIfu(String ifu) {
        this.ifu = ifu;
    }

    public String getCnss() {
        return cnss;
    }

    public void setCnss(String cnss) {
        this.cnss = cnss;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelMobile() {
        return telMobile;
    }

    public void setTelMobile(String telMobile) {
        this.telMobile = telMobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
//
//    public FormeJur getIdFormeJ() {
//        return idFormeJ;
//    }
//
//    public void setIdFormeJ(FormeJur idFormeJ) {
//        this.idFormeJ = idFormeJ;
//    }
//
//    @XmlTransient
//    @JsonIgnore
//    public List<DrtEntre> getDrtEntreList() {
//        return drtEntreList;
//    }
//
//    public void setDrtEntreList(List<DrtEntre> drtEntreList) {
//        this.drtEntreList = drtEntreList;
//    }
//
//    @XmlTransient
//    @JsonIgnore
//    public List<Operateur> getOperateurList() {
//        return operateurList;
//    }
//
//    public void setOperateurList(List<Operateur> operateurList) {
//        this.operateurList = operateurList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnt != null ? idEnt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entreprise)) {
            return false;
        }
        Entreprise other = (Entreprise) object;
        if ((this.idEnt == null && other.idEnt != null) || (this.idEnt != null && !this.idEnt.equals(other.idEnt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Entreprise[ idEnt=" + idEnt + " ]";
    }

}
