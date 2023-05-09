/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Livreur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entities.LivreurInterface;
import Interfaces.ILivreurInterface;
import Session.UserSession;
import Utils.MyConnection;

/**
 *
 * @author Cheima
 */
public class LivreurInterfaceService implements ILivreurInterface{

    @Override
    public void modifierLivreurD(LivreurInterface lv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LivreurInterface> afficherLivreurD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   


    
    
}