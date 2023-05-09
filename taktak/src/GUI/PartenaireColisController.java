/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import taktak.entities.ColisN;
import taktak.entities.LivreurInterfaceN;
import taktak.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Najet
 */
public class PartenaireColisController implements Initializable {
 Connection myconn = MyConnection.getInstance().getConnexion(); // connexion à la base de données
    @FXML
    private JFXButton historiquebtn;
    @FXML
    private JFXButton Accueilbtn;
    @FXML
    private JFXButton Meslivreurbtn;
    @FXML
    private JFXButton reclamation_btn;
    @FXML
    private JFXButton fermer;
    @FXML
    private JFXButton retour;
     @FXML
    private TableView<ColisN> tvColis;
    @FXML
    private TableColumn<ColisN, String> colref;
    @FXML
    private TableColumn<ColisN, Double> colhauteur;
    @FXML
    private TableColumn<ColisN, Double> collargeur;
    @FXML
    private TableColumn<ColisN, Double> colpoids;
    @FXML
    private TableColumn<ColisN, Double> colprix;
    @FXML
    private TableColumn<ColisN, Boolean> colfragile;
    @FXML
    private TableColumn<ColisN, Boolean> colinflammable;
    @FXML
    private TableColumn<ColisN, String> coldepart;
    @FXML
    private TableColumn<ColisN, String> coldestination;
    @FXML
    private TableColumn<ColisN, String> coletat_colis;
    @FXML
    private TableColumn<ColisN, String> colzone;
    @FXML
    private TableColumn<ColisN, Boolean> colurgent;
    private TableColumn<ColisN, String> collivreur;

     @FXML
    private TableView<LivreurInterfaceN> tvLiveurs; 
    @FXML
    private TableColumn<LivreurInterfaceN, String> colnom;
    @FXML
    private TableColumn<LivreurInterfaceN, String> colprenom;
    @FXML
    private TableColumn<LivreurInterfaceN, String> colemail;
    @FXML
    private TableColumn<LivreurInterfaceN, String> colnumtel;
    @FXML
    private TableColumn<LivreurInterfaceN, String> coladresse;
    @FXML
    private TableColumn<LivreurInterfaceN, Integer> colNbreColisCourant;
    @FXML
    private TableColumn<LivreurInterfaceN, Integer> colNbreColistotal;  
    @FXML
    private TableColumn<LivreurInterfaceN, Integer> colNbreReclamation;
    @FXML
    private Button btnRecherche;
    @FXML
    private TextField tfLivreur;

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
     try {
         affichageColis();
     } catch (SQLException ex) {
         Logger.getLogger(PartenaireColisController.class.getName()).log(Level.SEVERE, null, ex);
     }
     try {
         afficherLivreur();
     } catch (SQLException ex) {
         Logger.getLogger(PartenaireColisController.class.getName()).log(Level.SEVERE, null, ex);
     }
     
       
    }    

    @FXML
    private void hist(MouseEvent event) {
    }

    @FXML
    private void hist(ActionEvent event) {
    }

    @FXML
    private void open_reclamation(ActionEvent event) {
    }

    @FXML
    private void cancel(MouseEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void retourner(MouseEvent event) {
    }

    @FXML
    private void retourner(ActionEvent event) {
    }

   
    
    
    
    
     //   affichage des livreurs
public ObservableList<LivreurInterfaceN> getLivreurInterfaceList() throws SQLException {
   ObservableList<LivreurInterfaceN> livreurList = FXCollections.observableArrayList();
        try {
            String sql = "select * from livreur where id_partenaire = ?";
            PreparedStatement pstmt = myconn.prepareStatement(sql);
            pstmt.setInt(1, 31);//set id_partenaire
            ResultSet s = pstmt.executeQuery();
            while (s.next()) {

              LivreurInterfaceN lv = new LivreurInterfaceN(  
                        s.getInt("id"),
                        s.getString("nom"),
                        s.getString("prenom"),
                        s.getString("email"),
                        s.getString("numtel"),
                        s.getString("adresse"),
                        s.getInt("nbre_reclamation"),
                        s.getInt("nbre_colis_total"),
                        s.getInt("nbre_colis_courant"),                        
                        s.getString("password")) ;
              
                    livreurList.add(lv);           
                
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return livreurList; 
        
    }

     
 public void afficherLivreur() throws SQLException{
 
     ObservableList<LivreurInterfaceN> List = getLivreurInterfaceList();

     
    colnom.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("nom"));   
    colprenom.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("prenom"));
    colemail.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("email")); 
    coladresse.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("adresse"));     
    colnumtel.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("numtel"));
    colNbreReclamation.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN ,Integer>("nbre_reclamation"));
    colNbreColistotal.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN ,Integer>("nbre_colis_total"));
    colNbreColisCourant.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , Integer>("nbre_colis_courant"));
    
   
    tvLiveurs.setItems(List);
     
 }
    //affichage de colis du livreur par email
    
   public ObservableList<ColisN> getAllColisList(int id_livreur) throws SQLException {
    ObservableList<ColisN> ColisList = FXCollections.observableArrayList();
    try {
        String sql = "SELECT ref, hauteur, largeur, poids, prix, fragile, inflammable, depart, destination, etat_colis, zone, urgent FROM colis WHERE id_livreur = ?";

        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setInt(1,id_livreur);
        ResultSet s = ste.executeQuery();

        while (s.next()) {
            ColisN cls = new ColisN(                          
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
                s.getBoolean("urgent")) ;
            ColisList.add(cls);           
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return ColisList; 
}


private void affichageColis() throws SQLException {
 int id_livreur = 6;
    ObservableList<ColisN> List = getAllColisList(id_livreur);

    colref.setCellValueFactory(new PropertyValueFactory<ColisN, String>("ref"));
    colhauteur.setCellValueFactory(new PropertyValueFactory<ColisN, Double>("hauteur"));
    collargeur.setCellValueFactory(new PropertyValueFactory<ColisN, Double>("largeur"));     
    colpoids.setCellValueFactory(new PropertyValueFactory<ColisN, Double>("poids"));
    colprix.setCellValueFactory(new PropertyValueFactory<ColisN, Double>("prix"));
    colfragile.setCellValueFactory(new PropertyValueFactory<ColisN, Boolean>("fragile"));
    colinflammable.setCellValueFactory(new PropertyValueFactory<ColisN, Boolean>("inflammable"));
    coldepart.setCellValueFactory(new PropertyValueFactory<ColisN, String>("depart")); 
    coldestination.setCellValueFactory(new PropertyValueFactory<ColisN, String>("destination")); 
    coletat_colis.setCellValueFactory(new PropertyValueFactory<ColisN, String>("etat_colis")); 
    colzone.setCellValueFactory(new PropertyValueFactory<ColisN, String>("zone")); 
    colurgent.setCellValueFactory(new PropertyValueFactory<ColisN, Boolean>("urgent")); 

   
        tvColis.setItems(List);      
   
}
 

    @FXML
private void handleMouseAction(MouseEvent event)  {
      LivreurInterfaceN liv  = tvLiveurs.getSelectionModel().getSelectedItem();     
        
}
    @FXML
    private void chercherLivreur(ActionEvent event) throws SQLException {
        String nom = tfLivreur.getText();
        ObservableList<ColisN> ColisList = FXCollections.observableArrayList();
        
            try {
       String sql = "SELECT colis.ref, colis.hauteur, colis.largeur, colis.poids, colis.prix, colis.fragile, colis.inflammable, colis.depart, colis.destination, colis.etat_colis, colis.zone, colis.urgent, colis.id_client, colis.id_livreur FROM colis JOIN (SELECT id FROM livreur WHERE nom = ?) livreur ON colis.id_livreur = livreur.id;";

        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setString(1,nom);
        ResultSet s = ste.executeQuery();

        while (s.next()) {
            ColisN cls = new ColisN(                          
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
                s.getBoolean("urgent")) ;
            ColisList.add(cls);           
        }
           ObservableList<ColisN> List = FXCollections.observableArrayList();

    colref.setCellValueFactory(new PropertyValueFactory<ColisN, String>("ref"));
    colhauteur.setCellValueFactory(new PropertyValueFactory<ColisN, Double>("hauteur"));
    collargeur.setCellValueFactory(new PropertyValueFactory<ColisN, Double>("largeur"));     
    colpoids.setCellValueFactory(new PropertyValueFactory<ColisN, Double>("poids"));
    colprix.setCellValueFactory(new PropertyValueFactory<ColisN, Double>("prix"));
    colfragile.setCellValueFactory(new PropertyValueFactory<ColisN, Boolean>("fragile"));
    colinflammable.setCellValueFactory(new PropertyValueFactory<ColisN, Boolean>("inflammable"));
    coldepart.setCellValueFactory(new PropertyValueFactory<ColisN, String>("depart")); 
    coldestination.setCellValueFactory(new PropertyValueFactory<ColisN, String>("destination")); 
    coletat_colis.setCellValueFactory(new PropertyValueFactory<ColisN, String>("etat_colis")); 
    colzone.setCellValueFactory(new PropertyValueFactory<ColisN, String>("zone")); 
    colurgent.setCellValueFactory(new PropertyValueFactory<ColisN, Boolean>("urgent")); 

   
       tvColis.setItems(ColisList);
  
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
     
    }

  //button recherche afficher par nom
        ObservableList<LivreurInterfaceN> chercherLivreurInterfaceList() throws SQLException {
    ObservableList<LivreurInterfaceN> livreurList = FXCollections.observableArrayList();
    try {
        String sql = "SELECT * FROM livreur WHERE nom = ?";// nom=?
        PreparedStatement pstmt = myconn.prepareStatement(sql);
        pstmt.setString(1, tfLivreur.getText());
        ResultSet s = pstmt.executeQuery(); // execute the query without any argument
        while (s.next()) {
            LivreurInterfaceN lv = new LivreurInterfaceN(  
                    s.getInt("id"),
                    s.getString("nom"),
                    s.getString("prenom"),
                    s.getString("email"),
                    s.getString("numtel"),
                    s.getString("adresse"),
                    s.getInt("nbre_reclamation"),
                    s.getInt("nbre_colis_total"),
                    s.getInt("nbre_colis_courant"),                    
                    s.getString("password")) ;
       
            
            livreurList.add(lv);           
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return livreurList; 
}
     public void affichercherche() throws SQLException{
 
     ObservableList<LivreurInterfaceN> List = chercherLivreurInterfaceList();

   // colID.setCellValueFactory(new PropertyValueFactory<LivreurInterface , Integer>("id"));  
    colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));   
    colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    colemail.setCellValueFactory(new PropertyValueFactory<>("email"));     
    colnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
    coladresse.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("adresse"));
    colNbreReclamation.setCellValueFactory(new PropertyValueFactory<>("nbre_reclamation"));
    colNbreColistotal.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN ,Integer>("nbre_colis_total"));
    colNbreColisCourant.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN ,Integer>("NbreColisCourant"));
    tvLiveurs.setItems(List);
     
 }    
// the end of this button recherche


}
