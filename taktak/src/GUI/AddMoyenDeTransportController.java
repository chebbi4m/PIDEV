package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import taktak.entities.MoyenDeTransport;
import  taktak.utils.MyConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddMoyenDeTransportController implements Initializable {

    @FXML
    private TextField marqueField;
    @FXML
    private RadioButton camionRadioButton;
    @FXML
    private RadioButton voitureRadioButton;
    @FXML
    private TextField matriculeField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;

    private Stage dialogStage;
    private ToggleGroup typeToggleGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeToggleGroup = new ToggleGroup();
        camionRadioButton.setToggleGroup(typeToggleGroup);
        voitureRadioButton.setToggleGroup(typeToggleGroup);
    }

    @FXML
    private void handleAddButton() {
        // Get the field values
        String marque = marqueField.getText();
        int type = 0;
        if (voitureRadioButton.isSelected()) {
            type = 1;
        }
        String matricule = matriculeField.getText();


        try {
            Connection connection = MyConnection.getInstance().getConnexion();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO moyen_de_transport(marque, type, Matricule) VALUES (?, ?, ? )");
            statement.setString(1, marque);
            statement.setInt(2, type);
            statement.setString(3, matricule);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        marqueField.getScene().getWindow().hide();    }

    @FXML
    private void handleCancelButton() {
        marqueField.getScene().getWindow().hide();    }

}
