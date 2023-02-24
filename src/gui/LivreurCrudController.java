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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import taktak.entities.Livreur;
import taktak.entities.LivreurInterface;
import taktak.services.LivreurInterfaceService;
import taktak.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Cheima
 */
public class LivreurCrudController implements Initializable {
    Connection myconn = MyConnection.getInstance().getConnexion();
      private LivreurInterfaceService livreurInterfaceService;
    @FXML
    private ImageView LogoCont;
    @FXML
    private Label LivreurCont;
    @FXML
    private ImageView LivreurLogo;
    @FXML
    private Button LogoutBtn;
    @FXML
    private HBox ListeCont;
    @FXML
    private HBox HistoriqueCont;
    @FXML
    private HBox ReclamationsCont;
    @FXML
    private Button UpdateeBtn;
    @FXML
    private TextField NomAffich;
    @FXML
    private TextField PrenomAffich;
    @FXML
    private TextField LoginAffich;
    @FXML
    private TextField EmailAffich;
    @FXML
    private TextField NumtelAffich;
    @FXML
    private TextField MdpAffich;
    @FXML
    private VBox LivreurFonct;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherLivreurById(1);
        
    } 
    @FXML
    private void OpenProfile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LivreurCrud.fxml"));
        Parent root = loader.load();
        Stage CurrentStage = (Stage) LivreurLogo.getScene().getWindow();
        Scene scene = new Scene(root,1080,720);
        CurrentStage.setScene(scene);
        CurrentStage.show();
    }
    
   private void afficherLivreurById(int id) {
    try {
        String sql = "SELECT * FROM livreur WHERE id = ?";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        
        if (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            String numtel = rs.getString("numtel");
            String login = rs.getString("login");
            String mdp = rs.getString("mdp");
            NomAffich.setText(nom);
            PrenomAffich.setText(prenom);
            LoginAffich.setText(login);
            EmailAffich.setText(email);
            NumtelAffich.setText(numtel);
            MdpAffich.setText(mdp);
            System.out.println(mdp);
        } else {
            System.out.println("No livreur found with id " + id);
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}

    @FXML
    private void modifierLivreurDNew(ActionEvent event) {
          try{      
        String sql = "UPDATE livreur SET nom=?, prenom=?, email=?, numtel=?, login=?, mdp=? WHERE id = ? ";
        PreparedStatement ste = myconn.prepareStatement(sql);
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
        System.out.println("livreur updated successfully!");
         }
         catch(SQLException e) {
        System.out.println(e);
    }
    }



}
