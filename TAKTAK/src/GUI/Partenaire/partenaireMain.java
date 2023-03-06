/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Partenaire;

import java.io.IOException;
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
import taktak.entities.LivreurInterface;

/**
 *
 * @author Najet
 */
public class partenaireMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Livreurs.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Gestion Partenaire");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
       LivreurInterface livreur = new LivreurInterface();
    public void checkAvailability() {
    if (livreur.getNbre_colis_courant() <= 5) {
        livreur.setNbre_colis_courant(livreur.getNbre_colis_courant() + 1);
        System.out.println("Livreur disponible");
    } else {
        System.out.println("Livreur non disponible");
    }
}
    
}
