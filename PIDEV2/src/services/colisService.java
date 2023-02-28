package services;

import entities.colis;
import interfaces.Icolis;
import utils.SqlConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class colisService implements Icolis{
     Connection myconn = SqlConnection.MyConnection();
    static Connection con = null;
    static ResultSet rs = null;
    static PreparedStatement ste = null;
    @Override
    public  void ajoutercolis(colis cls) {
        con = SqlConnection.MyConnection();
        String insert = "insert into colis values(?,?,?,?,?,?,?,?,?,?,?)";
        try {


            ste = con.prepareStatement(insert);

            ste.setInt(1, cls.getId());
            ste.setString(2, cls.getRef());
            ste.setDouble(3, cls.getHauteur());
            ste.setDouble(4, cls.getLargeur());
            ste.setDouble(5, cls.getPoids());
            ste.setDouble(6, cls.getPrix());
            ste.setBoolean(7, cls.getFragile());
            ste.setString(8, cls.getDepart());
            ste.setString(9, cls.getDestination());
            ste.setInt(10, cls.getId_client());
            ste.setInt(11, cls.getId_partenaire());
            ste.executeUpdate();
            System.out.println("colis ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }}



