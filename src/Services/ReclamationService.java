/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entities.reclamation;
import Utils.MyConnection;

/**
 *
 * @author chebbi4m
 */
public class ReclamationService {
    
    public void ajouterReclamation(reclamation reclamation) throws SQLException {
        Connection myconn = MyConnection.getInstance().getConnexion();
        
        try {
            PreparedStatement ste = myconn.prepareStatement(
                    "INSERT INTO reclamation (text, personne_reclame, type_reclamation, date, id_client, ref ,stars ) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    ste.setString(1, reclamation.getText());
                    ste.setString(2, reclamation.getPersonne_reclame());
                    ste.setString(3, reclamation.getType_reclamation());
                    ste.setDate(4, reclamation.getDate());
                    ste.setInt(5, reclamation.getId_client());
                    ste.setString(6, reclamation.getRef_colis());
                    ste.setInt(7, reclamation.getStars());
                    System.out.println(reclamation.getText());
                    System.out.println(reclamation.getPersonne_reclame());
                    System.out.println(reclamation.getType_reclamation());
                    System.out.println(reclamation.getId_client());
                    System.out.println(reclamation.getRef_colis());
                    System.out.println(reclamation.getDate());
            ste.executeUpdate();
            System.out.println("reclamation added with success");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void modiferReclamation(reclamation rec) throws SQLException {
        Connection myconn = MyConnection.getInstance().getConnexion();
        
        try {
            PreparedStatement ste = myconn.prepareStatement(
            "UPDATE reclamation SET text = ?, stars = ? WHERE id = ?");
            ste.setString(1, rec.getText());
            ste.setInt(2, rec.getStars());
            ste.setInt(3, rec.getId());
            ste.executeUpdate();

            System.out.println("Reclamation updated successfully!");



        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteReclamation(int id) {
        Connection myconn = MyConnection.getInstance().getConnexion();
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement(
                    "DELETE FROM reclamation WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("reclamation deleted with success");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
//    public void updateReclamation(reclamation reclamation) {
//        Connection myconn = MyConnection.getInstance().getConnexion();
//        try {
//            PreparedStatement preparedStatement = myconn.prepareStatement(
//                    "UPDATE reclamation SET text=?, nom_personne=?, ref_colis=? WHERE id=?");
//            preparedStatement.setString(1, reclamation.getText());
//            preparedStatement.setString(2, reclamation.getNomPersonne());
//            preparedStatement.setString(3, reclamation.getRefColis());
//            preparedStatement.setInt(4, reclamation.getId());
//            preparedStatement.executeUpdate();
//            System.out.println("reclamation updated with success");
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//    }
    
    public List<reclamation> afficherReclamation() {
        List<reclamation> reclamations = new ArrayList<>();

        Connection myconn = MyConnection.getInstance().getConnexion();
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement("SELECT * FROM reclamation");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_client = resultSet.getInt("id_client");
                String text = resultSet.getString("text");
                String personne_reclame = resultSet.getString("personne_reclame");
                String ref_colis = resultSet.getString("ref");
                String type_reclamation = resultSet.getString("type_reclamation");
                Date date_reclamation = resultSet.getDate("date");
                int stars = resultSet.getInt("stars");
                reclamation reclamation = new reclamation(id, text, personne_reclame, type_reclamation, date_reclamation, id_client, ref_colis,stars);
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return reclamations;
    }
    
    public List<reclamation> afficherReclamationByLogin(String login) {
        List<reclamation> reclamations = new ArrayList<>();

        Connection myconn = MyConnection.getInstance().getConnexion();
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement("SELECT * FROM reclamation where login=? ");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_client = resultSet.getInt("id_client");
                String text = resultSet.getString("text");
                String personne_reclame = resultSet.getString("personne_reclame");
                String ref_colis = resultSet.getString("ref_colis");
                String type_reclamation = resultSet.getString("type_reclamation");
                Date date_reclamation = resultSet.getDate("date");
                int stars = resultSet.getInt("stars");
                reclamation reclamation = new reclamation(id, text, personne_reclame, type_reclamation, date_reclamation, id_client, ref_colis,stars);
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return reclamations;
    }
    
    public void incrementerNbrReclamation(String type_personne, int id) {
    Connection myconn = MyConnection.getInstance().getConnexion();
    try {
        if (type_personne.equals("Livreur")) {
            String sql = "UPDATE livreur SET nbre_reclamation = nbre_reclamation + 1 WHERE id = ?;";
            PreparedStatement preparedStatement = myconn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Livreur reclamation incremented");
        } else if (type_personne.equals("Partenaire")) {
            String sql = "UPDATE partenaire SET nbre_reclamation = nbre_reclamation + 1 WHERE id = ?;";
            PreparedStatement preparedStatement = myconn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Partenaire reclamation incremented");
        }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public List<reclamation>afficherMesReclamation(String name_livreur){
        List<reclamation> reclamations = new ArrayList<>();

        Connection myconn = MyConnection.getInstance().getConnexion();
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement("SELECT * FROM reclamation where personne_reclamé = ? ");
            preparedStatement.setString(1, name_livreur);
            System.out.println(name_livreur);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String text = resultSet.getString("text");
                
                String ref_colis = resultSet.getString("ref");
                String type_reclamation = resultSet.getString("type_reclamation");
                Date date_reclamation = resultSet.getDate("date");
                reclamation reclamation = new reclamation(text, type_reclamation, date_reclamation, ref_colis);
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println(reclamations);
        return reclamations;
    }

}
    
