/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Partenaire;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import taktak.entities.Colis;

/**
 * FXML Controller class
 *
 * @author Najet
 */
public class ColisController implements Initializable {

    @FXML
    private Button btnlogout;
    @FXML
    private ImageView logo;
    @FXML
    private TableView<Colis> tvColis;

    @FXML
    private TableColumn<Colis, String> colref;
    @FXML
    private TableColumn<Colis, Double> colhauteur;
    @FXML
    private TableColumn<Colis, Double> collargeur;
    @FXML
    private TableColumn<Colis, Double> colpoids;
    @FXML
    private TableColumn<Colis, Double> colprix;
    @FXML
    private TableColumn<Colis, Boolean> colfragile;
    @FXML
    private TableColumn<Colis, Boolean> colinflammable;
    @FXML
    private TableColumn<Colis, String> coldepart;
    @FXML
    private TableColumn<Colis, String> coldestination;
    @FXML
    private TableColumn<Colis, String> coletat_colis;
    @FXML
    private TableColumn<Colis, String> colzone;
    @FXML
    private TableColumn<Colis, Boolean> colurgent;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
