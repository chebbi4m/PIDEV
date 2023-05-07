/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;
import java.util.Properties;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Hex;
import org.controlsfx.control.Notifications;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * FXML Controller class
 *
 * @author yasoulanda
 */
public class ForgetLivreurController implements Initializable {
 @FXML private Button fermer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML private TextField emailField;
    
 public void recoverPassword() throws Exception {
    String username = "yasmine.cherif@esprit.tn";
    String password = "223AFT0324";
    String recipient = emailField.getText();
    Connection myconn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
        myconn = MyConnection.getInstance().getConnexion();
        String sql = "SELECT count(1) FROM livreur WHERE email = '" + recipient + "'";
        stmt = myconn.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            if (rs.getInt(1) == 1) {
                // Générer un nouveau mot de passe avec les fonctions randomBytes et binToHex
                byte[] randomBytes = new byte[8];
                SecureRandom.getInstanceStrong().nextBytes(randomBytes);
                String newPassword = new String(Hex.encodeHex(randomBytes));
                // Encodez le mot de passe avec le même algorithme que Symfony
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String encodedPassword = encoder.encode(newPassword);
                // Mettre à jour le mot de passe dans la base de données
                String sqlUpdate = "UPDATE livreur SET password = ? WHERE email = ?";
                PreparedStatement stmtUpdate = myconn.prepareStatement(sqlUpdate);
                stmtUpdate.setString(1, encodedPassword);
                stmtUpdate.setString(2, recipient);
                stmtUpdate.executeUpdate();
                
                 // Mettre à jour le mot de passe dans la base de données table users
                String sqlUpdat = "UPDATE users SET password = ? WHERE email = ?";
                 stmtUpdate = myconn.prepareStatement(sqlUpdat);
                stmtUpdate.setString(1, encodedPassword);
                stmtUpdate.setString(2, recipient);
                stmtUpdate.executeUpdate();
                
                // Envoyer le mail avec le nouveau mot de passe
                String subject = "Récuperation du mot de passe ! ";
                String content = "Votre nouveau mot de passe est : " + newPassword;
                Properties properties = new Properties();
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.ssl.trust", "*");
                properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
                message.setSubject(subject);
                message.setText(content);
                Transport.send(message);
                check();
            }
            else {
                error("Vérifier votre adresse mail ");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } 
}    
 
 
        public void cancel (ActionEvent e){
               Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
    }
    
        public String motdepasse(){
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 8; i++) {
                    char c = chars.charAt(random.nextInt(chars.length()));
                    sb.append(c);
                }
                return sb.toString();
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
  
    public void error (String ch){
    Image image = new Image("Images/cross.png");
     Notifications notification = Notifications.create();
     notification.graphic(new ImageView(image));
     notification.title("Erreur");
     notification.text(ch);
     notification.hideAfter(Duration.seconds(3));
     notification.position(Pos.CENTER);
     notification.show();
    }
     public void check () throws IOException{
     Image image = new Image("Images/accept.png");
     Notifications notification = Notifications.create();
     notification.graphic(new ImageView(image));
     notification.title("Succès");
     notification.text("Le mail est envoyé ! ");
     notification.hideAfter(Duration.seconds(2));
     notification.position(Pos.CENTER);
     notification.show();
    }
    
}
