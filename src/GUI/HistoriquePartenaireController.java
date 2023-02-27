/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Crud.Client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.Connection;
import Crud.MyConnection;
import static com.mysql.cj.Messages.getString;
import java.sql.ResultSetMetaData;



/**
 * FXML Controller class
 *
 * @author yasoulanda
 */
public class HistoriquePartenaireController implements Initializable {
        @FXML private JFXButton fermer;
        //@FXML private JFXButton deconnecter;
        @FXML private JFXTextField username;
        
    
      @FXML private TableView<Client> tablev;
      @FXML private TableColumn<Client, String> nomclient;
      @FXML private TableColumn<Client, String> numtel;
      @FXML private TableColumn<Client, String> nomlivreur;
      @FXML private TableColumn<Client, String> refcolis;
      @FXML private TableColumn<Client, String> prixcolis;
    
        Connection myconn = MyConnection.getInstance().getConnexion();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
                   public ObservableList<Client> afficherListeClients() {
        tablev.getItems().clear();
        ObservableList<Client> clients = FXCollections.observableArrayList();
                
        try {
            Statement stmt = myconn.createStatement();
            String query = "SELECT client.nom, client.numtel " +
                           "FROM client, colis, livreur, partenaire " +
                           "WHERE client.login ='" + username.getText() + "' "+
                           "AND client.id = colis.id_client " +
                           "AND colis.id_livreur = livreur.id " +
                           "AND livreur.id_partenaire = partenaire.id";
             // stmt(1, username.getText());
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String nom = rs.getString("nom");
                String numtel = rs.getString("numtel");

                Client cl = new Client(
                    rs.getString("nom"),
                    rs.getString("numtel"));
                clients.add(cl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
        return clients;
    }
            
    public void afficherClients (){
        ObservableList<Client> list = afficherListeClients();
        
        nomclient.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        numtel.setCellValueFactory(new PropertyValueFactory<Client,String>("numtel"));
        tablev.setItems(list);
        //tablev.setItems(clientList);
    }              
    
        public void cancel (ActionEvent e){
               Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
    }
        
        
    public void retourner (ActionEvent e) throws IOException{
        

        
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();
    
}
}
