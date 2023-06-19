package Collecte;

import Acteur.ActeurDAO;
import Moyen_transp.Moyen_transDAO;
//import Nature_Produit.Nature_ProduitDAO;
import Produits.ProduitDAO;
import Pays.PaysDAO;
import Point_Collecte.Point_CollecteDAO;
import TypeAnimal.TypeAnimalDAO;
//import ValCollectes.ValCollecteDAO;
//import beans.MessageBean;
//import cilssimis.classes.gestionmail;//import classes.gestionmail;
import classes.DateHelper;
import static classes.DateHelper.df;
import classes.factory;
import entites.Acteur;
import entites.Collecte;
import entites.MoyenTrans;
import entites.Operateur;
import entites.OperateurDetail;
import entites.Pays;
import entites.PointCollecte;
import entites.Produit;
import entites.TypeAnimal;
import entites.ValCollecte;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
//import org.richfaces.component.UIDataTable;

//@ViewScoped
//@Named("collecte")
public class CollecteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Collecte collecte;
    private final Operateur oper;
    private String formu;
     private Date dateflow;
    private String id_ptch;
    private String id_ptdech;
    private String codedev;
    private String id_pays;
    private String id_pays_dest;
    private String id_collecteur;
    private String id_natprod;
//     private String id_produit;
    private String id_moyen;
    private String id_typean;
    private String id_actor;
    private boolean init;
//    private Integer index = 0;
    private List<Collecte> collecte_list = new ArrayList();
    private List<Collecte> vivriers_list = new ArrayList();
    private List<Collecte> animaux_list = new ArrayList();
    private String message;
    private boolean nouveau;
//    private UIDataTable dataTable;

    private final CollecteDAO pDao = new CollecteDAO();
    private final ProduitDAO prodDao = new ProduitDAO();
    private final TypeAnimalDAO typeaDao = new TypeAnimalDAO();
    private final ActeurDAO actorDao = new ActeurDAO();
    private final Point_CollecteDAO ptcolDao = new Point_CollecteDAO();
    private final Moyen_transDAO moytransDao = new Moyen_transDAO();
    private final PaysDAO Daopays = new PaysDAO();
    PointCollecte pt_ch, pt_dech, pt_col;
    private Acteur actor;
    EntityManager entityManager = factory.getemf().createEntityManager();

    //------------------------
    // Variables utiliser pour les validaton des collectes
    private Date date_deb, date_fin, date_val;
//    private ValCollecteDAO valco = new ValCollecteDAO();
    ValCollecte valcoll;
//    private Date date_val;
//    private Date date_deb;
//    private Date date_fin;
    private String msg_validation;
     private String vntach = "0";



    public boolean isInit() {
//    public boolean init() {
        message = "";
        getCollecte_list();
        if (!collecte_list.isEmpty()) {
            collecte = (Collecte) collecte_list.get(0);
            date_val = collecte.getDateCollecte();
        }
  //              if (collecte != null) {
    //        date_val = collecte.getDateCollecte();
      //  }
        //Concerne les validation
        // On considere uniquement le mois en cours
        int mois = DateHelper.moisencours();
        int dernjr = DateHelper.dernierjour(); // + 20;
        int annee = DateHelper.annee();
        try {
            date_deb = df.parse(annee + "-" + mois + "-01");
            date_fin = df.parse(annee + "-" + mois + "-" + dernjr);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
//            Logger.getLogger(OffresBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        vntach = "0";
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public CollecteBean() {
        collecte = new Collecte();
        FacesContext ctx = FacesContext.getCurrentInstance();
        oper = (Operateur) ctx.getExternalContext().getSessionMap().get("user");
//        System.out.println(oper);

        // On recherche ici le point de collecte de 
        // l'utilisateur connectee
        if (oper == null) {
            return;
        }
        Query query = entityManager.createNamedQuery("OperateurDetail.findByCodeoper");
        query.setParameter("codeoper", oper.getCodeoper());
        List<OperateurDetail> data_lst = query.getResultList();
        if (!data_lst.isEmpty()) {
//            System.out.println("Super !!! Saisie des Collectes autorise");
            pt_col = data_lst.get(0).getIdPtcollecte();
        } else {
//            System.out.println("Attention cet utilisateur ne peux saisir des Collectes");
            pt_col = null;
        }
    }

    
    public void create() {
//        System.out.println("propertyName1: " + formu);
//        oper = operDao.selectFind("KOAN");
        actor = actorDao.selectFind(id_actor);
//        collecteur = collecteurDao.selectFind("KOAN");
        if (nouveau) {
            try {
//                System.out.println(id_natprod + "-" + formu + "-" + id_typean + "-" + id_ptch + "-" + pt_col.getIdPtcollecte() + "-" + id_ptdech + "-" + id_moyen);
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Produit str = prodDao.selectFind(id_natprod);
                TypeAnimal type_an = typeaDao.selectFind(id_typean);
                Pays pays_src = Daopays.selectFind(id_pays);                
                pt_ch = ptcolDao.selectFind(id_ptch);                
//                pt_dech = ptcolDao.selectFind(id_ptdech);
                pt_dech = ptcolDao.selectFind(id_ptdech);
                MoyenTrans moy_transp = moytransDao.selectFind(id_moyen);
//                String reportDate = df.format(new Date());
                String reportDate = df.format(new Date()).replace("/", "");
                int der_ind = pDao.select_nb_collecte(pt_col);
//                String id_real = pt_col.getIdPays().getIdPays() + reportDate + der_ind; 
                String id_real = id_pays + reportDate + der_ind; 
                Collecte ap_cata = pDao.selectFind(id_real);
                while (ap_cata != null) {
                    der_ind++;
//                    id_real = pt_col.getIdPays().getIdPays() + reportDate + der_ind;
                    id_real = id_pays + reportDate + der_ind; 
                    ap_cata = pDao.selectFind(id_real);
                }
                
                collecte.setIdCollecte(id_real);
                if (formu.equals("ANI")) {
                    collecte.setIdTypean(type_an);
                    collecte.setIdProduit(null);
                } else {
                    collecte.setIdProduit(str);
                    collecte.setIdTypean(null);
                }
                collecte.setIdPtcharge(pt_ch);
                collecte.setIdPtdecharge(pt_dech);
                collecte.setIdPtcollecte(pt_col);
                collecte.setIdMoyTrans(moy_transp);
//                collecte.setIdPays(pt_col.getIdPays());
                collecte.setIdPays(pays_src);
                collecte.setIdActeur(actor);
                collecte.setTypeCollecte(formu);
                collecte.setDateCollecte(dateflow);
//                collecte.setIdNatproduit(str);
                collecte.setDatecre(new Date());
                collecte.setDatemaj(new Date());
                collecte.setOpcre(oper);
                collecte.setOpmaj(oper);
                pDao.insert(collecte);
            } catch (Exception hx) {
                message = hx.getMessage();
//                style_message = "err_message";
            }
        } else {
            try {
                collecte.setDatecre(new Date());
                collecte.setDatemaj(new Date());
                collecte.setOpcre(oper);
                collecte.setOpmaj(oper);
                collecte.setIdActeur(actor);
//                collecte.setIdCollecteur(collecteur);
                pDao.update(collecte);
            } catch (Exception hx) {
//                hx.printStackTrace();
                message = hx.getMessage();
            }
        }
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNouveau() {
        return nouveau;
    }

    public void setNouveau(boolean nouveau) {
        this.nouveau = nouveau;
    }

    public Collecte getCollecte() {
        return collecte;
    }

    public void setCollecte(Collecte serv) {
        this.collecte = serv;
    }

    public List<Collecte> getCollecte_list() {
        collecte_list = pDao.selectAll();
//                offres_list = pDao.selectAll();
        vivriers_list = pDao.select_type_collecte("VI");
        animaux_list = pDao.select_type_collecte("ANI");
        switch (vntach) {
            case "0":
                return collecte_list;
            case "1":
                return vivriers_list;
            default:
                return animaux_list;
        }
    }

    public void setCollecte_list(List<Collecte> mise_list) {
        this.collecte_list = mise_list;
    }

//    public UIDataTable getDataTable() {
//        return dataTable;
//    }
//
//    public void setDataTable(UIDataTable dataTable) {
//        this.dataTable = dataTable;
//    }

    public String getId_pays() {
        return id_pays;
    }

    /**
     * @param id_pays the id_pays to set
     */
    public void setId_pays(String id_pays) {
        this.id_pays = id_pays;
    }

    /**
     * @return the id_ptch
     */
    public String getId_ptch() {
        return id_ptch;
    }

    /**
     * @param id_ptch the id_ptch to set
     */
    public void setId_ptch(String id_ptch) {
        this.id_ptch = id_ptch;
    }

//    /**
//     * @return the id_ptco
//     */
//    public String getId_ptco() {
//        return id_ptco;
//    }
//
//    /**
//     * @param id_ptco the id_ptco to set
//     */
//    public void setId_ptco(String id_ptco) {
//        this.id_ptco = id_ptco;
//    }
    /**
     * @return the id_collecteur
     */
    public String getId_collecteur() {
        return id_collecteur;
    }

    /**
     * @param id_collecteur the id_collecteur to set
     */
    public void setId_collecteur(String id_collecteur) {
        this.id_collecteur = id_collecteur;
    }

    /**
     * @return the id_natprod
     */
    public String getId_natprod() {
        return id_natprod;
    }

    /**
     * @param id_natprod the id_natprod to set
     */
    public void setId_natprod(String id_natprod) {
        this.id_natprod = id_natprod;
    }

    /**
     * @return the id_moyen
     */
    public String getId_moyen() {
        return id_moyen;
    }

    /**
     * @param id_moyen the id_moyen to set
     */
    public void setId_moyen(String id_moyen) {
        this.id_moyen = id_moyen;
    }

    /**
     * @return the id_typean
     */
    public String getId_typean() {
        return id_typean;
    }

    /**
     * @param id_typean the id_typean to set
     */
    public void setId_typean(String id_typean) {
        this.id_typean = id_typean;
    }

    /**
     * @return the id_pays_dest
     */
    public String getId_pays_dest() {
        return id_pays_dest;
    }

    /**
     * @param id_pays_dest the id_pays_dest to set
     */
    public void setId_pays_dest(String id_pays_dest) {
        this.id_pays_dest = id_pays_dest;
    }

    /**
     * @return the vivriers_list
     */
    public List<Collecte> getVivriers_list() {
        vivriers_list = pDao.select_type_collecte("VI");
//        return lst;
        return vivriers_list;
    }

    /**
     * @param vivriers_list the vivriers_list to set
     */
    public void setVivriers_list(List<Collecte> vivriers_list) {
        this.vivriers_list = vivriers_list;
    }

    /**
     * @return the animaux_list
     */
    public List<Collecte> getAnimaux_list() {
        animaux_list = pDao.select_type_collecte("ANI");
        return animaux_list;
    }

    /**
     * @param animaux_list the animaux_list to set
     */
    public void setAnimaux_list(List<Collecte> animaux_list) {
        this.animaux_list = animaux_list;
    }

    /**
     * @return the formu
     */
//    public void formu() {
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        Map<String, String> params;
//        params = externalContext.getRequestParameterMap();
//        String action = params.get("action");
////          //...
//
//    }
//       public String showResult() {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        Map<String, String> params
//                = fc.getExternalContext().getRequestParameterMap();
//        return params.get("natform");
////        return "result";
//    }
//     public void action() {         
//        System.out.println("propertyName1: " + formu);
////        System.out.println("propertyName2: " + propertyName2);
//    }
//              public String getFormu() {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        Map<String, String> params
//                = fc.getExternalContext().getRequestParameterMap();
//        return params.get("natform");
////        return "result";
//    }
//       
//    public String getFormu() {
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        Map<String, String> params;
//        params = externalContext.getRequestParameterMap();
//        formu = params.get("action");
//        return formu;
//    }
    /**
     * @param formu the formu to set
     */
    public void setFormu(String formu) {
        this.formu = formu;
    }

    /**
     * @return the id_ptdech
     */
    public String getId_ptdech() {
        return id_ptdech;
    }

    /**
     * @param id_ptdech the id_ptdech to set
     */
    public void setId_ptdech(String id_ptdech) {
        this.id_ptdech = id_ptdech;
    }

    /**
     * @return the id_actor
     */
    public String getId_actor() {
        return id_actor;
    }

    /**
     * @param id_actor the id_actor to set
     */
    public void setId_actor(String id_actor) {
        this.id_actor = id_actor;
    }

    /**
     * @return the date_deb
     */
    public Date getDate_deb() {
        return date_deb;
    }

    /**
     * @param date_deb the date_deb to set
     */
    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    /**
     * @return the date_fin
     */
    public Date getDate_fin() {
        return date_fin;
    }

    /**
     * @param date_fin the date_fin to set
     */
    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    /**
     * @return the date_val
     */
    public Date getDate_val() {
        return date_val;
    }

    /**
     * @param date_val the date_val to set
     */
    public void setDate_val(Date date_val) {
        this.date_val = date_val;
    }

    /**
     * @return the vntach
     */
    public String getVntach() {
        return vntach;
    }

    /**
     * @param vntach the vntach to set
     */
    public void setVntach(String vntach) {
        this.vntach = vntach;
    }

    /**
     * @return the codedev
     */
    public String getCodedev() {
        return codedev;
    }

    /**
     * @param codedev the codedev to set
     */
    public void setCodedev(String codedev) {
        this.codedev = codedev;
    }

//    /**
//     * @return the id_produit
//     */
//    public String getId_produit() {
//        return id_produit;
//    }
//
//    /**
//     * @param id_produit the id_produit to set
//     */
//    public void setId_produit(String id_produit) {
//        this.id_produit = id_produit;
//    }

    /**
     * @return the dateflow
     */
    public Date getDateflow() {
        return dateflow;
    }

    /**
     * @param dateflow the dateflow to set
     */
    public void setDateflow(Date dateflow) {
        this.dateflow = dateflow;
    }

}
