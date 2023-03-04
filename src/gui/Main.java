/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import taktak.entities.Livreur;

/**
 *
 * @author LENOVO THINKPAD E15
 */
public class Main extends Application {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LivreurInterface.fxml"));
        primaryStage.setTitle("taktak");
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.show();
    }

}
