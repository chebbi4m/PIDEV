/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.PartenaireN;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Interfaces.IPartenaireN;

/**
 *
 * @author Najet
 */
public class PartenaireServiceN implements IPartenaireN{
    
    
       Connection myconn= MyConnection.getInstance().getConnexion();
 
  
   
    @Override
    public void modifierPartenaire(PartenaireN p) {
        String sql = "UPDATE partenaire SET nom = ?, email = ?, numtel = ?, zone = ?, prix_poids = ?, prix_zone = ?, inflammable = ?, fragile = ? WHERE id = ?";
        try{
            PreparedStatement ste = myconn.prepareStatement(sql);

        ste.setString(1,p.getNom());
        ste.setString(2,p.getEmail());
        ste.setInt(3,p.getNumtel());           
        ste.setString(4,p.getZone());
        ste.setDouble(5,p.getPrix_poids());
        ste.setDouble(6,p.getPrix_zone());
        ste.setBoolean(7,p.isInflammable());
        ste.setBoolean(8,p.isFragile());       
        ste.setInt(9,p.getId());
        ste.executeUpdate();
                    System.out.println("partenaire modifier");
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        
        
        
    }

    @Override
    public void supprimerPartenaire(PartenaireN p) {
        String sql ="DELETE from partenaire where id =?";
        try{
                PreparedStatement ste = myconn.prepareStatement(sql);
                ste.setInt(1,p.getId()); 
                ste.executeUpdate();
                    System.out.println("partenaire supprimer");
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
       
    }

    @Override
    public List<PartenaireN> afficherPartenaire()
    {
        List<PartenaireN> partenaire = new ArrayList<>();
         try{
       String sql="select * from partenaire";
       Statement ste = myconn.createStatement();
        ResultSet s = ste.executeQuery(sql);
        while(s.next()){
        
                    PartenaireN p = new PartenaireN(
                            s.getInt("id"),
                            s.getString("nom"),
                            s.getString("email"),  
                            s.getInt("numtel"),                            
                            s.getString("zone"),                         
                            s.getDouble("prix_poids"),
                            s.getDouble("prix_zone"),
                            s.getBoolean("inflammable"),
                            s.getBoolean("fragile"),
                            s.getString("login"),
                            s.getString("Password")
                            
                    );                   
                    partenaire.add(p);
                    
                   
                        }
           System.out.println("partenaire afficher");
       }
       catch(SQLException ex){
           System.out.println(ex);
       }
       
       return partenaire;
       
       
    }
    
}
