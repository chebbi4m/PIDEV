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
public class Partenaire {

    public static void setItems(ObservableList<Partenaire> List) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id ; 
    private String nom; 
    private String email;
    private int numtel;
    private String moyen_transport;
    private String zone  ; 
    private double prix_poids;
    private double prix_zone;
    private boolean inflammable ;
    private boolean fragile ;
    private String login; 
    private String mdp;
    public Partenaire(){
    };
    public Partenaire(int id, String nom, String email, int numtel, String moyen_transport, String zone, double prix_poids, double prix_zone, boolean inflammable, boolean fragile, String login, String mdp) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
        this.moyen_transport = moyen_transport;
        this.zone = zone;
        this.prix_poids = prix_poids;
        this.prix_zone = prix_zone;
        this.inflammable = inflammable;
        this.fragile = fragile;
        this.login = login;
        this.mdp = mdp;
    }
 

    

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
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

    public String getMoyen_transport() {
        return moyen_transport;
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

    

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMoyen_transport(String moyen_transport) {
        this.moyen_transport = moyen_transport;
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

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Partenaire{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", numtel=" + numtel + ", moyen_transport=" + moyen_transport + ", zone=" + zone + ", prix_poids=" + prix_poids + ", prix_zone=" + prix_zone + ", inflammable=" + inflammable + ", fragile=" + fragile + ", login=" + login + ", mdp=" + mdp + '}';
    }

    

   

   

   
    
    
}
