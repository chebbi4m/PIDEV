package gui;
import entities.colis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import services.colisService;
import utils.SqlConnection;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class coliscontroller implements Initializable {
    static Connection con = null;
    static ResultSet rs = null;
    static PreparedStatement st = null;



    @FXML
    private TableColumn<colis, Integer> col_id;

    @FXML
    private TableColumn<colis, String> col_ref;

    @FXML
    private TableColumn<colis, Double> col_hauteur;

    @FXML
    private TableColumn<colis, Double> col_largeur;

    @FXML
    private TableColumn<colis, Double> col_poids;

    @FXML
    private TableColumn<colis, Double> col_prix;

    @FXML
    private TableColumn<colis, Boolean> col_fragile;

    @FXML
    private TableColumn<colis, String> col_depart;

    @FXML
    private TableColumn<colis, String> col_destination;

    @FXML
    private TableColumn<colis, Integer> col_id_client;

    @FXML
    private TableColumn<colis, Integer> col_id_paiement;

    @FXML
    private TableView<colis> table_colis;
    Connection myconn = SqlConnection.MyConnection();
@FXML
public void Ajouter(ActionEvent event){
    colis cls = new colis();
    colisService cs = new colisService();
    cs.ajoutercolis(cls);
}


    public static ObservableList<colis> getDatacolis() {

        ObservableList<colis> list = FXCollections.observableArrayList();
        String query = "select* from colis";
        con = SqlConnection.MyConnection();
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                colis st = new colis();
                st.setId(rs.getInt("id"));
                st.setRef(rs.getString("ref"));
                st.setHauteur(rs.getDouble("hauteur"));
                st.setLargeur(rs.getDouble("largeur"));
                st.setPoids(rs.getDouble("poids"));
                st.setPrix(rs.getDouble("prix"));
                st.setFragile(rs.getBoolean("fragile"));
                st.setDepart(rs.getString("depart"));
                st.setDestination(rs.getString("destination"));
                st.setId_client(rs.getInt("id_client"));
                st.setId_paiement(rs.getInt("id_paiement"));
            list.add(st);}
        } catch (SQLException e){
        throw new RuntimeException(e);



    }
        return list;
    }
    public void showcolis(){
        ObservableList<colis> list = getDatacolis();
        table_colis.setItems(list);
        col_id.setCellValueFactory(new PropertyValueFactory<colis,Integer>("id"));
        col_ref.setCellValueFactory(new PropertyValueFactory<colis,String>("ref"));
        col_hauteur.setCellValueFactory(new PropertyValueFactory<colis,Double>("hauteur"));
        col_largeur.setCellValueFactory(new PropertyValueFactory<colis,Double>("largeur"));
        col_poids.setCellValueFactory(new PropertyValueFactory<colis,Double>("poids"));
        col_prix.setCellValueFactory(new PropertyValueFactory<colis,Double>("prix"));
        col_fragile.setCellValueFactory(new PropertyValueFactory<colis,Boolean>("fragile"));
        col_depart.setCellValueFactory(new PropertyValueFactory<colis,String>("depart"));
        col_destination.setCellValueFactory(new PropertyValueFactory<colis,String>("destination"));
        col_id_client.setCellValueFactory(new PropertyValueFactory<colis,Integer>("id_client"));
        col_id_paiement.setCellValueFactory(new PropertyValueFactory<colis,Integer>("id_paiement"));


    }
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        showcolis();

    }
}
