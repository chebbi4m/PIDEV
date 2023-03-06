/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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

    @FXML private JFXButton fermer;
    @FXML private JFXButton retour;
    @FXML private Label nomlogin;
    @FXML
    private JFXButton historique;
    @FXML
    private JFXButton historique1;
    @FXML
    private JFXButton historique2;
    @FXML
    private JFXButton reclamation_btn;
    @FXML
    private TableView<reclamationTable> reclamation_table;
    @FXML
    private TableColumn<reclamationTable, String> ref_col;
    @FXML
    private TableColumn<reclamationTable, String> type_col;
    @FXML
    private TableColumn<reclamationTable, String> reclamation_col;
    @FXML
    private TableColumn<reclamationTable, String> date_col;
    @FXML
    private TableColumn<reclamationTable, String> personne_col;
    
    ObservableList<reclamationTable> ReclamationList = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Partenaire pr = new Partenaire();
        PartenaireService ps = new PartenaireService();
        pr = (Partenaire) UserSession.INSTANCE.get("partenaire");
        nomlogin.setText(pr.getLogin());
        
        try {
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
    
//       public void hist (ActionEvent e) throws IOException{
//        
//
//        
//        Stage stage = new Stage ();
//        Parent root = FXMLLoader.load(getClass().getResource("HistoriquePartenaire.fxml"));  
//        Scene scene = new Scene (root);
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.show();
//        ((Node)e.getSource()).getScene().getWindow().hide();
//    
//}
//       
//       public void histLiv (ActionEvent e) throws IOException{
//        
//
//        
//        Stage stage = new Stage ();
//        Parent root = FXMLLoader.load(getClass().getResource("HistoriqueLivreur.fxml"));  
//        Scene scene = new Scene (root);
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.show();
//        ((Node)e.getSource()).getScene().getWindow().hide();
//    
//}


    
    private int getLivreuridByRef(String ref) throws SQLException{
        Connection myconn = MyConnection.getInstance().getConnexion();
        PreparedStatement ste = myconn.prepareStatement("SELECT id_livreur FROM colis where ref = '" + ref+"'");
        ResultSet resultSet = ste.executeQuery();
        System.out.println();
        int livreur_id = 0;
        if (resultSet.next()) {
            livreur_id = resultSet.getInt(1);
            
        }
        return livreur_id;
        
        
        
    }
    
    private void loadPartenaireReclamation() throws SQLException {
        ReclamationService rs = new ReclamationService();
        ReclamationList.clear();
        Connection myconn = MyConnection.getInstance().getConnexion();
        
        Partenaire pr = new Partenaire();
        pr = (Partenaire) UserSession.INSTANCE.get("partenaire");
         
        int partenaire_id = pr.getId();
        String sql = "SELECT * " +
                   "FROM reclamation " +
                   "WHERE personne_reclamé IN ( " +
                   "  SELECT CONCAT(prenom, ' ', nom) " +
                   "  FROM livreur " +
                   "  WHERE id_partenaire = ? " +
                   ")";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setInt(1, partenaire_id);
        ResultSet partenaireResult = ste.executeQuery();
        while (partenaireResult.next()) {

            System.out.println(partenaireResult.getString("personne_reclamé"));
            String text = partenaireResult.getString("text");
            String ref_colis = partenaireResult.getString("ref");
            String personne_reclame = partenaireResult.getString("personne_reclamé");
            String type_reclamation = partenaireResult.getString("type_reclamation");
            Date date_reclamation = partenaireResult.getDate("date");
            reclamationTable reclamation = new reclamationTable(ref_colis, date_reclamation, type_reclamation, text, personne_reclame);
            ReclamationList.add(reclamation);

        }
        ref_col.setCellValueFactory(new PropertyValueFactory<>("ref_colis"));
        personne_col.setCellValueFactory(new PropertyValueFactory<>("nom_du_reclame"));
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
