/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import taktak.entities.Livreur;
import taktak.services.LivreurService;
import taktak.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Cheima
 */
public class LoginPageController implements Initializable {
    
    @FXML
    private TextField loginco;
    @FXML
    private TextField mdpco;
    @FXML
    private TextField creerNom;
    @FXML
    private TextField creerPrenom;
    @FXML
    private TextField creerEmail;
    @FXML
    private TextField creerNum;
    @FXML
    private TextField creerMdp;
    @FXML
    private Button btncreer;
    @FXML
    private TextField creerLogin;
     LivreurService livreurService = new LivreurService();
    @FXML
    private Button btnco;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    List<Livreur> livreur = new ArrayList<>();


    @FXML
    private void inscrire(ActionEvent event) {
        Livreur newLivreur = new Livreur();
        newLivreur.setNom(creerNom.getText());
        newLivreur.setPrenom(creerPrenom.getText());
        newLivreur.setEmail(creerEmail.getText());
        newLivreur.setNumtel(creerNum.getText());
        newLivreur.setMdp(creerMdp.getText());
        newLivreur.setLogin(creerLogin.getText());
        livreurService.ajouterLivreur(newLivreur);

//        livreurList.clear();
//        livreurList.addAll(livreurService.afficherLivreur());
//        livreurObservableList.clear();
//        livreurObservableList.addAll(livreurList);
    }

    @FXML
    private void connecter(ActionEvent event) {
        
        Connection myconn = MyConnection.getInstance().getConnexion();
        Boolean mpdtemp = false;
        Boolean logintemp = false;
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement("SELECT * FROM livreur");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String mdp = resultSet.getString("mdp");
                System.out.println("input");
                System.out.println(mdpco.getText());
                System.out.println(loginco.getText());
                System.out.println("in database");
                System.out.println(mdp);
                System.out.println(login);
                if (login == loginco.getText()){
                    if(mdp == mdpco.getText()){
                         mpdtemp = true;
                         logintemp = true;
                         break;
                    }
                    
                    
                }
                System.out.println(mpdtemp);
                System.out.println(logintemp);
                
                
            }
            if (mpdtemp == true && logintemp == true){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Login successful");
                    alert.setHeaderText(null);
                    alert.setContentText("Login successful");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("information incorrecte");
                    alert.setHeaderText(null);
                    alert.setContentText("information incorrecte");
                    alert.showAndWait();
                }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    
}
