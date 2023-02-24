/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.entities;

/**
 *
 * @author Cheima
 */
public class LivreurInterface {
    private String nom;
    private String prenom;
    private String email;
    private String numtel;
    private String login;
    private String mdp;
   
    //Constructors

    public LivreurInterface(String nom, String prenom, String email, String numtel, String login, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.login = login;
        this.mdp = mdp;
    }
    
    //Getters

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getNumtel() {
        return numtel;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }
    
    //Setters

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    //toString

    @Override
    public String toString() {
        return "LivreurInterface{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", numtel=" + numtel + ", login=" + login + ", mdp=" + mdp + '}';
    }
       
}


