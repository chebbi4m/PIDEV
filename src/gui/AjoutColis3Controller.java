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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import taktak.entities.Colis;
import taktak.entities.RandomGenerator;
import taktak.services.ColisService;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class AjoutColis3Controller implements Initializable {
    
    
    @FXML
    private RadioButton getFragileOui;

    @FXML
    private ToggleGroup fragile;

    @FXML
    private RadioButton fragileNon;

    @FXML
    private RadioButton getInflammableOui;

    @FXML
    private ToggleGroup inflammable;

    @FXML
    private RadioButton inflammableNon;

    @FXML
    private RadioButton getUrgentOui;

    @FXML
    private ToggleGroup urgent;

    @FXML
    private RadioButton urgentNon;

    @FXML
    private Text getZone;

    @FXML
    private Text getDepart;

    @FXML
    private Text getDestination;

    @FXML
    private Text getHauteur;

    @FXML
    private Text getLargeur;

    @FXML
    private Text getPoids;

    @FXML
    private Text getPrix;

    @FXML
    void btnModifier(ActionEvent event) {

    }

    @FXML
    void btnAnnuler(ActionEvent event) {
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
    void radioInflammable(ActionEvent event) {

    }

    @FXML
    void radioUrgent(ActionEvent event) {

    }

    @FXML
    void radiofragile(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void MyFunction(String zone, String depart, String destination, String hauteur, String largeur, String poids, String fragile,String inflammable, String urgent, String prix){
        getZone.setText(zone);
        getDepart.setText(depart);
        getDestination.setText(destination);
        getHauteur.setText(hauteur);
        getLargeur.setText(largeur);
        getPoids.setText(poids);
        getFragileOui.setText(fragile);
        getInflammableOui.setText(inflammable);
        getUrgentOui.setText(urgent); 
        getPrix.setText(prix);
    }
    
    @FXML
    void btnConfirmer(ActionEvent event) {
        
        RandomGenerator generator = new RandomGenerator();
        String randomString = generator.generateRandomString();
        String ref = randomString;
        
        Colis cls = new Colis();
        ColisService cs = new ColisService();
        cls.setRef(ref);
        cls.setHauteur(Integer.parseInt(getHauteur.getText()));
        cls.setLargeur(Integer.parseInt(getLargeur.getText()));
        cls.setPoids(Integer.parseInt(getPoids.getText()));
        cls.setFragile(getFragileOui.isSelected());
        cls.setInflammable(getInflammableOui.isSelected());
        cls.setDepart(getDepart.getText());
        cls.setDestination(getDestination.getText());
        cls.setEtat_colis(cls.getEtat_colis());
        cls.setZone(getZone.getText());
        cls.setUrgent(getUrgentOui.isSelected());
        cls.setPrix(Integer.parseInt(getPrix.getText()));
        cls.setId_client(1);
        cls.setId_livreur(0);
       
        cs.ajouterColis(cls);
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Colis.fxml"));
            Parent root = (Parent) loader.load();
           
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
