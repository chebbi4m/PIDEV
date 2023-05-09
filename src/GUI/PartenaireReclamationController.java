/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Client;
import Entities.Livreur;
import Entities.LivreurInterface;
import Entities.Partenaire;
import Entities.reclamation;
import Entities.reclamationTable;
import Services.LivreurService;
import Services.PartenaireService;
import Services.ReclamationService;
import Services.emailService;
import Session.UserSession;
import Utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author nedia
 */
public class PartenaireReclamationController implements Initializable {

    
    
    ObservableList<reclamationTable> ReclamationList = FXCollections.observableArrayList();
    @FXML
    private Label nomlogin;
    @FXML
    private JFXButton historique;
    @FXML
    private JFXButton historique1;
    @FXML
    private JFXButton historique2;
    @FXML
    private JFXButton reclamation_btn;
    @FXML
    private JFXButton fermer;
    @FXML
    private JFXButton retour;
    @FXML
    private TableView<reclamationTable> reclamation_table;
    @FXML
    private TableColumn<reclamationTable, String> livreur_prenom_col;
    @FXML
    private TableColumn<reclamationTable, String> livreur_nom_col;
    @FXML
    private TableColumn<reclamationTable, String> type_col;
    @FXML
    private TableColumn<reclamationTable, String> reclamation_col;
    @FXML
    private TableColumn<reclamationTable, String> date_col;
    @FXML
    private TableColumn<reclamationTable, String> ref_col;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //        Partenaire pr = new Partenaire();
//        PartenaireService ps = new PartenaireService();
//        pr = (Partenaire) UserSession.INSTANCE.get("partenaire");
//        nomlogin.setText(pr.getLogin());
        loadPartenaireReclamation();    
        } catch (SQLException ex) {
            Logger.getLogger(PartenaireReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }    

    @FXML
    public void cancel (ActionEvent e){
               Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
    }
    
    @FXML
    public void retourner (ActionEvent e) throws IOException{
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("LoginClient.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();
    
    }

    private void loadPartenaireReclamation() throws SQLException {
//        ReclamationService rs = new ReclamationService();
        Connection myconn = MyConnection.getInstance().getConnexion();
//        
        Partenaire pr = new Partenaire();
        pr = (Partenaire) UserSession.INSTANCE.get("partenaire");
         
        int partenaire_id = pr.getId();
        String sql = "SELECT r.*, l.nom , l.prenom FROM reclamation r JOIN colis c ON c.ref = r.ref JOIN livreur l ON l.id = c.id_livreur JOIN partenaire p ON p.id = l.id_partenaire WHERE p.user_id = ?";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setInt(1, partenaire_id);
        ResultSet partenaireResult = ste.executeQuery();
        while (partenaireResult.next()) {
            String text = partenaireResult.getString("text");
            String ref_colis = partenaireResult.getString("ref");
            String type_reclamation = partenaireResult.getString("type_reclamation");
            Date date_reclamation = partenaireResult.getDate("date");
            int stars = partenaireResult.getInt("stars");
            String livreur_nom = partenaireResult.getString("nom");
            String livreur_prenom = partenaireResult.getString("prenom");
            reclamationTable reclamation = new reclamationTable(ref_colis, date_reclamation, type_reclamation, text, stars, livreur_nom, livreur_prenom);
            ReclamationList.add(reclamation);

        }
        ref_col.setCellValueFactory(new PropertyValueFactory<>("ref_colis"));
        livreur_prenom_col.setCellValueFactory(new PropertyValueFactory<>("livreur_prenom"));
        livreur_nom_col.setCellValueFactory(new PropertyValueFactory<>("livreur_nom"));
        type_col.setCellValueFactory(new PropertyValueFactory<>("type_reclamation"));
        reclamation_col.setCellValueFactory(new PropertyValueFactory<>("text"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        reclamation_table.setItems(ReclamationList); 
        
        
        
    }


    @FXML
    private void reclamation(ActionEvent event) {
        
    }

    @FXML
    private void cancel(MouseEvent event) {
    }

    @FXML
    private void retourner(MouseEvent event) {
    }



    
}
