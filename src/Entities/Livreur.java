/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.util.Objects;

/**
 *
 * @author yasoulanda
 */
public class Livreur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String numtel;
    private String password;
    private int nbre_reclamation ;
    private int nbre_colis_total ;
    private int nbre_colis_courant;
    private String adresse;
    private int user_id;

   
    
    
    //constructors

    public Livreur() {}
    
    public Livreur(int id) {
        this.id = id;
    }
    
    public Livreur(String nom, String prenom, String email, String numtel,String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.password=password;
    }

    public Livreur(int aInt, String string, String string0, String string1, String string2, String string3, String string4, boolean add) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


     public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    //getters
    public int getId() {
        return id;
    }

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
    
    //setters

    public void setId(int id) {
        this.id = id;
    }

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.nom);
        hash = 11 * hash + Objects.hashCode(this.prenom);
        hash = 11 * hash + Objects.hashCode(this.email);
        hash = 11 * hash + Objects.hashCode(this.numtel);
        hash = 11 * hash + Objects.hashCode(this.password);
        hash = 11 * hash + this.nbre_reclamation;
        hash = 11 * hash + this.nbre_colis_total;
        hash = 11 * hash + this.nbre_colis_courant;
        hash = 11 * hash + Objects.hashCode(this.adresse);
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
        final Livreur other = (Livreur) obj;
        if (this.nbre_reclamation != other.nbre_reclamation) {
            return false;
        }
        if (this.nbre_colis_total != other.nbre_colis_total) {
            return false;
        }
        if (this.nbre_colis_courant != other.nbre_colis_courant) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.numtel, other.numtel)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Livreur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", numtel=" + numtel + ", password=" + password + ", nbre_reclamation=" + nbre_reclamation + ", nbre_colis_total=" + nbre_colis_total + ", nbre_colis_courant=" + nbre_colis_courant + ", adresse=" + adresse + '}';
    }
     
    
    
   
}   