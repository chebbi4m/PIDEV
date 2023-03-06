/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Client;
import Entities.reclamation;
import Services.ClientService;
import Services.ReclamationService;
import Services.emailService;
import Session.UserSession;
import Utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

/**                                                           
 * FXML Controller class
 *
 * @author yasoulanda
 */
public class WelcomeController implements Initializable {
    @FXML private JFXButton fermer;
    @FXML private JFXButton retour;
    @FXML private Label nomlogin;
    @FXML
    private JFXButton historique;
    @FXML
    private JFXButton historique1;
    @FXML
    private JFXButton historique2;
    @FXML
    private JFXButton reclamation_btn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
//          Client client = getClientFromDatabase();
//        nomlogin.setText(client.getNom());

        //nomlogin = client.g ;
        
        Client pr = new Client();
          ClientService ps = new ClientService();
          pr = (Client) UserSession.INSTANCE.get("client");
          nomlogin.setText(pr.getLogin());
    }  
    
    @FXML
    public void cancel (ActionEvent e){
               Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
    }
    
    @FXML
    public void retourner (ActionEvent e) throws IOException{
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("LoginClient.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();
    
}
    
//       public void hist (ActionEvent e) throws IOException{
//        
//
//        
//        Stage stage = new Stage ();
//        Parent root = FXMLLoader.load(getClass().getResource("HistoriquePartenaire.fxml"));  
//        Scene scene = new Scene (root);
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.show();
//        ((Node)e.getSource()).getScene().getWindow().hide();
//    
//}
//       
//       public void histLiv (ActionEvent e) throws IOException{
//        
//
//        
//        Stage stage = new Stage ();
//        Parent root = FXMLLoader.load(getClass().getResource("HistoriqueLivreur.fxml"));  
//        Scene scene = new Scene (root);
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.show();
//        ((Node)e.getSource()).getScene().getWindow().hide();
//    
//}

    @FXML
    private void reclamation(ActionEvent event) throws IOException, SQLException {
        
        
       Parent root = FXMLLoader.load(getClass().getResource("clienReclamation.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) historique.getScene().getWindow();
        window.setScene(scene);

    }
    
    
    @FXML
    private void cancel(MouseEvent event) {
    }

    @FXML
    private void retourner(MouseEvent event) {
    }


    
       
}