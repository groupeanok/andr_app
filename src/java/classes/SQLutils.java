package classes;

//import cilssimis.entites.Operateur;
import entites.Parametres;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLutils {
//    private initial in = new initial();
       public static Connection conn_imis(Parametres paramsys) {
//    public static Connection connexion() {
        Connection connect = null;
        Properties props = new Properties();
//        props.put("user", "konombo");
//        props.put("password", "bonjour");
        props.put("user", "konombo");
        props.put("password", "anok_groupe_1974");
        props.put("defaultRowPrefetch", "30");
        props.put("defaultBatchValue", "5");
        props.put("characterEncoding", "UTF-8");
        props.put("useUnicode", "true");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(paramsys.getDbUrlImis()+ "?rewriteBatchedStatements=true", props);
//            connect = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/bdcilssimis?rewriteBatchedStatements=true", props);
//            connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/realisation?rewriteBatchedStatements=true", props);
//            connect.setAutoCommit(false);
//            connect.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println(e.getMessage());
        }
        return connect;
    }
      public static Connection conn_at() {
        Connection connect = null;
        Properties props = new Properties();
        props.put("user", "konombo");
        props.put("password", "anok_groupe_1974");
//        props.put("user", "konombo");
//        props.put("password","anok_groupe_1974");
        props.put("defaultRowPrefetch", "30");
        props.put("defaultBatchValue", "5");
        props.put("characterEncoding", "UTF-8");
        props.put("useUnicode", "true");
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
//            connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ecollecte?useSSL=false ", props);
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://hybrid1386.ca.ns.planethoster.net:3306/ecollecte?rewriteBatchStatements=true?characterEncoding=UTF-8", props);
//            connect.setAutoCommit(false);
//            connect.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println(e.getMessage());
        }
        return connect;
    }
     
       
       
//    public static Connection connexion(Parametres paramsys) {
//        Connection connect = null;
//        Properties props = new Properties();
//        props.put("user", "konombo");
//        props.put("password", "bonjour");
//        props.put("defaultRowPrefetch", "30");
//        props.put("defaultBatchValue", "5");
//        props.put("characterEncoding", "UTF-8");
//        props.put("useUnicode", "true");
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
////            connect = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/bdcilssimis?rewriteBatchedStatements=true", props);
//            connect = (Connection) DriverManager.getConnection(paramsys.getDbUrlEadmin()+ "?rewriteBatchedStatements=true", props);
//            connect.setAutoCommit(false);
//            connect.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//        } catch (ClassNotFoundException | SQLException e) {
//        }
//        return connect;
//    }
    
//    public static Connection conn_preacilss(Parametres paramsys) {
//        Connection connect = null;
//        Properties props = new Properties();
//        props.put("user", "konombo");
//        props.put("password", "bonjour");
//        props.put("defaultRowPrefetch", "30");
//        props.put("defaultBatchValue", "5");
//        props.put("characterEncoding", "UTF-8");
//        props.put("useUnicode", "true");
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
////                        connect = (Connection) DriverManager.getConnection(paramsys.getDbUrlpreacilss()+ "?rewriteBatchedStatements=true", props);
////            connect = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.1.4:3306/realisation?rewriteBatchedStatements=true", props);
//            connect.setAutoCommit(false);
//            connect.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//        } catch (ClassNotFoundException | SQLException e) {
//        }
//        return connect;
//    }

  /*  public static Connection connect_real() {
        Connection connect = null;
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "");
        props.put("defaultRowPrefetch", "30");
        props.put("defaultBatchValue", "5");
        props.put("characterEncoding", "UTF-8");
        props.put("useUnicode", "true");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/realisation?rewriteBatchedStatements=true", props);
            connect.setAutoCommit(false);
            connect.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
        return connect;
    }

    public static void closeCon(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
        }
    }

    public static void closeStat(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
        }
    }

    public static void closeRes(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
        }
    }*/

    private SQLutils() {
    }
}
