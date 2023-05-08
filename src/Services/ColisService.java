/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Colis;
import java.sql.*;
import java.util.List;
import Interfaces.IColis;
import Utils.MyConnection;
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
            String sql="insert into colis values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
            PreparedStatement ste= myconn.prepareStatement(sql);
            ste.setInt(1, cls.getId());
            ste.setString(2, cls.getRef());
            ste.setInt(3, cls.getHauteur());
            ste.setInt(4, cls.getLargeur());
            ste.setInt(5, cls.getPoids());
            ste.setBoolean(6, cls.getFragile());
            ste.setBoolean(7, cls.getInflammable());
            ste.setString(8, cls.getDepart());
            ste.setString(9, cls.getDestination());
            ste.setString(10, cls.getEtat_colis());
            ste.setString(11, cls.getZone());
            ste.setBoolean(12, cls.getUrgent());
            ste.setInt(13, cls.getPrix());
            ste.setInt(14, cls.getId_client());
            ste.setInt(15, cls.getId_livreur());
            ste.setInt(16, cls.getId_partenaire());
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
            ste.setString(1, cls.getRef());
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
            String sql = "UPDATE colis SET id=?, hauteur=?, largeur=?, poids=?, prix=?, fragile=?,depart=?, destination=?, id_client=?, id_livreur=? WHERE ref=?";
            PreparedStatement ste=myconn.prepareStatement(sql);
            ste.setInt(1, cls.getId()); 
            ste.setString(2, cls.getRef());
            ste.setInt(3, cls.getHauteur());
            ste.setInt(4, cls.getLargeur());
            ste.setInt(5, cls.getPoids());
            ste.setBoolean(6, cls.getFragile());
            ste.setBoolean(7, cls.getInflammable());
            ste.setString(8, cls.getDepart());
            ste.setString(9, cls.getDestination());
            ste.setString(10, cls.getEtat_colis());
            ste.setString(11, cls.getZone());
            ste.setBoolean(12, cls.getUrgent());
            ste.setInt(13, cls.getPrix());
            ste.setInt(14, cls.getId_client());
            ste.setInt(15, cls.getId_livreur());
            ste.setInt(16, cls.getId_partenaire());    
            ste.executeUpdate();
            System.out.println("colis with ref " + cls.getRef() + " updated successfully!");    
        }
         catch (SQLException e) {
            System.out.println(e);    
        }
    }
    
    
    @Override
public List<Colis> afficherColis() {
    List<Colis> colis = new ArrayList<>();
    try {
        String sql = "SELECT * FROM colis";
        Statement ste = myconn.createStatement();
        ResultSet s = ste.executeQuery(sql);
        while (s.next()) {
            Colis cls = new Colis(
                    s.getString("ref"),
                    s.getInt("hauteur"),
                    s.getInt("largeur"),
                    s.getInt("poids"),
                    s.getBoolean("fragile"),
                    s.getBoolean("inflammable"),
                    s.getString("depart"),
                    s.getString("destination"),
                    s.getString("etat_colis"),
                    s.getString("zone"),
                    s.getBoolean("urgent"),
                    s.getInt("prix"),
                    s.getInt("id_client"),
                    s.getInt("id_livreur"),
                    s.getInt("id_partenaire")
            );
            colis.add(cls);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return colis;
}
   
}
