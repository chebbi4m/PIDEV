/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Services.SuivieColisService;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author nedia
 */
public class HomePageController implements Initializable {

    @FXML
    private TextField tracking_input;
    @FXML
    private Text etat_track;
    @FXML
    private Button login_button;
    @FXML
    private Button tracking_button;
    @FXML
    private Button visualiser_button;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    public void getTrack() throws SQLException{
        SuivieColisService suivieColis = new SuivieColisService();
        if(tracking_input.getText() == null){
         tracking_input.setText("no");
        }
        else{
            etat_track.setText(suivieColis.getEtatColis(tracking_input.getText()));
        }
        
    }
    
    @FXML
    public void openVisualiserDevis() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualiserDevis.fxml"));
        Parent root = loader.load();
        Stage currentStage = (Stage) tracking_input.getScene().getWindow();
        Scene scene = new Scene(root,1080,650);
        currentStage.setScene(scene);
        currentStage.show();
    }

    @FXML
    private void login_btn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginClient.fxml"));
        Parent root = loader.load();
        Stage currentStage = (Stage) tracking_input.getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.show();
        
    }
    
}
