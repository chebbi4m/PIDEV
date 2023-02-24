/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Partenaire;

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
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import taktak.entities.Partenaire;
import taktak.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Najet
 */
public class partenaireFXMLController implements Initializable {
   
    @FXML
    private TextField tfID;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfMdp;

    @FXML
    private TextField tfAdresse;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNumtel;
    @FXML
    private TableView<Partenaire> tvPartenaire;
    @FXML
    private TableColumn<Partenaire, Integer>colID;
    @FXML
    private TableColumn<Partenaire, String>colNom;
 
    @FXML
    private TableColumn<Partenaire, String>colAdresse;
    @FXML
    private TableColumn<Partenaire, String>colEmail;
    @FXML
    private TableColumn<Partenaire, Integer>colNumtel;
    @FXML
    private TableColumn<Partenaire, String>colLogin;
    @FXML
    private TableColumn<Partenaire, String>colMdp;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    Connection myconn = MyConnection.getInstance().getConnexion();
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
       
        
        if(event.getSource()== btnAjouter){
            ajouterPartenaire();
        }
        if(event.getSource()== btnModifier){
            modifierPartenaire();
            
        }
        if(event.getSource()== btnSupprimer){
            supprimerPartenaire();
        }
        
        
        
        
        
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficherPartenaire();           
            
        } catch (SQLException ex) {
            Logger.getLogger(partenaireFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        
               
        
        
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
                rs.getString("adresse"),
                rs.getString("email"),
                rs.getInt("numtel"),
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

        colID.setCellValueFactory(new PropertyValueFactory<Partenaire , Integer>("id"));
     colNom.setCellValueFactory(new PropertyValueFactory<Partenaire , String>("nom"));
     colAdresse.setCellValueFactory(new PropertyValueFactory<Partenaire , String>("adresse"));
     colEmail.setCellValueFactory(new PropertyValueFactory<Partenaire , String>("email"));
     colNumtel.setCellValueFactory(new PropertyValueFactory<Partenaire , Integer>("numtel"));
     colLogin.setCellValueFactory(new PropertyValueFactory<Partenaire , String>("login"));
     colMdp.setCellValueFactory(new PropertyValueFactory<Partenaire , String>("mdp"));
     
     tvPartenaire.setItems(List);
     //table view
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


private void ajouterPartenaire() throws SQLException{

   // String query = "INSERT INTO partenaire VALUES ('" + tfID.getText() + "', '" + tfNom.getText() + "', '" + tfAdresse.getText() + "', '" + tfEmail.getText()
   //         + "'"  + ", '" + tfNumtel.getText() + "', '" + tfLogin.getText() + "', '" + tfMdp.getText() + "')";
  //  executeQuery(query);
    
     try {
            //Inserer le Partenaire
            PreparedStatement stmt = myconn.prepareStatement("INSERT INTO partenaire (id,nom, adresse,email, numtel, login, mdp) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, tfID.getText());
            stmt.setString(2, tfNom.getText());
            stmt.setString(3, tfAdresse.getText());
            stmt.setString(4, tfEmail.getText());
            stmt.setString(5, tfNumtel.getText());
            stmt.setString(6, tfLogin.getText());
            stmt.setString(7, tfMdp.getText());
            
            stmt.executeUpdate();
            System.out.println("partenaire insére");
         } catch (SQLException ex) {
            System.out.println(ex);
        } afficherPartenaire();
}


private void modifierPartenaire() throws SQLException{

   // String query = "UPDATE partenaire SET nom='" + tfNom.getText() + "', adresse='" + tfAdresse.getText() + "', email='" + tfEmail.getText() + "', numtel='" + tfNumtel.getText() + "', login='" +
   //         tfLogin.getText() + "', mdp='" + tfMdp.getText() + "' WHERE id= " + tfID.getText() + "";

   // executeQuery(query);
  
    
    
    
    String sql="UPDATE partenaire SET nom = ? ,adresse = ? ,email = ?,numtel = ?  , login = ? , mdp = ?  WHERE id = ? ";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
            ste.setString(1,tfNom.getText());
            ste.setString(2,tfAdresse.getText());
            ste.setString(3,tfEmail.getText());
            ste.setString(4,tfNumtel.getText()); 
            ste.setString(5,tfLogin.getText());
            ste.setString(6,tfMdp.getText());             
            ste.setString(7,tfID.getText());

                ste.executeUpdate();
                System.out.println(" partenaire modifié " );
                        //afficherpartenaire();

        } catch (SQLException ex) {
            System.out.println(ex);
        }         afficherPartenaire();
    
    
    
    
}
private void supprimerPartenaire() throws SQLException{

  //  String query = "DELETE from partenaire WHERE id='" + tfID.getText() + "'";
   // executeQuery(query);
    
    
     try {
            PreparedStatement preparedStatement = myconn.prepareStatement("DELETE FROM partenaire where id = ?");
            preparedStatement.setString(1,tfID.getText());
            preparedStatement.executeUpdate();
              System.out.println(" partenaire supprimé " );
        } catch (SQLException ex) {
              System.out.println(ex);
        }afficherPartenaire();
}

   // private void executeQuery(String query) {
    //    try {
    //        Connection myconn = MyConnection.getInstance().getConnexion();
     //       Statement st;
            
     //       st=myconn.createStatement();
     //       st.executeUpdate(query);
     //   } catch (SQLException ex) {
     //       Logger.getLogger(partenaireFXMLController.class.getName()).log(Level.SEVERE, null, ex);
     //   }
   // }

    @FXML // click on query will showed on tfField
    private void handleMouseAction(MouseEvent event) {
      Partenaire p  = tvPartenaire.getSelectionModel().getSelectedItem();
        tfID.setText("" + p.getId());
         tfNom.setText( p.getNom());
          tfAdresse.setText( p.getAdresse());
           tfEmail.setText( p.getEmail());
            tfNumtel.setText("" + p.getNumtel());
             tfLogin.setText( p.getLogin());
              tfMdp.setText(p.getMdp());
    }

    

 
}
