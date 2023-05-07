package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MyConnection {
    String url = "jdbc:mysql://localhost:3306/taktakv";// DB name
    String login= "root";
    String password="";
    Connection myConexBD;

    public static MyConnection instance ;

    private MyConnection() {
        try {
            myConexBD = DriverManager.getConnection(url, login, password);
            System.out.println("connexion reussie");
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        System.out.println("MyConnection constructor executed");
    }
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
    public Connection getConnexion(){
        return myConexBD;
    }
}
