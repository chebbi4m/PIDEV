/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Client;
import Interfaces.IClient;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author yasoulanda
 */
public class ClientService implements IClient{
    
    Client client = new Client();
    Connection myconn = MyConnection.getInstance().getConnexion();
    
    
@Override
    public void ajouterClient (Client c) {
        try{
            String sql="insert into client values(?,?,?,?,?,?,?)";
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1,c.getId());    
            ste.setString(2,c.getNom());
            ste.setString(3,c.getPrenom());
            ste.setString(4,c.getNumtel());
            ste.setString(5,c.getEmail());
            ste.setString(6,c.getAdresse());
            ste.setString(7,c.getPassword());            

            ste.executeUpdate();
            System.out.println("client inséré");
        }catch(SQLException ex ){
        System.out.print(ex);
        }
                   
    }

    @Override
    public void modifierClient(Client c) {
         String sql="UPDATE client SET nom = ?,prenom = ?,numtel = ?,email = ?,adresse = ? , password = ?  WHERE id = ?";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
            ste.setString(1,c.getNom());
            ste.setString(2,c.getPrenom());
            ste.setString(3,c.getNumtel());
            ste.setString(4,c.getEmail());
            ste.setString(5,c.getAdresse()); 
            ste.setString(6,c.getPassword());  
            ste.setInt(7,c.getId());    

                ste.executeUpdate();
                System.out.println("client modifié");
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        }
        
    

    @Override
    public void supprimerClient(Client c) {
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement("DELETE FROM client where id = ?");
            preparedStatement.setInt(1,c.getId());
            preparedStatement.executeUpdate();
             System.out.println("client cupprimé");
        } catch (SQLException ex) {
             Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Client> afficherClients() {
        
List<Client> clients = new ArrayList<>();
try {
    String sql = "SELECT * FROM client";
    Statement stmt = myconn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
        Client cl = new Client(
            rs.getInt("id"),
            rs.getString("nom"),
            rs.getString("prenom"),
            rs.getString("numtel"),
            rs.getString("email"),
            rs.getString("adresse"),
            rs.getString("password"));
        clients.add(cl);
    }
} catch (SQLException ex) {
    // Log the exception or rethrow it
    throw new RuntimeException("Failed to get clients", ex);
} 

return clients;

    }
    
     public Client getUserData(String emaill) {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        String query = "SELECT * FROM client where email = '" + emaill +"'";
        try { //System.out.println(username);
            PreparedStatement ste = myconn.prepareStatement(query);
            ste.executeQuery();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                client.setId(rs.getInt("id"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setNumtel(rs.getString("numtel"));
                client.setEmail(rs.getString("email"));
                client.setAdresse(rs.getString("adresse"));
                client.setPassword(rs.getString("password"));
                
                clients.add(client);
            } //System.out.println(client);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return client;
    }
     
     
}
