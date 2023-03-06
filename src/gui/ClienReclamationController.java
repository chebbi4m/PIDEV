/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Client;
import Entities.reclamation;
import Services.ClientService;
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
public class ClienReclamationController implements Initializable {

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
    private Label type_text2;
    @FXML
    private TextArea reclamation_text;
    @FXML
    private TextField ref_colis_text;
    @FXML
    private Label type_text3;
    @FXML
    private ComboBox<String> type_reclamation_box;
    @FXML
    private Label type_text1;
    @FXML
    private Button submit_btn;
    ObservableList<reclamation> ReclamationList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type_reclamation_box.getItems().addAll(
                "Réclamation pour retard de livraison",
                "Réclamation pour colis perdu",
                "Réclamation pour colis endommagé",
                "Réclamation pour mauvaise manipulation du colis",
                "Réclamation concernant le comportement impoli du livreur"
        );

        
        Client pr = new Client();
        ClientService ps = new ClientService();
        pr = (Client) UserSession.INSTANCE.get("client");
        nomlogin.setText(pr.getLogin());
        
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
    
    private void loadClientReclamation() {
        ReclamationList.clear();
        Connection myconn = MyConnection.getInstance().getConnexion();
        Client client = new Client();
        client = (Client) UserSession.INSTANCE.get("client");
        int id_client = client.getId();
        try {
            PreparedStatement ste = myconn.prepareStatement("SELECT * FROM reclamation where id_client = ?");
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
    
    private void loadWorkReclamation() {
        ReclamationList.clear();
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
    
    public void clearFields(){
        ref_colis_text.clear();
        reclamation_text.clear();
        type_reclamation_box.setValue(null);
    }

    @FXML
    private void cancel(MouseEvent event) {
    }

    @FXML
    private void retourner(MouseEvent event) {
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) throws SQLException {
        ReclamationService rs = new ReclamationService();
        emailService es = new emailService();
        
        Client client = new Client();
        client = (Client) UserSession.INSTANCE.get("client");
        
        
        
        Boolean ref_checker = false;
        
        int id_client = client.getId();
        String type_reclamation = type_reclamation_box.getValue();
        LocalDateTime now = LocalDateTime.now();
        Date date_reclamation = Date.valueOf(now.toLocalDate());
        String text = reclamation_text.getText();
        String ref_colis = ref_colis_text.getText();
        int personne_reclame = getLivreuridByRef(ref_colis);
//        if (refColisChecker(ref_colis) == true){
            ref_checker = true;
//        }
        
        if (ref_checker == true){
            reclamation rec = new reclamation(1, text, personne_reclame, type_reclamation, date_reclamation, id_client, ref_colis);
            
            rs.ajouterReclamation(rec);
            rs.incrementerNbrReclamation(type_reclamation,personne_reclame);
            loadClientReclamation();
            clearFields();
            
            es.sendEmail("Reclamation", text + "\n" + "colis de ref : " + ref_colis);
            
            

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information incorrecte");
            alert.setHeaderText(null);
            alert.setContentText("La reference est incorrecte");
            alert.showAndWait();
        }
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }
    
}
