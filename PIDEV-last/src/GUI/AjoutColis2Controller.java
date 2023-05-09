/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class AjoutColis2Controller implements Initializable {
    
    @FXML
    private Label nomlogin;

    @FXML
    void btnAccueil(ActionEvent event) {

    }

    @FXML
    void btnDeconnecter(ActionEvent event) {

    }

    @FXML
    void btnHistorique(ActionEvent event) {

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
        try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Colis.fxml"));
                Parent root = (Parent) loader.load();           
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

    @FXML
    void carte(ActionEvent event) {

    }
    
    @FXML
    void Livraison(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
