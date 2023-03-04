/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import taktak.entities.Colis;
import taktak.entities.RandomGenerator;
import taktak.services.ColisService;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class AjoutColisController implements Initializable {
    
     @FXML
    private TextField fZone;

    @FXML
    private TextField fDepart;

    @FXML
    private TextField fDestination;

    @FXML
    private TextField fHauteur;

    @FXML
    private TextField fPoids;

    @FXML
    private TextField fLargeur;

    @FXML
    private TextField fFragile;

    @FXML
    private TextField fInflammable;

    @FXML
    private TextField fUrgent;
    
      @FXML
    private ComboBox cbZone;

    @FXML
    private Label labelTest;
    
    @FXML
    private ComboBox cbDepart;
    
    @FXML
    private ComboBox cbDestination;

    @FXML
    void selectZone(ActionEvent event) {
        String s = cbZone.getSelectionModel().getSelectedItem().toString();
            if(s.equals("Nationale")){
                cbDepart.getItems().clear();
                cbDepart.getItems().addAll("Tunis", "Nabeul");
                cbDestination.getItems().clear();
                cbDestination.getItems().addAll("Tunis", "Nabeul");
            }else{
                cbDepart.getItems().clear();
                cbDepart.getItems().addAll("Asie", "Afrique", "Europe");
                cbDestination.getItems().clear();
                cbDestination.getItems().addAll("Asie", "Afrique", "Europe");
            }
    }

        
    @FXML
    void selectDepart(ActionEvent event) {
        //String dep = cbDepart.getSelectionModel().getSelectedItem().toString();
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> zone = FXCollections.observableArrayList("Nationale", "Internationale");
        cbZone.setItems(zone);
        //ObservableList<String> depart = FXCollections.observableArrayList("Tunis", "Nabeul");
        //cbDepart.setItems(depart);
    }   
    
    @FXML
    void BtnAdd(ActionEvent event) {
       RandomGenerator generator = new RandomGenerator();
       String randomString = generator.generateRandomString();
       
       String ref = randomString;
       String zone = fZone.getText();
       String depart = fDepart.getText();
       String destination = fDestination.getText();
       int hauteur = Integer.parseInt(fHauteur.getText());
       int largeur = Integer.parseInt(fLargeur.getText());
       int poids = Integer.parseInt(fPoids.getText());
       boolean fragile = Boolean.parseBoolean(fFragile.getText());
       boolean inflammable = Boolean.parseBoolean(fInflammable.getText());
       boolean urgent = Boolean.parseBoolean(fUrgent.getText());
              
       Colis cls = new Colis(/*ref, hauteur, largeur, poids, 10, fragile, inflammable, depart, destination, null, zone, urgent, 0, 0*/);
       ColisService cs = new ColisService();
       
       cls.setRef(ref);
       cls.setHauteur(hauteur);
       cls.setLargeur(largeur);
       cls.setPoids(poids);
       cls.setFragile(fragile);
       cls.setInflammable(inflammable);
       cls.setDepart(depart);
       cls.setDestination(destination);
       cls.setEtat_colis(cls.getEtat_colis());
       cls.setZone(zone);
       cls.setUrgent(urgent);
       cls.setId_client(1);
       cls.setId_livreur(0);
       
       cs.ajouterColis(cls);
       
    }
    
}
