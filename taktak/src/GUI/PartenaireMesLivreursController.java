/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import session.UserSession;
import taktak.entities.ColisN;
import taktak.entities.LivreurInterfaceN;
import taktak.entities.PartenaireN;
import taktak.services.PartenaireServiceN;
import taktak.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Najet
 */
public class PartenaireMesLivreursController implements Initializable {
    Connection myconn = MyConnection.getInstance().getConnexion(); // connexion à la base de données

    @FXML
    private JFXButton historiquebtn;
    @FXML
    private JFXButton Accueilbtn;
    @FXML
    private JFXButton Meslivreurbtn;
    @FXML
    private JFXButton reclamation_btn;
    @FXML
    private JFXButton fermer;
    @FXML
    private JFXButton retour;
    @FXML
    private TableView<LivreurInterfaceN> tvLiveurs; 
    @FXML
    private TableColumn<LivreurInterfaceN, String> colnom;
    @FXML
    private TableColumn<LivreurInterfaceN, String> colprenom;
    @FXML
    private TableColumn<LivreurInterfaceN, String> colemail;
    @FXML
    private TableColumn<LivreurInterfaceN, String> colnumtel;
    @FXML
    private TableColumn<LivreurInterfaceN, String> coladresse;
    @FXML
    private TableColumn<LivreurInterfaceN, Integer> colNbreColisCourant;
    @FXML
    private TableColumn<LivreurInterfaceN, Integer> colNbreColistotal;  
    @FXML
    private TableColumn<LivreurInterfaceN, Integer> colNbreReclamation;
    @FXML
    private TextField tfLivreur;
    @FXML
    private Button btnRecherche;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNumtel;  
    @FXML
    private TextField tfAdresse;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button colisbtn;
    @FXML
    private Button enregistrer;
 
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       try {
            afficherLivreur();
        } catch (SQLException ex) {
            Logger.getLogger(PartenaireMesLivreursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void hist(MouseEvent event) {
    }

    @FXML
    private void hist(ActionEvent event) {
    }

    @FXML
    private void open_reclamation(ActionEvent event) {
    }

   

    @FXML
    private void cancel(ActionEvent event) {
    }

  

    @FXML
    private void retourner(ActionEvent event) {
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
         LivreurInterfaceN liv  = tvLiveurs.getSelectionModel().getSelectedItem();     
         tfNom.setText( liv.getNom());
          tfPrenom.setText( liv.getPrenom());
           tfEmail.setText( liv.getEmail());
            tfNumtel.setText("" + liv.getNumtel());
            tfAdresse.setText( liv.getAdresse());
         
    }

    @FXML
    private void chercherLivreur(ActionEvent event) throws SQLException {
          affichercherche();
    }


    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
         ajouterLivreur();
        Alert1();
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
         supprimerLivreur();
        Alert2();
    }
    
    public  void generatePdf(String fileName, List<String> mdtDetails) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Create a content stream for adding to the PDF
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Set the font
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);

        // Define the content to be added to the PDF
        List<String> content = Arrays.asList(
                "Nom: " + mdtDetails.get(0),
                "Prenom: " + mdtDetails.get(1),
                "Numtel: " + mdtDetails.get(2)
        );

        // Set the position for adding the content
        float startX = 100;
        float startY = page.getMediaBox().getHeight() - 100;

        // Add the content to the PDF
        for (String line : content) {
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);
            contentStream.showText(line);
            contentStream.endText();
            startY -= 30;
        }

        // Close the content stream
        contentStream.close();

        // Save the PDF document
        document.save(fileName);

        // Close the PDF document
        document.close();
    }

     //   affichage des livreurs
public ObservableList<LivreurInterfaceN> getLivreurInterfaceList() throws SQLException {
   ObservableList<LivreurInterfaceN> livreurList = FXCollections.observableArrayList();
        try {
            String sql = "select * from livreur where id_partenaire = ?";
            PartenaireN p =new PartenaireN();
            PreparedStatement pstmt = myconn.prepareStatement(sql);
            pstmt.setInt(1, 31);//set id_partenaire
            ResultSet s = pstmt.executeQuery();
            while (s.next()) {

              LivreurInterfaceN lv = new LivreurInterfaceN(  
                      
                        s.getInt("id"),
                        s.getString("nom"),
                        s.getString("prenom"),
                        s.getString("email"),
                        s.getString("numtel"),
                        s.getString("adresse"),
                        s.getInt("nbre_reclamation"),
                        s.getInt("nbre_colis_total"),
                        s.getInt("nbre_colis_courant"),                        
                        s.getString("password")) ;
              
                    livreurList.add(lv);           
                
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return livreurList; 
        
    }

     
 public void afficherLivreur() throws SQLException{
 
     ObservableList<LivreurInterfaceN> List = getLivreurInterfaceList();

     
    colnom.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("nom"));   
    colprenom.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("prenom"));
    colemail.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("email")); 
    coladresse.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("adresse"));     
    colnumtel.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("numtel"));
    colNbreReclamation.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN ,Integer>("nbre_reclamation"));
    colNbreColistotal.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN ,Integer>("nbre_colis_total"));
    colNbreColisCourant.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , Integer>("nbre_colis_courant"));
    
   // colLogin.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("login"));     
  //  colMdp.setCellValueFactory(new PropertyValueFactory<LivreurInterface , String>("mdp"));
    tvLiveurs.setItems(List);
     
 }
    
 
 
  //button recherche afficher par nom
        ObservableList<LivreurInterfaceN> chercherLivreurInterfaceList() throws SQLException {
    ObservableList<LivreurInterfaceN> livreurList = FXCollections.observableArrayList();
    try {
        String sql = "SELECT * FROM livreur WHERE nom = ?";// nom=?
        PreparedStatement pstmt = myconn.prepareStatement(sql);
        pstmt.setString(1, tfLivreur.getText());
        ResultSet s = pstmt.executeQuery(); // execute the query without any argument
        while (s.next()) {
            LivreurInterfaceN lv = new LivreurInterfaceN(  
                    s.getInt("id"),
                    s.getString("nom"),
                    s.getString("prenom"),
                    s.getString("email"),
                    s.getString("numtel"),
                    s.getString("adresse"),
                    s.getInt("nbre_reclamation"),
                    s.getInt("nbre_colis_total"),
                    s.getInt("nbre_colis_courant"),                    
                    s.getString("password")) ;
       
            
            livreurList.add(lv);           
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return livreurList; 
}
     public void affichercherche() throws SQLException{
 
     ObservableList<LivreurInterfaceN> List = chercherLivreurInterfaceList();

   // colID.setCellValueFactory(new PropertyValueFactory<LivreurInterface , Integer>("id"));  
    colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));   
    colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    colemail.setCellValueFactory(new PropertyValueFactory<>("email"));     
    colnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
    coladresse.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN , String>("adresse"));
    colNbreReclamation.setCellValueFactory(new PropertyValueFactory<>("nbre_reclamation"));
    colNbreColistotal.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN ,Integer>("nbre_colis_total"));
    colNbreColisCourant.setCellValueFactory(new PropertyValueFactory<LivreurInterfaceN ,Integer>("NbreColisCourant"));
    tvLiveurs.setItems(List);
     
 }    
// the end of this button recherche
     
     
 private void ajouterLivreur() throws SQLException {
    try {
        // Get a valid id_partenaire value from the partenaire table
        int id_Partenaire = 31;
        String query = "SELECT id FROM partenaire";
        PreparedStatement pstmt = myconn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            id_Partenaire = rs.getInt("id");
        } else {
            System.out.println("No partenaire records found");
            return;
        }
        
        // Insert the new livreur record with the valid id_partenaire value
        query = "INSERT INTO livreur (id, nom, prenom, email, numtel, adresse, nbre_reclamation, nbre_colis_total, nbre_colis_courant, password, id_partenaire) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pstmt = myconn.prepareStatement(query);
         LivreurInterfaceN liv=new LivreurInterfaceN();
        pstmt.setInt(1, liv.getId());
        pstmt.setString(2,tfNom.getText());
        pstmt.setString(3, tfPrenom.getText());
        pstmt.setString(4, tfEmail.getText());
        pstmt.setString(5, tfNumtel.getText());
        pstmt.setString(6, tfAdresse.getText());
        pstmt.setInt(7, 0);
        pstmt.setInt(8, 0);
        pstmt.setInt(9, 0);                    
        pstmt.setInt(11, id_Partenaire);
        pstmt.executeUpdate();
        
        System.out.println("livreur inséré");
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    
    afficherLivreur();
}

private void supprimerLivreur() throws SQLException {//when u select on livrerus and click on button supprimer gonna supprime
      
        // Get a valid id_partenaire value from the partenaire table
        int id_Partenaire = 31;
        String query = "SELECT id FROM partenaire";
        PreparedStatement pstmt = myconn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            id_Partenaire = rs.getInt("id");
        } else {
            System.out.println("No partenaire records found");
            return;
        }
    String sql = "DELETE FROM livreur WHERE Nom = ? ";
    try {
        LivreurInterfaceN liv = tvLiveurs.getSelectionModel().getSelectedItem();
        if (liv == null) {
            System.out.println("No LivreurInterface selected");
            return;
        }
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setString(1, liv.getNom());
        int numRowsDeleted = ste.executeUpdate();
        if (numRowsDeleted > 0) {
            System.out.println("LivreurInterface supprimé: " + liv);
        } else {
            System.out.println("Aucun LivreurInterface n'a été supprimé");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression du LivreurInterface: " + ex);
    }
    afficherLivreur();
}



    @FXML
    private void cancel(MouseEvent event) {
    }

    @FXML
    private void retourner(MouseEvent event) {
        
     
    }

    @FXML
    private void colis(ActionEvent event) {
          try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PartenaireColis.fxml"));
            Parent root = (Parent) loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
    
    
     public void Alert1(){  
 Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Livreur Ajouter");
    alert.setContentText("Le Livreur a été ajouté avec succès.");
    alert.showAndWait();
 }
 
  public void Alert2(){  
 Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Livreur Supprimer");
    alert.setContentText("Le Livreur a été Supprimé avec succès.");
    alert.showAndWait();
 }

    @FXML
    private void pdf(ActionEvent event) {
       String Nom ="najtt";
         String prenom ="najtt";
           String email ="Najetchebbi12345@gmail.com";
            List<String> mdtDetails = Arrays.asList(Nom, prenom, email);
            String fileName = "Partenaire" + ".pdf";
           
           
              try {
                PDFGenerator.generateMdtPdf(fileName, mdtDetails);
            } catch (IOException ex) {
                System.out.println(ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors de la génération du PDF.");
                alert.showAndWait();
                if (email != null) {
                String subject = "Mise à jour de votre moyen de transport";
                String body = "Bonjour "  + ",\n\n Votre Liste des Livreurs a été mis à jour.\n\nCordialement,\n\nL'équipe de livraison";
                Send(email, subject, body,fileName);
            }
               
            }
            }
       private void Send(String email, String subject, String body, String fileName) {
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

    }

