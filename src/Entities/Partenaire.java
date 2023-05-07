/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class Partenaire {
    private int id ; 
    private String nom;  
    private String email;
    private String numtel;
    private String zone  ; 
    private double prix_poids ;
    private double prix_zone;
    private boolean inflammable ;
    private boolean fragile ;
    //private String login; 
    private String password;
        private int user_id;

    public Partenaire(){
    };

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        public Partenaire(int id, String nom, String email, String numtel, String login, String password) {
        this.id=id;
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
       // this.login = login;
        this.password = password;
    }
    
    public Partenaire( String nom, String email, String numtel, String login, String password) {
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
        //this.login = login;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    
    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public double getPrix_poids() {
        return prix_poids;
    }

    public void setPrix_poids(double prix_poids) {
        this.prix_poids = prix_poids;
    }

    public double getPrix_zone() {
        return prix_zone;
    }

    public void setPrix_zone(double prix_zone) {
        this.prix_zone = prix_zone;
    }

    public boolean isInflammable() {
        return inflammable;
    }

    public void setInflammable(boolean inflammable) {
        this.inflammable = inflammable;
    }

    public boolean isFragile() {
        return fragile;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Partenaire{"  + ", nom=" + nom + ", email=" + email + ", numtel=" + numtel + ", zone=" + zone + ", prix_poids=" + prix_poids + ", prix_zone=" + prix_zone + ", inflammable=" + inflammable + ", fragile=" + fragile + ", password=" + password + '}';
    }

    

 
    
}