/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

/**
 *
 * @author Somda
 */
//public class OperateurDAO {
//    
//}
//package model.dao;
import classes.factory;
//import static classes.gestionmail.mail_exp_candidature;
import entites.Operateur;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * C'est le DAO qui permet d'effectuer des opérations portant sur une personne
 * dans la base de données.
 *
 * @author <a href="mailto:djo.mos.contact@gmail.com">djo.mos</a>
 *
 */
public class UserDAO implements Serializable {

    EntityManager entityManager = factory.getemf().createEntityManager();

    public Operateur selectFind(String code) {
        Operateur oper = entityManager.find(Operateur.class, code);
        return oper;

    }

    // Se connecter par le nom d'utilisateur et le mot de passe
    public Operateur SeConnecter(String code, String pw) {
        Query query = entityManager.createNamedQuery("Operateur.Connect");
        query.setParameter("codeoper", code);
        query.setParameter("password", pw);
        return (Operateur) query.getSingleResult();
    }

    // Se connecter par le Email et le mot de passe
    public Operateur SeConnecterViaMel(String code, String pw) {
        Query query = entityManager.createNamedQuery("Operateur.Connectvm");
        query.setParameter("meloper", code);
        query.setParameter("password", pw);
        return (Operateur) query.getSingleResult();
    }

    public String select_nb_cand_tot() {
//        if (!entityManager.getTransaction().isActive()) {
//            entityManager.getTransaction().begin();
//        }
        Query query = entityManager.createNamedQuery("Operateur.nbre");
        Object taille = query.getSingleResult();
        if (taille == null) {
            return "01";
        } else {
            Integer i = Integer.valueOf(taille.toString());
            return String.valueOf(i + 1);
        }
    }

    public List<Operateur> selectAll() {
//        if (!entityManager.getTransaction().isActive()) {
//            entityManager.getTransaction().begin();
//        }
        Query qsniv = entityManager.createNamedQuery("Operateur.findAll"); //, Nivo.class);
//        TblCollecte.findAll
        List<Operateur> operateurs = qsniv.getResultList();
        return operateurs;
    }

    public List<Operateur> selectAllorder() {
//        if (!entityManager.getTransaction().isActive()) {
//            entityManager.getTransaction().begin();
//        }
        Query qsniv = entityManager.createNamedQuery("Operateur.findAllorder"); //, Nivo.class);
//        TblCollecte.findAll
        List<Operateur> operateurs = qsniv.getResultList();
        return operateurs;
    }

    public List<Operateur> selectAlltrie() {
//        if (!entityManager.getTransaction().isActive()) {
//            entityManager.getTransaction().begin();
//        }
        Query qsniv = entityManager.createNamedQuery("Operateur.findAll1"); //, Nivo.class);
//        TblCollecte.findAll
        List<Operateur> operateurs = qsniv.getResultList();
        return operateurs;
    }

    public List<Operateur> select_bygrppays(String code, String pays) {
//        if (!entityManager.getTransaction().isActive()) {
//            entityManager.getTransaction().begin();
//        }
        Query query = entityManager.createNamedQuery("Operateur.findbygrppa"); //, Nivo.class);        
        query.setParameter("idgroupe", code);
        query.setParameter("nationalite", pays);
        return query.getResultList();
    }

    public List<Operateur> select_bygroupe(String code) {
        Query query = entityManager.createNamedQuery("Operateur.findbygrp"); //, Nivo.class);        
        query.setParameter("idgroupe", code);
        return query.getResultList();
    }

    public List<Operateur> select_bypfoc(String code) {
        Query query = entityManager.createNamedQuery("Operateur.findCollByPfoc"); //, Nivo.class);        
        query.setParameter("idPfoc", code);
        return query.getResultList();
    }

    public List<Operateur> select_bycode(String code) {
        Query query = entityManager.createNamedQuery("Operateur.findByCodeoper"); //, Nivo.class);        
        query.setParameter("codeoper", code);
        return query.getResultList();
    }

    public List<Operateur> select_bymail(String code) {
        Query query = entityManager.createNamedQuery("Operateur.findByMeloper"); //, Nivo.class);        
        query.setParameter("meloper", code);
        return query.getResultList();
    }

    public void insert(Operateur u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(u);
        entityManager.getTransaction().commit();
//        return u;
    }

    public void delete(Operateur u) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.merge(u));
        entityManager.getTransaction().commit();
    }

    public void update(Operateur u) {
//        entityManager.getTransaction().begin();
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }

        entityManager.merge(u);
        entityManager.getTransaction().commit();
//        return u;
    }
}
