package GUI.Partenaire;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;


import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import taktak.entities.Partenaire;
import taktak.services.PartenaireService;
import taktak.utils.MyConnection;


/**
 * FXML Controller class
 *
 * @author Najet
 */
public class partenaireinterfaceController implements Initializable {
Connection myconn = MyConnection.getInstance().getConnexion();
      @FXML
    private TextField tfNom;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNumtel;

    private TextField tfMdp;

    @FXML
    private Button  btnModifier;

    @FXML
    private Button btnlogout;

    @FXML
    private ImageView logo;

    @FXML
    private Button btnlivreurs;

    @FXML
    private Button btncolis;

    @FXML
    private Button btnhistorique;

    @FXML
    private Button btnreclamation;


    @FXML
    private TextField tfPrix_poids;

    @FXML
    private TextField tfPrix_zone;

    @FXML
    private TextField tfInflammable;

    @FXML
    private TextField tfFragile;

    @FXML
    private ToggleGroup colis;    
    @FXML
    private ToggleGroup test;
    
    @FXML
    private ComboBox<String> listeMoy_transport;
    @FXML
    private ComboBox<String> listeZone;
    @FXML
    private RadioButton Ouiinflammable;
    @FXML
    private RadioButton Noninflammable;
    @FXML
    private RadioButton Ouifragile;
    @FXML
    private RadioButton Nonfragile;
     @FXML
     
     
     
     
    private void inflammable(ActionEvent event) throws SQLException {
//        try {
//    PreparedStatement ps = myconn.prepareStatement("UPDATE partenaire SET inflammable = ? WHERE id = ?");
//    ps.setBoolean(1, Ouiinflammable.isSelected());    
//    ps.setInt(2, 3);
//    ps.executeUpdate();
//    System.out.println("Update successful");
//} catch (SQLException e) {
//    System.out.println("Update failed: " + e.getMessage());
//}
    }
//
   @FXML
    private void fragile(ActionEvent event) {
//        try {
//    PreparedStatement ps = myconn.prepareStatement("UPDATE partenaire SET fragile = ? WHERE id = ?");
//    ps.setBoolean(1, Ouifragile.isSelected());    
//    ps.setInt(2, 3);
//    ps.executeUpdate();
//    System.out.println("Update successful");
//} catch (SQLException e) {
//    System.out.println("Update failed: " + e.getMessage());
//}
   }
   
    @FXML
    void livreurs(ActionEvent event) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Livreurs.fxml"));
            Parent root = (Parent) loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    };
    

    void modifierPartenaire(ActionEvent event) {

    }
 @FXML
    void Colis(ActionEvent event) {
try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
            Parent root = (Parent) loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void Historique(ActionEvent event) {
try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Historique.fxml"));
            Parent root = (Parent) loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void Reclamations(ActionEvent event) {
try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
            Parent root = (Parent) loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


     
        
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    try {           
        afficherPartenaire();
    } catch (SQLException ex) {
        Logger.getLogger(partenaireinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
    }
          
          listeMoy_transport.setItems(FXCollections.observableArrayList("Voiture","Bateau","Avion"));
          listeZone.setItems(FXCollections.observableArrayList("Nationale","Internationale"));
    }    

@FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
       
       
        if(event.getSource()==  btnModifier){
            modifierPartenaire();
            Alert();
            
        }
//        

    }
    
    
    
    
    
    

      

 public ObservableList<Partenaire> getPartenaireList() throws SQLException {
    ObservableList<Partenaire> partenaireList = FXCollections.observableArrayList();
   
    String query = "SELECT * from partenaire";
    PreparedStatement prepareStatement = myconn.prepareStatement(query);

    try {
        ResultSet rs = prepareStatement.executeQuery();
        while (rs.next()) {
            Partenaire partenaire = new Partenaire (
                rs.getInt("id"),
                rs.getString("nom"),     
                rs.getString("email"),   
                rs.getInt("numtel"),  
                rs.getString("moyen_transport"),   
                rs.getString("zone"),   
                rs.getDouble("prix_poids"),   
                rs.getDouble("prix_zone"), 
                rs.getBoolean("inflammable"), 
                rs.getBoolean("fragile "), 
                rs.getString("login"),
                rs.getString("mdp")
            );
            partenaireList.add(partenaire);
        }
    } catch (SQLException ex) {
    }

    return partenaireList;
}
 public void afficherPartenaire() throws SQLException{
 
     ObservableList<Partenaire> List = getPartenaireList();

     
  //  tfNom.setCellValueFactory(new PropertyValueFactory<Partenaire , String>("nom"));   
  //   tfEmail.setCellValueFactory(new PropertyValueFactory<Partenaire , String>("email"));
  //   tfNumtel.setCellValueFactory(new PropertyValueFactory<Partenaire , Integer>("numtel"));     
  //   tfMdp.setCellValueFactory(new PropertyValueFactory<Partenaire , String>("mdp"));
    
    // Partenaire.setItems(List);
     
 }
 
 
 
 
 
 
 
 
 
 
//public void insertPartenaire(Partenaire p) throws SQLException {
//    Connection myconn = MyConnection.getInstance().getConnexion();
//    String query = "INSERT INTO partenaire (nom, adresse, email, numtel, login, mdp) " +
//                   "VALUES (?, ?, ?, ?, ?, ?)";
//    PreparedStatement prepareStatement = myconn.prepareStatement(query);
//    prepareStatement.setString(1, p.getNom().getText());
//    prepareStatement.setString(2, p.getAdresse());
//    prepareStatement.setString(3, p.getEmail());
//    prepareStatement.setInt(4, p.getNumtel());
//    prepareStatement.setString(5, p.getLogin());
//    prepareStatement.setString(6, p.getMdp());
//    
//    int rowsUpdated = prepareStatement.executeUpdate();
//    if (rowsUpdated > 0) {
//        System.out.println("Partenaire inséré avec succès.");
//    }
////}Connection myconn=  MyConnection.getInstance().getConnexion(); 
//
//    PreparedStatement stmt = myconn.prepareStatement("INSERT INTO partenaire (id,nom, adree, numtel, email, adresse, login, mdp) VALUES (?, ?, ?, ?, ?, ?, ?)");
//            stmt.setInt(1, Integer.parseInt(tfID.getText()));
//            stmt.setString(1, tfNom.getText());
//            stmt.setString(2, tfAdresse.getText());
//            stmt.setString(3, tfEmail.getText());
//            stmt.setString(4, tfNumtel.getText());
//            stmt.setString(5, tfLogin.getText());
//            stmt.setString(6, tfMdp.getText());
//            
//            stmt.executeUpdate();


//private void ajouterPartenaire() throws SQLException{
//
//   // String query = "INSERT INTO partenaire VALUES ('" + tfID.getText() + "', '" + tfNom.getText() + "', '" + tfAdresse.getText() + "', '" + tfEmail.getText()
//   //         + "'"  + ", '" + tfNumtel.getText() + "', '" + tfLogin.getText() + "', '" + tfMdp.getText() + "')";
//  //  executeQuery(query);
//    
//     try {
//            //Inserer le Partenaire
//            PreparedStatement stmt = myconn.prepareStatement("INSERT INTO partenaire (id,nom, adresse,email, numtel, login, mdp) VALUES (?, ?, ?, ?, ?, ?, ?)");
//            stmt.setString(1, tfID.getText());
//            stmt.setString(2, tfNom.getText());
//            stmt.setString(3, tfAdresse.getText());
//            stmt.setString(4, tfEmail.getText());
//            stmt.setString(5, tfNumtel.getText());
//            stmt.setString(6, tfLogin.getText());
//            stmt.setString(7, tfMdp.getText());
//            
//            stmt.executeUpdate();
//            System.out.println("partenaire insére");
//         } catch (SQLException ex) {
//            System.out.println(ex);
//        } afficherPartenaire();
//}


public void modifierPartenaire() throws SQLException {         
    String sql = "UPDATE partenaire SET nom = ?, email = ?, numtel = ?, moyen_transport = ?, zone = ?, prix_poids = ?, prix_zone = ?, inflammable = ?, fragile = ?, login = ?, mdp = ? WHERE id = ?";
    try {
        PreparedStatement ste = myconn.prepareStatement(sql);

        ste.setString(1, tfNom.getText());
        ste.setString(2, tfEmail.getText());
        ste.setString(3, tfNumtel.getText());
        ste.setString(4, listeMoy_transport.getValue());   
        ste.setString(5, listeZone.getValue());
        ste.setDouble(6, Double.parseDouble(tfPrix_poids.getText()));
        ste.setDouble(7, Double.parseDouble(tfPrix_zone.getText()));
        ste.setBoolean(8, Ouiinflammable.isSelected());
        ste.setBoolean(9, Ouifragile.isSelected());
        ste.setString(10, "jumia");
        ste.setString(11, "jumia132");
        ste.setInt(12, 4);
        
        ste.executeUpdate();
        System.out.println("Partenaire modifié");
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    
    afficherPartenaire();  
}


//            int  id=1;
//            String nom =tfNom.getText() ;
//            String email=tfEmail.getText();
//            int numtel = Integer.parseInt(tfNumtel.getText());
//            String moy_transport = listeMoy_transport.getValue();
//            String zone =listeMoy_transport.getValue();
//            Double prix_poids = Double.parseDouble(tfPrix_poids.getText());
//            Double prix_zone = Double.parseDouble(tfPrix_zone.getText());          
//            Boolean inflammable=Boolean.parseBoolean(tfInflammable.getText());
//            Boolean fragile=Boolean.parseBoolean(tfFragile.getText());
//            String login = "yoyo";
//            String mdp=  "yoyo";
//            
//            Partenaire partenaire = new Partenaire (
//             id, nom,email,numtel,moy_transport,zone,prix_poids,prix_zone,inflammable,fragile,login,mdp
//            );
//            
//             PartenaireService ps = new PartenaireService();
//            ps.modifierPartenaire(partenaire );
//            System.out.println("partenaire modifier");
        
   




//    @FXML         
//    private void ajouterPartenaire() throws SQLException{         
//    
//            int  id=3;
//            String nom =tfNom.getText() ;
//            String email=tfEmail.getText();
//            int numtel = Integer.parseInt(tfNumtel.getText());
//            String moy_transport = listeMoy_transport.getValue();
//            String zone =listeMoy_transport.getValue();
//            Double prix_poids = Double.parseDouble(tfPrix_poids.getText());
//            Double prix_zone = Double.parseDouble(tfPrix_zone.getText());          
//            Boolean inflammable=Boolean.parseBoolean(tfInflammable.getText());
//            Boolean fragile=Boolean.parseBoolean(tfFragile.getText());
//            String login = "fawetah";
//            String mdp=tfMdp.getText();
//            
//            Partenaire partenaire = new Partenaire (
//             id, nom,email,numtel,moy_transport,zone,prix_poids,prix_zone,inflammable,fragile,login,mdp
//            );
//            
//             PartenaireService ps = new PartenaireService();
//            ps.ajouterPartenaire(partenaire );
//            
//     afficherPartenaire();      
//   
//}











//private void supprimerPartenaire() throws SQLException{
//
//  //  String query = "DELETE from partenaire WHERE id='" + tfID.getText() + "'";
//   // executeQuery(query);
//    
//    
//     try {
//            PreparedStatement preparedStatement = myconn.prepareStatement("DELETE FROM partenaire where id = ?");
//            preparedStatement.setString(1,tfID.getText());
//            preparedStatement.executeUpdate();
//              System.out.println(" partenaire supprimé " );
//        } catch (SQLException ex) {
//              System.out.println(ex);
//        }afficherPartenaire();
//}

   // private void executeQuery(String query) {
    //    try {
    //        Connection myconn = MyConnection.getInstance().getConnexion();
     //       Statement st;
            
     //       st=myconn.createStatement();
     //       st.executeUpdate(query);
     //   } catch (SQLException ex) {
     //       Logger.getLogger(PartenaireSurfaceController.class.getName()).log(Level.SEVERE, null, ex);
     //   }
   // }

//    @FXML // click on query will showed on tfField
//    private void handleMouseAction(MouseEvent event) {
//      Partenaire p  = tvPartenaire.getSelectionModel().getSelectedItem();
//        tfID.setText("" + p.getId());
//         tfNom.setText( p.getNom());
//          tfAdresse.setText( p.getAdresse());
//           tfEmail.setText( p.getEmail());
//            tfNumtel.setText("" + p.getNumtel());
//             tfLogin.setText( p.getLogin());
//              tfMdp.setText(p.getMdp());
//    }

   

     public void Alert(){  
 Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Confirmation des données");
    alert.setContentText("Vos informations ont été confirmées avec succès.");
    alert.showAndWait();
 }

    

}