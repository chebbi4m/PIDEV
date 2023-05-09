/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Entities.LivreurInterface;
import Entities.Colis;
import Entities.LivreurTableViewData;
import Session.UserSession;
import Utils.MyConnection;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.CustomTextField;


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
    private ImageView LogoCont;
    @FXML
    private Button LogoutBtn;
   
    @FXML
    private ImageView LivreurLogo;
    
    @FXML
    private Label name;
    @FXML
    private Button PrintBtn;
   @FXML
private TableColumn<Colis, String> refCol;
@FXML
private TableColumn<Colis, Integer> hauteurCol;
@FXML
private TableColumn<Colis, Integer> poidsCol;
@FXML
private TableColumn<Colis, Boolean> fragileCol;
@FXML
private TableColumn<Colis, Boolean> inflammableCol;
@FXML
private TableColumn<Colis, String> zoneCol;
@FXML
private TableColumn<Colis, Boolean> urgentCol;
@FXML
private TableView<Colis> colisTableView;
ObservableList<Colis> unassignedColisList=FXCollections.observableArrayList();
ObservableList<Colis> assignedColisList=FXCollections.observableArrayList();
@FXML
private CustomTextField searchTextField;
    @FXML
    private Button unassignedColis;
    @FXML
    private Button myColis;
    @FXML
    private Button reclamation;
    @FXML
    private Button addAvailability;
    @FXML
    private TableColumn<?, ?> departCol;
    @FXML
    private TableColumn<?, ?> destinationCol;
    @FXML
        private Button assignBtn;
    @FXML
    private Button searchZoneBtn;
    @FXML
    private ComboBox<String> etatColisSelect;
    @FXML
    private Button updateEtatBtn;
    @FXML
    private Label etatLabel;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
           etatColisSelect.getItems().addAll(
               "Pas livré",
               "Livré",
               "En cours"
        
        );
         viewUnassignedColis(27);
     
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
 
   public void viewUnassignedColis(int user_id) {
    List<Colis> unassignedColisList = new ArrayList<>();
    try {
        etatColisSelect.setVisible(false);
        updateEtatBtn.setVisible(false);
        etatLabel.setVisible(false);
        Connection conn = MyConnection.getInstance().getConnexion();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM colis WHERE id_livreur IS NULL AND id_client IS NOT NULL AND id_partenaire = (SELECT id_partenaire FROM livreur WHERE user_id = ?)");
        stmt.setInt(1, user_id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Colis colis = new Colis();
            colis.setId(rs.getInt("id"));
            colis.setRef(rs.getString("ref"));
            colis.setHauteur(rs.getInt("hauteur"));
            colis.setLargeur(rs.getInt("largeur"));
            colis.setPoids(rs.getInt("poids"));
            colis.setFragile(rs.getBoolean("fragile"));
            colis.setInflammable(rs.getBoolean("inflammable"));
            colis.setDepart(rs.getString("depart"));
            colis.setDestination(rs.getString("destination"));
            colis.setZone(rs.getString("zone"));
            colis.setUrgent(rs.getBoolean("urgent"));
            unassignedColisList.add(colis);
        }

        refCol.setCellValueFactory(new PropertyValueFactory<>("ref"));
        hauteurCol.setCellValueFactory(new PropertyValueFactory<>("hauteur"));
        poidsCol.setCellValueFactory(new PropertyValueFactory<>("poids"));
        fragileCol.setCellValueFactory(new PropertyValueFactory<>("fragile"));
        inflammableCol.setCellValueFactory(new PropertyValueFactory<>("inflammable"));
        departCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        zoneCol.setCellValueFactory(new PropertyValueFactory<>("zone"));
        urgentCol.setCellValueFactory(new PropertyValueFactory<>("urgent"));
        
       colisTableView.setItems(FXCollections.observableList(unassignedColisList));

    } catch (SQLException e) {
        e.printStackTrace();
    }

}
    @FXML
    private void assignColisToLivreur(ActionEvent event) throws SQLException {
        int user_id=27;
      Connection conn = MyConnection.getInstance().getConnexion();
      Colis c =  colisTableView.getSelectionModel().getSelectedItem();
      
       PreparedStatement stmt = conn.prepareStatement("UPDATE colis SET id_livreur = ( SELECT id FROM livreur  WHERE user_id = ?) WHERE ref = ? ");
        stmt.setInt(1, user_id);
        stmt.setString(2, c.getRef());
        stmt.executeUpdate();
        viewUnassignedColis(user_id);   
    }

    @FXML
    private void myColisAction(ActionEvent event) {
        etatColisSelect.setVisible(true);
        updateEtatBtn.setVisible(true);
        etatLabel.setVisible(true);
        int user_id=27;
        assignBtn.setVisible(false);
          List<Colis> assignedColisList = new ArrayList<>();
    try {
        Connection conn = MyConnection.getInstance().getConnexion();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM colis c JOIN livreur l ON c.id_livreur = l.id WHERE l.user_id = ?");
        stmt.setInt(1, user_id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Colis colis = new Colis();
            colis.setId(rs.getInt("id"));
            colis.setRef(rs.getString("ref"));
            colis.setHauteur(rs.getInt("hauteur"));
            colis.setLargeur(rs.getInt("largeur"));
            colis.setPoids(rs.getInt("poids"));
            colis.setFragile(rs.getBoolean("fragile"));
            colis.setInflammable(rs.getBoolean("inflammable"));
            colis.setDepart(rs.getString("depart"));
            colis.setDestination(rs.getString("destination"));
            colis.setZone(rs.getString("zone"));
            colis.setUrgent(rs.getBoolean("urgent"));
            assignedColisList.add(colis);
        }

        refCol.setCellValueFactory(new PropertyValueFactory<>("ref"));
        hauteurCol.setCellValueFactory(new PropertyValueFactory<>("hauteur"));
        poidsCol.setCellValueFactory(new PropertyValueFactory<>("poids"));
        fragileCol.setCellValueFactory(new PropertyValueFactory<>("fragile"));
        inflammableCol.setCellValueFactory(new PropertyValueFactory<>("inflammable"));
        departCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        zoneCol.setCellValueFactory(new PropertyValueFactory<>("zone"));
        urgentCol.setCellValueFactory(new PropertyValueFactory<>("urgent"));
        
       colisTableView.setItems(FXCollections.observableList(assignedColisList));

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void viewUnassignedColisAction(ActionEvent event) {
         assignBtn.setVisible(true);
        viewUnassignedColis(27);
    }

    @FXML
    private void SearchZoneAction(ActionEvent event) {
        String zone = searchTextField.getText();
        searchUnassignedColisByZone(zone);
    }
    
     public void searchUnassignedColisByZone(String zone) {
    List<Colis> colisList = new ArrayList<>();
    try {
         Connection conn = MyConnection.getInstance().getConnexion();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM colis WHERE id_livreur IS NULL AND id_client IS NOT NULL AND zone = ?");
        stmt.setString(1, zone);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Colis colis = new Colis();
            colis.setId(rs.getInt("id"));
            colis.setRef(rs.getString("ref"));
            colis.setHauteur(rs.getInt("hauteur"));
            colis.setLargeur(rs.getInt("largeur"));
            colis.setPoids(rs.getInt("poids"));
            colis.setFragile(rs.getBoolean("fragile"));
            colis.setInflammable(rs.getBoolean("inflammable"));
            colis.setDepart(rs.getString("depart"));
            colis.setDestination(rs.getString("destination"));
            colis.setZone(rs.getString("zone"));
            colis.setUrgent(rs.getBoolean("urgent"));
            unassignedColisList.add(colis);
        }
        refCol.setCellValueFactory(new PropertyValueFactory<>("ref"));
        hauteurCol.setCellValueFactory(new PropertyValueFactory<>("hauteur"));
        poidsCol.setCellValueFactory(new PropertyValueFactory<>("poids"));
        fragileCol.setCellValueFactory(new PropertyValueFactory<>("fragile"));
        inflammableCol.setCellValueFactory(new PropertyValueFactory<>("inflammable"));
        departCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        zoneCol.setCellValueFactory(new PropertyValueFactory<>("zone"));
        urgentCol.setCellValueFactory(new PropertyValueFactory<>("urgent"));
        
        colisTableView.setItems(FXCollections.observableArrayList(unassignedColisList));


    } catch (SQLException e) {
        e.printStackTrace();
    }
    // update the TableView with the results
    
}

    @FXML
    private void addAvailabilityAction(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("LivreurAvailability.fxml"));
            Parent root = loader.load();
            Stage CurrentStage = (Stage) addAvailability.getScene().getWindow();
            Scene scene = new Scene(root,1080,720);
            CurrentStage.setScene(scene);
            CurrentStage.show();

    }

    @FXML
    private void updateEtatAction(ActionEvent event) throws SQLException {
          int user_id=27;
          String etat_colis = etatColisSelect.getValue();
      Connection conn = MyConnection.getInstance().getConnexion();
      Colis c =  colisTableView.getSelectionModel().getSelectedItem();
      
     PreparedStatement stmt = conn.prepareStatement("UPDATE colis SET etat_colis = ? WHERE id_livreur = (SELECT id FROM livreur WHERE user_id = ?) AND ref = ?");
      
        stmt.setString(1, etat_colis);
        stmt.setInt(2, user_id);
        stmt.setString(3, c.getRef());
        stmt.executeUpdate();
         
    }
          @FXML
    private void PrintAction(ActionEvent event) {
        Colis c= colisTableView.getSelectionModel().getSelectedItem();
        String ref= c.getRef();
        int hauteur = c.getHauteur();
        int poids = c.getPoids();
        boolean fragile = c.getFragile();
        boolean inflammable = c.getInflammable();
        String depart = c.getDepart();
        String destination = c.getDestination();
        String zone = c.getZone();
        boolean urgent = c.getUrgent();
        


        
        Document document = new Document();

        try {
            // Create a PdfWriter to write the Document to a file
            PdfWriter.getInstance(document, new FileOutputStream("listeColis.pdf"));

            // Open the Document
            document.open();

            // Add some content to the Document
            Paragraph paragraph = new Paragraph();
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph title = new Paragraph("Votre colis", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            paragraph.add(
                "Colis : " + ref + 
                "\nHauteur : " + hauteur + 
                "\nPoids : " + poids +
                "\nFragile : " + fragile +
                "\nInflammable : " + inflammable +
                "\nDépart : " + depart +
                "\nDestination : " + destination +
                "\nZone : " + zone +
                "\nUrgent : " + urgent  
            );
            document.add(paragraph);

            // Close the Document
            document.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
@FXML
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

    @FXML
    private void historiquePage(MouseEvent event) {
    }

    @FXML
    private void retourner(MouseEvent event) {
    }

    @FXML
    private void openReclamation(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("livreurReclamation.fxml"));
            Parent root = loader.load();
            Stage CurrentStage = (Stage) reclamation.getScene().getWindow();
            Scene scene = new Scene(root);
            CurrentStage.setScene(scene);
            CurrentStage.show();
    }



    
    
    
    
     
 
}