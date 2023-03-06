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
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import taktak.entities.Colis;
import taktak.services.ColisService;
import taktak.utils.MyConnection;
import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class ColisController implements Initializable {
    Connection myconn = MyConnection.getInstance().getConnexion();
    
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
    }
    

    @FXML
    void getAddView(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutColis1.fxml"));
            Parent root = (Parent) loader.load();
           
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //System.out.println();
    }

    @FXML
    void print(MouseEvent event) {
        
    }
    @FXML
    void refreshTable() {
        afficherColis();
    }
    
     public ObservableList <Colis> afficherListColis() {
        ObservableList<Colis> colis = FXCollections.observableArrayList();
        try {
            String sql = "select * from colis where etat_colis = 'En cours' order by id desc";
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
                        s.getInt(14),
                        s.getInt(15));
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
}
