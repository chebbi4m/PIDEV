/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.awt.Image;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import taktak.entities.Colis;
import taktak.services.ColisService;
import taktak.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author LENOVO THINKPAD E15
 */
public class TableViewController implements Initializable {
    Connection myconn = MyConnection.getInstance().getConnexion();
    
    @FXML
    private TableView<Colis> colisTable;

    @FXML
    private TableColumn<Colis, String> refCol;

    @FXML
    private TableColumn<Colis, String> departCol;

    @FXML
    private TableColumn<Colis, String> destinationCol;

    @FXML
    private TableColumn<Colis, Integer> poidsCol;

    @FXML
    private TableColumn<Colis, Integer> prixCol;

    @FXML
    private TableColumn<Colis, Integer> actionCol;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherColis();
    }    
    
    @FXML
    private void close(MouseEvent event) {

    }

    @FXML
    void getAddView(MouseEvent event) {
                try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutColis.fxml"));
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
    void print(MouseEvent event) {

    }

    @FXML
    void refreshTable() {
        /*try {
            ColisList.clear();
            query = "SELECT * FROM `colis`";
            preparedStatement = myconn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                ColisList.add(new Colis(
                        resultSet.getString("ref"),
                        resultSet.getInt("hauteur"),
                        resultSet.getInt("largeur"),
                        resultSet.getInt("poids"),
                        resultSet.getInt("prix"),
                        resultSet.getBoolean("fragile"),
                        resultSet.getBoolean("inflammable"),
                        resultSet.getString("depart"),
                        resultSet.getString("destination"),
                        resultSet.getString("etat_colis"),
                        resultSet.getString("zone"),
                        resultSet.getBoolean("urgent"),
                        resultSet.getInt("id_client"),
                        resultSet.getInt("id_livreur")));
                colisTable.setItems(ColisList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
     public ObservableList <Colis> afficherListColis() {
        ObservableList<Colis> colis = FXCollections.observableArrayList();
        try {
            String sql = "select * from colis where etat_colis = 'En cours' order by id desc";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Colis cls = new Colis(s.getString("ref"),
                        s.getInt(3),
                        s.getInt(4),
                        s.getInt(5),
                        s.getInt(6),
                        s.getBoolean(7),
                        s.getBoolean(8),
                        s.getString("depart"),
                        s.getString("destination"),
                        s.getString("etat_colis"),
                        s.getString("zone"),
                        s.getBoolean(13),
                        s.getInt(14),
                        s.getInt(15));
                colis.add(cls);
       
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return colis;

        }


    public void afficherColis() {
       ObservableList<Colis> list = afficherListColis();
       //refreshTable();
       refCol.setCellValueFactory(new PropertyValueFactory<>("ref"));
       departCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
       destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
       poidsCol.setCellValueFactory(new PropertyValueFactory<>("poids"));
       prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
       colisTable.setItems(list);
    }
    @FXML
    void btnDel(ActionEvent event) {
       try {
            PreparedStatement preparedStatement = myconn.prepareStatement("DELETE FROM client where id = ?");
            //preparedStatement.setString(1,id.getText());
            preparedStatement.executeUpdate();
              //check(" client supprimé " );
        } catch (SQLException ex) {
              System.out.println(ex);
        }
        afficherColis();
    }
    
     /*public void check (String msg) throws IOException{

     Image image = new Image("Images/accept.png") {};
     Notifications notification = Notifications.create();
     notification.graphic(new ImageView(image));
     notification.title("Succès");
     notification.text(msg);
     notification.hideAfter(Duration.seconds(1));
     notification.position(Pos.CENTER);
     notification.show();
    }*/
    
}
