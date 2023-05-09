



import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import session.UserSession;
import taktak.entities.PartenaireN;
import taktak.utils.MyConnection;
import javafx.fxml.Initializable;
import taktak.services.PartenaireServiceN;


public class AccueilPartenaireController implements Initializable{
    Connection myconn = MyConnection.getInstance().getConnexion(); // connexion à la base de données



    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNumtel;

    @FXML
    private ComboBox<String> listeZone;

    @FXML
    private TextField tfPrix_poids;

    @FXML
    private TextField tfPrix_zone;

    @FXML
    private Button btnModifier;
    @FXML
    private RadioButton Ouiinflammable;
    @FXML
    private RadioButton Ouifragile;
    @FXML
    private JFXButton historiquebtn;
    @FXML
    private JFXButton Accueilbtn;
    @FXML
    private JFXButton Meslivreurbtn;
    @FXML
    private JFXButton reclamation_btn;
    @FXML
    private JFXButton fermer;
    @FXML
    private JFXButton retour;
    @FXML
    private ToggleGroup colis;
    @FXML
    private RadioButton Noninflammable;
    @FXML
    private ToggleGroup colis1;
    @FXML
    private ToggleGroup test;
    @FXML
    private RadioButton Nonfragile;
    @FXML
    private ToggleGroup test1;

   


    @FXML
    void cancel(MouseEvent event) {

    }

  

   

    @FXML
    void hist(MouseEvent event) {

    }

  

    @FXML
    void open_reclamation(ActionEvent event) {

    }

    @FXML
    void retourner(MouseEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        
        String[] items = {"Nationale", "Internationale"};
         listeZone.getItems().setAll(items);

        
    }

   

   

  




    private void handleButtonAction(ActionEvent event)  {

        if (event.getSource() == btnModifier) {
            try {
                modifierPartenaire();
            } catch (SQLException ex) {
                Logger.getLogger(AccueilPartenaireController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert();
        }
    }
    
    
public ObservableList<PartenaireN> getPartenaireList() {
    ObservableList<PartenaireN> partenaireList = FXCollections.observableArrayList();

    String query = "SELECT * from partenaire";
    try (
         PreparedStatement prepareStatement = myconn.prepareStatement(query);
         ResultSet rs = prepareStatement.executeQuery()) {
        while (rs.next()) {
            PartenaireN partenaire = new PartenaireN (
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getInt("numtel"),
                    rs.getString("zone"),
                    rs.getDouble("prix_poids"),
                    rs.getDouble("prix_zone"),
                    rs.getBoolean("inflammable"),
                    rs.getBoolean("fragile"),
                    rs.getString("login"),
                    rs.getString("password")
            );
            partenaireList.add(partenaire);
        }
    } catch (SQLException ex) {
        System.err.println("Error fetching partenaires: " + ex.getMessage());
        ex.printStackTrace();
    }

    return partenaireList;
}

public void afficherPartenaire() throws SQLException{
 
     ObservableList<PartenaireN> ListPartenaire = getPartenaireList();

     
  //  tfNom.setCellValueFactory(new PropertyValueFactory<Partenaire , String>("nom"));   
   //  tfEmail.setCellValueFactory(new PropertyValueFactory<Partenaire , String>("email"));
   //  tfNumtel.setCellValueFactory(new PropertyValueFactory<Partenaire , Integer>("numtel"));     
   
    
 //    PartenaireN.setItems(ListPartenaire);
     
 }
    
    
   public void modifierPartenaire() throws SQLException {         
    String sql = "UPDATE partenaire SET nom = ?, email = ?, numtel = ?, zone = ?, prix_poids = ?, prix_zone = ?, inflammable = ?, fragile = ? WHERE id = 21";
    try {
          
        PreparedStatement ste = myconn.prepareStatement(sql);
          String[] items = {"Nationale", "Internationale"};
         listeZone.getItems().setAll(items);
        PartenaireN pr = new PartenaireN();        
          pr = (PartenaireN) UserSession.INSTANCE.get("partenaire");
        ste.setString(1, tfNom.getText());
        ste.setString(2, tfEmail.getText());
        ste.setString(3, tfNumtel.getText());           
        ste.setString(4, listeZone.getValue());
        ste.setDouble(5, Double.parseDouble(tfPrix_poids.getText()));
        ste.setDouble(6, Double.parseDouble(tfPrix_zone.getText()));
        ste.setBoolean(7, Ouiinflammable.isSelected());
        ste.setBoolean(8, Ouifragile.isSelected());        
        
        
        ste.executeUpdate();
        System.out.println("Partenaire modifié");
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    
    afficherPartenaire();  
}   
    
    
     public void Alert(){  
 Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Confirmation des données");
    alert.setContentText("Vos informations ont été confirmées avec succès.");
    alert.showAndWait();
 }

    @FXML
    private void hist(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void retourner(ActionEvent event) {
    }

    @FXML
    private void inflammable(ActionEvent event) {
    }

    @FXML
    private void fragile(ActionEvent event) {
    }


 
    
    
    
    
    

}
