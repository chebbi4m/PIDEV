package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import taktak.entities.reclamation;
import taktak.services.ReclamationService;
import taktak.utils.MyConnection;

public class ReclamationFXMLController implements Initializable {

    @FXML
    private TextField nomPersonneTextField;

    @FXML
    private TextArea textTextField;

    @FXML
    private TextField refColisTextField;

    @FXML
    private TableColumn<reclamation, String> ref_id;

    @FXML
    private TableColumn<reclamation, String> nom_id;

    @FXML
    private TableColumn<reclamation, String> reclamation_id;

    @FXML
    private TableView<reclamation> reclamation_table;

    ObservableList<reclamation> ReclamationList = FXCollections.observableArrayList();

    ReclamationService rs = new ReclamationService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadElements();
    }

    @FXML
    private void handleSaveButtonAction() {
        String nomPersonne = nomPersonneTextField.getText();
        String text = textTextField.getText();
        String refColis = refColisTextField.getText();
        reclamation rec = new reclamation(1, text, nomPersonne, refColis);

        rs.ajouterReclamation(rec);
        handleRefreshButtonAction();
    }

    @FXML
    private void handleCancelButtonAction() {
        // Close the form or reset the fields
    }

    @FXML
    private void handleRefreshButtonAction() {
        ReclamationList.clear();
        Connection myconn = MyConnection.getInstance().getConnexion();
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement("SELECT * FROM reclamation");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                String nomPersonne = resultSet.getString("nom_personne");
                String refColis = resultSet.getString("ref_colis");
                reclamation reclamation = new reclamation(id, text, nomPersonne, refColis);
                ReclamationList.addAll(reclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        ref_id.setCellValueFactory(new PropertyValueFactory<>("refColis"));
        nom_id.setCellValueFactory(new PropertyValueFactory<>("nomPersonne"));
        reclamation_id.setCellValueFactory(new PropertyValueFactory<>("text"));
        reclamation_table.setItems(ReclamationList);
    }

    private void loadElements() {
        ref_id.setCellValueFactory(new PropertyValueFactory<>("refColis"));
        nom_id.setCellValueFactory(new PropertyValueFactory<>("nomPersonne"));
        reclamation_id.setCellValueFactory(new PropertyValueFactory<>("text"));
        handleRefreshButtonAction();
    }
}
