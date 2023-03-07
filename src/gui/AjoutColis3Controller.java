/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Entities.Colis;
import Entities.Livreur;
import Entities.RandomGenerator;
import Services.ClientService;
import Services.ColisService;
import Services.LivreurService;
import Session.UserSession;
import java.util.Random;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class AjoutColis3Controller implements Initializable {
    
    @FXML
    private Label nomlogin;
    
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
            Stage stage = new Stage ();
            Parent root = FXMLLoader.load(getClass().getResource("Colis.fxml"));  
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Client pr = new Client();
          ClientService ps = new ClientService();
          pr = (Client) UserSession.INSTANCE.get("client");
          nomlogin.setText(pr.getLogin());
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
        
        /*Livreur lv= new Livreur();
        LivreurService ls = new LivreurService();
        lv = (Livreur) UserSession.INSTANCE.get("livreur");
        int getIdLivreur = lv.getId();*/
        
        Client cl = new Client();
        ClientService clis= new ClientService();
        cl = (Client) UserSession.INSTANCE.get("client");
        int getClientId = cl.getId();
        
        
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
        cls.setId_client(getClientId);
        //cls.setId_livreur(getIdLivreur);
        
        String[] array = new String[] {"ARAMEX", "DHL", "FEDEX"};
        Random rand = new Random();
        String newPartenaire =(array[rand.nextInt(array.length)]);
        
        cls.setNom_partenaire(newPartenaire);
        System.out.println(newPartenaire);
       
        cs.ajouterColis(cls);
        
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
}
