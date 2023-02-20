/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.services;
import taktak.entities.Colis;
import java.sql.*;
import java.util.List;
import taktak.interfaces.IColis;
import taktak.utils.MyConnection;
import java.util.*;
/**
 *
 * @author LENOVO THINKPAD E15
 */
public class ColisService implements IColis{
    
    Connection myconn = MyConnection.getInstance().getConnexion();
    
    @Override
    public void ajouterColis(Colis cls) {
        try {
            String sql="insert into colis values(?,?,?,?,?,?,?,?,?,?)"; 
            PreparedStatement ste= myconn.prepareStatement(sql);
            ste.setInt(1, cls.getId());
            ste.setString(2, cls.getRef());
            ste.setInt(3, cls.getHauteur());
            ste.setInt(4, cls.getLargeur());
            ste.setInt(5, cls.getPoids());
            ste.setInt(6, cls.getPrix());
            ste.setBoolean(7, cls.getFragile());
            ste.setString(8, cls.getDestination());
            ste.setInt(9, cls.getId_client());
            ste.setInt(10, cls.getId_paiement());
            ste.executeUpdate();
            System.out.println("colis ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     @Override
    public void supprimerColis(Colis cls) {
        try{
            String sql="DELETE FROM colis WHERE ref=?"; 
            PreparedStatement ste=myconn.prepareStatement(sql);
            ste.setString(2, cls.getRef());
            ste.executeUpdate();
            System.out.println("Colis with ref" + cls.getRef()+ "COLIS DELETED SUCCESSFULLY");
        }
        catch (SQLException e) {
            System.out.println(e);    
        }
    }
    @Override 
    public void modifierColis (Colis cls){
        try{
            String sql = "UPDATE colis SET id=?, hauteur=?, largeur=?, poids=?, prix=?, fragile=?, destination=?, id_client=?, id_paiement=? WHERE ref=?";
            PreparedStatement ste=myconn.prepareStatement(sql);
            ste.setInt(1, cls.getId()); 
            ste.setString(2, cls.getRef());
            ste.setInt(3, cls.getHauteur());
            ste.setInt(4, cls.getLargeur());
            ste.setInt(5, cls.getPoids());
            ste.setInt(6, cls.getPrix());
            ste.setBoolean(7, cls.getFragile());
            ste.setString(8, cls.getDestination());
            ste.setInt(9, cls.getId_client());
            ste.setInt(10, cls.getId_paiement());    
            ste.executeUpdate();
            System.out.println("colis with ref " + cls.getRef() + " updated successfully!");    
        }
         catch (SQLException e) {
            System.out.println(e);    
        }
    }
    
    
    @Override
    public List<Colis> afficherColis() {
        List <Colis> colis = new ArrayList<>();
        try {
            String sql = "select * from colis";
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
                        s.getString("destination"),
                        s.getInt(9),
                        s.getInt(10));
                colis.add(cls);
       
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return colis;
    }  
    
}
