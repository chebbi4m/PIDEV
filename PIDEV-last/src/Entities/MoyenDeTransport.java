package Entities;

import Utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MoyenDeTransport {

    private int id;
  

    private String marque;
 

    private int type;
 

    private String matricule;
  

    private int idPar;
   

    private int livreurId;

    public MoyenDeTransport(int id, String marque, int type, String matricule) {
        this.id = id;
        this.marque = marque;
        this.type = type;
        this.matricule = matricule;
    }
    

    public MoyenDeTransport(int id, String marque, int type, String matricule, int idPar, int livreurId) {
        this.id = id;
        this.marque = marque;
        this.type = type;
        this.matricule = matricule;
        this.idPar = idPar;
        this.livreurId = livreurId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getIdPar() {
        return idPar;
    }

    public void setIdPar(int idPar) {
        this.idPar = idPar;
    }

    public int getLivreurId() {
        return livreurId;
    }

    public void setLivreurId(int livreurId) {
        this.livreurId = livreurId;
    }
    public void deleteFromDatabase() throws SQLException {
        String sql = "DELETE FROM moyen_de_transport WHERE id = ?";
        PreparedStatement statement = MyConnection.getInstance().getConnexion().prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
