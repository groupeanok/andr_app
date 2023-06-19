package Pays;

import classes.factory;
import entites.Devise;
import entites.Pays;
import entites.TraCorridor;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PaysDAO implements Serializable {

    EntityManager entityManager = factory.getemf().createEntityManager();

//    public List<Pays> selectAll() {
//        Query qsniv = entityManager.createNamedQuery("Pays.findAll"); //, Nivo.class);
//        List<Pays> operateurs = qsniv.getResultList();
//        return operateurs;
//    }
    public List<Pays> select_cilss_Pays() {
        Query qsniv = entityManager.createNamedQuery("Pays.findByCI"); //, Nivo.class);
        List<Pays> lespays = qsniv.getResultList();
        return lespays;
    }
    
    public List<Devise> select_currency() {
        Query query = entityManager.createNamedQuery("Pays.findCurrency"); //, Nivo.class);
//        query.setParameter("idPays", pays);
        List<Devise> les_dev = query.getResultList();
//        System.out.println("Nombre de corridor trouvés : " + les_cor.size());
        return les_dev;
    }
    
     public List<Devise> select_allDevises() {
        Query query = entityManager.createNamedQuery("Pays.findByDansCi2"); //, Nivo.class);
//        query.setParameter("idPays", pays);
        List<Devise> les_dev = query.getResultList();
//        System.out.println("Nombre de corridor trouvés : " + les_cor.size());
        return les_dev;
    }
    
    public List<TraCorridor> select_corridor(Pays pays) {
        Query query = entityManager.createNamedQuery("TraCorridor.pays"); //, Nivo.class);
        query.setParameter("idPays", pays);
        List<TraCorridor> les_cor = query.getResultList();
//        System.out.println("Nombre de corridor trouvés : " + les_cor.size());
        return les_cor;
    }

    public Pays selectFind(String code) {
        return entityManager.find(Pays.class, code);
    }

    public String select_nb_ins_tot() {
        Query query = entityManager.createNamedQuery("Pays.nbre");
        Object taille = query.getSingleResult();
        if (taille == null) {
            return "01";
        } else {
            Integer i = Integer.valueOf(taille.toString());
            return String.valueOf(i + 1);
        }
    }

    public Pays insert(Pays u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        return u;
    }

    public void delete(Pays u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public Pays update(Pays u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        u = entityManager.merge(u);
        entityManager.getTransaction().commit();
        return u;
    }

//    public Pays Find_pays(String conv) {
//        Query query = entityManager.createNamedQuery("Pays.findByNumcpteimis");
//        query.setParameter("numcpteimis", conv);
//        try {
//            return (Pays) query.getSingleResult();
//        } catch (Exception ex) {
//            return null;
//        }
//    }
}
