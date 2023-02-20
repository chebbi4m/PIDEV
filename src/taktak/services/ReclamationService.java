/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import taktak.entities.reclamation;
import taktak.utils.MyConnection;

/**
 *
 * @author chebbi4m
 */
public class ReclamationService {
    
    public void ajouterReclamation(reclamation reclamation) {
        Connection myconn = MyConnection.getInstance().getConnexion();
        try {
            PreparedStatement ste = myconn.prepareStatement(
                    "INSERT INTO reclamation (text, nom_personne, ref_colis) VALUES (?, ?, ?)");
                    ste.setString(1, reclamation.getText());
                    ste.setString(2, reclamation.getNomPersonne());
                    ste.setString(3, reclamation.getRefColis());
            ste.executeUpdate();
            System.out.println("reclamation added with success");
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
    
    public void updateReclamation(reclamation reclamation) {
        Connection myconn = MyConnection.getInstance().getConnexion();
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement(
                    "UPDATE reclamation SET text=?, nom_personne=?, ref_colis=? WHERE id=?");
            preparedStatement.setString(1, reclamation.getText());
            preparedStatement.setString(2, reclamation.getNomPersonne());
            preparedStatement.setString(3, reclamation.getRefColis());
            preparedStatement.setInt(4, reclamation.getId());
            preparedStatement.executeUpdate();
            System.out.println("reclamation updated with success");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public List<reclamation> afficherReclamation() {
        List<reclamation> reclamations = new ArrayList<>();

        Connection myconn = MyConnection.getInstance().getConnexion();
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement("SELECT * FROM reclamation");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                String nomPersonne = resultSet.getString("nom_personne");
                String refColis = resultSet.getString("ref_colis");
                reclamation reclamation = new reclamation(id, text, nomPersonne, refColis);
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return reclamations;
    }
    
}
