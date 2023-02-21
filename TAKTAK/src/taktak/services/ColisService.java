/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taktak.services;

import taktak.entities.Colis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



import taktak.interfaces.IColis;
import taktak.utils.MyConnection;

/**
 *
 * @author yasoulanda
 */
public class ColisService implements IColis{
    Connection myconn = MyConnection.getInstance().getConnexion();
    
    @Override
    public void ajouterColis(Colis cls) {
        try {
            String sql="insert into colis values(?,?,?,?,?,?,?,?,?,?,?)"; 
            PreparedStatement ste= myconn.prepareStatement(sql);
            
            ste.setInt(1, cls.getId());           
            ste.setString(2, cls.getRef());
            ste.setInt(3, cls.getHauteur());
            ste.setInt(4, cls.getLargeur());
            ste.setInt(5, cls.getPoids());
            ste.setInt(6, cls.getPrix());
            ste.setBoolean(7, cls.getFragile());
             ste.setString(8, cls.getDepart());           
            ste.setString(9, cls.getDestination());
            ste.setInt(10, cls.getId_client());
            ste.setInt(11, cls.getId_paiment());
            ste.executeUpdate();
            System.out.println("colis ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
     @Override
    public void modifierColis(Colis cls) {
        String sql="UPDATE colis SET ref = ?,hauteur = ?,largeur = ?,poids = ?,prix = ?, fragile = ?,depart = ?,destination = ?,id_client = ?,id_paiement = ? WHERE id = ?";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
                
                ste.setString(1, cls.getRef());
                ste.setInt(2, cls.getHauteur());
                ste.setInt(3, cls.getLargeur());
                ste.setInt(4, cls.getPoids());
                ste.setInt(5, cls.getPrix());
                ste.setBoolean(6, cls.getFragile());
                ste.setString(7, cls.getDepart());           
                ste.setString(8, cls.getDestination());
                ste.setInt(9, cls.getId_client());
                ste.setInt(10, cls.getId_paiment());
                ste.setInt(11, cls.getId());                          
                ste.executeUpdate();
                System.out.println("colis modifié");
        } catch (SQLException ex) {
            System.out.println(ex);
        }    
    }
    
    @Override
    public void supprimerColis(Colis cls) {
                try {
            PreparedStatement preparedStatement = myconn.prepareStatement("DELETE FROM colis where id = ?");
            preparedStatement.setInt(1,cls.getId());
            preparedStatement.executeUpdate();
             System.out.println("colis cupprimé");
        } catch (SQLException ex) {
            Logger.getLogger(ColisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Colis> afficherColis() {
        List <Colis> colis = new ArrayList<>();
        try {
            String sql = "select * from colis ";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Colis cls = new Colis(
                        s.getInt(1),
                        s.getString("ref"),
                        s.getInt(3),
                        s.getInt(4),
                        s.getInt(5),
                        s.getInt(6),
                        s.getBoolean(7),
                        s.getString("depart"),                        
                        s.getString("destination"),
                        s.getInt(10),
                        s.getInt(11));
                colis.add(cls);
       
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return colis;
    }
 
}
