package services;

import entities.colis;
import interfaces.Icolis;
import utils.SqlConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class colisService implements Icolis{
     Connection myconn = SqlConnection.MyConnection();
    static Connection con = null;
    static ResultSet rs = null;
    static PreparedStatement ste = null;
    @Override
    public  void ajoutercolis(colis cls) {
        con = SqlConnection.MyConnection();
        String insert ="insert into colis values(?,?,?,?,?,?,?,?,?,?,?)";
        try {


             ste= con.prepareStatement(insert);

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
            ste.setInt(11, cls.getId_paiment());
            ste.executeUpdate();
            System.out.println("colis ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }



    @Override
    public void modifiercolis(colis cls) {
        String sql="UPDATE colis SET ref = ?,hauteur = ?,largeur = ?,poids = ?,prix = ?, fragile = ?,depart = ?,destination = ?,id_client = ?,id_paiement = ? WHERE id = ?";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);

            ste.setString(1, cls.getRef());
            ste.setDouble(2, cls.getHauteur());
            ste.setDouble(3, cls.getLargeur());
            ste.setDouble(4, cls.getPoids());
            ste.setDouble(5, cls.getPrix());
            ste.setBoolean(6, cls.getFragile());
            ste.setString(7, cls.getDepart());
            ste.setString(8, cls.getDestination());
            ste.setInt(9, cls.getId_client());
            ste.setInt(10, cls.getId_paiment());
            ste.setInt(11, cls.getId());
            ste.executeUpdate();
            System.out.println("colis modifié");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimercolis(colis cls) {
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement("DELETE FROM colis where id = ?");
            preparedStatement.setInt(1,cls.getId());
            preparedStatement.executeUpdate();
            System.out.println("colis supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(colisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




}