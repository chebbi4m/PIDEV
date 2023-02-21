/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.entities;


import java.sql.Date;

/**
 *
 * @author Najet
 */
public class Paiement {
   private  int id_colis;
   private  String date ;
   private  String type ;

  

    public Paiement(int id_colis, String type ,String date ) {
        this.id_colis = id_colis;
        this.date = date;
        this.type = type;
    }

    public int getId_colis() {
        return id_colis;
    }

    public void setId_colis(int id_colis) {
        this.id_colis = id_colis;
    }
  

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

   
    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id_colis;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paiement other = (Paiement) obj;
        if (this.id_colis != other.id_colis) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Paiement{" + "id_colis=" + id_colis + ", date=" + date + ", type=" + type + '}';
    }
   
}
