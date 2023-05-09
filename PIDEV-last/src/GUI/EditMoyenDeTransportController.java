    package GUI;
    import Entities.LivreurInterfaceN;
    import Entities.MoyenDeTransport;
    import Utils.MyConnection;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.*;
    import javafx.scene.layout.VBox;

    import javax.activation.DataHandler;
    import javax.activation.DataSource;
    import javax.activation.FileDataSource;
    import javax.mail.*;
    import javax.mail.internet.InternetAddress;
    import javax.mail.internet.MimeBodyPart;
    import javax.mail.internet.MimeMessage;
    import javax.mail.internet.MimeMultipart;
    import java.io.File;
    import java.io.IOException;
    import java.net.URL;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.*;

    public class EditMoyenDeTransportController implements Initializable {

        @FXML
        private TextField marqueField;

        @FXML
        private TextField typeField;

        @FXML
        private TextField matriculeField;

        @FXML
        private ComboBox<LivreurInterfaceN> livreurIdCombo;
        @FXML
        private VBox form;


        private MoyenDeTransport selectedMdt;
        private TableView<MoyenDeTransport> tableView;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            marqueField.requestFocus();
            matriculeField.requestFocus();
            typeField.requestFocus();

            // Populate the ComboBox with Livreur objects
            List<LivreurInterfaceN> livreurList = new ArrayList<>();
            try {
                Connection conn = MyConnection.getInstance().getConnexion();
                String query = "SELECT id, nom, prenom FROM livreur";
                PreparedStatement pst = conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    LivreurInterfaceN livreur = new LivreurInterfaceN(id, nom, prenom);
                    livreurIdCombo.getItems().add(livreur);
                }

            } catch (SQLException ex) {
                System.out.println(ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors de la récupération des livreurs.");
                alert.showAndWait();
            }
            livreurIdCombo.getItems().addAll(livreurList);
        }

        public void setSelectedMdt(MoyenDeTransport selectedMdt) {
            this.selectedMdt = selectedMdt;
            marqueField.setText(selectedMdt.getMarque());
            typeField.setText(Integer.toString(selectedMdt.getType()));
            matriculeField.setText(selectedMdt.getMatricule());
            for (LivreurInterfaceN livreur : livreurIdCombo.getItems()) {
                if (livreur.getId() == selectedMdt.getLivreurId()) {
                    livreurIdCombo.getSelectionModel().select(livreur);
                    break;
                }
            }
        }

        public void setTableView(TableView<MoyenDeTransport> tableView) {
            this.tableView = tableView;
        }

        @FXML
        private void handleSaveButtonClicked() {
            String marque = marqueField.getText();
            int type = Integer.parseInt(typeField.getText());
            String matricule = matriculeField.getText();
            LivreurInterfaceN selectedLivreur = livreurIdCombo.getSelectionModel().getSelectedItem();
            int livreurId = (selectedLivreur != null) ? selectedLivreur.getId() : 0;
            selectedMdt.setMarque(marque);
            selectedMdt.setType(type);
            selectedMdt.setMatricule(matricule);
            selectedMdt.setLivreurId(livreurId);
            try {
                Connection conn = MyConnection.getInstance().getConnexion();
                PreparedStatement stm = conn.prepareStatement("UPDATE moyen_de_transport SET marque=?, type=?, matricule=?, livreur_id=? WHERE id=?");
                stm.setString(1, marque);
                stm.setInt(2, type);
                stm.setString(3, matricule);
                stm.setInt(4, livreurId);
                stm.setInt(5, selectedMdt.getId());
                int affectedRows = stm.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Updating moyen_de_transport failed, no rows affected.");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors de la mise à jour des données.");
                alert.showAndWait();
            }

            String email = null;
            try {
                Connection conn = MyConnection.getInstance().getConnexion();
                PreparedStatement stm = conn.prepareStatement("SELECT email FROM livreur WHERE id=?");
                stm.setInt(1, livreurId);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    email = rs.getString("email");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors de la récupération de l'email du livreur.");
                alert.showAndWait();
            }
            String typeString;
            if (type == 1) {
                typeString = "Voiture";
            } else {
                typeString = "Camion";
            }
            List<String> mdtDetails = Arrays.asList(marque, typeString, matricule);
            String fileName = "MoyenDeTransport_" + selectedMdt.getId() + ".pdf";
            try {
                PDFGenerator.generateMdtPdf(fileName, mdtDetails);
            } catch (IOException ex) {
                System.out.println(ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors de la génération du PDF.");
                alert.showAndWait();
            }


            if (email != null) {
                String subject = "Mise à jour de votre moyen de transport";
                String body = "Bonjour " + selectedLivreur.getPrenom() + ",\n\nVotre moyen de transport a été mis à jour.\n\nCordialement,\n\nL'équipe de livraison";
                sendEmailToLivreur(email, subject, body,fileName);
            }

            tableView.refresh();
            marqueField.getScene().getWindow().hide();
        }

        private void sendEmailToLivreur(String email, String subject, String body, String fileName) {
            String host = "smtp.gmail.com";
            String username = "mootez202@gmail.com";
            String password = "ppbifuhwieebcjbg";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject(subject);
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(body);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(fileName);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(new File(fileName).getName());
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
                Transport.send(message);
                System.out.println("Email sent to: " + email);
            } catch (MessagingException ex) {
                System.out.println("Failed to send email to: " + email);
                ex.printStackTrace();
            }

        }

        @FXML
        private void handleCancelButtonClicked() {
            // Close the edit window without saving any changes
            marqueField.getScene().getWindow().hide();
        }
    }

