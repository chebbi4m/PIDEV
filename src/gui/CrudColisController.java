/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import taktak.utils.MyConnection;
import taktak.entities.Colis;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class CrudColisController implements Initializable {
    
     @FXML
    private TableView<Colis> tablev;
    
     @FXML
    private TableColumn<Colis, String> refc;

    @FXML
    private TableColumn<Colis, Integer> hauteurc;

    @FXML
    private TableColumn<Colis, Integer> largeurc;

    @FXML
    private TableColumn<Colis, Integer> poidsc;

    @FXML
    private TableColumn<Colis, Integer> prixc;

    @FXML
    private TableColumn<Colis, Boolean> fragilec;

    @FXML
    private TableColumn<Colis, String> departc;

    @FXML
    private TableColumn<Colis, String> destinationc;

    @FXML
    private TableColumn<Colis, String> nomReceveurc;

    @FXML
    private TableColumn<Colis, Integer> idClientc;

    @FXML
    private TableColumn<Colis, Integer> idPartenairec;

    @FXML
    private TableColumn<Colis, Integer> idLivreurc;
    
    @FXML
    void Retour(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionColis.fxml"));
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
    void RafraichirColis(ActionEvent event) {

    }
    
    @FXML
    void AjouterColis(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterColis.fxml"));
            Parent root = (Parent) loader.load();
           
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    Connection myconn = MyConnection.getInstance().getConnexion();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @FXML private JFXButton retour;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        afficherColis();
    }
    
     public ObservableList <Colis> afficherListColis() {
        ObservableList<Colis> colis = FXCollections.observableArrayList();
        try {
            String sql = "select from colis order by id desc";
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
                refc.setCellValueFactory(new PropertyValueFactory<Colis,String>("ref"));
                hauteurc.setCellValueFactory(new PropertyValueFactory<Colis,Integer>("hauteur"));
                largeurc.setCellValueFactory(new PropertyValueFactory<Colis,Integer>("largeur"));
                poidsc.setCellValueFactory(new PropertyValueFactory<Colis,Integer>("poids"));
                prixc.setCellValueFactory(new PropertyValueFactory<Colis,Integer>("prix"));
                fragilec.setCellValueFactory(new PropertyValueFactory<Colis,Boolean>("fragile"));
                departc.setCellValueFactory(new PropertyValueFactory<Colis,String>("depart"));
                destinationc.setCellValueFactory(new PropertyValueFactory<Colis,String>("destintion"));
                nomReceveurc.setCellValueFactory(new PropertyValueFactory<Colis,String>("nom_receveur"));
                idClientc.setCellValueFactory(new PropertyValueFactory<Colis,Integer>("id_client"));
                idPartenairec.setCellValueFactory(new PropertyValueFactory<Colis,Integer>("id_partenaire"));
                idLivreurc.setCellValueFactory(new PropertyValueFactory<Colis,Integer>("id_livreur"));
                tablev.setItems(list);
        }
    
     public void retour (ActionEvent e) throws IOException{
            Stage stage = new Stage ();
            Parent root = FXMLLoader.load(getClass().getResource("GestionColis.fxml"));  
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            ((Node)e.getSource()).getScene().getWindow().hide();
        }  
        
    
}
