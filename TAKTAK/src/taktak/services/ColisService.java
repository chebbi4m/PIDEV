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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author LENOVO THINKPAD E15
 */
public class ColisService implements IColis{
    
    Connection myconn = MyConnection.getInstance().getConnexion();
    
    @Override
    public void ajouterColis(Colis cls) {
        try {
            String sql="insert into colis values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
            PreparedStatement ste= myconn.prepareStatement(sql);
            ste.setString(1, cls.getRef());
            ste.setInt(2, cls.getHauteur());
            ste.setInt(3, cls.getLargeur());
            ste.setInt(4, cls.getPoids());
            ste.setInt(5, cls.getPrix());
            ste.setBoolean(6, cls.getFragile());
            ste.setBoolean(7, cls.getInflammable());
            ste.setString(8, cls.getDepart());
            ste.setString(9, cls.getDestination());
            ste.setString(10, cls.getEtat_colis());
            ste.setString(11, cls.getZone());
            ste.setBoolean(12, cls.getUrgent());
            ste.setInt(13, cls.getId_client());
            ste.setInt(14, cls.getId_livreur());
            ste.executeUpdate();
            System.out.println("colis ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
     @Override
    public void modifierColis(Colis cls) {
        String sql="update colis set (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) where id= ?";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
                ste.setString(1, cls.getRef());
                ste.setInt(2, cls.getHauteur());
                ste.setInt(3, cls.getLargeur());
                ste.setInt(4, cls.getPoids());
                ste.setInt(5, cls.getPrix());
                ste.setBoolean(6, cls.getFragile());
                ste.setBoolean(7, cls.getInflammable());
                ste.setString(8, cls.getDepart());
                ste.setString(9, cls.getDestination());
                ste.setString(10, cls.getEtat_colis());
                ste.setString(11, cls.getZone());
                ste.setBoolean(12, cls.getUrgent());
                ste.setInt(12, cls.getId_client());
                ste.setInt(11, cls.getId_livreur());
                ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }    
    }
    
    /*@Override
    public void supprimerColis(Colis cls) {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/
    
    @Override
    public void supprimerColis(Colis cls) {
                try {
            PreparedStatement preparedStatement = myconn.prepareStatement("DELETE FROM colis where id = ?");
            preparedStatement.setInt(1,cls.getId());
            preparedStatement.executeUpdate();
             System.out.println("colis supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(ColisService.class.getName()).log(Level.SEVERE, null, ex);
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

                Colis cls = new Colis(s.getString("ref"),
                        s.getInt(3),
                        s.getInt(4),
                        s.getInt(5),
                        s.getInt(6),
                        s.getBoolean(7),
                        s.getBoolean(8),
                        s.getString("depart"),
                        s.getString("destination"),
                        s.getString("etat_colis"),
                        s.getString("zone"),
                        s.getBoolean(13),
                        s.getInt(14),
                        s.getInt(15));
                colis.add(cls);
       
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return colis;
    } 
    
}