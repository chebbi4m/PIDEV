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
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String numtel;
    private int nbre_reclamation;
    private int nbre_colis_total;
    private int nbre_colis_courant;
    private String login;
    private String mdp;
   
    //Constructors

    public LivreurInterface(int id, String nom, String prenom, String email, String numtel, int nbre_reclamation, int nbre_colis_total, int nbre_colis_courant, String login, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.nbre_reclamation = nbre_reclamation;
        this.nbre_colis_total = nbre_colis_total;
        this.nbre_colis_courant = nbre_colis_courant;
        this.login = login;
        this.mdp = mdp;
    }

    public int getNbre_reclamation() {
        return nbre_reclamation;
    }

    public void setNbre_reclamation(int nbre_reclamation) {
        this.nbre_reclamation = nbre_reclamation;
    }

    public int getNbre_colis_total() {
        return nbre_colis_total;
    }

    public void setNbre_colis_total(int nbre_colis_total) {
        this.nbre_colis_total = nbre_colis_total;
    }

    public int getNbre_colis_courant() {
        return nbre_colis_courant;
    }

    public void setNbre_colis_courant(int nbre_colis_courant) {
        this.nbre_colis_courant = nbre_colis_courant;
    }

 
  
    
    //Getters

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
        return "LivreurInterface{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", numtel=" + numtel + ", nbre_reclamation=" + nbre_reclamation + ", nbre_colis_total=" + nbre_colis_total + ", nbre_colis_courant=" + nbre_colis_courant + ", login=" + login + ", mdp=" + mdp + '}';
    }

  
}