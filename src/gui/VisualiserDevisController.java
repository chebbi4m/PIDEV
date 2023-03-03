/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


/**
 * FXML Controller class
 *
 * @author nedia
 */
public class VisualiserDevisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField destination_text;
    @FXML
    private TextField depart_text;
    @FXML
    private TextField hauteur_text;
    @FXML
    private TextField largeur_text;
    @FXML
    private TextField poids_text;
    @FXML
    private ComboBox<String> zone_text;
    @FXML
    private CheckBox fragile_checkbox;
    @FXML
    private CheckBox inflammable_checkbox;
    @FXML
    private Button submit_button;
    @FXML
    private CheckBox urgent_checkbox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zone_text.getItems().addAll("Amerique", "Afrique", "Europe","Asie","Australie");
        
        final String numberFormatter = "[\\d\\.]*";
        
        hauteur_text.setTextFormatter(new TextFormatter<>(change ->
        (change.getControlNewText().matches(numberFormatter)) ? change : null));
        largeur_text.setTextFormatter(new TextFormatter<>(change ->
        (change.getControlNewText().matches(numberFormatter)) ? change : null));
        poids_text.setTextFormatter(new TextFormatter<>(change ->
        (change.getControlNewText().matches(numberFormatter)) ? change : null));
    }

    @FXML
    private void visualiser(ActionEvent event) {
        float hauteur = Float.parseFloat(hauteur_text.getText());
        float poids = Float.parseFloat(poids_text.getText());
        float largeur = Float.parseFloat(largeur_text.getText());
        String destination = destination_text.getText();
        String depart = depart_text.getText();
        String zone = zone_text.getValue();
        Boolean fragile = fragile_checkbox.isSelected();
        Boolean urgent = urgent_checkbox.isSelected();
        Boolean inflammable = inflammable_checkbox.isSelected();
    }
}
