/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Entities.Client;
import Services.ClientService;
import Session.UserSession;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WelcomeController implements Initializable{

    @FXML
    private Label nomlogin;


    @FXML
    private JFXButton fermer;

    @FXML
    private JFXButton retour;
    
    @FXML
    void btnAccueil(ActionEvent event) {

    }

    @FXML
    void btnColis(ActionEvent event) throws IOException {
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Colis.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void btnHistorique(ActionEvent event) throws IOException {
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("HistoriqueColis.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void cancel(MouseEvent event) {

    }

    void reclamation(ActionEvent event) {

    }

    @FXML
    void retourner(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Client pr = new Client();
          ClientService ps = new ClientService();
          pr = (Client) UserSession.INSTANCE.get("client");
          nomlogin.setText(pr.getPrenom());
    }

    @FXML
    private void openReclamation(ActionEvent event) throws IOException {
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("clienReclamation.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void retourner(ActionEvent event) {
    }

}
