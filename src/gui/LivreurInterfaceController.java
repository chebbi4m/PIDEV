/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Client;
import Entities.Livreur;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Entities.LivreurInterface;
import Entities.LivreurTableViewData;
import Services.LivreurService;
import Session.UserSession;
import Utils.MyConnection;
import javafx.scene.Node;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Cheima
 */
public class LivreurInterfaceController implements Initializable {
    Connection myconn = MyConnection.getInstance().getConnexion();
    @FXML
    private VBox LivreurFonct;
    @FXML
    private HBox ListeCont;
    @FXML
    private HBox HistoriqueCont;
    @FXML
    private HBox ReclamationsCont;
    @FXML
    private ImageView LogoCont;
    @FXML
    private Button LogoutBtn;
    @FXML
    private TableColumn<LivreurInterface, String> RefCol;
    @FXML
    private TableColumn<LivreurInterface, String> NomCol;
    @FXML
    private TableColumn<LivreurInterface, String> DestinationCol;
    @FXML
    private TableColumn<LivreurInterface, String> PayementCol;
    @FXML
    private TableColumn<LivreurInterface, String> EtatCol;
    @FXML
    private ImageView LivreurLogo;
    @FXML
    private TableView<LivreurTableViewData> LivreurTable;
    @FXML
    private Label LivreurCont;
    @FXML
    private Button SavingButton;
    @FXML
    private ComboBox<String> etat;
    
    @FXML
    private Label name;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        Livreur lv = new Livreur();
          LivreurService ls = new LivreurService();
          lv = (Livreur) UserSession.INSTANCE.get("livreur");
          name.setText(lv.getLogin());
        
        try {
            displayLivreurTable();
        } catch (SQLException e){
            System.out.println(e);
        }
      

    }    

    @FXML
    private void OpenProfile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LivreurCrud.fxml"));
        Parent root = loader.load();
        Stage CurrentStage = (Stage) LivreurLogo.getScene().getWindow();
        Scene scene = new Scene(root,1080,720);
        CurrentStage.setScene(scene);
        CurrentStage.show();
  
     
    }

    @FXML
    private void LogoutClick(ActionEvent event) throws IOException {
    Stage currentStage = (Stage) LogoutBtn.getScene().getWindow();
    currentStage.close();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("LivreurInterface.fxml"));
    Parent root = loader.load();
    Stage loginStage = new Stage();
    Scene scene = new Scene(root,1080,720);
    loginStage.setScene(scene);
    loginStage.show();
    }
    
    private void displayLivreurTable() throws SQLException {
    Connection myconn = MyConnection.getInstance().getConnexion();
    Livreur lv= new Livreur();
    LivreurService ls= new LivreurService();
    lv= (Livreur) UserSession.INSTANCE.get("livreur");
    String login= lv.getLogin();
    String sql = "SELECT colis.ref, CONCAT(client.nom, ' ', client.prenom) AS nom, colis.destination, paiement.type, colis.etat_colis " +
             "FROM colis " +
             "INNER JOIN client ON colis.id_client = client.id " +
             "INNER JOIN livreur ON colis.id_livreur = livreur.id " +
             "LEFT JOIN paiement ON colis.id = paiement.id_colis " +
             "WHERE colis.id_livreur = ( " +
             "SELECT id " +
             "FROM livreur " +
             "WHERE login = '"+login+"')";
    PreparedStatement ps = myconn.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    ObservableList<LivreurTableViewData> dataList = FXCollections.observableArrayList();
    while (rs.next()) {
        String ref = rs.getString("ref");
        String nom = rs.getString("nom");
        String destination = rs.getString("destination");
        String type = rs.getString("type");
        String etat_colis = rs.getString("etat_colis");
        LivreurTableViewData data = new LivreurTableViewData(ref,nom, destination, type, etat_colis);
        dataList.add(data); 
    }
    RefCol.setCellValueFactory(new PropertyValueFactory<>("ref"));
    NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
    DestinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
    PayementCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    EtatCol.setCellValueFactory(new PropertyValueFactory<>("etat_colis"));
    LivreurTable.setItems(dataList);
    etat.getItems().addAll("en cours", "livree", "non livree");
}
        @FXML
     private void saveEtat(ActionEvent event) throws SQLException {
     LivreurTableViewData selectedData = LivreurTable.getSelectionModel().getSelectedItem();
     if (selectedData == null) {
     return;
     }
     String ref= selectedData.getRef();
     String selectedEtat = etat.getValue();
     if (selectedEtat == null) {
     return;
     }
     String sql = "UPDATE colis SET etat_colis = ? WHERE ref = ?";
     try (PreparedStatement ps = myconn.prepareStatement(sql)) {
     ps.setString(1, selectedEtat);
     ps.setString(2, ref);
     int rowsAffected = ps.executeUpdate();
     if (rowsAffected > 0) {
     System.out.println("Successfully updated the etat_colis column in the database.");
     
             // get email and password from user input or configuration
             String username = "cheima.douiss@esprit.tn";
             String password = "12345654321280458";

             // create properties object to configure email server and protocol
             Properties props = new Properties();
             props.put("mail.smtp.auth", "true");
             props.put("mail.smtp.starttls.enable", "true");
             props.put("mail.smtp.host", "smtp.gmail.com");
             props.put("mail.smtp.port", "587");

             // create session with authentication information
             Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                 protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(username, password);
                 }
             });  

             // get the email address of the client
             String emailSql = "SELECT client.email " +
                          "FROM client " +
                          "INNER JOIN colis ON client.id = colis.id_client " +
                          "WHERE colis.ref = ?";
             try (PreparedStatement ste = myconn.prepareStatement(emailSql)) {
                 ste.setString(1, ref);
                 ResultSet emailResult = ste.executeQuery();
                     String clientEmail = null;
                     if (emailResult.next()) {
                         clientEmail = emailResult.getString("email");
                     }
                     // send email to client
                     if (clientEmail != null) {
                         try {
                             Message message = new MimeMessage(session);
                             message.setFrom(new InternetAddress(username));
                             message.setRecipients(
                                 Message.RecipientType.TO,
                                 InternetAddress.parse(clientEmail)
                             );
                             message.setSubject("Modification etat colis");
                             message.setText("Salut " + selectedData.getNom() + ",\n\nVotre livraison avec la reférence " 
                                 + selectedData.getRef() + " est \"" + selectedEtat + "\".\n\nmerci,\nBonne journée");

                             // send email
                             Transport.send(message);

                             // show confirmation message
                             Alert alert = new Alert(AlertType.INFORMATION);
                             alert.setTitle("Email sent");
                             alert.setHeaderText(null);
                             alert.setContentText("Email sent successfully.");
                             alert.showAndWait();

                         } catch (MessagingException e) {
                             // show error message if email fails to send
                             Alert alert = new Alert(AlertType.ERROR);
                             alert.setTitle("Email error");
                             alert.setHeaderText(null);
                             alert.setContentText("Failed to send email.");
                             alert.showAndWait();
                         }
                     } else {
                         System.out.println("Could not retrieve email address for client.");
                     }
                 }
             }
        }
     
     }


        public void retourner (ActionEvent e) throws IOException{

        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("LoginLivreur.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();
    
}
    
    public void historiquePage (ActionEvent e) throws IOException{

        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("HistoriqueLivreur.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();
    
}
}