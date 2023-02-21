package gui;

import entities.compte;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.SqlConnection;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class livreurController implements Initializable {
    static Connection con = null;
    static ResultSet rs = null;
    static PreparedStatement st = null;
    @FXML
    private AnchorPane Formlivreur;

    @FXML
    private TextField field_IDuser;

    @FXML
    private TextField field_mail;

    @FXML
    private TextField field_nom;

    @FXML
    private TextField field_num;

    @FXML
    private TextField field_prenom;

@FXML
void saveDriver(Action event){
    password p = new password();
    String insert = "insert into compte(login,mdp) values(?,?,?";
    con = SqlConnection.MyConnection();
    try{
    st = con.prepareStatement(insert);
        st.setString(1, compte.login);
    st.setString(2, compte.mdp.toString());
        st.executeUpdate();
} catch (SQLException e) {

        throw new RuntimeException(e);
    }
}


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }}

