/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.entities;

import javafx.collections.ObservableList;


/**
 *
 * @author Najet
 */
public class PartenaireN {

  
 
    private int id ; 
    private String nom; 
    private String email;
    private int numtel;
    private String zone  ; 
    private double prix_poids;
    private double prix_zone;
    private boolean inflammable ;
    private boolean fragile ;
    private String login; 
    private String password;
    public PartenaireN(){};
    public PartenaireN(int id, String nom, String email, int numtel,  String zone, double prix_poids, double prix_zone, boolean inflammable, boolean fragile, String login, String password) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
        this.zone = zone;
        this.prix_poids = prix_poids;
        this.prix_zone = prix_zone;
        this.inflammable = inflammable;
        this.fragile = fragile;
        this.login = login;
        this.password = password;
    }
  public PartenaireN(int id, String nom, String email, int numtel,  String zone, double prix_poids, double prix_zone, boolean inflammable, boolean fragile) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
        this.zone = zone;
        this.prix_poids = prix_poids;
        this.prix_zone = prix_zone;
        this.inflammable = inflammable;
        this.fragile = fragile;
    
  }
   

    

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    

    public String getEmail() {
        return email;
    }

 

    public String getZone() {
        return zone;
    }

    public double getPrix_poids() {
        return prix_poids;
    }

    public double getPrix_zone() {
        return prix_zone;
    }

    public boolean isInflammable() {
        return inflammable;
    }

    public boolean isFragile() {
        return fragile;
    }


    public int getNumtel() {
        return numtel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

     public void setPassword(String password) {
        this.password = password;
    }
 

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setPrix_poids(double prix_poids) {
        this.prix_poids = prix_poids;
    }

    public void setPrix_zone(double prix_zone) {
        this.prix_zone = prix_zone;
    }

    public void setInflammable(boolean inflammable) {
        this.inflammable = inflammable;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    

    public void setLogin(String login) {
        this.login = login;
    }

   


        @Override
    public String toString() {
        return "Partenaire{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", numtel=" + numtel + ", zone=" + zone + ", prix_poids=" + prix_poids + ", prix_zone=" + prix_zone + ", inflammable=" + inflammable + ", fragile=" + fragile + ", login=" + login + ", password=" + password + '}';
    }

   

   

   
    
    
}
