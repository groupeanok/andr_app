/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "collecte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Collecte.findAll", query = "SELECT c FROM Collecte c")
    ,
    @NamedQuery(name = "Collecte.find2All", query = "SELECT c FROM Collecte c order by c.idPays,c.typeCollecte")
    , @NamedQuery(name = "Collecte.find3All", query = "SELECT c FROM Collecte c order by c.idProduit.idProduit,c.idTypean.idTypean")
    ,
    @NamedQuery(name = "Collecte.nbre", query = "SELECT count(g) FROM Collecte g where g.dateCollecte = :dateCollecte AND g.idPtcollecte =:idPtcollecte")
    ,
    @NamedQuery(name = "Collecte.UsedProd", query = "SELECT distinct g.idProduit FROM Collecte g")
    ,
     @NamedQuery(name = "Collecte.UsedAni", query = "SELECT distinct g.idTypean FROM Collecte g")
    ,
    
//    @NamedQuery(name = "Collecte.defile_vi", query = "SELECT SUM(g.nbreGros),SUM(g.nbreGros) FROM Collecte g where g.dateCollecte = :dateCollecte AND g.idPtcollecte =:idPtcollecte")
//    ,
    @NamedQuery(name = "Collecte.findByInterval", query = "SELECT c FROM Collecte c WHERE c.datecre >=:datedeb and c.datecre <=:datefin order by c.datecre DESC")
    ,
    @NamedQuery(name = "Collecte.findByIntervalType", query = "SELECT c FROM Collecte c WHERE c.dateCollecte >=:datedeb and c.dateCollecte <=:datefin AND c.typeCollecte = :typeCollecte order by c.datecre DESC"),
//            + " "),
    @NamedQuery(name = "Collecte.findByIdCollecte", query = "SELECT c FROM Collecte c WHERE c.idCollecte = :idCollecte")
    ,
    @NamedQuery(name = "Collecte.findByIdMoyTrans", query = "SELECT c FROM Collecte c WHERE c.idMoyTrans = :idMoyTrans")
    ,
//    @NamedQuery(name = "Collecte.findByNumMoytrans", query = "SELECT c FROM Collecte c WHERE c.numMoytrans = :numMoytrans")
//    ,
    @NamedQuery(name = "Collecte.findByTypeCollecte", query = "SELECT c FROM Collecte c WHERE c.typeCollecte = :typeCollecte order by c.datecre DESC")
    ,
//    @NamedQuery(name = "Collecte.findByDatecre", query = "SELECT c FROM Collecte c WHERE c.datecre = :datecre")
//    ,
//    @NamedQuery(name = "Collecte.findByDatemaj", query = "SELECT c FROM Collecte c WHERE c.datemaj = :datemaj")
//    ,
    @NamedQuery(name = "Collecte.findByDateCollecte", query = "SELECT c FROM Collecte c WHERE c.dateCollecte = :dateCollecte")
    ,
//    @NamedQuery(name = "Collecte.findByNbreSacpan", query = "SELECT c FROM Collecte c WHERE c.nbreSacpan = :nbreSacpan")
//    ,
//    @NamedQuery(name = "Collecte.findByPdbyunite", query = "SELECT c FROM Collecte c WHERE c.pdbyunite = :pdbyunite")
//    ,
//    @NamedQuery(name = "Collecte.findByPdbysac", query = "SELECT c FROM Collecte c WHERE c.pdbysac = :pdbysac")
//    ,
//    @NamedQuery(name = "Collecte.findByPrixtranspunit", query = "SELECT c FROM Collecte c WHERE c.prixtranspunit = :prixtranspunit")
//    ,
    @NamedQuery(name = "Collecte.findByCoursAvecDollard", query = "SELECT c FROM Collecte c WHERE c.coursAvecDollard = :coursAvecDollard")
//    ,
//    @NamedQuery(name = "Collecte.findByNbreGros", query = "SELECT c FROM Collecte c WHERE c.nbreGros = :nbreGros")
//    ,
//    @NamedQuery(name = "Collecte.findByPrixGros", query = "SELECT c FROM Collecte c WHERE c.prixGros = :prixGros")
//    ,
//    @NamedQuery(name = "Collecte.findByNbreMoyen", query = "SELECT c FROM Collecte c WHERE c.nbreMoyen = :nbreMoyen")
//    ,
//    @NamedQuery(name = "Collecte.findByPrixMoyen", query = "SELECT c FROM Collecte c WHERE c.prixMoyen = :prixMoyen")
//    ,
//    @NamedQuery(name = "Collecte.findByNbrePetit", query = "SELECT c FROM Collecte c WHERE c.nbrePetit = :nbrePetit")
//    ,
//    @NamedQuery(name = "Collecte.findByPrixPetit", query = "SELECT c FROM Collecte c WHERE c.prixPetit = :prixPetit")
    ,
    @NamedQuery(name = "Collecte.findByValide", query = "SELECT c FROM Collecte c WHERE c.valide = :valide")})
public class Collecte implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "valide")
    private boolean valide;
   
    @JoinColumn(name = "codedev", referencedColumnName = "codedev")
    @ManyToOne(optional = false)
    private Devise codedev;

    @Basic(optional = false)
    @NotNull
    @Column(name = "val_nivpfoc")
    private boolean valNivpfoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "val_nivreg")
    private boolean valNivreg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coordx")
    private float coordx;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coordy")
    private float coordy;
    @Size(max = 50)
    @Column(name = "photo")
    private String photo;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id_collecte")
    private String idCollecte;

    @JoinColumn(name = "id_moy_trans", referencedColumnName = "id_moy_trans")
    @ManyToOne(optional = false)
    private MoyenTrans idMoyTrans;

//    
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 10)
//    @Column(name = "id_moy_trans")
//    private String idMoyTrans;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)

    @Column(name = "num_moytrans")
    private String numMoytrans;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)

    @Column(name = "dec_number")
    private String decNumber;
    
    
    
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "type_collecte")
    private String typeCollecte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATECRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEMAJ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datemaj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_collecte")
    @Temporal(TemporalType.DATE)
    private Date dateCollecte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nbre_sacpan")
    private int nbreSacpan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pdbyunite")
    private int pdbyunite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pdbysac")
    private int pdbysac;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prixtranspunit")
    private int prixtranspunit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cours_avec_dollard")
    private float coursAvecDollard;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nbre_gros")
    private int nbreGros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_gros")
    private int prixGros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nbre_moyen")
    private int nbreMoyen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_moyen")
    private int prixMoyen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nbre_petit")
    private int nbrePetit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_petit")
    private int prixPetit;
    @JoinColumn(name = "id_acteur", referencedColumnName = "id_acteur")
    @ManyToOne(optional = false)
    private Acteur idActeur;
    @JoinColumn(name = "idPays", referencedColumnName = "idPays")
    @ManyToOne(optional = false)
    private Pays idPays;
    @JoinColumn(name = "id_ptcollecte", referencedColumnName = "id_ptcollecte")
    @ManyToOne(optional = false)
    private PointCollecte idPtcollecte;
    @JoinColumn(name = "id_ptcharge", referencedColumnName = "id_ptcollecte")
    @ManyToOne(optional = false)
    private PointCollecte idPtcharge;
    @JoinColumn(name = "OPMAJ", referencedColumnName = "CODEOPER")
    @ManyToOne(optional = false)
    private Operateur opmaj;
    @JoinColumn(name = "OPCRE", referencedColumnName = "CODEOPER")
    @ManyToOne(optional = false)
    private Operateur opcre;
    @JoinColumn(name = "id_typean", referencedColumnName = "id_typean")
    @ManyToOne
    private TypeAnimal idTypean;
    @JoinColumn(name = "id_ptdecharge", referencedColumnName = "id_ptcollecte")
    @ManyToOne(optional = false)
    private PointCollecte idPtdecharge;

    @JoinColumn(name = "id_produit", referencedColumnName = "id_produit")
    @ManyToOne
    private Produit idProduit;
//    
//    @JoinColumn(name = "id_natproduit", referencedColumnName = "id_natproduit")
//    @ManyToOne
//    private NatureProduit idNatproduit;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "collecte")
    private ValCollecte valCollecte;
    
    
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "otherfes")
    private int otherfes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cpc_regime")
    private String cpcRegime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "passage_douane")
    private boolean passageDouane;
    @JoinColumn(name = "unitid", referencedColumnName = "unitid")
    @ManyToOne
    private Units unitid;

    public Collecte() {
    }

    public Collecte(String idCollecte) {
        this.idCollecte = idCollecte;
    }

    public Collecte(String idCollecte, MoyenTrans idMoyTrans, String numMoytrans, String typeCollecte, Date datecre, Date datemaj, Date dateCollecte, int nbreSacpan, int pdbyunite, int pdbysac, int prixtranspunit, int coursAvecDollard, int nbreGros, int prixGros, int nbreMoyen, int prixMoyen, int nbrePetit, int prixPetit, boolean valide) {
        this.idCollecte = idCollecte;
        this.idMoyTrans = idMoyTrans;
        this.numMoytrans = numMoytrans;
        this.typeCollecte = typeCollecte;
        this.datecre = datecre;
        this.datemaj = datemaj;
        this.dateCollecte = dateCollecte;
        this.nbreSacpan = nbreSacpan;
        this.pdbyunite = pdbyunite;
        this.pdbysac = pdbysac;
        this.prixtranspunit = prixtranspunit;
        this.coursAvecDollard = coursAvecDollard;
        this.nbreGros = nbreGros;
        this.prixGros = prixGros;
        this.nbreMoyen = nbreMoyen;
        this.prixMoyen = prixMoyen;
        this.nbrePetit = nbrePetit;
        this.prixPetit = prixPetit;
        this.valide = valide;
    }

    public String getIdCollecte() {
        return idCollecte;
    }

    public void setIdCollecte(String idCollecte) {
        this.idCollecte = idCollecte;
    }

    public MoyenTrans getIdMoyTrans() {
        return idMoyTrans;
    }

    public void setIdMoyTrans(MoyenTrans idMoyTrans) {
        this.idMoyTrans = idMoyTrans;
    }

    public String getNumMoytrans() {
        return numMoytrans;
    }

    public void setNumMoytrans(String numMoytrans) {
        this.numMoytrans = numMoytrans;
    }

    public String getTypeCollecte() {
        return typeCollecte;
    }

    public void setTypeCollecte(String typeCollecte) {
        this.typeCollecte = typeCollecte;
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

    public Date getDateCollecte() {
        return dateCollecte;
    }

    public void setDateCollecte(Date dateCollecte) {
        this.dateCollecte = dateCollecte;
    }

    public int getNbreSacpan() {
        return nbreSacpan;
    }

    public void setNbreSacpan(int nbreSacpan) {
        this.nbreSacpan = nbreSacpan;
    }

    public int getPdbyunite() {
        return pdbyunite;
    }

    public void setPdbyunite(int pdbyunite) {
        this.pdbyunite = pdbyunite;
    }

    public int getPdbysac() {
        return pdbysac;
    }

    public void setPdbysac(int pdbysac) {
        this.pdbysac = pdbysac;
    }

    public int getPrixtranspunit() {
        return prixtranspunit;
    }

    public void setPrixtranspunit(int prixtranspunit) {
        this.prixtranspunit = prixtranspunit;
    }

    public float getCoursAvecDollard() {
        return coursAvecDollard;
    }

    public void setCoursAvecDollard(float coursAvecDollard) {
        this.coursAvecDollard = coursAvecDollard;
    }

    public int getNbreGros() {
        return nbreGros;
    }

    public void setNbreGros(int nbreGros) {
        this.nbreGros = nbreGros;
    }

    public int getPrixGros() {
        return prixGros;
    }

    public void setPrixGros(int prixGros) {
        this.prixGros = prixGros;
    }

    public int getNbreMoyen() {
        return nbreMoyen;
    }

    public void setNbreMoyen(int nbreMoyen) {
        this.nbreMoyen = nbreMoyen;
    }

    public int getPrixMoyen() {
        return prixMoyen;
    }

    public void setPrixMoyen(int prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    public int getNbrePetit() {
        return nbrePetit;
    }

    public void setNbrePetit(int nbrePetit) {
        this.nbrePetit = nbrePetit;
    }

    public int getPrixPetit() {
        return prixPetit;
    }

    public void setPrixPetit(int prixPetit) {
        this.prixPetit = prixPetit;
    }


    public Acteur getIdActeur() {
        return idActeur;
    }

    public void setIdActeur(Acteur idActeur) {
        this.idActeur = idActeur;
    }

    public Pays getIdPays() {
        return idPays;
    }

    public void setIdPays(Pays idPays) {
        this.idPays = idPays;
    }

    public PointCollecte getIdPtcollecte() {
        return idPtcollecte;
    }

    public void setIdPtcollecte(PointCollecte idPtcollecte) {
        this.idPtcollecte = idPtcollecte;
    }

    public PointCollecte getIdPtcharge() {
        return idPtcharge;
    }

    public void setIdPtcharge(PointCollecte idPtcharge) {
        this.idPtcharge = idPtcharge;
    }

    public Operateur getOpmaj() {
        return opmaj;
    }

    public void setOpmaj(Operateur opmaj) {
        this.opmaj = opmaj;
    }

    public Operateur getOpcre() {
        return opcre;
    }

    public void setOpcre(Operateur opcre) {
        this.opcre = opcre;
    }

    public TypeAnimal getIdTypean() {
        return idTypean;
    }

    public void setIdTypean(TypeAnimal idTypean) {
        this.idTypean = idTypean;
    }

    public PointCollecte getIdPtdecharge() {
        return idPtdecharge;
    }

    public void setIdPtdecharge(PointCollecte idPtdecharge) {
        this.idPtdecharge = idPtdecharge;
    }

    //    private Produit idProduit;
    public Produit getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Produit idNatproduit) {
        this.idProduit = idNatproduit;
    }
//    public NatureProduit getIdNatproduit() {
//        return idNatproduit;
//    }
//
//    public void setIdNatproduit(NatureProduit idNatproduit) {
//        this.idNatproduit = idNatproduit;
//    }

    public ValCollecte getValCollecte() {
        return valCollecte;
    }

    public void setValCollecte(ValCollecte valCollecte) {
        this.valCollecte = valCollecte;
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
        if (!(object instanceof Collecte)) {
            return false;
        }
        Collecte other = (Collecte) object;
        if ((this.idCollecte == null && other.idCollecte != null) || (this.idCollecte != null && !this.idCollecte.equals(other.idCollecte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "anok_imis.entites.Collecte[ idCollecte=" + idCollecte + " ]";
    }

    /**
     * @return the imageFlux
     */

    /**
     * @return the otherfes
     */
    public int getOtherfes() {
        return otherfes;
    }

    /**
     * @param otherfes the otherfes to set
     */
    public void setOtherfes(int otherfes) {
        this.otherfes = otherfes;
    }


    /**
     * @return the cpcRegime
     */
    public String getCpcRegime() {
        return cpcRegime;
    }

    /**
     * @param cpcRegime the cpcRegime to set
     */
    public void setCpcRegime(String cpcRegime) {
        this.cpcRegime = cpcRegime;
    }

    /**
     * @return the passageDouane
     */
    public boolean isPassageDouane() {
        return passageDouane;
    }

    /**
     * @param passageDouane the passageDouane to set
     */
    public void setPassageDouane(boolean passageDouane) {
        this.passageDouane = passageDouane;
    }

    /**
     * @return the unitid
     */
    public Units getUnitid() {
        return unitid;
    }

    /**
     * @param unitid the unitid to set
     */
    public void setUnitid(Units unitid) {
        this.unitid = unitid;
    }

    /**
     * @return the decNumber
     */
    public String getDecNumber() {
        return decNumber;
    }

    /**
     * @param decNumber the decNumber to set
     */
    public void setDecNumber(String decNumber) {
        this.decNumber = decNumber;
    }


    public boolean getValNivpfoc() {
        return valNivpfoc;
    }

    public void setValNivpfoc(boolean valNivpfoc) {
        this.valNivpfoc = valNivpfoc;
    }

    public boolean getValNivreg() {
        return valNivreg;
    }

    public void setValNivreg(boolean valNivreg) {
        this.valNivreg = valNivreg;
    }


    public float getCoordx() {
        return coordx;
    }

    public void setCoordx(float coordx) {
        this.coordx = coordx;
    }

    public float getCoordy() {
        return coordy;
    }

    public void setCoordy(float coordy) {
        this.coordy = coordy;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

   
    public boolean getValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
    public Devise getCodedev() {
        return codedev;
    }

    public void setCodedev(Devise codedev) {
        this.codedev = codedev;
    }

}
