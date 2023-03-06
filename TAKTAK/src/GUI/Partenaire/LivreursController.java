/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Partenaire;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import taktak.entities.Colis;
import taktak.entities.LivreurInterface;

import taktak.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Najet
 */
public class LivreursController implements Initializable {
Connection myconn = MyConnection.getInstance().getConnexion(); // connexion à la base de données
    @FXML
    private Button btnlogout;
    @FXML
    private ImageView logo;
    
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TableView<LivreurInterface> tvLiveurs; 
    @FXML
    private TableColumn<LivreurInterface, String> colnom;
    @FXML
    private TableColumn<LivreurInterface, String> colprenom;
    @FXML
    private TableColumn<LivreurInterface, String> colemail;
    @FXML
    private TableColumn<LivreurInterface, String> colnumtel;
    @FXML
    private TableColumn<LivreurInterface, Integer> colNbreColisCourant;
    @FXML
    private TableColumn<LivreurInterface, Integer> colNbreColistotal;  
    @FXML
    private TableColumn<LivreurInterface, Integer> colNbreReclamation;
    @FXML
    private TableColumn<LivreurInterface, String> coldisponibilité;
    @FXML
    private Button btnRecherche;
    @FXML
    private TextField tfLivreur;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNumtel;
    @FXML
    private TableView<Colis> tvColis;
    @FXML
    private TableColumn<Colis, String> colref;
    @FXML
    private TableColumn<Colis, Double> colhauteur;
    @FXML
    private TableColumn<Colis, Double> collargeur;
    @FXML
    private TableColumn<Colis, Double> colpoids;
    @FXML
    private TableColumn<Colis, Double> colprix;
    @FXML
    private TableColumn<Colis, Boolean> colfragile;
    @FXML
    private TableColumn<Colis, Boolean> colinflammable;
    @FXML
    private TableColumn<Colis, String> coldepart;
    @FXML
    private TableColumn<Colis, String> coldestination;
    @FXML
    private TableColumn<Colis, String> coletat_colis;
    @FXML
    private TableColumn<Colis, String> colzone;
    @FXML
    private TableColumn<Colis, Boolean> colurgent;
    @FXML
    private TableColumn<Colis, String> collivreur;
    @FXML
    private Button btnaffecter;
    @FXML
    private TableView<Colis> tvcolis1;
    @FXML
    private TableColumn<Colis, String> colref1;
    @FXML
    private TableColumn<Colis, Double> colhauteur1;
    @FXML
    private TableColumn<Colis, Double> collargeur1;
    @FXML
    private TableColumn<Colis, Double> colpoids1;
    @FXML
    private TableColumn<Colis, Double> colprix1;
    @FXML
    private TableColumn<Colis, Boolean> colfragile1;
    @FXML
    private TableColumn<Colis, Boolean> colinflammable1;
    @FXML
    private TableColumn<Colis, String> coldepart1;
    @FXML
    private TableColumn<Colis, String> coldestination1;
    @FXML
    private TableColumn<Colis, String> coletatcolis1;
    @FXML
    private TableColumn<Colis, String> colzone1;
    @FXML
    private TableColumn<Colis, Boolean> colurgent1;
    
   
  
 

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
             afficherLivreur();
    } catch (SQLException ex) {
        Logger.getLogger(LivreursController.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
            afficherColis();//affichage de colis de chaque livreur
    } catch (SQLException ex) {
        Logger.getLogger(LivreursController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
      checkDisponibility();
    try {
        affichercolis();//affichage de colis 
    } catch (SQLException ex) {
        Logger.getLogger(LivreursController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        
        modifierLivreur();
        Alert3();
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
        ajouterLivreur();
        Alert1();
       
    }
  

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
        supprimerLivreur();
        Alert2();
    }
    
    @FXML
    private void affecterColisAuLivreur(ActionEvent event) {
         checkDisponibility();
         
         Alert();
    }
    
    //fonction de disponnibilté du livreur + incrementation du colis
   
    public void checkDisponibility() { 
 LivreurInterface liv = new LivreurInterface();
 
    String sql = "UPDATE livreur SET nbre_colis_courant = nbre_colis_courant + 1, nbre_colis_total = nbre_colis_total + 1 WHERE nbre_colis_courant < 5 AND id = ?";
    try {
        
        PreparedStatement ste = myconn.prepareStatement(sql);                
         
         ste.setInt(1, liv.getNbre_colis_courant());
         ste.setInt(2, liv.getNbre_colis_total());
        ste.setInt(3, 2);   //id                                           
        ste.executeUpdate();
        System.out.println( liv.getNom());
    } catch (SQLException ex) {
        System.out.println(ex);
       System.out.println("livreur n'est pas disponible");
    } 
 }
     

    
    
    
    
    
    
    

    
    
   @FXML   /// btn recherche 
   public void chercherLivreur() throws SQLException{
       
       affichercherche();
       
       
   }
   
   
    //button recherche afficher par nom
        ObservableList<LivreurInterface> chercherLivreurInterfaceList() throws SQLException {
    ObservableList<LivreurInterface> livreurList = FXCollections.observableArrayList();
    try {
        String sql = "SELECT * FROM livreur WHERE id = ?";// nom=?
        PreparedStatement pstmt = myconn.prepareStatement(sql);
        pstmt.setString(1, tfLivreur.getText());
        ResultSet s = pstmt.executeQuery(); // execute the query without any argument
        while (s.next()) {
            LivreurInterface lv = new LivreurInterface(  
                    s.getInt("id"),
                    s.getString("nom"),
                    s.getString("prenom"),
                    s.getString("email"),
                    s.getString("numtel"),
                    s.getInt("nbre_reclamation"),
                    s.getInt("nbre_colis_total"),
                    s.getInt("nbre_colis_courant"),
                    s.getString("login"),
                    s.getString("mdp")) ;
       
            
            livreurList.add(lv);           
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return livreurList; 
}
     public void affichercherche() throws SQLException{
 
     ObservableList<LivreurInterface> List = chercherLivreurInterfaceList();

   // colID.setCellValueFactory(new PropertyValueFactory<LivreurInterface , Integer>("id"));  
    colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));   
    colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    colemail.setCellValueFactory(new PropertyValueFactory<>("email"));     
    colnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
    colNbreReclamation.setCellValueFactory(new PropertyValueFactory<>("nbre_reclamation"));
    colNbreColistotal.setCellValueFactory(new PropertyValueFactory<LivreurInterface ,Integer>("nbre_colis_total"));
    colNbreColisCourant.setCellValueFactory(new PropertyValueFactory<LivreurInterface ,Integer>("NbreColisCourant"));
    coldisponibilité.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("disponible"));
   // colLogin.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("login"));     
  //  colMdp.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("mdp"));
    tvLiveurs.setItems(List);
     
 }    
// the end of this button recherche
     
     
     
     
     

 private void ajouterLivreur() throws SQLException{

// String query = "INSERT INTO livreur VALUES ('" + tfID.getText() + "', '" + tfNom.getText() + "', '" + tfPrenom.getText() + "', '" + tfEmail.getText()
//         + "'"  + ", '" + tfNumtel.getText() +  "')";
//         executeQuery(query);
    
     try {
            //Inserer le Partenaire
            PreparedStatement pstmt = myconn.prepareStatement("INSERT INTO `livreur` (`id`, `nom`, `prenom`, `email`, `numtel`, `nbre_reclamation`, `nbre_colis_total`, `nbre_colis_courant`, `login`, `mdp`,`id_partenaire`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
           
            pstmt.setInt(1, 6);
            pstmt.setString(2,tfNom.getText());
            pstmt.setString(3, tfPrenom.getText());
            pstmt.setString(4, tfEmail.getText());
            pstmt.setString(5, tfNumtel.getText());
            pstmt.setInt(6, 0);
            pstmt.setInt(7, 0);
            pstmt.setInt(8, 0);
            pstmt.setString(9, "");
            pstmt.setString(10, "");
            pstmt.setInt(11, 1);
          
            
            pstmt.executeUpdate();
            System.out.println("livreur insére");
         } catch (SQLException ex) {
           System.out.println(ex);
        } afficherLivreur();
}
     
     private void modifierLivreur() throws SQLException{
      try {
            //modifier le Partenaire
            PreparedStatement pstmt = myconn.prepareStatement(" UPDATE `livreur` SET `nom`=?, `prenom`=?, `email`=?, `numtel`=?, `nbre_reclamation`=?, `nbre_colis_total`=?, `nbre_colis_courant`=?, `login`=?, `mdp`=?, `id_partenaire`=? WHERE `id`=?");
           
            
            pstmt.setString(1,tfNom.getText());
            pstmt.setString(2, tfPrenom.getText());
            pstmt.setString(3, tfEmail.getText());
            pstmt.setString(4, tfNumtel.getText());
            pstmt.setInt(5, 0);
            pstmt.setInt(6, 0);
            pstmt.setInt(7, 0);
            pstmt.setString(8, "");
            pstmt.setString(9, "");
            pstmt.setInt(10, 1);
            pstmt.setInt(11, 6);
          
            
            pstmt.executeUpdate();
            System.out.println("livreur modifier");
         } catch (SQLException ex) {
           System.out.println(ex);
        } afficherLivreur();
     }
     
     
     private void supprimerLivreur() throws SQLException{
     String sql ="DELETE from livreur where id =?";
        try{
                PreparedStatement ste = myconn.prepareStatement(sql);
                ste.setInt(1, 6);
                ste.executeUpdate();
                    System.out.println("livreur supprimer");
        }
        catch(SQLException ex){
            System.out.println(ex);
        } afficherLivreur();
     
         
         
     }
     public void update() throws SQLException {
     //String sql="UPDATE colis SET id_livreur = ? WHERE id = ?;";
     //String query = "UPDATE livreur SET nbre_colis_courant = nbre_colis_courant + 1, nbre_colis_total = nbre_colis_total + 1 WHERE nbre_colis_courant < 5 AND id = ?";
String sql="START TRANSACTION;\n" +
"UPDATE livreur SET nbre_colis_courant = nbre_colis_courant + 1, nbre_colis_total = nbre_colis_total + 1 WHERE nbre_colis_courant < 5 AND id = ?;\n" +
"UPDATE colis SET id_livreur = ? WHERE id = ?;\n" +
"COMMIT;";
                Statement ste = myconn.createStatement();
                ResultSet s = ste.executeQuery(sql);        
                        
         
}
   
     
  //   
public ObservableList<LivreurInterface> getLivreurInterfaceList() throws SQLException {
   ObservableList<LivreurInterface> livreurList = FXCollections.observableArrayList();
        try {
            String sql = "select * from livreur ";//where id_partenaire=1
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

              LivreurInterface lv = new LivreurInterface(  
                        s.getInt("id"),
                        s.getString("nom"),
                        s.getString("prenom"),
                        s.getString("email"),
                        s.getString("numtel"),
                        s.getInt("nbre_reclamation"),
                        s.getInt("nbre_colis_total"),
                        s.getInt("nbre_colis_courant"),
                        s.getString("login"),
                        s.getString("mdp")) ;
              
                    livreurList.add(lv);           
                
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return livreurList; 
        
    }
     
 public void afficherLivreur() throws SQLException{
 
     ObservableList<LivreurInterface> List = getLivreurInterfaceList();

   // colID.setCellValueFactory(new PropertyValueFactory<LivreurInterface , Integer>("id"));  
    colnom.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("nom"));   
    colprenom.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("prenom"));
    colemail.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("email"));     
    colnumtel.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("numtel"));
    colNbreReclamation.setCellValueFactory(new PropertyValueFactory<LivreurInterface ,Integer>("nbre_reclamation"));
    colNbreColistotal.setCellValueFactory(new PropertyValueFactory<LivreurInterface ,Integer>("nbre_colis_total"));
    colNbreColisCourant.setCellValueFactory(new PropertyValueFactory<LivreurInterface , Integer>("nbre_colis_courant"));
    coldisponibilité.setCellValueFactory(new PropertyValueFactory<LivreurInterface ,String>("disponible"));
   // colLogin.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("login"));     
  //  colMdp.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("mdp"));
    tvLiveurs.setItems(List);
     
 }

     
//affichage de colis 
    
    public ObservableList<Colis> getColisList() throws SQLException {
   ObservableList<Colis> ColisList = FXCollections.observableArrayList();
        try {
            String sql = "select * from colis";
                PreparedStatement ste = myconn.prepareStatement(sql);                         
                ResultSet s = ste.executeQuery();

            
            while (s.next()) {

              Colis cls = new Colis(                          
                        s.getString("ref"),
                        s.getInt("hauteur"),
                        s.getInt("largeur"),
                        s.getInt("poids"),
                        s.getInt("prix"),
                        s.getBoolean("fragile"),
                        s.getBoolean("inflammable"),
                        s.getString("depart"),
                        s.getString("destination"),
                        s.getString("etat_colis"),
                        s.getString("zone"),
                        s.getBoolean("urgent"),
                        s.getInt("id_client"),
                        s.getInt("id_livreur")) ;
                        ColisList.add(cls);           
                
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ColisList; 
        
    }
    public void affichercolis() throws SQLException{
 
     ObservableList<Colis> List = getColisList();

   // colID.setCellValueFactory(new PropertyValueFactory<LivreurInterface , Integer>("id"));  
    colref1.setCellValueFactory(new PropertyValueFactory<Colis , String>("ref"));   
    colhauteur1.setCellValueFactory(new PropertyValueFactory<Colis , Double>("hauteur"));
    collargeur1.setCellValueFactory(new PropertyValueFactory<Colis , Double>("largeur"));     
    colpoids1.setCellValueFactory(new PropertyValueFactory<Colis , Double>("poids"));
    colprix1.setCellValueFactory(new PropertyValueFactory<Colis ,Double>("prix"));
    colfragile1.setCellValueFactory(new PropertyValueFactory<Colis ,Boolean>("fragile"));
    colinflammable1.setCellValueFactory(new PropertyValueFactory<Colis , Boolean>("inflammable"));
    coldepart1.setCellValueFactory(new PropertyValueFactory<Colis , String>("depart")); 
    coldestination1.setCellValueFactory(new PropertyValueFactory<Colis , String>("destination")); 
    coletatcolis1.setCellValueFactory(new PropertyValueFactory<Colis , String>("etat_colis")); 
    colzone1.setCellValueFactory(new PropertyValueFactory<Colis , String>("zone")); 
    colurgent1.setCellValueFactory(new PropertyValueFactory<Colis ,Boolean>("urgent")); 
   
  
    tvcolis1.setItems(List);      
    }  
       
 public void afficherColis() throws SQLException{
 
 
 }

    
    
 //affichage de colis du livreur par email
    
    public ObservableList<Colis> getColisList(String email) throws SQLException {
   ObservableList<Colis> ColisList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT colis.ref, colis.hauteur, colis.largeur, colis.poids, colis.prix, colis.fragile, colis.inflammable, colis.depart, colis.destination, colis.etat_colis, colis.zone, colis.urgent, colis.id_client, colis.id_livreur " +
             "FROM colis " +
             "JOIN (SELECT id FROM livreur WHERE email = ?) livreur ON colis.id_livreur = livreur.id";
                PreparedStatement ste = myconn.prepareStatement(sql);          
                ste.setString(1, email);
                ResultSet s = ste.executeQuery();

            
            while (s.next()) {

              Colis cls = new Colis(                          
                        s.getString("ref"),
                        s.getInt("hauteur"),
                        s.getInt("largeur"),
                        s.getInt("poids"),
                        s.getInt("prix"),
                        s.getBoolean("fragile"),
                        s.getBoolean("inflammable"),
                        s.getString("depart"),
                        s.getString("destination"),
                        s.getString("etat_colis"),
                        s.getString("zone"),
                        s.getBoolean("urgent"),
                        s.getInt("id_client"),
                        s.getInt("id_livreur")) ;
                        ColisList.add(cls);           
                
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ColisList; 
        
    }
     @FXML // click on query will showed on tfField
    private void handleMouseAction(MouseEvent event) throws SQLException {
        
     LivreurInterface liv  = tvLiveurs.getSelectionModel().getSelectedItem();     
         tfNom.setText( liv.getNom());
          tfPrenom.setText( liv.getPrenom());
           tfEmail.setText( liv.getEmail());
            tfNumtel.setText("" + liv.getNumtel());
         ObservableList<Colis> List = getColisList(liv.getEmail());

   // colID.setCellValueFactory(new PropertyValueFactory<LivreurInterface , Integer>("id"));  
    colref.setCellValueFactory(new PropertyValueFactory<Colis , String>("ref"));   
    colhauteur.setCellValueFactory(new PropertyValueFactory<Colis , Double>("hauteur"));
    collargeur.setCellValueFactory(new PropertyValueFactory<Colis , Double>("largeur"));     
    colpoids.setCellValueFactory(new PropertyValueFactory<Colis , Double>("poids"));
    colprix.setCellValueFactory(new PropertyValueFactory<Colis ,Double>("prix"));
    colfragile.setCellValueFactory(new PropertyValueFactory<Colis ,Boolean>("fragile"));
    colinflammable.setCellValueFactory(new PropertyValueFactory<Colis , Boolean>("inflammable"));
    coldepart.setCellValueFactory(new PropertyValueFactory<Colis , String>("depart")); 
    coldestination.setCellValueFactory(new PropertyValueFactory<Colis , String>("destination")); 
    coletat_colis.setCellValueFactory(new PropertyValueFactory<Colis , String>("etat_colis")); 
    colzone.setCellValueFactory(new PropertyValueFactory<Colis , String>("zone")); 
    colurgent.setCellValueFactory(new PropertyValueFactory<Colis ,Boolean>("urgent")); 
    collivreur.setCellValueFactory(new PropertyValueFactory<Colis , String>("livreur")); 
  
    tvColis.setItems(List);      
    }  

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    
 public void Alert(){  
 Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Colis affecté");
    alert.setContentText("Le colis a été affecté au livreur avec succès.");
    alert.showAndWait();
 }
 public void Alert1(){  
 Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Livreur Ajouter");
    alert.setContentText("Le Livreur a été ajouté avec succès.");
    alert.showAndWait();
 }
 
  public void Alert2(){  
 Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Livreur Supprimer");
    alert.setContentText("Le Livreur a été Supprimé avec succès.");
    alert.showAndWait();
 }
  public void Alert3(){  
 Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Livreur Modifier");
    alert.setContentText("Le Livreur a été Modifié avec succès.");
    alert.showAndWait();
 }
 
 
}





    
    

