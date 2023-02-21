package gui;

import entities.compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.SqlConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class comptecontroller implements Initializable {
    static Connection con = null;
    static ResultSet rs = null;
    static PreparedStatement st = null;
    @FXML
    private TableColumn<compte, Integer> col_id;

    @FXML
    private TableColumn<compte, String> col_login;

    @FXML
    private TableColumn<compte, password> col_mdp;
    @FXML
    private TableView<compte> table_user;
    @FXML

    public static ObservableList<compte> getDatacomptes() {

        ObservableList<compte> list = FXCollections.observableArrayList();
        String query = "select* from compte";
        con = SqlConnection.MyConnection();
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                compte st = new compte();
                st.setId(rs.getInt("id"));
                st.setLogin(rs.getString("login"));
                list.add(st);}
        } catch (SQLException e){
            throw new RuntimeException(e);



        }
        return list;
    }
    public  void showusers(){
        ObservableList<compte> list = getDatacomptes();
        table_user.setItems(list);
        col_id.setCellValueFactory(new PropertyValueFactory<compte,Integer>("id"));
        col_login.setCellValueFactory(new PropertyValueFactory<compte,String>("login"));
        col_mdp.setCellValueFactory(new PropertyValueFactory<compte,password>("mdp"));

    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
showusers();

    }}