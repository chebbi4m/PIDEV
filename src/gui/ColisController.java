/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import Entities.Client;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Entities.Colis;
import Services.ClientService;
import Session.UserSession;
import Utils.MyConnection;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class ColisController implements Initializable {
    Connection myconn = MyConnection.getInstance().getConnexion();
    
    @FXML
    private Label nomlogin;
    
    
    @FXML
    private TableView<Colis> colisTable;

    @FXML
    private TableColumn<Colis, String> refCol;

    @FXML
    private TableColumn<Colis, String> departCol;

    @FXML
    private TableColumn<Colis, String> destinationCol;

    @FXML
    private TableColumn<Colis, Integer> poidsCol;

    @FXML
    private TableColumn<Colis, Integer> prixCol;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherColis();
       Client pr = new Client();
       ClientService ps = new ClientService();
       pr = (Client) UserSession.INSTANCE.get("client");
       nomlogin.setText(pr.getLogin());
    }
    
    @FXML
    public void btnDeconnecter(ActionEvent event) {
                
        try{
            Stage stage = new Stage ();
            Parent root = FXMLLoader.load(getClass().getResource("LoginClient.fxml"));  
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    void print(MouseEvent event) throws FileNotFoundException {
        print();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message d'alerte");
        alert.setHeaderText("Votre facture a été imprimé avec succès");
        alert.showAndWait();
    }
    @FXML
    void refreshTable() {
        afficherColis();
    }
    
    @FXML
    void getAddView(MouseEvent event) {
        try{
            
            Stage stage = new Stage ();
            Parent root = FXMLLoader.load(getClass().getResource("AjoutColis1.fxml"));  
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            ((Node)event.getSource()).getScene().getWindow().hide(); 
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //System.out.println();
    }
    
     public ObservableList <Colis> afficherListColis() {
        ObservableList<Colis> colis = FXCollections.observableArrayList();
            Client cl = new Client();
            ClientService clis= new ClientService();
            cl = (Client) UserSession.INSTANCE.get("client");
            int getClientId = cl.getId();
        try {
            
            String sql = "SELECT * FROM colis WHERE etat_colis = 'en cours' AND id_client = '" + getClientId + "' ORDER BY id DESC";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Colis cls = new Colis(s.getString("ref"),
                        s.getInt(3),
                        s.getInt(4),
                        s.getInt(5),
                        s.getInt(6),
                        s.getBoolean(7),
                        s.getBoolean(8),
                        s.getString("depart"),
                        s.getString("destination"),
                        s.getString("etat_colis"),
                        s.getString("zone"),
                        s.getBoolean(13),
                        s.getInt("id_client"),
                        s.getInt(15),
                        s.getString("nom_partenaire"));
                colis.add(cls);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return colis;
        }

    public void afficherColis() {
       ObservableList<Colis> list = afficherListColis();
       //refreshTable();
       refCol.setCellValueFactory(new PropertyValueFactory<>("ref"));
       departCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
       destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
       poidsCol.setCellValueFactory(new PropertyValueFactory<>("poids"));
       prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
       colisTable.setItems(list);
    }
    
     @FXML
    void btnAccueil(ActionEvent event) {
        try{
            Stage stage = new Stage ();
            Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));  
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            ((Node)event.getSource()).getScene().getWindow().hide(); 
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @FXML
    void btnHistorique(ActionEvent event) {
        try{
            Stage stage = new Stage ();
            Parent root = FXMLLoader.load(getClass().getResource("HistoriqueColis.fxml"));  
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            ((Node)event.getSource()).getScene().getWindow().hide(); 
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @FXML
    void btnAnnuler(ActionEvent event){
        String sql ="DELETE FROM `colis` WHERE ref = ? ";
        try{
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setString(1, refCol.getCellData(0));
            ste.executeUpdate();
            System.out.println("colis supprimer");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'alerte");
            alert.setHeaderText("Colis annulé");
            alert.showAndWait();
        }
        catch(SQLException ex){
            System.out.println(ex);
        }afficherColis();
    }

private void print() throws FileNotFoundException {
    Colis selectedItem = colisTable.getSelectionModel().getSelectedItem();
    String ref = selectedItem.getRef();
    String depart = selectedItem.getDepart();
    String destination = selectedItem.getDestination();
    int prix = selectedItem.getPrix();
    Document document = new Document();

    // Create a File object with the name of the file
    File file = new File("facture.pdf");

    try {
        // Check if the file exists
        if (file.exists()) {
            // If the file exists, add a timestamp to the filename to create a new file
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            file = new File("facture_" + timestamp + ".pdf");
        }

        // Create a PdfWriter to write the Document to the file
        PdfWriter.getInstance(document, new FileOutputStream(file));

        // Open the Document
        document.open();

        // Create a table with 2 columns
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorderWidth(1);
        table.setSpacingAfter(10f);

        Paragraph header = new Paragraph("Ma facture", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, BaseColor.BLUE));

        PdfPCell headerCell = new PdfPCell(header);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        // Set the properties of the cell, including the border
        headerCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
        headerCell.setBorderColor(BaseColor.BLACK);
        headerCell.setBorderWidth(2f);
        headerCell.setPaddingBottom(10f);


        // Add the header cell to a new table
        PdfPTable headerTable = new PdfPTable(1);
        headerTable.setWidthPercentage(100);
        headerTable.addCell(headerCell);

        // Add the header table to the document
        document.add(headerTable);

        // Add some content to the table
        table.addCell(new PdfPCell(new Phrase("Référence:", FontFactory.getFont(FontFactory.TIMES, 14))));
        table.addCell(new PdfPCell(new Phrase(ref, FontFactory.getFont(FontFactory.TIMES, 14))));
        table.addCell(new PdfPCell(new Phrase("Départ:", FontFactory.getFont(FontFactory.TIMES, 14))));
        table.addCell(new PdfPCell(new Phrase(depart, FontFactory.getFont(FontFactory.TIMES, 14))));
        table.addCell(new PdfPCell(new Phrase("Destination:", FontFactory.getFont(FontFactory.TIMES, 14))));
        table.addCell(new PdfPCell(new Phrase(destination, FontFactory.getFont(FontFactory.TIMES, 14))));
        table.addCell(new PdfPCell(new Phrase("Prix Total:", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.RED))));
        table.addCell(new PdfPCell(new Phrase(prix + " DT", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.RED))));
        
        // Add the table to the Document
        document.add(table);

        // Close the Document
        document.close();
        
        System.out.println("Facture imprimé");
    } catch (DocumentException ex) {
        System.out.println(ex);
    }
}

}
