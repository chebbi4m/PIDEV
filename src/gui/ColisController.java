/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import taktak.utils.MyConnection;
import taktak.entities.Colis;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class ColisController implements Initializable {
    
    Connection myconn = MyConnection.getInstance().getConnexion();
    
    @FXML
    private TableView<Colis> tableV;

    @FXML
    private TableColumn<Colis, String> refc;

    @FXML
    private TableColumn<Colis, String> destinationc;

    @FXML
    private TableColumn<Colis, String> etatc;

    @FXML
    private TableColumn<Colis, Integer> prixc;

    @FXML
    void BtnColis(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Colis.fxml"));
            Parent root = (Parent) loader.load();
           
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void BtnHistorique(ActionEvent event) {
        
    }
    
    @FXML
    void BtnSupprimer(ActionEvent event) {

    }
    
    @FXML
    void BtnRaffraichir(ActionEvent event) {

    }
    
    @FXML
    void BtnAjouter(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Etape1.fxml"));
            Parent root = (Parent) loader.load();
           
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
        public void afficherColis (){
                ObservableList<Colis> list = afficherListColis();
                refc.setCellValueFactory(new PropertyValueFactory<>("ref"));
                prixc.setCellValueFactory(new PropertyValueFactory<>("prix"));
                destinationc.setCellValueFactory(new PropertyValueFactory<>("destination"));
                etatc.setCellValueFactory(new PropertyValueFactory<>("etat_colis"));
                tableV.setItems(list);
        }
    
}
