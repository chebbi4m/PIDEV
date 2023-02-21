package utils;

import entities.colis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



import javax.swing.*;
import java.sql.*;




public class SqlConnection {
    static String user ="root";
    static String password ="";
    static String url = "jdbc:mysql://localhost:3306/taktak";
    static String driver = "com.mysql.cj.jdbc.Driver";
    public static Connection MyConnection() {
        Connection conn= null;
        try {
          conn = DriverManager.getConnection(url, user, password);
            JOptionPane.showMessageDialog(null,"connexion reussie");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }}


