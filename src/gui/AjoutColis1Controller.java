/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import static sun.management.Agent.error;
import taktak.entities.Colis;
import taktak.entities.RandomGenerator;
import taktak.services.ColisService;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class AjoutColis1Controller implements Initializable {
    
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> zone = FXCollections.observableArrayList("Nationale", "Internationale");
        cbZone.setItems(zone);
    }
    
    @FXML
    void btnRetour(ActionEvent event) {
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
    
    @FXML
    void btnSuivant(ActionEvent event) {
        //RandomGenerator generator = new RandomGenerator();
        //String randomString = generator.generateRandomString();
       
        //String ref = randomString;
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
        
        //int hauteur = Integer.parseInt(fxHauteur.getText());
        //int largeur = Integer.parseInt(fxLargeur.getText());
        //int poids = Integer.parseInt(fxPoids.getText());

        
        if (getCbZone.length() == 0 || depart.length() == 0 || destination.length() == 0 || hauteur.length() == 0 || largeur.length() == 0 || poids.length() == 0) {
            error("Les champs ne peuvent pas être vides");
            valid = false ;         
        }else{
            if ((!hauteur.matches("\\d+"))) {
                error("Le champs ne doit contenir que des chiffres !");
                valid = false ;            
            }
            else if ((!largeur.matches("\\d+"))) {
                error("Le champs ne doit contenir que des chiffres !");
                valid = false ;            
            }
            if ((!poids.matches("\\d+"))) {
                error("Le champs ne doit contenir que des chiffres !");
                valid = false ;            
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
                stage.show();
            }catch (IOException ex) {
            System.out.println(ex.getMessage());
          }
       }
        
        
        //Colis cls = new Colis();
        //ColisService cs = new ColisService();
       
       /*cls.setRef(ref);
       cls.setHauteur(hauteur);
       cls.setLargeur(largeur);
       cls.setPoids(poids);
       cls.setFragile(getFragile);
       cls.setInflammable(getInflammable);
       cls.setDepart("Tunis");
       cls.setDestination("Tunis");
       cls.setEtat_colis(cls.getEtat_colis());
       cls.setZone(getZone);
       cls.setUrgent(getUrgent);
       cls.setId_client(1);
       cls.setId_livreur(0);
       
       cs.ajouterColis(cls);*/
       

    }
}
