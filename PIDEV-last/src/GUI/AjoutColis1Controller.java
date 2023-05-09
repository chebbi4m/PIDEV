/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Client;
import Services.ClientService;
import Session.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static sun.management.Agent.error;
/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class AjoutColis1Controller implements Initializable {
    
    @FXML
    private Label nomlogin;
    
    @FXML
    private TextField fxHauteur;

    @FXML
    private TextField fxLargeur;

    @FXML
    private TextField fxPoids;
    
    @FXML
    private RadioButton fragileOui;

    @FXML
    private ToggleGroup fragile;

    @FXML
    private RadioButton fragileNon;
    
    @FXML
    private ToggleGroup inflammable;

    @FXML
    private RadioButton inflammableOui;

    @FXML
    private RadioButton inflammableNon;

    @FXML
    private RadioButton urgentOui;

    @FXML
    private ToggleGroup urgent;

    @FXML
    private RadioButton urgentNon;

    @FXML
    void radioInflammable(ActionEvent event) {

    }

    @FXML
    void radioUrgent(ActionEvent event) {

    }

    @FXML
    void radiofragile(ActionEvent event) {

    }
    
    @FXML
    private ComboBox cbZone;

    @FXML
    void selectZone(ActionEvent event) {
        String getZone = cbZone.getSelectionModel().getSelectedItem().toString();
    }
    
    @FXML
    private TextField fxDepart;

    @FXML
    private TextField fxDestination;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> zone = FXCollections.observableArrayList("Nationale", "Internationale");
        cbZone.setItems(zone);
       
        /*Client pr = new Client();
          ClientService ps = new ClientService();
          pr = (Client) UserSession.INSTANCE.get("client");
          System.out.println(pr);
          nomlogin.setText(pr.getLogin());*/
    }
    
    @FXML
    public void btnDeconnecter(ActionEvent event) throws IOException {
                
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("LoginClient.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
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
    void btnRetour(ActionEvent event) {
        try{
            Stage stage = new Stage ();
            Parent root = FXMLLoader.load(getClass().getResource("Colis.fxml"));  
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
    void btnSuivant(ActionEvent event) {
        String getCbZone = cbZone.getValue().toString();
        String depart = fxDepart.getText();
        String destination = fxDestination.getText();
        String hauteur = fxHauteur.getText();
        String largeur = fxLargeur.getText();
        String poids = fxPoids.getText();
        boolean getFragile = fragileOui.isSelected();
        boolean getInflammable = inflammableOui.isSelected();
        boolean getUrgent = urgentOui.isSelected();
        Boolean valid = true;
        
        if (getCbZone.length() == 0 || depart.length() == 0 || destination.length() == 0 || hauteur.length() == 0 || largeur.length() == 0 || poids.length() == 0) {
            error("Les champs ne peuvent pas être vides");
            valid = false ;         
        }else{
            if ((!hauteur.matches("\\d+"))) {
                error("Le champs ne doit contenir que des chiffres !");
                valid = false ;   
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'alerte");
                alert.setHeaderText("Veuillez saisir un chiffre");
                alert.showAndWait();
            }
            else if ((!largeur.matches("\\d+"))) {
                error("Le champs ne doit contenir que des chiffres !");
                valid = false ; 
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'alerte");
                alert.setHeaderText("Veuillez saisir un chiffre");
                alert.showAndWait();
            }
            if ((!poids.matches("\\d+"))) {
                error("Le champs ne doit contenir que des chiffres !");
                valid = false ;            
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'alerte");
                alert.setHeaderText("Veuillez saisir un chiffre");
                alert.showAndWait();
            }
        }
        
        int newPrix = 0;
        
        if(valid = true){
            
       if(Integer.parseInt(poids)<= 5 && "Internationale".equals(getCbZone)){
           int[] array = new int[] {170, 150, 100, 80};
           Random rand = new Random();
           newPrix=(array[rand.nextInt(array.length)]);
       }
       else if(Integer.parseInt(poids)<= 5 && "Nationale".equals(getCbZone)){
           int[] array = new int[] {10, 9, 8, 7};
           Random rand = new Random();
           newPrix=(array[rand.nextInt(array.length)]);
       }
       
       else if(Integer.parseInt(poids)> 5 && "Internationale".equals(getCbZone)){
           int[] array = new int[] {180, 185, 186, 187};
           Random rand = new Random();
           newPrix=(array[rand.nextInt(array.length)]);
       }
       
       else if(Integer.parseInt(poids)> 5 && "Nationale".equals(getCbZone)){
           int[] array = new int[] {15, 16, 17, 18};
           Random rand = new Random();
           newPrix=(array[rand.nextInt(array.length)]);
       }
       
       else{
           System.out.println("Vérifier le prix et la zone");
       }
            
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutColis3.fxml"));
                Parent root = (Parent) loader.load();
                
                AjoutColis3Controller controller = loader.getController();
                controller.MyFunction(getCbZone, depart, destination, hauteur, largeur, poids, Boolean.toString(getFragile), Boolean.toString(getInflammable), Boolean.toString(getUrgent), Integer.toString(newPrix));
           
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
            }catch (IOException ex) {
            System.out.println(ex.getMessage());
          }
       }
    }
          }
