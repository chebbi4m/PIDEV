/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.Properties;
import javafx.scene.control.Alert;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author chebbi4m
 */
public class emailService {
    public void sendEmail(String header, String content){
        String username = "chebbi.mohamed.1@esprit.tn";
            String password = "223JMT1915";

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
                    InternetAddress.parse("chebbim4@gmail.com")
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
}
