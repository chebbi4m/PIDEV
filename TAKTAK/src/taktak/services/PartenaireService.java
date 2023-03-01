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
        
 String sql="insert into partenaire (id,nom,email,numtel,moyen_transport,zone,prix_poids,prix_zone,inflammable,fragile,login,mdp) values(?,?,?,?,?,?,?,?,?,?,?,?)";
    try{
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setInt(1,p.getId());
        ste.setString(2,p.getNom());
        ste.setString(3,p.getEmail());
        ste.setInt(4,p.getNumtel());
        ste.setString(5,p.getMoyen_transport());   
        ste.setString(6,p.getZone());
        ste.setDouble(7,p.getPrix_poids());
        ste.setDouble(8,p.getPrix_zone());
        ste.setBoolean(9,p.isInflammable());
        ste.setBoolean(10,p.isFragile());
        ste.setString(11,p.getLogin());
        ste.setString(12,p.getMdp());
       
        
        ste.executeUpdate();
        System.out.println("partenaire ajoutée");
 
       }
    catch(SQLException ex ){
        System.out.print(ex);
        }
    
    
    }
    @Override
    public void modifierPartenaire(Partenaire p) {
        String sql ="UPDATE partenaire SET nom = ? , email = ? ,numtel = ?, moyen_transport=?,zone=?,prix_poids=?,prix_zone=?,inflammable=?,fragile=?,  login = ? ,mdp = ? where id =?";
        try{
            PreparedStatement ste = myconn.prepareStatement(sql);

        ste.setString(1,p.getNom());
        ste.setString(2,p.getEmail());
        ste.setInt(3,p.getNumtel());
        ste.setString(4,p.getMoyen_transport());   
        ste.setString(5,p.getZone());
        ste.setDouble(6,p.getPrix_poids());
        ste.setDouble(7,p.getPrix_zone());
        ste.setBoolean(8,p.isInflammable());
        ste.setBoolean(9,p.isFragile());
        ste.setString(10,p.getLogin());
        ste.setString(11,p.getMdp());
        ste.setInt(12,p.getId());
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
                            s.getString("email"),  
                            s.getInt("numtel"),
                            s.getString("moyen_transport"),
                            s.getString("zone"),                         
                            s.getDouble("prix_poids"),
                            s.getDouble("prix_zone"),
                            s.getBoolean("inflammable"),
                            s.getBoolean("fragile"),
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
