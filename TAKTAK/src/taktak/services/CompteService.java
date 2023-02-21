/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taktak.services;
import taktak.entities.Compte;
import java.sql.Connection;
import taktak.interfaces.ICompte;
import java.sql.SQLException;
import taktak.utils.MyConnection;
import java.sql.*;

/**
 *
 * @author yasoulanda
 */
    public class CompteService implements ICompte{
        
     Connection myconn = MyConnection.getInstance().getConnexion();
    
@Override
    public void ajouterCompte (Compte cp) {
        try {
            String sql="insert into compte (id, login, mdp ) values (?,?,?)"; 
            PreparedStatement ste= myconn.prepareStatement(sql);
            ste.setInt(1,cp.getId());
            ste.setString(2,cp.getLogin());
            ste.setString(3,cp.getMdp());
                      
            ste.executeUpdate();
             System.out.println("compte ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void modifierCompte(Compte cp) {
         String sql="update compte set (?,?,?) where id= ?";
        try{
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1,cp.getId());
            ste.setString(2,cp.getLogin());
            ste.setString(3,cp.getMdp());
            
            ste.executeUpdate();
        }catch(SQLException ex ){
        System.out.print(ex);
        }
        }
        
    

    @Override
    public void supprimerCompte() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    @Override
    public void afficherCompte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
