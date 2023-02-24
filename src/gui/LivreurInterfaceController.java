/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import taktak.entities.LivreurInterface;

/**
 * FXML Controller class
 *
 * @author Cheima
 */
public class LivreurInterfaceController implements Initializable {

    @FXML
    private VBox LIvreurFonct;
    @FXML
    private HBox ListeCont;
    @FXML
    private HBox HistoriqueCont;
    @FXML
    private HBox ReclamationsCont;
    @FXML
    private ImageView LogoCont;
    @FXML
    private Button LogoutBtn;
    @FXML
    private TableColumn<LivreurInterface, String> RefCol;
    @FXML
    private TableColumn<LivreurInterface, String> NomCol;
    @FXML
    private TableColumn<LivreurInterface, String> DestinationCol;
    @FXML
    private TableColumn<LivreurInterface, String> PayementCol;
    @FXML
    private TableColumn<LivreurInterface, String> EtatCol;
    @FXML
    private ImageView LivreurLogo;
    @FXML
    private TableView<?> LivreurTable;
    @FXML
    private Label LivreurCont;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OpenProfile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LivreurCrud.fxml"));
        Parent root = loader.load();
        Stage CurrentStage = (Stage) LivreurLogo.getScene().getWindow();
        Scene scene = new Scene(root,1080,720);
        CurrentStage.setScene(scene);
        CurrentStage.show();
  
     
    }
    
}
