package gui;


import entities.partenaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import utils.SqlConnection;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class partenaireController implements Initializable {
    static Connection con = null;
    static ResultSet rs = null;
    static PreparedStatement st = null;


    @FXML
    private TableColumn<partenaire, String> col_nom;

    @FXML
    private TableColumn<partenaire, String> col_Transport;

    @FXML
    private TableColumn<partenaire, Boolean> col_Fragile;

    @FXML
    private TableColumn<partenaire, Integer> col_numtel;

    @FXML
    private TableColumn<partenaire, Boolean> col_Inflammable;

    @FXML
    private TableColumn<partenaire, Double> col_Prix1;
    @FXML
    private TableColumn<partenaire, Double> col_Prix2;
    @FXML
    private TableView<partenaire> table_partenaire;
    @FXML
    private TextField searchField;
    @FXML
    private void searchByName(KeyEvent event)
    {
        String searchText = searchField.getText().toLowerCase();
        for (partenaire person : table_partenaire.getItems()) {
            if (person.getNom().toLowerCase().contains(searchText)) {
                table_partenaire.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                table_partenaire.scrollTo(person);
                return;
            }}
        if (!table_partenaire.getSelectionModel().isEmpty()) {
            table_partenaire.scrollTo(table_partenaire.getSelectionModel().getSelectedIndex());
        }}





    public static ObservableList<partenaire> getDataparteniare() {

        ObservableList<partenaire> list = FXCollections.observableArrayList();
        String query = "select* from partenaire";
        con = SqlConnection.MyConnection();
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                partenaire st = new partenaire();
                st.setInflammable(rs.getBoolean("inflammable"));
                st.setNom(rs.getString("nom"));
                st.setMoyen_transport(rs.getString("moyen_transport"));
                st.setFragile(rs.getBoolean("fragile"));
                st.setNumtel(rs.getInt("numtel"));
                st.setPrix_poids(rs.getDouble("prix_poids"));
                st.setPrix_zone(rs.getDouble("prix_zone"));
                list.add(st);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
        return list;
    }

    public void showpartenaire() {
        ObservableList<partenaire> list = getDataparteniare();
        table_partenaire.setItems(list);
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_Transport.setCellValueFactory(new PropertyValueFactory<>("moyen_transport"));
        col_Fragile.setCellValueFactory(new PropertyValueFactory<>("fragile"));
        col_numtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        col_Prix1.setCellValueFactory(new PropertyValueFactory<>("prix_zone"));
        col_Prix2.setCellValueFactory(new PropertyValueFactory<>("prix_poids"));
        col_Inflammable.setCellValueFactory(new PropertyValueFactory<>("inflammable"));


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showpartenaire();
    }
    @FXML
    private Button Next;

    @FXML
    public void Navigate() throws IOException {
        // Load the FXML file for the other page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("paiement.fxml"));
        Parent root = loader.load();

        // Replace the scene root with the other page's root node
        Scene scene = Next.getScene();
        scene.setRoot(root);
        scene.getStylesheets().add("style.css");
        NavigateEvent event = new NavigateEvent ("/GUI/paiement.fxml");
        Next.fireEvent(event);
    }


}
