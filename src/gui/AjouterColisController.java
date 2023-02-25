/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class AjouterColisController implements Initializable {
    
        @FXML
    private JFXTextField FxRef;

    @FXML
    private JFXTextField FxHauteur;

    @FXML
    private JFXTextField FxLargeur;

    @FXML
    private JFXTextField FxPoids;

    @FXML
    private JFXTextField FxPrix;

    @FXML
    private JFXTextField FxFragile;

    @FXML
    private JFXTextField FxDepart;

    @FXML
    private JFXTextField FxDestination;

    @FXML
    private JFXTextField FxNomReceveur;

    @FXML
    private JFXTextField FxIdClient;

    @FXML
    private JFXTextField FxIdPaiement;

    @FXML
    private JFXTextField FxIdLivreur;

        @FXML
    void BtnAjouter(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierColis.fxml"));
            Parent root = (Parent) loader.load();
            
            ModifierColisController controller = loader.getController();
            controller.MyFunction(FxRef.getText(), FxHauteur.getText(), FxLargeur.getText(), FxPoids.getText(), FxPrix.getText(), FxFragile.getText(), FxDepart.getText(), FxDestination.getText(), FxNomReceveur.getText(), FxIdClient.getText(), FxIdPaiement.getText(), FxIdLivreur.getText());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch(Exception e){
            System.out.append("Erreur d'affichage");
            e.printStackTrace();
        }
    }
    
   

    @FXML
    void BtnAnnuler(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudColis.fxml"));
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
