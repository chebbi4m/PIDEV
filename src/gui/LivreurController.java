/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import taktak.entities.Livreur;

/**
 * FXML Controller class
 *
 * @author Cheima
 */
public class LivreurController implements Initializable {
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

    
    public void handleAddButton(ActionEvent event) {
    // Code to add a new Livreur
      Livreur newLivreur = new Livreur(169,"mohamed", "chebbi", "douisscheima4@gmail.com", "99781095","hello","mdp");
    newLivreur.setNom(nomTextField.getText());
    newLivreur.setPrenom(prenomTextField.getText());
    newLivreur.setNumtel(numtelTextField.getText());
    newLivreur.setLogin(loginTextField.getText());
    newLivreur.setMdp(mdpTextField.getText());
    

}

    public void handleDeleteButton(ActionEvent event) {
    // Code to delete a selected Livreur
}

    public void handleUpdateButton(ActionEvent event) {
    // Code to update a selected Livreur
}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
      nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
      prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      numtelcol.setCellValueFactory(new PropertyValueFactory<>("numtel"));
      logincol.setCellValueFactory(new PropertyValueFactory<>("login"));
      mdpcol.setCellValueFactory(new PropertyValueFactory<>("mdp"));
      addBtn.setOnAction(this::handleAddButton);
      deleteBtn.setOnAction(this::handleDeleteButton);
      updateBtn.setOnAction(this::handleUpdateButton);
    }  
    
    

   
}
