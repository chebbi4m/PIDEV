/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
public class ClientReclamationController implements Initializable {

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
    @FXML
    private Label type_text2;
    @FXML
    private TextArea reclamation_text;
    @FXML
    private TextField ref_colis_text;
    @FXML
    private TextField stars_text;
    @FXML 
    private TextField personne_reclame_text;
    @FXML
    private Label type_text3;
    @FXML
    private ComboBox<String> type_reclamation_box;
    @FXML
    private Label type_text1;
    @FXML
    private Button submit_btn;
    ObservableList<reclamation> ReclamationList = FXCollections.observableArrayList();
    @FXML
    private Label type_text31;
    @FXML
    private Label type_text311;
    @FXML
    private Button modifer_button;
    @FXML
    private Button delete_btn;

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
//
//        
////        Client pr = new Client();
////        ClientService ps = new ClientService();
////        pr = (Client) UserSession.INSTANCE.get("client");
////        nomlogin.setText(pr.getLogin());
        loadClientReclamation();
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
            String sql = "SELECT * FROM reclamation WHERE id_client = (SELECT id FROM client WHERE user_id = ?)";
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1, id_client);
            System.out.println(id_client);
            ResultSet resultSet = ste.executeQuery();

            while (resultSet.next()) {
                String text = resultSet.getString("text");
                String type_reclamation = resultSet.getString("type_reclamation");
                String refColis = resultSet.getString("ref");
                Date date_reclamation = resultSet.getDate("date");
                int stars = resultSet.getInt("stars");


                
                reclamation reclamation = new reclamation(text, type_reclamation, date_reclamation, refColis, stars);
                ReclamationList.addAll(reclamation);
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
        Connection myconn = MyConnection.getInstance().getConnexion();
        ReclamationService rs = new ReclamationService();
        emailService es = new emailService();
        
//        Client client = new Client();
//        client = (Client) UserSession.INSTANCE.get("client");
        
        
        
        Boolean ref_checker = false;
        
//        int id_client = client.getId();
        int id_client = 26;
        String type_reclamation = type_reclamation_box.getValue();
        LocalDateTime now = LocalDateTime.now();
        Date date_reclamation = Date.valueOf(now.toLocalDate());
        String text = reclamation_text.getText();
        String ref_colis = ref_colis_text.getText();
        String personne_reclame = personne_reclame_text.getText();
        int stars = Integer.parseInt(stars_text.getText());

        PreparedStatement stmt = myconn.prepareStatement( "SELECT id FROM reclamation WHERE ref = ?");
        stmt.setString(1, ref_colis);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                reclamation rec = new reclamation(id,text,stars);
                rs.modiferReclamation(rec);
                System.out.println("aaaa");
            } else {
                System.out.println("bbbb");
                
                ref_checker = true;

                if (ref_checker == true){
                    reclamation rec = new reclamation(1, text, personne_reclame, type_reclamation, date_reclamation, id_client, ref_colis, stars);

                    rs.ajouterReclamation(rec);
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
        loadClientReclamation();
        
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void modifier_reclamation(ActionEvent event) throws SQLException {
        Connection myconn = MyConnection.getInstance().getConnexion();
        ReclamationService rs = new ReclamationService();

        // Get the selected item from the table
        reclamation selectedReclamation = reclamation_table.getSelectionModel().getSelectedItem();

        // Print the values of the selected item
        if (selectedReclamation != null) {
            PreparedStatement ste = myconn.prepareStatement("SELECT id FROM reclamation where text = ?");
            ste.setString(1, selectedReclamation.getText());
            ResultSet resultSet = ste.executeQuery();

            while (resultSet.next()) {
                int reclamation_id = resultSet.getInt("id");
                System.out.println("Selected reclamation id: " + reclamation_id);
                type_reclamation_box.setValue(selectedReclamation.getType_reclamation());
                stars_text.setText(String.valueOf(selectedReclamation.getStars()));
                personne_reclame_text.setText("cannot be changed");
                ref_colis_text.setText(selectedReclamation.getRef_colis());
                reclamation_text.setText(selectedReclamation.getText());

            }
            
        } else {
            System.out.println("No reclamation selected");
        }
    }


    @FXML
    private void delete_reclamation(ActionEvent event) throws SQLException {
        Connection myconn = MyConnection.getInstance().getConnexion();
        ReclamationService rs = new ReclamationService();

        // Get the selected item from the table
        reclamation selectedReclamation = reclamation_table.getSelectionModel().getSelectedItem();

        // Print the values of the selected item
        if (selectedReclamation != null) {
            PreparedStatement ste = myconn.prepareStatement("SELECT id FROM reclamation where text = ?");
            ste.setString(1, selectedReclamation.getText());
            ResultSet resultSet = ste.executeQuery();

            while (resultSet.next()) {
                int reclamation_id = resultSet.getInt("id");
                System.out.println("Selected reclamation id: " + reclamation_id);
                rs.deleteReclamation(reclamation_id);
                loadClientReclamation();
            }
            
        } else {
            System.out.println("No reclamation selected");
        }
    }
    
}
