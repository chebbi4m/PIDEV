/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Client;
import Entities.Partenaire;
import Services.ClientService;
import Services.PartenaireService;
import Session.UserSession;
import com.gluonhq.impl.charm.a.b.b.e;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author yasoulanda
 */
public class AccueilPartenaireController implements Initializable {
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
    private JFXButton reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//          Client client = getClientFromDatabase();
//        nomlogin.setText(client.getNom());

        //nomlogin = client.g ;
        
        Partenaire pr = new Partenaire();
          PartenaireService ps = new PartenaireService();
          pr = (Partenaire) UserSession.INSTANCE.get("partenaire");
          nomlogin.setText(pr.getNom());
    }  
    
    @FXML
    public void cancel (ActionEvent e){
               Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
    }
    
    @FXML
    public void retourner (ActionEvent e) throws IOException{
        

        
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("LoginPartenaire.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();
    
}
    
    @FXML
       public void hist (ActionEvent e) throws IOException{
        

        
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("HistoriquePartenaire.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();
    
}

    @FXML
    private void openReclamation(ActionEvent event) throws IOException {
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("partenaireReclamation.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

       

}
