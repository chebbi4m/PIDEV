/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Partenaire;

import java.net.URL;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.image.ImageView;
import taktak.entities.LivreurInterface;
import taktak.entities.Partenaire;
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
    private TableColumn<LivreurInterface, ?> colnumtel;
    @FXML
    private TableColumn<LivreurInterface, ?> colNbreColisCourant;
    @FXML
    private TableColumn<LivreurInterface, ?> colNbreColistotal;
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

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) {
    }

    @FXML
    private void Ajouter(ActionEvent event) {
    }

    @FXML
    private void Supprimer(ActionEvent event) {
    }
    
    
    
    
    
    
    
    
     public void chercherLivreur(LivreurInterface lv){
    

   
      
   }
     
//public ObservableList<LivreurInterface> getLivreurInterfaceList() throws SQLException {
//    ObservableList<LivreurInterface> livreurList = FXCollections.observableArrayList();
//
//       List <LivreurInterface> LivreurD = new ArrayList<>();
//        try {
//            String sql = "select * from livreur";
//            Statement ste = myconn.createStatement();
//            ResultSet s = ste.executeQuery(sql);
//            while (s.next()) {
//
//              LivreurInterface lv = = new LivreurInterface(  
//                        s.getInt("id"),
//                        s.getString("nom"),
//                        s.getString("prenom"),
//                        s.getString("email"),
//                        s.getString("numtel"),
//                        s.getString("login"),
//                        s.getString("mdp")) ;
//                    LivreurD.add(lv);           
//                
//            }
//        }catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return LivreurD; 
//    }
//     
// public void afficherLivreurD() throws SQLException{
// 
//     ObservableList<LivreurInterface> List = getLivreurInterfaceList();
//
//     
//     colnom.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("nom"));   
//    colprenom.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("prenom"));
//     colemail.setCellValueFactory(new PropertyValueFactory<LivreurInterface , Integer>("email"));     
//     colnumtel.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("numtel"));
//    colNbreColisCourant.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("NbreColisCourant"));
//    colNbreColistotal.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("NbreColisCourant"));
//    LivreurInterface.setItems(List);
//     
// }
}





    
    

