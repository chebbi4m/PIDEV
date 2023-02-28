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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class Etape3Controller implements Initializable {
    
        @FXML
    private TextField getDepart;

    @FXML
    private TextField getDestination;

    @FXML
    private TextField getHauteur;

    @FXML
    private TextField getLargeur;

    @FXML
    private TextField getPoids;

    @FXML
    private TextField getFragile;

    @FXML
    private TextField getInflammable;

    @FXML
    private TextField getZone;

    @FXML
    private TextField getUrgent;

    @FXML
    private TextField getPrix;
    
    @FXML
    private void BtnAnnuler(ActionEvent event){
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

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void MyFunction(String depart, String destination, String hauteur, String largeur, String poids, String fragile,String inflammable, String zone, String urgent, String prix){
        getDepart.setText(depart);
        getDestination.setText(destination);
        getHauteur.setText(hauteur);
        getLargeur.setText(largeur);
        getPoids.setText(poids);
        getFragile.setText(fragile);
        getInflammable.setText(inflammable);
        getZone.setText(zone);
        getUrgent.setText(urgent); 
        getPrix.setText(prix);
    }
    
    
    
}
