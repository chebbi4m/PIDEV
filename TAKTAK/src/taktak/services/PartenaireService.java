/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.services;

import taktak.entities.Partenaire;
import taktak.interfaces.IPartenaire;
import taktak.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Najet
 */
public class PartenaireService implements IPartenaire{
    
    
       Connection myconn= MyConnection.getInstance().getConnexion();
 
  
    @Override
    public void ajouterPartenaire(Partenaire p) {
        
 String sql="insert into partenaire (id,nom,adresse,email,numtel,login,mdp) values(?,?,?,?,?,?,?)";
    try{
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setInt(1,p.getId());
        ste.setString(2,p.getNom());
        ste.setString(3,p.getEmail());
        ste.setString(4,p.getAdresse());        
        ste.setInt(5,p.getNumtel());
        ste.setString(6,p.getLogin());
        ste.setString(7,p.getMdp());
       
        
        ste.executeUpdate();
        System.out.println("partenaire ajoutée");
 
       }
    catch(SQLException ex ){
        System.out.print(ex);
        }
    
    
    }
    @Override
    public void modifierPartenaire(Partenaire p) {
        String sql ="UPDATE partenaire SET nom = ? ,  adresse = ? , email = ? ,numtel = ?  , login = ? ,mdp = ? where id =?";
        try{
            PreparedStatement ste = myconn.prepareStatement(sql);

                ste.setString(1,p.getNom());
                ste.setString(6,p.getAdresse());               
                ste.setString(5,p.getEmail());
                ste.setInt(4,p.getNumtel());                
                ste.setString(2,p.getLogin());
                ste.setString(3,p.getMdp());
                ste.setInt(7,p.getId());
                ste.executeUpdate();
                    System.out.println("partenaire modifier");
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        
        
        
    }

    @Override
    public void supprimerPartenaire(Partenaire p) {
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
    public List<Partenaire> afficherPartenaire()
    {
        List<Partenaire> partenaire = new ArrayList<>();
         try{
       String sql="select * from partenaire";
       Statement ste = myconn.createStatement();
        ResultSet s = ste.executeQuery(sql);
        while(s.next()){
        
                    Partenaire p = new Partenaire(
                            s.getInt("id"),
                            s.getString("nom"),
                            s.getString("adresse"),
                            s.getString("email"),
                            s.getInt("numtel"),
                            s.getString("login"),
                            s.getString("mdp")
                            
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
