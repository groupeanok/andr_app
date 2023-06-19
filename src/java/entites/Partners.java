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
@Table(name = "partners")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partners.findAll", query = "SELECT p FROM Partners p")
    , @NamedQuery(name = "Partners.findByPartnerid", query = "SELECT p FROM Partners p WHERE p.partnerid = :partnerid")
    , @NamedQuery(name = "Partners.findByName", query = "SELECT p FROM Partners p WHERE p.name = :name")
    , @NamedQuery(name = "Partners.findByLink", query = "SELECT p FROM Partners p WHERE p.link = :link")
    , @NamedQuery(name = "Partners.findByLogo", query = "SELECT p FROM Partners p WHERE p.logo = :logo")
    , @NamedQuery(name = "Partners.findByLastupdate", query = "SELECT p FROM Partners p WHERE p.lastupdate = :lastupdate")})
public class Partners implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "partnerid")
    private String partnerid;
    @Size(max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 800)
    @Column(name = "link")
    private String link;
    @Size(max = 500)
    @Column(name = "logo")
    private String logo;
    @Size(max = 20)
    @Column(name = "lastupdate")
    private String lastupdate;

    public Partners() {
    }

    public Partners(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partnerid != null ? partnerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partners)) {
            return false;
        }
        Partners other = (Partners) object;
        if ((this.partnerid == null && other.partnerid != null) || (this.partnerid != null && !this.partnerid.equals(other.partnerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Partners[ partnerid=" + partnerid + " ]";
    }
    
}
