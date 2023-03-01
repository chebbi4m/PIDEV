/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import taktak.entities.LivreurInterface;
import taktak.interfaces.ILivreurInterface;
import taktak.utils.MyConnection;

/**
 *
 * @author Cheima
 */
public class LivreurInterfaceService implements ILivreurInterface{
    Connection myconn = MyConnection.getInstance().getConnexion();
    public void modifierLivreurD(LivreurInterface lv, int id) {
       try {
        String sql = "UPDATE livreur SET nom=?, prenom=?, email=?, numtel=?, login=?, mdp=? WHERE id = ? ";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setString(1, lv.getNom());
        ste.setString(2, lv.getPrenom());
        ste.setString(3, lv.getEmail());
        ste.setString(4, lv.getNumtel());
        ste.setString(5, lv.getLogin());
        ste.setString(6, lv.getMdp());
        ste.executeUpdate();
    }
       catch (SQLException e) {
        System.out.println(e);
    }
    }

    @Override
    public List<LivreurInterface> afficherLivreurD() {
       List <LivreurInterface> LivreurD = new ArrayList<>();
        try {
            String sql = "select * from livreur";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

//                LivreurInterface lv = null;
//                lv = new LivreurInterface(  
//                        s.getInt("id"),
//                        s.getString("nom"),
//                        s.getString("prenom"),
//                        s.getString("email"),
//                        s.getString("numtel"),
//                        s.getString("login"),
//                        s.getString("mdp")) ;
//                        LivreurD.add(lv);           
//                
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LivreurD; 
    }

    @Override
    public void modifierLivreurD(LivreurInterface lv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      
    public void chercherLivreur(LivreurInterface lv){
        try{
        String sql = "SELECT * FROM livreur where nom=? ";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setString(1, lv.getNom());
        
        System.out.println("livreur with id " + lv.getId() + " " + "et" + " " + "nom " + lv.getNom() + " " + "est trouvé!");   
        }catch (SQLException e) {
        System.out.println(e);
        }
    }

    @Override
    public void ajouterLivreur(LivreurInterface lv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}