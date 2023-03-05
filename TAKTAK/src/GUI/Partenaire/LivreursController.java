/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Partenaire;

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

import taktak.services.LivreurInterfaceService;
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
            afficherColis();
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
         addColisLivreur();
         Alert();
    }
    
    //fonction de disponnibilté du livreur + incrementation du colis
    LivreurInterface livreur = new LivreurInterface();
    public void checkDisponibility() {
    if (livreur.getNbre_colis_courant() <= 5) {
        livreur.setNbre_colis_courant(livreur.getNbre_colis_courant() + 1);
        System.out.println("Livreur disponible");
    } else {
        System.out.println("Livreur non disponible");
    }
}
   public void addColisLivreur(){   
       Colis cls = new Colis();
   String sql="update colis set Ref = ?,Hauteur =?,Largeur = ?,Poids = ?,Prix = ?, "
           + "Fragile=?,Inflammable=?,Depart=?,Destination=?,Etat_colis=?,Zone=?,Urgent=?,Id_client=?,Id_livreur=? where id= 1";//bch nzid lel colis livreur par nom f blaset id-livreur
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
                ste.setInt(13, cls.getId_client());
                ste.setInt(14, cls.getId_livreur());
                ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("colis affecté a un livreur");
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
        String sql = "SELECT * FROM livreur WHERE nom = ?";
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
                    s.getString("mdp"),
                     s.getBoolean("disponible")) ;
       
            
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
            pstmt.setInt(11, 4);
          
            
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
                ste.setInt(1, 4);
                ste.executeUpdate();
                    System.out.println("livreur supprimer");
        }
        catch(SQLException ex){
            System.out.println(ex);
        } afficherLivreur();
     
         
         
     }
   
     
     
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
                        s.getString("mdp"),
                        s.getBoolean("disponible")) ;
              
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
    colNbreReclamation.setCellValueFactory(new PropertyValueFactory<LivreurInterface ,Integer>("NbreColisCourant"));
    colNbreColisCourant.setCellValueFactory(new PropertyValueFactory<LivreurInterface ,Integer>("NbreColisCourant"));
    colNbreColistotal.setCellValueFactory(new PropertyValueFactory<LivreurInterface , Integer>("NbreColisCourant"));
    coldisponibilité.setCellValueFactory(new PropertyValueFactory<LivreurInterface ,String>("disponible"));
   // colLogin.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("login"));     
  //  colMdp.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("mdp"));
    tvLiveurs.setItems(List);
     
 }

    @FXML // click on query will showed on tfField
    private void handleMouseAction(MouseEvent event) {
        
     LivreurInterface liv  = tvLiveurs.getSelectionModel().getSelectedItem();     
         tfNom.setText( liv.getNom());
          tfPrenom.setText( liv.getPrenom());
           tfEmail.setText( liv.getEmail());
            tfNumtel.setText("" + liv.getNumtel());
            
    }  
        

    
    public ObservableList<Colis> getColisList() throws SQLException {
   ObservableList<Colis> ColisList = FXCollections.observableArrayList();
        try {
            String sql = "select * from colis ";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
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
     
 public void afficherColis() throws SQLException{
 
     ObservableList<Colis> List = getColisList();

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





    
    

