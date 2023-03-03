/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import taktak.entities.reclamation;
import taktak.services.ReclamationService;




public class Main extends Application {
    
    public static void main(String[] args) {
       
//       Colis cls = new Colis ("abcd123", 152, 243, 42, 2, true, "Tunis", 2, 2);
       reclamation rec = new reclamation(3, "colis is here", "Doe Doe", "6969");
 
//       ColisService cs = new ColisService();
       ReclamationService rs = new ReclamationService();
//       cs.ajouterColis(cls);

//        rs.ajouterReclamation(rec);
//        rs.deleteReclamation(1);
//        rs.updateReclamation(rec);
//        System.out.println(rs.afficherReclamation());
        launch(args);
//        System.out.println(cs.afficherColis());
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("reclamationFXML.fxml"));
        primaryStage.setTitle("taktak");
        primaryStage.setScene(new Scene(root, 1080, 650));
        primaryStage.show();
    }
   
    
}
