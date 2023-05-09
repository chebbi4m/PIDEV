/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Client;
import Entities.Livreur;
import Entities.LivreurInterface;
import Entities.reclamation;
import Services.LivreurService;
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
public class LivreurReclamationController implements Initializable {

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
    private TableView<reclamation> reclamation_table;
    @FXML
    private TableColumn<reclamation, String> ref_col;
    @FXML
    private TableColumn<reclamation, String> type_col;
    @FXML
    private TableColumn<reclamation, String> reclamation_col;
    @FXML
    private TableColumn<reclamation, String> date_col;
    @FXML
    private TableColumn<reclamation, String> stars_col;
    
    ObservableList<reclamation> ReclamationList = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Livreur pr = new Livreur();
//        LivreurService ps = new LivreurService();
//        pr = (Livreur) UserSession.INSTANCE.get("livreur");
//        nomlogin.setText(pr.getLogin());
        loadLivreurReclamation();
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
    
    private void loadLivreurReclamation() {
        ReclamationService rs = new ReclamationService();
        ReclamationList.clear();
        Connection myconn = MyConnection.getInstance().getConnexion();
        
        Livreur livreur = new Livreur();
        livreur = (Livreur) UserSession.INSTANCE.get("livreur");
        
        try {
            String sql = "SELECT r.*FROM livreur l INNER JOIN colis c ON l.id = c.id_livreur INNER JOIN reclamation r ON c.ref = r.ref WHERE l.user_id = ?";
            PreparedStatement preparedStatement = myconn.prepareStatement(sql);
            preparedStatement.setInt(1, livreur.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String text = resultSet.getString("text");
                String ref_colis = resultSet.getString("ref");
                String type_reclamation = resultSet.getString("type_reclamation");
                Date date_reclamation = resultSet.getDate("date");
                int stars = resultSet.getInt("stars");
                reclamation reclamation = new reclamation(text, type_reclamation, date_reclamation, ref_colis,stars);
                ReclamationList.add(reclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        ref_col.setCellValueFactory(new PropertyValueFactory<>("ref_colis"));
        type_col.setCellValueFactory(new PropertyValueFactory<>("type_reclamation"));
        reclamation_col.setCellValueFactory(new PropertyValueFactory<>("text"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        stars_col.setCellValueFactory(new PropertyValueFactory<>("stars"));
        reclamation_table.setItems(ReclamationList);



        
    }
    
    private void loadWorkReclamation() {
        Connection myconn = MyConnection.getInstance().getConnexion();
        try {
            PreparedStatement ste = myconn.prepareStatement("SELECT * FROM reclamation where login = ?");
            Client client = new Client();
            client = (Client) UserSession.INSTANCE.get("client");
            int id_client = client.getId();
            ste.setInt(1, id_client);
            ResultSet resultSet = ste.executeQuery();

            while (resultSet.next()) {
                String text = resultSet.getString("text");
                String type_reclamation = resultSet.getString("type_reclamation");
                String refColis = resultSet.getString("ref");
                Date date_reclamation = resultSet.getDate("date");

                
                
                reclamation reclamation = new reclamation(text, type_reclamation, date_reclamation, refColis);
                ReclamationList.addAll(reclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        ref_col.setCellValueFactory(new PropertyValueFactory<>("ref_colis"));
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
