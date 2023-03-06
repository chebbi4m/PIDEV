/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import Entities.devis;
import Utils.MyConnection;


/**
 * FXML Controller class
 *
 * @author nedia
 */
public class VisualiserDevisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField destination_text;
    @FXML
    private TextField depart_text;
    @FXML
    private TextField hauteur_text;
    @FXML
    private TextField largeur_text;
    @FXML
    private TextField poids_text;
    @FXML
    private ComboBox<String> zone_text;
    @FXML
    private CheckBox fragile_checkbox;
    @FXML
    private CheckBox inflammable_checkbox;
    @FXML
    private Button submit_button;
    @FXML
    private CheckBox urgent_checkbox;
    @FXML
    private TableView<devis> resultatDevis;
    
    ObservableList<devis> devisList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<devis, String> partenaire_col;
    @FXML
    private TableColumn<devis, Date> date_col;
    @FXML
    private TableColumn<devis, String> prix_col;
    @FXML
    private Button print_button;
    @FXML
    private TableColumn<devis, String> from_col;
    @FXML
    private TableColumn<devis, String> to_col;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zone_text.getItems().addAll("Amerique", "Afrique", "Europe","Asie","Australie");
        
        final String numberFormatter = "[\\d\\.]*";
        
        hauteur_text.setTextFormatter(new TextFormatter<>(change ->
        (change.getControlNewText().matches(numberFormatter)) ? change : null));
        largeur_text.setTextFormatter(new TextFormatter<>(change ->
        (change.getControlNewText().matches(numberFormatter)) ? change : null));
        poids_text.setTextFormatter(new TextFormatter<>(change ->
        (change.getControlNewText().matches(numberFormatter)) ? change : null));
        
        resultatDevis.setVisible(false);
        print_button.setVisible(false);
    }

    @FXML
    private void visualiser(ActionEvent event) {
        calculerPrix();
        resultatDevis.setVisible(true);
    }
    
    private void calculerPrix() {
        Connection myconn = MyConnection.getInstance().getConnexion();
        resultatDevis.getItems().clear();

        try {
            
            
            
            float hauteur = Float.parseFloat(hauteur_text.getText());
            float poids = Float.parseFloat(poids_text.getText());
            float largeur = Float.parseFloat(largeur_text.getText());
            String destination = destination_text.getText();
            String depart = depart_text.getText();
            String zone = zone_text.getValue();
            int fragile ;
            int urgent ;
            int inflammable ;
            if (fragile_checkbox.isSelected() == true){
                fragile = 1;
            }else{
                fragile = 0;
            }
            if (urgent_checkbox.isSelected() == true){
                urgent = 1;
            }else{
                urgent = 0;
            }
            if (inflammable_checkbox.isSelected() == true){
                inflammable = 1;
            }else{
                inflammable = 0;
            }
            
            String sql = "SELECT * FROM partenaire WHERE zone LIKE '%" + zone + "%'";

            if (inflammable == 1) {
                sql += " AND inflammable = 1";
            }

            if (fragile == 1) {
                sql += " AND fragile = 1";
            }
            
            System.out.println(sql);
            PreparedStatement ste = myconn.prepareStatement(sql);
            ResultSet resultSet = ste.executeQuery();

            while (resultSet.next()) {
                int final_price = 50 ;
                String partenaire_name = resultSet.getString("nom");
                String partenaire_zone = resultSet.getString("Zone");
                int partenaire_poids = resultSet.getInt("Prix_poids");
                String partenaire_zone_prix = resultSet.getString("Prix_zone");
                
                System.out.println(partenaire_name);
                
                String[] zones = partenaire_zone.split("_");
                String[] price_zones = partenaire_zone_prix.split("_");
                
                int[] prices = new int[price_zones.length];
                for (int i = 0; i < price_zones.length; i++) {
                    prices[i] = Integer.parseInt(price_zones[i]);
                }
                
                
                for (int i = 0; i < zones.length; i++) {
                    if (zones[i].equals(zone)) {
                        System.out.println("Index is: " + i); 
                        final_price = final_price + prices[i];
                    }
                }
                if(poids > 5){
                    final_price = final_price + partenaire_poids;
                }
                
                if (hauteur > 20){
                    final_price = final_price + 20;
                }
                
                if (largeur > 20){
                    final_price = final_price + 20;
                }
                
                System.out.println(final_price);
                LocalDate currentDate = LocalDate.now(); // Get current date
                LocalDate date_livraison = currentDate.plus(7, ChronoUnit.DAYS);
                System.out.println(partenaire_name);
                System.out.println(date_livraison);
                System.out.println(final_price);
                devis devis = new devis(partenaire_name, date_livraison, depart, destination, final_price);
                devisList.addAll(devis);
                
            }
        } catch (SQLException ex) {
            
            System.out.println(ex);
        }

        partenaire_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        from_col.setCellValueFactory(new PropertyValueFactory<>("depart"));
        to_col.setCellValueFactory(new PropertyValueFactory<>("destination"));
        prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
        resultatDevis.getItems().setAll(devisList);
        print_button.setVisible(true);


    }
    

    @FXML
    private void print() {
        devis selectedItem = resultatDevis.getSelectionModel().getSelectedItem();
        String name = selectedItem.getNom();
        LocalDate date = selectedItem.getDate();
        String depart = selectedItem.getDepart();
        String destination = selectedItem.getDestination();
        int prix = selectedItem.getPrix();
        Document document = new Document();

        try {
            // Create a PdfWriter to write the Document to a file
            PdfWriter.getInstance(document, new FileOutputStream("devis.pdf"));

            // Open the Document
            document.open();

            // Add some content to the Document
            Paragraph paragraph = new Paragraph();
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph title = new Paragraph("DEVIS", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            paragraph.add(
                "Partenaire : " + name + 
                "\nDepart : " + depart + 
                "\nVers : " + destination + 
                "\nDate de livraison : " + date +
                "\nLe Prix Total est : " + prix
            );
            document.add(paragraph);

            // Close the Document
            document.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
