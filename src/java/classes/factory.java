package classes;

import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;
import org.hibernate.jpa.HibernatePersistenceProvider;

public class factory {
    private static final String PERSISTENCE_UNIT_NAME = "E-COLLECTEPU";
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

//       static Properties props = new Properties();
//        props.put("user", "konombo");
//        props.put("password", "bonjour");           
//    PersistenceProvider provider = new HibernatePersistenceProvider();
//    EntityManagerFactory emf = provider.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

     private EntityManager entityManager = null;
     
    public static EntityManagerFactory getemf() {
//        props.put("user", "konombo");
//        props.put("password", "anok_groupe_1974");
//        props.put("defaultRowPrefetch", "30");
//        props.put("defaultBatchValue", "5");
//        props.put("characterEncoding", "UTF-8");
//        props.put("useUnicode", "true");
//        emf = provider.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,props);
        return emf;
    }
    public void setEntityManger() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.entityManager = factory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    private factory() {
    }
}
