package gui;


import entities.partenaire;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;




public class testGUI extends Application {



    @Override
    public void start(Stage Stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("partenaire.fxml"));
        Parent root = loader.load();;
        partenaireController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage.setScene(scene);
        Image icon = new Image(getClass().getResourceAsStream("Icon.png"));
            Stage.getIcons().add(icon);
            Stage.setTitle("Colis");
        scene.addEventHandler(NavigateEvent.NAVIGATE, event -> {
            try {
                FXMLLoader newLoader = new FXMLLoader(getClass().getResource(event.getDestination()));
                Parent newRoot = newLoader.load();
                scene.setRoot(newRoot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        scene.getStylesheets().add("style.css");
            Stage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
