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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import taktak.entities.Paiement;

import taktak.interfaces.IPaiement;
import taktak.utils.MyConnection;

/**
 *
 * @author Najet
 */
public class PaiementService implements IPaiement {
    
    Connection myconn=  MyConnection.getInstance().getConnexion();

        
    @Override
    public void AjouterPaiement(Paiement pa) {
        try {
            
            String sql=" insert into paiement (id_colis, type, date) values(?,?,?)";
            PreparedStatement ste=myconn.prepareStatement(sql);
            
                ste.setInt(1,pa.getId_colis());
                ste.setString(2,pa.getType());
                ste.setString(3,pa.getDate());
                ste.executeUpdate();
            System.out.println("paiement ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(PaiementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    
    @Override
    public void ModifierPaiement(Paiement pa) {
      try {
            
            String sql=" Update paiement set type =?  where id_colis =?";
            PreparedStatement ste=myconn.prepareStatement(sql);
            
                ste.setString(1,pa.getType());
                ste.setInt(2,pa.getId_colis());
               
                ste.executeUpdate();
            System.out.println("paiement modifier");
        } catch (SQLException ex) {
            Logger.getLogger(PaiementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    
    
    @Override
    public List<Paiement> AfficherPaiement() {
      {
        List<Paiement> paiement = new ArrayList<>();
         try{
       String sql="select * from paiement";
       Statement ste = myconn.createStatement();
        ResultSet s = ste.executeQuery(sql);
        while(s.next()){
        
                    Paiement pa = new Paiement(
                            s.getInt("id_colis"),
                            s.getString("type"),
                            s.getString("date"));                   
                    paiement.add(pa);
                    
                   
                        }
           System.out.println("paiement afficher");
       }
       catch(SQLException ex){
           System.out.println(ex);
       }
       
       return paiement;
       
       
    }
    }
    
}
