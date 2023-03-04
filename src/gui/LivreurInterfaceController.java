/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.mysql.cj.Session;
import com.mysql.cj.protocol.Message;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import taktak.entities.LivreurInterface;
import taktak.entities.LivreurTableViewData;
import taktak.utils.MyConnection;

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
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    String sql = "SELECT colis.ref,CONCAT(client.nom, ' ', client.prenom) AS nom,colis.destination,paiement.type,colis.etat_colis " +
            "FROM colis " +
            "INNER JOIN client ON colis.id_client = client.id " +
            "INNER JOIN livreur ON colis.id_livreur = livreur.id " +
            "LEFT JOIN paiement ON colis.id = paiement.id_colis " +
            "WHERE colis.id_livreur = ?";
    System.out.println("aaaaa");
    PreparedStatement ps = myconn.prepareStatement(sql);
    ps.setInt(1,1); 
    ResultSet rs = ps.executeQuery();
    ObservableList<LivreurTableViewData> dataList = FXCollections.observableArrayList();
    System.out.println("bbbbb");
    while (rs.next()) {
        String ref = rs.getString("ref");
        String nom = rs.getString("nom");
        String destination = rs.getString("destination");
        String type = rs.getString("type");
        String etat_colis = rs.getString("etat_colis");
        LivreurTableViewData data = new LivreurTableViewData(ref,nom, destination, type, etat_colis);
        dataList.add(data); 
        System.out.println("ccccc");
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
    String selectedEtat = etat.getValue();
    if (selectedData != null && selectedEtat != null) {
        String sql = "UPDATE colis SET etat_colis = ? WHERE ref = ?";
        PreparedStatement ps = myconn.prepareStatement(sql);
        ps.setString(1, selectedEtat);
        ps.setString(2, selectedData.getRef());
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Successfully updated the etat_colis column in the database.");
            
            // Send notification email to the client
        String clientEmail = selectedData.getEmail();
        String subject = "Delivery status update";
        String message = "Dear " + selectedData.getNom() + ",\n\nThe status of your delivery with reference number " 
                        + selectedData.getRef() + " has been updated to \"" + newEtat + "\".\n\nBest regards,\nLivreurApp team";
        EmailSender.sendEmail(clientEmail, subject, message);
        System.out.println("Notification email sent successfully!");
    } catch (SQLException e) {
        System.out.println("Error updating delivery status: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error sending notification email: " + e.getMessage());
        } else {
            System.out.println("Failed to update the etat_colis column in the database.");
        }
    } else {
        System.out.println("Please select a row and an etat value.");
    }
}
    
    public class EmailSender {

    public void sendEmail(String to, String subject, String message) {

        String from = "your-email@example.com";
        String password = "your-email-password";
        String host = "smtp.gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
            System.out.println("Email sent successfully.");
        } catch (MessagingException ex) {
            System.out.println("Failed to send email: " + ex.getMessage());
        }
    }
}

}