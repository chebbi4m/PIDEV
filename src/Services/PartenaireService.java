/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Partenaire;
import Interfaces.IPartenaire;
import Interfaces.IPartenaire;
import Entities.Partenaire;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Najet
 */
public class PartenaireService implements IPartenaire{
    
      Partenaire partenaire = new Partenaire();
       Connection myconn= MyConnection.getInstance().getConnexion();
 
  
    @Override
    public void ajouterPartenaire(Partenaire p) {
        
 String sql="insert into partenaire (nom,email,numtel,login,mdp) values(?,?,?,?,?)";
    try{
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setString(1,p.getNom());
        ste.setString(2,p.getEmail());
        ste.setString(3,p.getNumtel());
        ste.setString(4,p.getLogin());
        ste.setString(5,p.getMdp());
       
        
        ste.executeUpdate();
        System.out.println("partenaire ajouté");
 
       }
    catch(SQLException ex ){
        System.out.print(ex);
        }
    
    
    }
    @Override
    public void modifierPartenaire(Partenaire p) {
        
        
    }

    @Override
    public void supprimerPartenaire(Partenaire p) {
        String sql ="DELETE from partenaire where id =?";
        try{
                PreparedStatement ste = myconn.prepareStatement(sql);
                ste.setInt(1,p.getId()); 
                ste.executeUpdate();
                    System.out.println("partenaire supprimé");
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
                            s.getString("nom"),
                            s.getString("email"),  
                            s.getString("numtel"),
                            s.getString("login"),
                            s.getString("mdp") );                   
                    partenaire.add(p);
                        }
           System.out.println("partenaire affiché");
       }
       catch(SQLException ex){
           System.out.println(ex);
       }
       
       return partenaire;
       
       
    }
    
    public Partenaire getUserData(String username) {
        ObservableList<Partenaire> partenaires = FXCollections.observableArrayList();
        String query = "SELECT * FROM partenaire where login = '" + username +"'";
        try { //System.out.println(username);
            PreparedStatement ste = myconn.prepareStatement(query);
            ste.executeQuery();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                partenaire.setId(rs.getInt("id"));
                partenaire.setNom(rs.getString("nom"));
                partenaire.setNumtel(rs.getString("numtel"));
                partenaire.setEmail(rs.getString("email"));
                partenaire.setLogin(rs.getString("login"));
                partenaire.setMdp(rs.getString("mdp"));
                
                partenaires.add(partenaire);
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return partenaire;
    }
    
    
}
