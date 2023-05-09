/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.LivreurAvailability;
import Utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author Cheima
 */
public class LivreurAvailabilityController implements Initializable {

    @FXML
    private ImageView LogoCont;
    @FXML
    private Label LivreurCont;
    @FXML
    private ImageView LivreurLogo;
    @FXML
    private Button LogoutBtn;
    @FXML
    private VBox LivreurFonct;
    @FXML
    private HBox ListeCont;
    @FXML
    private Label unassignedColis;
    @FXML
    private HBox HistoriqueCont;
    @FXML
    private Label myColis;
    @FXML
    private HBox ReclamationsCont;
    @FXML
    private Label reclamation;
    @FXML
    private HBox ReclamationsCont1;
    @FXML
    private Label addAvailability;
    @FXML
    private ComboBox<String> statusSelect;
    @FXML
    private TextField reasonText;
    @FXML
    private Button submitAvailabilityBtn;
    @FXML
    private Button profile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusSelect.getItems().addAll(
               "Available",
               "Unavailable"
        

        );
    }    


    @FXML
    private void LogoutClick(ActionEvent event) {
    }

    @FXML
    private void OpenListeColis(MouseEvent event) {
    }

    @FXML
    private void submitAvailabilityAction(ActionEvent event) {
       int user_id=27;
         int boolStatus =1;
         String statusVar = statusSelect.getValue();
         if (statusVar.equals( "Available")){
         boolStatus = 1;
         }
         else{
              boolStatus =0;
         }
           String reasonVar = reasonText.getText();
           
  try {
   Connection conn = MyConnection.getInstance().getConnexion();
    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM livreur_availability WHERE id_livreur=(SELECT id FROM livreur WHERE user_id=?)");
    stmt.setInt(1, user_id);  
    ResultSet res=stmt.executeQuery();
    if (res.next()) {
          int id_livreur = res.getInt("id_livreur");
   PreparedStatement updateStmt = conn.prepareStatement("UPDATE livreur_availability SET status = ?, reason = ? WHERE id_livreur = ?");
        updateStmt.setInt(1, boolStatus);  // Replace with the actual status value
        updateStmt.setString(2, reasonVar);  // Replace with the actual reason value
        updateStmt.setInt(3, 6);  // Replace with the actual id_livreur value
        updateStmt.executeUpdate();
    } else {
        System.out.println("livreur introuvable");
    }

  
} catch (SQLException e) {
    e.printStackTrace();
}

    
  
    }  


    @FXML
    private void openMyProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LivreurCrud.fxml"));
        Parent root = loader.load();
        Stage CurrentStage = (Stage) profile.getScene().getWindow();
        Scene scene = new Scene(root,1080,720);
        CurrentStage.setScene(scene);
        CurrentStage.show();
    }

    @FXML
    private void OpenProfileLiv(MouseEvent event) {
    }
    
}