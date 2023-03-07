/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Entities.reclamation;

/**
 * FXML Controller class
 *
 * @author chebbi4m
 */
public class UserReclamationFXMLController implements Initializable {

    @FXML
    private Button submit_btn;
    @FXML
    private TextField personne_text;
    @FXML
    private TextArea reclamation_text;
    @FXML
    private TextField id_client_text;
    @FXML
    private TextField ref_colis_text;
    @FXML
    private ComboBox<String> type_reclamation_box;

    ObservableList<reclamation> ReclamationList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<reclamation, String> ref_col;
    @FXML
    private TableColumn<reclamation, String> type_col;
    @FXML
    private TableColumn<reclamation, String> reclamation_col;
    @FXML
    private TableColumn<reclamation, String> date_col;
    @FXML
    private TableView<reclamation> userReclamationTable;
    @FXML
    private ComboBox<String> type_personne_box;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type_reclamation_box.getItems().addAll(
                "Réclamation pour retard de livraison",
                "Réclamation pour colis perdu",
                "Réclamation pour colis endommagé",
                "Réclamation pour mauvaise manipulation du colis",
                "Réclamation concernant le comportement impoli du livreur"
        );
        type_personne_box.getItems().addAll(
                "Livreur",
                "Partenaire"
        );
        
        
    }    
    
//    ReclamationService rs = new ReclamationService();
//    emailService es = new emailService();
//    
//    @FXML
//    private void ajouterReclamation(ActionEvent event) {
//        Boolean ref_checker = false;
//        
//        int id_client = Integer.parseInt(id_client_text.getText());
//        String type_reclamation = type_reclamation_box.getValue().toString();
//        LocalDateTime now = LocalDateTime.now();
//        Date date_reclamation = Date.valueOf(now.toLocalDate());
//        String personne_reclame = personne_text.getText();
//        String text = reclamation_text.getText();
//        String ref_colis = ref_colis_text.getText();
////        if (refColisChecker(ref_colis) == true){
//            ref_checker = true;
////        }
//        
//        if (ref_checker == true){
//            reclamation rec = new reclamation(1, text, personne_reclame, type_reclamation, date_reclamation, id_client, ref_colis);
//            
//            rs.ajouterReclamation(rec);
//            rs.incrementerNbrReclamation(type_personne_box.getValue().toString(),personne_reclame);
//            loadClientReclamation();
//            clearFields();
//            
//            es.sendEmail("Reclamation", text + "\n" + "colis de ref : " + ref_colis);
//            
//            
//
//        }else{
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("information incorrecte");
//            alert.setHeaderText(null);
//            alert.setContentText("La reference est incorrecte");
//            alert.showAndWait();
//        }
//        
//
//        
//    }
//    
//    
//    public boolean refColisChecker(String ref_colis) {
//        Connection myconn = MyConnection.getInstance().getConnexion();
//        boolean result = false;
//
//        try {
//            PreparedStatement ste;
//            ste = myconn.prepareStatement("SELECT COUNT(*) AS count FROM colis WHERE ref = ?");
//            ste.setString(1, ref_colis);
//            ResultSet resultSet = ste.executeQuery();
//
//            if (resultSet.next()) {
//                int count = resultSet.getInt("count");
//                result = (count > 0);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//
//        return result;
//    }
//
//    
//    private void loadClientReclamation() {
//        ReclamationList.clear();
//        Connection myconn = MyConnection.getInstance().getConnexion();
//        try {
//            PreparedStatement ste = myconn.prepareStatement("SELECT * FROM reclamation where id_client = ?");
//            int id_client = Integer.parseInt(id_client_text.getText());
//            ste.setInt(1, id_client);
//            ResultSet resultSet = ste.executeQuery();
//
//            while (resultSet.next()) {
//                String text = resultSet.getString("text");
//                String type_reclamation = resultSet.getString("type_reclamation");
//                String refColis = resultSet.getString("ref");
//                Date date_reclamation = resultSet.getDate("date");
//
//
//                
//                reclamation reclamation = new reclamation(text, type_reclamation, date_reclamation, refColis);
//                ReclamationList.addAll(reclamation);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//
//        ref_col.setCellValueFactory(new PropertyValueFactory<>("ref_colis"));
//        type_col.setCellValueFactory(new PropertyValueFactory<>("type_reclamation"));
//        reclamation_col.setCellValueFactory(new PropertyValueFactory<>("text"));
//        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
//        userReclamationTable.setItems(ReclamationList);
//    }
//    
//    
//    
//    private void loadWorkReclamation() {
//        ReclamationList.clear();
//        Connection myconn = MyConnection.getInstance().getConnexion();
//        try {
//            PreparedStatement ste = myconn.prepareStatement("SELECT * FROM reclamation where login = ?");
//            int id_client = Integer.parseInt(id_client_text.getText());//id_client_text the login input (after session session.login)
//            ste.setInt(1, id_client);// change this variable also
//            ResultSet resultSet = ste.executeQuery();
//
//            while (resultSet.next()) {
//                String text = resultSet.getString("text");
//                String type_reclamation = resultSet.getString("type_reclamation");
//                String refColis = resultSet.getString("ref");
//                Date date_reclamation = resultSet.getDate("date");
//
//                
//                
//                reclamation reclamation = new reclamation(text, type_reclamation, date_reclamation, refColis);
//                ReclamationList.addAll(reclamation);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//
//        ref_col.setCellValueFactory(new PropertyValueFactory<>("ref_colis"));
//        type_col.setCellValueFactory(new PropertyValueFactory<>("type_reclamation"));
//        reclamation_col.setCellValueFactory(new PropertyValueFactory<>("text"));
//        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
//        userReclamationTable.setItems(ReclamationList);
//    }
//    
//    
//    
//    public void clearFields(){
//        ref_colis_text.clear();
//        reclamation_text.clear();
//        personne_text.clear();
//        id_client_text.clear();
//        type_reclamation_box.setValue(null);
//    }
    
}
