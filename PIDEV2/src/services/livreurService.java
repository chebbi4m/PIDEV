package services;


import entities.colis;
import entities.livreur;
import interfaces.Ilivreur;

import utils.SqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class livreurService implements Ilivreur {
    Connection myconn = SqlConnection.MyConnection();
    static Connection con = null;
    static ResultSet rs = null;
    static PreparedStatement ste = null;
    public  void ajouterlivreur(livreur ls) {
        con = SqlConnection.MyConnection();
        String insert ="insert into livreur values(?,?,?,?,?,?)";
        try {


            ste= con.prepareStatement(insert);

            ste.setInt(1, ls.getId());
            ste.setString(2, ls.getNom());
            ste.setString(3, ls.getPrenom());
            ste.setString(4, ls.getEmail());
            ste.setInt(5, ls.getNumtel());
            ste.setString(6, ls.getId_compte());

            ste.executeUpdate();
            System.out.println("livreurs ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }



    @Override
    public List<livreur> afficherleslivreurs() {
        List <livreur> livreur = new ArrayList<>();
        try {
            String sql = "select * from livreur";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                livreur l = new livreur(
                        s.getInt(1),
                        s.getString("nom"),
                        s.getString("prenom"),
                        s.getString("email"),
                        s.getInt("numtel"),
                        s.getString("id_compte"));

                livreur.add(l);
                System.out.println(livreur);


            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return livreur;
    }

}
