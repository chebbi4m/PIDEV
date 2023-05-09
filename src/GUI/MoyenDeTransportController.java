package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

import Entities.MoyenDeTransport;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Utils.MyConnection;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MoyenDeTransportController implements Initializable {
   @FXML

    public Button voitureButton;
   @FXML
    public Button camionButton;

    @FXML
    public Button refreshButton;
    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;
    @FXML
    private TableView<MoyenDeTransport> table;
    @FXML
    private TableColumn<MoyenDeTransport, Integer> colId;
    @FXML
    private TableColumn<MoyenDeTransport, String> colMarque;
    @FXML
    private TableColumn<MoyenDeTransport, String> colType;
    @FXML
    private TableColumn<MoyenDeTransport, String> colMatricule;
    @FXML
    private TableColumn<MoyenDeTransport, String> assignedColumn;
    @FXML
    private TableColumn<MoyenDeTransport, String> actionsColumn;
    @FXML
    private void handleAddMoyenDeTransportButton() {
        // Create a new stage for the form
        Stage stage = new Stage();
        stage.setTitle("Add Moyen De Transport");

        // Load the FXML file for the form
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMoyenDeTransport.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Create a new scene with the form and set it as the stage's scene
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Node tableViewRoot = table.getScene().getRoot();
        tableViewRoot.setDisable(true);

        stage.showAndWait();

        tableViewRoot.setDisable(false);    }
    @FXML
    public void handleRefresh(ActionEvent event) {
        table.getItems().clear();
        table.setItems(getMoyenDeTransport());
        refreshButton.getStyleClass().add("button-secondary");

    }

    public static ObservableList<MoyenDeTransport> getMoyenDeTransport() {
        Connection conn = MyConnection.getInstance().getConnexion();
        ObservableList<MoyenDeTransport> list = FXCollections.observableArrayList();
        try {
            PreparedStatement stm = conn.prepareStatement("SELECT id, marque, type, matricule, livreur_id FROM moyen_de_transport");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                MoyenDeTransport mdt = new MoyenDeTransport(
                        rs.getInt("id"),
                        rs.getString("marque"),
                        rs.getInt("type"),
                        rs.getString("matricule"),
                        0, // idPar field not used
                         rs.getInt("livreur_id")                );
                list.add(mdt);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return list;
    }

    @FXML
    private void handleVoiture() {
        Connection conn = MyConnection.getInstance().getConnexion();
        ObservableList<MoyenDeTransport> list = FXCollections.observableArrayList();
        try {
            PreparedStatement stm = conn.prepareStatement("SELECT id, marque, type, matricule, livreur_id FROM moyen_de_transport WHERE type = 1");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                MoyenDeTransport mdt = new MoyenDeTransport(
                        rs.getInt("id"),
                        rs.getString("marque"),
                        rs.getInt("type"),
                        rs.getString("matricule"),
                        0, // idPar field not used
                        rs.getInt("livreur_id")                );
                list.add(mdt);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        table.setItems(list);
    }

    @FXML
    private void handleCamion() {
        Connection conn = MyConnection.getInstance().getConnexion();
        ObservableList<MoyenDeTransport> list = FXCollections.observableArrayList();
        try {
            PreparedStatement stm = conn.prepareStatement("SELECT id, marque, type, matricule, livreur_id FROM moyen_de_transport WHERE type = 0");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                MoyenDeTransport mdt = new MoyenDeTransport(
                        rs.getInt("id"),
                        rs.getString("marque"),
                        rs.getInt("type"),
                        rs.getString("matricule"),
                        0, // idPar field not used
                        rs.getInt("livreur_id")                );
                list.add(mdt);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        table.setItems(list);
    }

    @FXML
    private void handleSearch() {
        String searchQuery = searchField.getText().trim();

        if (!searchQuery.isEmpty()) {
            try {
                Connection conn = MyConnection.getInstance().getConnexion();
                PreparedStatement stm = conn.prepareStatement("SELECT id, marque, type, matricule, livreur_id FROM moyen_de_transport WHERE marque LIKE ?");
                stm.setString(1, "%" + searchQuery + "%");
                ResultSet rs = stm.executeQuery();

                ObservableList<MoyenDeTransport> searchResults = FXCollections.observableArrayList();
                while (rs.next()) {
                    MoyenDeTransport mdt = new MoyenDeTransport(
                            rs.getInt("id"),
                            rs.getString("marque"),
                            rs.getInt("type"),
                            rs.getString("matricule"),
                            0, // idPar field not used
                            rs.getInt("livreur_id")
                    );
                    searchResults.add(mdt);
                }

                table.setItems(searchResults);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
            table.setItems(getMoyenDeTransport());
        }
        searchField.requestFocus();
        searchButton.getStyleClass().add("button-primary");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        colType.setCellValueFactory(cellData -> {
            int type = cellData.getValue().getType();
            if (type == 0) {
                return new SimpleStringProperty("Camion");

            } else if (type == 1) {
                return new SimpleStringProperty("Voiture");
            } else return new SimpleStringProperty("uknown MoyenDeTransport");
        });
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        assignedColumn.setCellValueFactory(cellData -> {
            int livreurId = cellData.getValue().getLivreurId();
            if (livreurId == 0) {
                return new SimpleStringProperty("non");
            } else {
                return new SimpleStringProperty("oui");
            }
        });
        Callback<TableColumn<MoyenDeTransport, String>, TableCell<MoyenDeTransport, String>> cellFactory = new Callback<TableColumn<MoyenDeTransport, String>, TableCell<MoyenDeTransport, String>>() {
            @Override
            public TableCell call(final TableColumn<MoyenDeTransport, String> param) {
                final TableCell<MoyenDeTransport, String> cell = new TableCell<MoyenDeTransport, String>() {

                    final Button btnModifier = new Button("Modifier");

                    final Button btnDelete = new Button("Supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            MoyenDeTransport selectedMdt = getTableView() != null ? getTableView().getItems().get(getIndex()) : null;
                            if (selectedMdt != null) {
                                btnModifier.setOnAction(event -> {
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditMoyenDeTransport.fxml"));
                                        Parent root = loader.load();

                                        EditMoyenDeTransportController controller = loader.getController();
                                        controller.setSelectedMdt(selectedMdt);
                                        controller.setTableView(table);

                                        Scene scene = new Scene(root);
                                        Stage stage = new Stage();
                                        stage.setScene(scene);
                                        stage.setTitle("Modifier moyen de transport");
                                        stage.initModality(Modality.APPLICATION_MODAL);

                                        Node tableViewRoot = table.getScene().getRoot();
                                        tableViewRoot.setDisable(true);

                                        stage.showAndWait();

                                        tableViewRoot.setDisable(false);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });

                                btnDelete.setOnAction(event -> {
                                    try {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Confirmation de suppression");
                                        alert.setHeaderText("Voulez-vous vraiment supprimer ce moyen de transport ?");
                                        alert.setContentText("Cette action est irréversible");

                                        Optional<ButtonType> result = alert.showAndWait();
                                        if (result.isPresent() && result.get() == ButtonType.OK) {
                                            // Delete the selected object from the database
                                            selectedMdt.deleteFromDatabase();

                                            // Remove the selected object from the table view
                                            table.getItems().remove(selectedMdt);

                                            // Refresh the table view
                                            table.refresh();
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                });

                                HBox hbox = new HBox();
                                hbox.getChildren().addAll(btnModifier, btnDelete);
                                hbox.setSpacing(5);
                                setGraphic(hbox);
                                setText(null);
                            }
                        }
                    }
                };
                return cell;
            }
        };
        actionsColumn.setCellFactory(cellFactory);

        // Populate the table view with data
        table.setItems(getMoyenDeTransport());
        searchButton.setOnAction(event -> handleSearch());



    }}

