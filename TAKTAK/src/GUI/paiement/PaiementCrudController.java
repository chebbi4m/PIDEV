/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.paiement;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import taktak.entities.Paiement;
import taktak.utils.MyConnection;


/**
 * FXML Controller class
 *
 * @author Najet
 */
public class PaiementCrudController implements Initializable {

    @FXML
    private TextField tfID_colis;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfDate;
    @FXML
    private TableView<Paiement> tvpaiement;
    @FXML
    private TableColumn<Paiement, Integer> colID_colis;
    @FXML
    private TableColumn<Paiement, String> colType;
    @FXML
    private TableColumn<Paiement, String> colDate;
    
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
   Connection myconn = MyConnection.getInstance().getConnexion();

   
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
       
        
        if(event.getSource()== btnAjouter){
            ajouterPaiement();
        }
        if(event.getSource()== btnModifier){
            modifierPaiement();
        }
       
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try {           
            afficherPaiement();
        } catch (SQLException ex) {
            Logger.getLogger(PaiementCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
}
  
    
    
  
 public ObservableList<Paiement> getPaiementList() throws SQLException {
    ObservableList<Paiement> PaiementList = FXCollections.observableArrayList();
   
    String query = "SELECT * from paiement";
    PreparedStatement prepareStatement = myconn.prepareStatement(query);

    try {
        ResultSet rs = prepareStatement.executeQuery();
        while (rs.next()) {
            Paiement paiement = new Paiement (
                rs.getInt("ID_colis"),
                rs.getString("Type"),
                rs.getString("Date"));
            PaiementList.add(paiement);
        }
    } catch (SQLException ex) {
    }

    return PaiementList;
}  
    
    
  public void afficherPaiement() throws SQLException{
 
     ObservableList<Paiement> List = getPaiementList();
     colID_colis.setCellValueFactory(new PropertyValueFactory<>("ID_colis"));
     colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
     colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
    
     
     tvpaiement.setItems(List);
     //table view
 }  
    
    
    
  private void ajouterPaiement() throws SQLException{

    String query = "INSERT INTO paiement VALUES ('" + tfID_colis.getText() + "', '" + 
            tfType.getText() + "',  '" + tfDate.getText() + "')";
    executeQuery(query);
   afficherPaiement();
}


private void modifierPaiement()throws SQLException{

    String query = "UPDATE paiement SET nom='"  + tfType.getText() +  "'";

    executeQuery(query);
     afficherPaiement();
}
 

    private void executeQuery(String query) throws SQLException {
  
            Statement st;
            
            st=myconn.createStatement();
            st.executeUpdate(query);
             st.close();
    }
    
    
    //click on table the query showed on textfield 
        private void handleMouseAction(MouseEvent event) {
      Paiement p  = tvpaiement.getSelectionModel().getSelectedItem();
        tfID_colis.setText("" + p.getId_colis());
         tfType.setText( p.getType());
          tfDate.setText( p.getDate());
          
    }

    
    
    
    
    
}
