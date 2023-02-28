/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class Etape1Controller implements Initializable {
    @FXML
    private TextField fxDepart;

    @FXML
    private TextField fxDestination;

    @FXML
    private TextField fxHauteur;

    @FXML
    private TextField fxLargeur;
    
    int setPrix;

    @FXML
    private TextField fxPoids;

    
    String setFragile;

    @FXML
    private RadioButton fxFragileOui, fxFragileNon;
    
    @FXML
    void getFragile(ActionEvent event) {
        if(fxFragileOui.isSelected()){
            setFragile = fxFragileOui.getText();
        }
        else{
           setFragile = fxFragileNon.getText();
        }
    }
    
    String setInflammable;

    @FXML
    private RadioButton fxInflammableOui, fxInflammableNon;
    
    @FXML
    void getInflammable(ActionEvent event) {
        if(fxInflammableOui.isSelected()){
            setInflammable = fxInflammableOui.getText();
        }
        else{
           setInflammable = fxInflammableNon.getText();
        }
    }
    
    @FXML
    private TextField fxZone;
    
    String myZone;
    
    /*@FXML
    private ChoiceBox<String> CbZone;
    String[] zone = {"Natioanale","Internationale"};*/
    
    String setUrgent;

    @FXML
    private RadioButton fxUrgentOui, fxUrgentNon;
    
    @FXML
    void getUrgent(ActionEvent event) {
        if(fxUrgentOui.isSelected()){
            setUrgent = fxUrgentOui.getText();
        }
        else{
           setUrgent = fxUrgentNon.getText();
        }
    }


    
    @FXML
    void BtnRetour(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Historique.fxml"));
            Parent root = (Parent) loader.load();
           
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    


    @FXML
    void BtnSuivant(ActionEvent event) {
        try{
            if(Integer.parseInt(fxPoids.getText()) > 5){
                setPrix = 20;
            }
            else{
                setPrix =30;
            }
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Etape3.fxml"));
            Parent root = (Parent) loader.load();
            
            Etape3Controller controller = loader.getController();
            controller.MyFunction(fxDepart.getText(), fxDestination.getText(), fxHauteur.getText(), fxLargeur.getText(), fxPoids.getText(), setFragile, setInflammable, fxZone.getText(), setUrgent, Integer.toString(setPrix));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch(IOException e){
            System.out.append("Erreur d'affichage");
        }
        
        //System.out.println(fxPoids.getText());
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //CbZone.getItems().addAll(zone);
    }    
    
}
