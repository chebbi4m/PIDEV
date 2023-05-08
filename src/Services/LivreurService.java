/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Livreur;
import java.sql.*;
import java.util.List;
import Interfaces.ILivreur;
import Interfaces.ILivreur;
import Entities.Livreur;
import Utils.MyConnection;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author yasoulanda
 */
public class LivreurService implements ILivreur {
    Connection myconn = MyConnection.getInstance().getConnexion();
        Livreur livreur = new Livreur();

    @Override
    public void ajouterLivreur(Livreur liv) {
        try {
            String sql="insert into livreur values(?,?,?,?,?,?)"; 
            PreparedStatement ste= myconn.prepareStatement(sql);
            ste.setInt(1, liv.getId());
            ste.setString(2, liv.getNom());
            ste.setString(3, liv.getPrenom());
            ste.setString(4, liv.getEmail());
            ste.setString(5, liv.getNumtel());
            ste.setString(6, liv.getPassword());
            ste.executeUpdate();
            System.out.println("livreur ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void supprimerLivreur(Livreur liv) {
        try{
            String sql="DELETE FROM livreur WHERE id=?"; 
            PreparedStatement ste=myconn.prepareStatement(sql);
            ste.setInt(1, liv.getId());
            ste.executeUpdate();
            System.out.println("Livreur with id" + " "+ liv.getId()+ " " + "LIVREUR DELETED SUCCESSFULLY");
        }
        catch (SQLException e) {
            System.out.println(e);    
        }
    }
    
    @Override
    public void modifierLivreur(Livreur liv) {
    try {
        String sql = "UPDATE livreur SET nom=?, prenom=?, email=?, numtel=?, password=?  WHERE id=?";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setString(1, liv.getNom());
        ste.setString(2, liv.getPrenom());
        ste.setString(3, liv.getEmail());
        ste.setString(4, liv.getNumtel());
        ste.setString(5, liv.getPassword());
        ste.setInt(6, liv.getId());
        ste.executeUpdate();
        System.out.println("livreur with id " + liv.getId() + " updated successfully!");
    } catch (SQLException e) {
        System.out.println(e);
    }
}
    
    @Override
    public void chercherLivreur(Livreur liv){
        try{
        String sql = "SELECT * FROM livreur nom=?, WHERE id=?";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setString(1, liv.getNom());
        System.out.println("livreur with id " + liv.getId() + " " + "et" + " " + "nom " + liv.getNom() + " " + "est trouvé!");   
        }catch (SQLException e) {
        System.out.println(e);
        }
    }
    @Override
    public List<Livreur> afficherLivreur() {
        List <Livreur> livreur = new ArrayList<>();
        try {
            String sql = "select * from livreur";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Livreur liv = null;
                liv = new Livreur(    
                        s.getString("nom"),
                        s.getString("prenom"),
                        s.getString("email"),
                        s.getString("numtel"),
                        s.getString("password")) ;
                        livreur.add(liv);           
                
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return livreur;
    }
  public Livreur getUserData(String emaill) {
        ObservableList<Livreur> livreurs = FXCollections.observableArrayList();
        String query = "SELECT * FROM livreur where email = '" + emaill +"'";
        try {
            PreparedStatement ste = myconn.prepareStatement(query);
            ste.executeQuery();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                livreur.setId(rs.getInt("id"));
                livreur.setNom(rs.getString("nom"));
                livreur.setPrenom(rs.getString("prenom"));
                livreur.setNumtel(rs.getString("numtel"));
                livreur.setEmail(rs.getString("email"));
                livreur.setPassword(rs.getString("password"));
                
                livreurs.add(livreur);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return livreur;
    }
}