/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import taktak.entities.reclamation;
import taktak.services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author chebbi4m
 */
public class ReclamationFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private TextField nomPersonneTextField;

    @FXML
    private TextField textTextField;

    @FXML
    private TextField refColisTextField;

    @FXML
    private void handleSaveButtonAction() {
        String nomPersonne = nomPersonneTextField.getText();
        String text = textTextField.getText();
        String refColis = refColisTextField.getText();
        reclamation rec = new reclamation(1,text,nomPersonne,refColis);
        ReclamationService rs = new ReclamationService();
        rs.ajouterReclamation(rec);
        
        // Use these values to create a new Reclamation object and save it to the database using your DAO class
    }

    @FXML
    private void handleCancelButtonAction() {
        // Close the form or reset the fields
    }
    
}
