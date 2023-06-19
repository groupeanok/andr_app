/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "poste_douane")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PosteDouane.findAll", query = "SELECT p FROM PosteDouane p")
    , @NamedQuery(name = "PosteDouane.findByPosteid", query = "SELECT p FROM PosteDouane p WHERE p.posteid = :posteid")
    , @NamedQuery(name = "PosteDouane.findByIdPays", query = "SELECT p FROM PosteDouane p WHERE p.idPays = :idPays")
    , @NamedQuery(name = "PosteDouane.findByPosteName", query = "SELECT p FROM PosteDouane p WHERE p.posteName = :posteName")
    , @NamedQuery(name = "PosteDouane.findByCoordx", query = "SELECT p FROM PosteDouane p WHERE p.coordx = :coordx")
    , @NamedQuery(name = "PosteDouane.findByCoordy", query = "SELECT p FROM PosteDouane p WHERE p.coordy = :coordy")})
public class PosteDouane implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "old_id")
    private String oldId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poste_juxt")
    private boolean posteJuxt;
    @Size(max = 10)
    @Column(name = "link_poste")
    private String linkPoste;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "posteid")
    private String posteid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "idPays")
    private String idPays;
    @Size(max = 150)
    @Column(name = "poste_name")
    private String posteName;
    @Column(name = "coordx")
    private Float coordx;
    @Column(name = "coordy")
    private Float coordy;
    @OneToMany(mappedBy = "posteid")
    private List<PointCollecte> pointCollecteList;

    public PosteDouane() {
    }

    public PosteDouane(String posteid) {
        this.posteid = posteid;
    }

    public PosteDouane(String posteid, String idPays) {
        this.posteid = posteid;
        this.idPays = idPays;
    }

    public String getPosteid() {
        return posteid;
    }

    public void setPosteid(String posteid) {
        this.posteid = posteid;
    }

    public String getIdPays() {
        return idPays;
    }

    public void setIdPays(String idPays) {
        this.idPays = idPays;
    }

    public String getPosteName() {
        return posteName;
    }

    public void setPosteName(String posteName) {
        this.posteName = posteName;
    }

    public Float getCoordx() {
        return coordx;
    }

    public void setCoordx(Float coordx) {
        this.coordx = coordx;
    }

    public Float getCoordy() {
        return coordy;
    }

    public void setCoordy(Float coordy) {
        this.coordy = coordy;
    }

    @XmlTransient
    @JsonIgnore
    public List<PointCollecte> getPointCollecteList() {
        return pointCollecteList;
    }

    public void setPointCollecteList(List<PointCollecte> pointCollecteList) {
        this.pointCollecteList = pointCollecteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (posteid != null ? posteid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosteDouane)) {
            return false;
        }
        PosteDouane other = (PosteDouane) object;
        if ((this.posteid == null && other.posteid != null) || (this.posteid != null && !this.posteid.equals(other.posteid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.PosteDouane[ posteid=" + posteid + " ]";
    }

    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    public boolean getPosteJuxt() {
        return posteJuxt;
    }

    public void setPosteJuxt(boolean posteJuxt) {
        this.posteJuxt = posteJuxt;
    }

    public String getLinkPoste() {
        return linkPoste;
    }

    public void setLinkPoste(String linkPoste) {
        this.linkPoste = linkPoste;
    }
    
}
