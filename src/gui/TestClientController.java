/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Client;
import Services.ClientService;
import Session.UserSession;
import Utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
 * @author yasoulanda
 */
public class TestClientController implements Initializable {
        @FXML private JFXButton fermer;
        @FXML private JFXButton deconnecter;
        @FXML private Label name;
                Connection myconn = MyConnection.getInstance().getConnexion();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//          Client cl = new Client();
//          ClientService cs = new ClientService();
//          cl = (Client) UserSession.INSTANCE.get("client");
//          name.setText(cl.getLogin());
//          System.out.println(cl.getLogin());
    }  
    
            public void cancel (ActionEvent e){
               Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
    }
        
        
    public void retourner (ActionEvent e) throws IOException{
        

        
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("LoginClient.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();
    
}
    
}
