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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        public Partenaire(int id, String nom, String email, String numtel, String login, String mdp) {
        this.id=id;
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
        this.login = login;
        this.mdp = mdp;
    }
    
    public Partenaire( String nom, String email, String numtel, String login, String mdp) {
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
        this.login = login;
        this.mdp = mdp;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Partenaire{" + "nom=" + nom + ", email=" + email + ", numtel=" + numtel + ", login=" + login + ", mdp=" + mdp + '}';
    }

    
}