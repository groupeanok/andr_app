/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import entites.Pays;
import entites.Produit;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "tra_collecte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TraCollecte.findAll", query = "SELECT t FROM TraCollecte t")
    ,
    @NamedQuery(name = "TraCollecte.nbre", query = "SELECT count(g) FROM TraCollecte g ")
    ,
    @NamedQuery(name = "TraCollecte.findByIdCollecte", query = "SELECT t FROM TraCollecte t WHERE t.idCollecte = :idCollecte")
    ,
    @NamedQuery(name = "TraCollecte.findByRefcollecte", query = "SELECT t FROM TraCollecte t WHERE t.refcollecte = :refcollecte")
    ,
    @NamedQuery(name = "TraCollecte.findBySens", query = "SELECT t FROM TraCollecte t WHERE t.sens = :sens")
    ,
    @NamedQuery(name = "TraCollecte.findByTareCam", query = "SELECT t FROM TraCollecte t WHERE t.tareCam = :tareCam")
    ,
    @NamedQuery(name = "TraCollecte.findByPoidsGoods", query = "SELECT t FROM TraCollecte t WHERE t.poidsGoods = :poidsGoods")
    ,
    @NamedQuery(name = "TraCollecte.findByValueGoods", query = "SELECT t FROM TraCollecte t WHERE t.valueGoods = :valueGoods")
    ,
    @NamedQuery(name = "TraCollecte.findByDateheuredep", query = "SELECT t FROM TraCollecte t WHERE t.dateheuredep = :dateheuredep")
    ,
    @NamedQuery(name = "TraCollecte.findByDateheurearr", query = "SELECT t FROM TraCollecte t WHERE t.dateheurearr = :dateheurearr")
    ,
    @NamedQuery(name = "TraCollecte.findByMtpaieillicite", query = "SELECT t FROM TraCollecte t WHERE t.mtpaieillicite = :mtpaieillicite")
    ,
    @NamedQuery(name = "TraCollecte.findByDoccomplet", query = "SELECT t FROM TraCollecte t WHERE t.doccomplet = :doccomplet")
    ,
    @NamedQuery(name = "TraCollecte.findByIdTypepro", query = "SELECT t FROM TraCollecte t WHERE t.idTypepro = :idTypepro")
    ,
    @NamedQuery(name = "TraCollecte.findByDateCollecte", query = "SELECT t FROM TraCollecte t WHERE t.dateCollecte = :dateCollecte")
    ,
    @NamedQuery(name = "TraCollecte.findByidCorridor", query = "SELECT t FROM TraCollecte t WHERE t.idCorridor = :idCorridor")})
public class TraCollecte implements Serializable, Comparable<TraCollecte> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
//    @Size(min = 1, max = 20)
    @Column(name = "id_collecte")
    private String idCollecte;
    @Basic(optional = false)
    @NotNull
//    @Size(min = 1, max = 30)
    @Column(name = "refcollecte")
    private String refcollecte;
    @Basic(optional = false)
    @NotNull
//    @Size(min = 1, max = 30)
    @Column(name = "sens")
    private String sens;
    @Basic(optional = false)
    @NotNull
//    @Size(min = 1, max = 20)
    @Column(name = "tare_cam")
    private String tareCam;
    @Basic(optional = false)
    @NotNull
//    @Size(min = 1, max = 20)
    @Column(name = "poids_goods")
    private int poidsGoods;
    @Basic(optional = false)
    @NotNull
//    @Size(min = 1, max = 20)
    @Column(name = "value_goods")
    private int valueGoods;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dateheuredep")
    @Temporal(TemporalType.DATE)
    private Date dateheuredep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateheurearr")
    @Temporal(TemporalType.DATE)
    private Date dateheurearr;

    @Basic(optional = false)
    @NotNull
    @Column(name = "mtpaieillicite")
    private int mtpaieillicite;

    @Basic(optional = false)
    @NotNull
    @Column(name = "doccomplet")
    private boolean doccomplet;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 5)
//    @Column(name = "id_typepro")
//    private String idTypepro;
    @JoinColumn(name = "id_typepro", referencedColumnName = "id_type_proprio")
    @ManyToOne(optional = false)
    private TraTypeProprio idTypepro;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_collecte")
    @Temporal(TemporalType.DATE)
    private Date dateCollecte;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 2)
//    @Column(name = "id_fiche")
//    private String idFiche;
////    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCollecte")
//    private List<TraDatatraca> traDatatracaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "traCollecte")
    private List<TraDatatraca> traDatatracaList;

    @JoinColumn(name = "id_typecam", referencedColumnName = "id_moy_trans")
    @ManyToOne(optional = false)
    private MoyenTrans idTypecam;
    
    
    @JoinColumn(name = "id_pays_imma", referencedColumnName = "idPays")
    @ManyToOne(optional = false)
    private Pays idPaysImma;
    @JoinColumn(name = "id_nationalite", referencedColumnName = "idPays")
    @ManyToOne(optional = false)
    private Pays idNationalite;
    @JoinColumn(name = "id_typeproduit", referencedColumnName = "id_produit")
    @ManyToOne(optional = false)
    private Produit idTypeproduit;
    @JoinColumn(name = "id_niveau", referencedColumnName = "id_niveau")
    @ManyToOne(optional = false)
    private TraNiveausco idNiveau;
    @JoinColumn(name = "id_ville_dest", referencedColumnName = "codeville")
    @ManyToOne(optional = false)
    private Ville idVilleDest;
    @JoinColumn(name = "id_ville_src", referencedColumnName = "codeville")
    @ManyToOne(optional = false)
    private Ville idVilleSrc;

    @JoinColumn(name = "OPCRE", referencedColumnName = "CODEOPER")
    @ManyToOne(optional = false)
    private Operateur opcre;

    @JoinColumn(name = "OPMAJ", referencedColumnName = "CODEOPER")
    @ManyToOne(optional = false)
    private Operateur opmaj;

    @Basic(optional = false)
    @Column(name = "DATECRE")
    @Temporal(TemporalType.DATE)
    private Date datecre;

    @Basic(optional = false)
    @Column(name = "DATEMAJ")
    @Temporal(TemporalType.DATE)
    private Date datemaj;

//           @JoinColumn(name = "codedev", referencedColumnName = "codedev")
//    @ManyToOne
//    private Devise codedev;
    @JoinColumn(name = "id_corridor", referencedColumnName = "id_corridor")
    @ManyToOne(optional = false)
    private TraCorridor idCorridor;

    public TraCollecte() {
    }

    public TraCollecte(String idCollecte) {
        this.idCollecte = idCollecte;
    }

    public TraCollecte(String idCollecte, String refcollecte, String sens, String tareCam, int poidsGoods, int valueGoods, Date dateheuredep, Date dateheurearr, int mtpaieillicite, boolean doccomplet, TraTypeProprio idTypepro, Date dateCollecte, TraCorridor idFiche) {
        this.idCollecte = idCollecte;
        this.refcollecte = refcollecte;
        this.sens = sens;
        this.tareCam = tareCam;
        this.poidsGoods = poidsGoods;
        this.valueGoods = valueGoods;
        this.dateheuredep = dateheuredep;
        this.dateheurearr = dateheurearr;
        this.mtpaieillicite = mtpaieillicite;
        this.doccomplet = doccomplet;
        this.idTypepro = idTypepro;
        this.dateCollecte = dateCollecte;
        this.idCorridor = idFiche;
    }

    public String getIdCollecte() {
        return idCollecte;
    }

    public void setIdCollecte(String idCollecte) {
        this.idCollecte = idCollecte;
    }

    public String getRefcollecte() {
        return refcollecte;
    }

    public void setRefcollecte(String refcollecte) {
        this.refcollecte = refcollecte;
    }

    public String getSens() {
        return sens;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    public String getTareCam() {
        return tareCam;
    }

    public void setTareCam(String tareCam) {
        this.tareCam = tareCam;
    }

    public int getPoidsGoods() {
        return poidsGoods;
    }

    public void setPoidsGoods(int poidsGoods) {
        this.poidsGoods = poidsGoods;
    }

    public int getValueGoods() {
        return valueGoods;
    }

    public void setValueGoods(int valueGoods) {
        this.valueGoods = valueGoods;
    }

    public Date getDateheuredep() {
        return dateheuredep;
    }

    public void setDateheuredep(Date dateheuredep) {
        this.dateheuredep = dateheuredep;
    }

    public Date getDateheurearr() {
        return dateheurearr;
    }

    public void setDateheurearr(Date dateheurearr) {
        this.dateheurearr = dateheurearr;
    }

    public int getMtpaieillicite() {
        return mtpaieillicite;
    }

    public void setMtpaieillicite(int mtpaieillicite) {
        this.mtpaieillicite = mtpaieillicite;
    }

    public boolean getDoccomplet() {
        return doccomplet;
    }

    public void setDoccomplet(boolean doccomplet) {
        this.doccomplet = doccomplet;
    }

    public TraTypeProprio getIdTypepro() {
        return idTypepro;
    }

    public void setIdTypepro(TraTypeProprio idTypepro) {
        this.idTypepro = idTypepro;
    }

    public Date getDateCollecte() {
        return dateCollecte;
    }

    public void setDateCollecte(Date dateCollecte) {
        this.dateCollecte = dateCollecte;
    }
//
//    public String getIdFiche() {
//        return idFiche;
//    }
//
//    public void setIdFiche(String idFiche) {
//        this.idFiche = idFiche;
//    }

//    @XmlTransient
//    @JsonIgnore
//    public List<TraDatatraca> getTraDatatracaList() {
//        return traDatatracaList;
//    }
//
//    public void setTraDatatracaList(List<TraDatatraca> traDatatracaList) {
//        this.traDatatracaList = traDatatracaList;
//    }
    public MoyenTrans getIdTypecam() {
        return idTypecam;
    }

    public void setIdTypecam(MoyenTrans idTypecam) {
        this.idTypecam = idTypecam;
    }

    public Pays getIdPaysImma() {
        return idPaysImma;
    }

    public void setIdPaysImma(Pays idPaysImma) {
        this.idPaysImma = idPaysImma;
    }

    public Pays getIdNationalite() {
        return idNationalite;
    }

    public void setIdNationalite(Pays idNationalite) {
        this.idNationalite = idNationalite;
    }

    public Produit getIdTypeproduit() {
        return idTypeproduit;
    }

    public void setIdTypeproduit(Produit idTypeproduit) {
        this.idTypeproduit = idTypeproduit;
    }

    public TraNiveausco getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(TraNiveausco idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Ville getIdVilleDest() {
        return idVilleDest;
    }

    public void setIdVilleDest(Ville idVilleDest) {
        this.idVilleDest = idVilleDest;
    }

    public Ville getIdVilleSrc() {
        return idVilleSrc;
    }

    public void setIdVilleSrc(Ville idVilleSrc) {
        this.idVilleSrc = idVilleSrc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCollecte != null ? idCollecte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraCollecte)) {
            return false;
        }
        TraCollecte other = (TraCollecte) object;
        if ((this.idCollecte == null && other.idCollecte != null) || (this.idCollecte != null && !this.idCollecte.equals(other.idCollecte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.TraCollecte[ idCollecte=" + idCollecte + " ]";
    }

    /**
     * @return the opcre
     */
    public Operateur getOpcre() {
        return opcre;
    }

    /**
     * @param opcre the opcre to set
     */
    public void setOpcre(Operateur opcre) {
        this.opcre = opcre;
    }

    /**
     * @return the opmaj
     */
    public Operateur getOpmaj() {
        return opmaj;
    }

    /**
     * @param opmaj the opmaj to set
     */
    public void setOpmaj(Operateur opmaj) {
        this.opmaj = opmaj;
    }

    /**
     * @return the datecre
     */
    public Date getDatecre() {
        return datecre;
    }

    /**
     * @param datecre the datecre to set
     */
    public void setDatecre(Date datecre) {
        this.datecre = datecre;
    }

    /**
     * @return the datemaj
     */
    public Date getDatemaj() {
        return datemaj;
    }

    /**
     * @param datemaj the datemaj to set
     */
    public void setDatemaj(Date datemaj) {
        this.datemaj = datemaj;
    }

    /**
     * @return the traDatatracaList
     */
    public List<TraDatatraca> getTraDatatracaList() {
        return traDatatracaList;
    }

    /**
     * @param traDatatracaList the traDatatracaList to set
     */
    public void setTraDatatracaList(List<TraDatatraca> traDatatracaList) {
        this.traDatatracaList = traDatatracaList;
    }

    /**
     * @return the idCorridor
     */
    public TraCorridor getIdCorridor() {
        return idCorridor;
    }

    /**
     * @param idCorridor the idCorridor to set
     */
    public void setIdCorridor(TraCorridor idCorridor) {
        this.idCorridor = idCorridor;
    }

//    /**
//     * @return the codedev
//     */
//    public Devise getCodedev() {
//        return codedev;
//    }
//
//    /**
//     * @param codedev the codedev to set
//     */
//    public void setCodedev(Devise codedev) {
//        this.codedev = codedev;
//    }
//    @Override
//    public int compareTo(TraCollecte o) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public int compareTo(TraCollecte other) {
//         return Integer.compare(this.dateCollecte, other.dateCollecte);
        return getDateCollecte().compareTo(other.getDateCollecte());
    }
}
