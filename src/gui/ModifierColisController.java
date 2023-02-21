/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import taktak.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class ModifierColisController implements Initializable {
    
    @FXML
    private Label SetRef;

    @FXML
    private Label SetHauteur;

    @FXML
    private Label SetLargeur;

    @FXML
    private Label SetPoids;

    @FXML
    private Label SetPrix;

    @FXML
    private Label SetFragile;

    @FXML
    private Label SetDepart;

    @FXML
    private Label SetDestination;

    @FXML
    private Label SetNomReceveur;

    @FXML
    private Label SetIdClient;

    @FXML
    private Label SetIdPaiement;

    @FXML
    private Label SetIdLivreur;

    @FXML
    void BtnAjouter(ActionEvent event) {

    }

    @FXML
    void BtnAnnuler(ActionEvent event) {

    }

    @FXML
    void BtnAction(ActionEvent event) {
        
    }
    
    Connection myconn = MyConnection.getInstance().getConnexion();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void MyFunction(String ref, String hauteur, String largeur, String poids, String prix, String fragile,String depart, String destination, String nom_receveur, String id_client, String id_paiement, String id_livreur){
        SetRef.setText(ref);
        SetHauteur.setText(hauteur);
        SetLargeur.setText(largeur);
        SetPoids.setText(poids);
        SetPrix.setText(prix);
        SetFragile.setText(fragile);
        SetDepart.setText(depart);
        SetDestination.setText(destination);
        SetNomReceveur.setText(nom_receveur);
        SetIdClient.setText(id_client);
        SetIdPaiement.setText(id_paiement);
        SetIdLivreur.setText(id_livreur);
    }
    
    private void ajouter() throws IOException, SQLException {
        try {
            PreparedStatement stmt = this.myconn.prepareStatement("insert into colis VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, this.SetRef.getText());
            stmt.setString(2, this.SetHauteur.getText());
            stmt.setString(3, this.SetLargeur.getText());
            stmt.setString(4, this.SetPoids.getText());
            stmt.setString(5, this.SetPrix.getText());
            stmt.setString(6, this.SetFragile.getText());
            stmt.setString(7, this.SetDepart.getText());
            stmt.setString(7, this.SetDestination.getText());
            stmt.setString(7, this.SetNomReceveur.getText());
            stmt.setString(7, this.SetIdClient.getText());
            stmt.setString(7, this.SetIdPaiement.getText());
            stmt.setString(7, this.SetIdLivreur.getText());
            stmt.executeUpdate();
            //this.check(" client inseré ");
        } catch (SQLException var2) {
            System.out.println(var2);
        }

        //this.afficherClients();
    }
}
