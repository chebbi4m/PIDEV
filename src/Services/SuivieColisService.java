/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Utils.MyConnection;

/**
 *
 * @author nedia
 */
public class SuivieColisService {
    public String getEtatColis(String ref) throws SQLException{
        Connection myconn = MyConnection.getInstance().getConnexion();
        String sql = "SELECT etat_colis FROM colis WHERE ref = ?";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setString(1, ref);

        ResultSet rs = ste.executeQuery();

        if (rs.next()) {
           String etatColis = rs.getString("etat_colis");
           String etat = "Etat colis for reference number " + ref + " is: " + etatColis;
           return etat;
        } else {
            String etat = "No record found for reference number " + ref;
           return etat;
        }
    }
}
