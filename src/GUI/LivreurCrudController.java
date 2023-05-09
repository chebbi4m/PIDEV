/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Livreur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Entities.LivreurInterface;
import Services.LivreurInterfaceService;
import Services.LivreurService;
import Session.UserSession;
import Utils.MyConnection;
import java.util.Properties;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Cheima
 */
public class LivreurCrudController implements Initializable {
    Connection myconn = MyConnection.getInstance().getConnexion();
      private LivreurInterfaceService livreurInterfaceService;
    @FXML
    private ImageView LogoCont;
    @FXML
    private Label LivreurCont;
    @FXML
    private ImageView LivreurLogo;
    @FXML
    private Button LogoutBtn;
    private HBox ListeCont;
    @FXML
    private VBox LivreurFonct;
    @FXML
    private TextField nomLivreur;
    @FXML
    private TextField prenomLivreur;
    @FXML
    private TextField emailLivreur;
    @FXML
    private TextField numtelLivreur;
    @FXML
    private TextField adresseLivreur;
    @FXML
    private Button UpdateBtn;
    @FXML
    private Button unassignedColis;
    @FXML
    private Button myColis;
    @FXML
    private Button reclamation;
    @FXML
    private Button addAvailability;
    @FXML
    private Button deleteBtn;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showLivreurInformations();
//        livreurInterfaceService = new LivreurInterfaceService();
//        
        
//          Livreur lv = new Livreur();
//          LivreurService ls = new LivreurService();
//          lv = (Livreur) UserSession.INSTANCE.get("livreur");
//          LivreurCont.setText(lv.getEmail());
//        
//        
//        afficherLivreurById(lv.getEmail());
        
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
    

private void showLivreurInformations() {
 
   int user_id=27;
       try {
        String sql = "SELECT * FROM livreur WHERE user_id  = ?";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setInt(1, user_id);
        ResultSet rs = ste.executeQuery();
        
        if (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            int numtel = rs.getInt("numtel");
            String adresse = rs.getString("adresse");
            nomLivreur.setText(nom);
            prenomLivreur.setText(prenom);
            emailLivreur.setText(email);
            numtelLivreur.setText(String.valueOf(numtel));
            adresseLivreur.setText(adresse);
        } else {
            System.out.println("No livreur found with id " + user_id);
        }
    } catch (SQLException e) {
        System.out.println(e);
    }

}
    @FXML
    private void updateLivreurC(ActionEvent event) {   
        Connection conn = MyConnection.getInstance().getConnexion();
        PreparedStatement stmt = null;
        String nom=nomLivreur.getText();
        String prenom=prenomLivreur.getText();
        String email=emailLivreur.getText();
        String numtelStr = numtelLivreur.getText();
        int numtel = Integer.parseInt(numtelStr);
        String adresse=adresseLivreur.getText();
        boolean updated = false;
          try {
        stmt = conn.prepareStatement("UPDATE livreur SET nom=?, prenom=?, email=?, numtel=?, adresse=? WHERE user_id=?");
        stmt.setString(1, nom);
        stmt.setString(2, prenom);
        stmt.setString(3, email);
        stmt.setInt(4, numtel);
        stmt.setString(5, adresse);
        stmt.setInt(6, 27); // user_id = 27
        int rows = stmt.executeUpdate();
        if (rows > 0) {
            updated = true;
            String header = "Livreur information updated";
            String content = "The information of the livreur has been updated.";
            sendEmail(header, content);
        }
          System.out.println("Message sent successfully.");

    } catch (SQLException e) {
        e.printStackTrace();
          System.out.println("Failed to send message");
    }
   
}
    public void sendEmail(String header, String content){
        String username = "cheima.douiss@esprit.tn";
            String password = "12345654321280458";

            // create properties object to configure email server and protocol
            Properties properties = new Properties();
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.ssl.trust", "*");
                properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

            // create session with authentication information
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // prepare email message
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("douisscheima4@gmail.com")
                );
                message.setSubject(header);
                message.setText( content );

                // send email
                Transport.send(message);

                // show confirmation message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Email sent");
                alert.setHeaderText(null);
                alert.setContentText("Email sent successfully.");
                alert.showAndWait();

            } catch (MessagingException ex) {
                // show error message if email fails to send
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Email error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to send email.");
                alert.showAndWait();
                System.out.println(ex);
            }
    }

    
    private void OpenListeColis(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("LivreurInterface.fxml"));
        Parent root = loader.load();
        Stage CurrentStage = (Stage) ListeCont.getScene().getWindow();
        Scene scene = new Scene(root,1080,720);
        CurrentStage.setScene(scene);
        CurrentStage.show();
    }

    @FXML
    private void LogoutClick(ActionEvent e) throws IOException {
    
         Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("LoginLivreur.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();

    }

    @FXML
    private void historiquePage(MouseEvent event) {
    }

    @FXML
    private void viewUnassignedColisAction(ActionEvent event) {
         
    }

    @FXML
    private void myColisAction(ActionEvent event) {
    }

    @FXML
    private void historiquePage(ActionEvent event) {
    }

    @FXML
    private void addAvailabilityAction(ActionEvent event) {
    }

    @FXML
    private void deleteLivreurAction(ActionEvent event) {
         Connection conn = MyConnection.getInstance().getConnexion();
        PreparedStatement stmt = null;
        int user_id=27;
          try {
        String sql = "DELETE livreur, users FROM livreur INNER JOIN users ON livreur.user_id = users.id WHERE livreur.user_id = ?";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setInt(1, user_id);
        ste.executeUpdate();
        
    } catch (SQLException e) {
        System.out.println(e);
    }
        
    }
    
}