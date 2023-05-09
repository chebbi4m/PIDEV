/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entities.LivreurInterfaceN;
import Utils.MyConnection;
import Interfaces.ILivreurInterfaceN;

/**
 *
 * @author Cheima
 */
public class LivreurInterfaceServiceN implements ILivreurInterfaceN{
    Connection myconn = MyConnection.getInstance().getConnexion();
    
    
  //  public void modifierLivreurD(LivreurInterfaceN lv, int id) {
 ///      try {
  //      String sql = "UPDATE livreur SET nom=?, prenom=?, email=?, numtel=?, login=?, mdp=? WHERE id = ? ";
   //     PreparedStatement ste = myconn.prepareStatement(sql);
   //     ste.setString(1, lv.getNom());
   //     ste.setString(2, lv.getPrenom());
   //     ste.setString(3, lv.getEmail());
   //     ste.setString(4, lv.getNumtel());
   //     ste.setString(5, lv.getLogin());
   //     ste.setString(6, lv.getMdp());
   //     ste.executeUpdate();
  //  }
   //    catch (SQLException e) {
  //      System.out.println(e);
  //  }
  //  }

    @Override
    public List<LivreurInterfaceN> afficherLivreurD() {
       List <LivreurInterfaceN> LivreurD = new ArrayList<>();
        try {
            String sql = "select * from livreur";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

               LivreurInterfaceN lv= new LivreurInterfaceN(  
                       s.getInt("id"),
                       s.getString("nom"),
                       s.getString("prenom"),
                       s.getString("email"),
                       s.getString("numtel"),                      
                       s.getString("Adresse")) ;
                        LivreurD.add(lv);           
               
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LivreurD; 
    }

   
    
      
    @Override
    public void chercherLivreur(LivreurInterfaceN lv){
        try{
        String sql = "SELECT * FROM livreur where nom=? ";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setString(1, lv.getNom());
        
        System.out.println("livreur with id " + lv.getId() + " " + "et" + " " + "nom " + lv.getNom() + " " + "est trouvé!");   
        }catch (SQLException e) {
        System.out.println(e);
        }
    }
 //@Override
 //   public void modifierLivreurD(LivreurInterfaceN lv) {
  //     String sql ="UPDATE livreur SET nom = ? ,prenom=?, email = ? ,numtel = ?, nbre_reclamation=?,nbre_colis_total=?,nbre_colis_courant=?  where id =?";
  //      try{
  //          PreparedStatement ste = myconn.prepareStatement(sql);

  //      ste.setString(1,lv.getNom());
  //      ste.setString(2,lv.getPrenom());
  //      ste.setString(3,lv.getEmail());
   //     ste.setString(4,lv.getNumtel());   
   //     ste.setInt(5,lv.getNbre_reclamation());
   //     ste.setInt(6,lv.getNbre_colis_total());
   //     ste.setInt(7,lv.getNbre_colis_courant());
   //     ste.setInt(8,lv.getId());
   //     ste.executeUpdate();
    //                System.out.println("Livreur modifier");
   //     }
   //     catch(SQLException ex){
    //        System.out.println(ex);
    //    }
  //  }
    @Override
    public void ajouterLivreur(LivreurInterfaceN lv) {
        String sql ="INSERT INTO livreur (id, nom, prenom, email, numtel,adresse) VALUES (?, ?, ?, ?,?,?)";
        try{
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1,lv.getId());
            ste.setString(2,lv.getNom());
            ste.setString(3,lv.getPrenom());
            ste.setString(4,lv.getEmail());
            ste.setString(5,lv.getNumtel());    
            ste.setString(6,lv.getAdresse());
            
            ste.executeUpdate();
                System.out.println("Livreur ajouter");
        }
        catch(SQLException ex){
                System.out.println(ex);
        }
    }

    @Override
    public void supprimerLivreur(LivreurInterfaceN lv) {
        String sql ="delete from livreur  where id =?";
        try{
            PreparedStatement ste = myconn.prepareStatement(sql);      
            ste.setInt(1,lv.getId());
            ste.executeUpdate();
            System.out.println("Livreur Supprimer");
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
          
    }
    
    
    
    
  

//    public void checkDisponibility(LivreurInterfaceN livreur) {
//        if (livreur.getNbre_colis_courant() <= 5) {
//            livreur.setNbre_colis_courant(livreur.getNbre_colis_courant() + 1);
//            System.out.println(" livreur disponible");
//        } else {
//            System.out.println("livreur non disponible");
//        }
//    }

   







}