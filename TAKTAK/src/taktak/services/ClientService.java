package taktak.services;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.sql.*;

import taktak.interfaces.IClient;
import taktak.utils.MyConnection;

import taktak.entities.Client;


/**
 *
 * @author yasoulanda
 */
public class ClientService implements IClient{
    
     Connection myconn = MyConnection.getInstance().getConnexion();
    
@Override
    public void ajouterClient (Client c) {
       String sql="insert into client (nom, prenom ,numtel, email, adresse) values(?,?,?,?,?)";
        try{
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setString(1,c.getNom());
            ste.setString(2,c.getPrenom());
            ste.setString(3,c.getNumtel());
            ste.setString(4,c.getEmail());
            ste.setString(5,c.getAdresse());
            ste.executeUpdate();
        }catch(SQLException ex ){
        System.out.print(ex);
        }
    }

    @Override
    public void modifierClient(Client c) {
         String sql="update client (nom, prenom ,numtel, email, adresse) set(?,?,?,?,?)";
        try{
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setString(1,c.getNom());
            ste.setString(2,c.getPrenom());
            ste.setString(3,c.getNumtel());
            ste.setString(4,c.getEmail());
            ste.setString(5,c.getAdresse());
            ste.executeUpdate();
        }catch(SQLException ex ){
        System.out.print(ex);
        }
        }
        
    

    @Override
    public void supprimerClient() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void afficherClients() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
