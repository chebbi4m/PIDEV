package gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class testGUI extends Application {



    @Override
    public void start(Stage Stage) throws Exception {

            Parent parent = FXMLLoader.load(getClass().getResource("/gui/colis.fxml"));
            Scene scene = new Scene(parent);
            Stage.setScene(scene);
            Stage.setTitle("colis");
            Stage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }
}
