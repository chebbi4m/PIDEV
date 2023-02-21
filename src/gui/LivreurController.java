package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import taktak.entities.Livreur;
import taktak.services.LivreurService;
import taktak.utils.MyConnection;

public class LivreurController implements Initializable {
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfnumtel;
    @FXML
    private TextField tflogin;
    @FXML
    private TextField tfmdp;
    @FXML
    private TableView<Livreur> livreurTableView;
    @FXML
    private TableColumn<Livreur, Integer> idcol;
    @FXML
    private TableColumn<Livreur, String> nomcol;
    @FXML
    private TableColumn<Livreur, String> prenomcol;
    @FXML
    private TableColumn<Livreur, String> emailcol;
    @FXML
    private TableColumn<Livreur, String> numtelcol;
    @FXML
    private TableColumn<Livreur, String> logincol;
    @FXML
    private TableColumn<Livreur, String> mdpcol;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button updateBtn;

    LivreurService livreurService = new LivreurService();
    List<Livreur> livreurList = new ArrayList<>();
    ObservableList<Livreur> livreurObservableList = FXCollections.observableArrayList(livreurList);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();

        addBtn.setOnAction(this::handleAddButton);
        deleteBtn.setOnAction(this::handleDeleteButton);
        updateBtn.setOnAction(this::handleUpdateButton);
    }

    public void handleAddButton(ActionEvent event) {
        Livreur newLivreur = new Livreur();
        newLivreur.setId(Integer.parseInt(tfid.getText()));
        newLivreur.setNom(tfnom.getText());
        newLivreur.setPrenom(tfprenom.getText());
        newLivreur.setEmail(tfemail.getText());
        newLivreur.setNumtel(tfnumtel.getText());
        newLivreur.setLogin(tflogin.getText());
        newLivreur.setMdp(tfmdp.getText());
        livreurService.ajouterLivreur(newLivreur);

        livreurList.clear();
        livreurList.addAll(livreurService.afficherLivreur());
        livreurObservableList.clear();
        livreurObservableList.addAll(livreurList);
    }

    public void handleDeleteButton(ActionEvent event) {
        Livreur newLivreur = new Livreur();
        newLivreur.setId(Integer.parseInt(tfid.getText()));
        livreurService.supprimerLivreur(newLivreur);

        livreurList.clear();
        livreurList.addAll(livreurService.afficherLivreur());
        livreurObservableList.clear();
        livreurObservableList.addAll(livreurList);
    }

    public void handleUpdateButton(ActionEvent event) {
        Livreur newLivreur = new Livreur();
        newLivreur.setId(Integer.parseInt(tfid.getText()));
        newLivreur.setNom(tfnom.getText());           
        newLivreur.setPrenom(tfprenom.getText());
        newLivreur.setEmail(tfemail.getText());
        newLivreur.setNumtel(tfnumtel.getText());
        newLivreur.setLogin(tflogin.getText());
        newLivreur.setMdp(tfmdp.getText());
        livreurService.modifierLivreur(newLivreur);
        livreurList.clear();
        livreurList.addAll(livreurService.afficherLivreur());
        livreurObservableList.clear();
        livreurObservableList.addAll(livreurList);
        loadData();
}

public void loadData() {
    livreurList.clear();
    livreurList.addAll(livreurService.afficherLivreur());
    livreurObservableList.clear();
    livreurObservableList.addAll(livreurList);

    idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
    numtelcol.setCellValueFactory(new PropertyValueFactory<>("numtel"));
    logincol.setCellValueFactory(new PropertyValueFactory<>("login"));
    mdpcol.setCellValueFactory(new PropertyValueFactory<>("mdp"));

    livreurTableView.setItems(livreurObservableList);
}

