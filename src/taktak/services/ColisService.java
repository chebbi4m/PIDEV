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
            String sql="insert into colis values(null,?,?,?,?,?,?,?,?,?,?,?)"; 
            PreparedStatement ste= myconn.prepareStatement(sql);
            ste.setString(1, cls.getRef());
            ste.setInt(2, cls.getHauteur());
            ste.setInt(3, cls.getLargeur());
            ste.setInt(4, cls.getPoids());
            ste.setInt(5, cls.getPrix());
            ste.setBoolean(6, cls.getFragile());
            ste.setString(7, cls.getDepart());
            ste.setString(8, cls.getDestination());
            ste.setString(9, cls.getNom_receveur());
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
        String sql="update colis set (?,?,?,?,?,?,?,?,?,?,?) where id= ?";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
                ste.setString(1, cls.getRef());
                ste.setInt(2, cls.getHauteur());
                ste.setInt(3, cls.getLargeur());
                ste.setInt(4, cls.getPoids());
                ste.setInt(5, cls.getPrix());
                ste.setBoolean(6, cls.getFragile());
                ste.setString(8, cls.getDepart());
                ste.setString(7, cls.getDestination());
                ste.setString(8, cls.getNom_receveur());
                ste.setInt(9, cls.getId_client());
                ste.setInt(10, cls.getId_paiment());
                ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }    
    }
    
    @Override
    public void supprimerColis(Colis cls) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Colis> afficherColis() {
        List <Colis> colis = new ArrayList<>();
        try {
            String sql = "select * from colis";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Colis cls = new Colis(s.getString("ref"),
                        s.getInt(3),
                        s.getInt(4),
                        s.getInt(5),
                        s.getInt(6),
                        s.getBoolean(7),
                        s.getString("depart"),
                        s.getString("destination"),
                        s.getString("nom_receveur"),
                        s.getInt(11),
                        s.getInt(12));
                colis.add(cls);
       
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return colis;
    } 
    
}
