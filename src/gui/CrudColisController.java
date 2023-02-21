/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class CrudColisController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @FXML private JFXButton retour;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        
    }
    
     public void retour (ActionEvent e) throws IOException{
            Stage stage = new Stage ();
            Parent root = FXMLLoader.load(getClass().getResource("GestionColis.fxml"));  
            Scene scene = new Scene (root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            ((Node)e.getSource()).getScene().getWindow().hide();
        }  
        
    
}
