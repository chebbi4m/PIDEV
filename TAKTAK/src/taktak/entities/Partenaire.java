/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.entities;


/**
 *
 * @author Najet
 */
public class Partenaire {
    private int id ; 
    private String nom; 
    private String login; 
    private String mdp;
    private int numtel;
    private String email;
    private String adresse;
    

    public Partenaire(int id, String nom,  String email, String adresse,int numtel,String login,String mdp) {
        this.id = id;
        this.nom = nom;
        this.login =login;
        this.numtel = numtel;
        this.email = email;
        this.adresse = adresse;
        this.mdp=mdp;
       
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

    public String getAdresse() {
        return adresse;
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Partenaire{" + "id=" + id + ", nom=" + nom + ", login=" + login + ", mdp=" + mdp + ", numtel=" + numtel + ", email=" + email + ", adresse=" + adresse + '}';
    }

   

   

   
    
    
}
