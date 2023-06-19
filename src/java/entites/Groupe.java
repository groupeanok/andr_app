/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "groupe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g")
    ,
    @NamedQuery(name = "Groupe.findgrp", query = "SELECT g FROM Groupe g WHERE g.idgroupe not in ('PFOC','COLL')")
    ,
    @NamedQuery(name = "Groupe.findnotpro", query = "SELECT g FROM Groupe g WHERE g.idgroupe not in ('PRO')")
    ,
    @NamedQuery(name = "Groupe.findByIdgroupe", query = "SELECT g FROM Groupe g WHERE g.idgroupe = :idgroupe")
    ,
    @NamedQuery(name = "Groupe.findByGroupe", query = "SELECT g FROM Groupe g WHERE g.groupe = :groupe")})
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "idgroupe")
    private String idgroupe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "groupe")
    private String groupe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgroupe")
    private List<Operateur> operateurList;

    public Groupe() {
    }

    public Groupe(String idgroupe) {
        this.idgroupe = idgroupe;
    }

    public Groupe(String idgroupe, String groupe) {
        this.idgroupe = idgroupe;
        this.groupe = groupe;
    }

    public String getIdgroupe() {
        return idgroupe;
    }

    public void setIdgroupe(String idgroupe) {
        this.idgroupe = idgroupe;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    @XmlTransient
    @JsonIgnore
    public List<Operateur> getOperateurList() {
        return operateurList;
    }

    public void setOperateurList(List<Operateur> operateurList) {
        this.operateurList = operateurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgroupe != null ? idgroupe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.idgroupe == null && other.idgroupe != null) || (this.idgroupe != null && !this.idgroupe.equals(other.idgroupe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Groupe[ idgroupe=" + idgroupe + " ]";
    }

}
