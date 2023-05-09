/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.entities;

import java.util.Objects;
import javafx.collections.ObservableList;


public class LivreurInterfaceN {

  
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String numtel;
    private String adresse;
    private int nbre_reclamation;
    private int nbre_colis_total;
    private int nbre_colis_courant;  
    private String password;
    private int id_partenaire;

  
    
    //Constructors
    
    public LivreurInterfaceN(){}
     public LivreurInterfaceN(int id) {
       
    }

    public LivreurInterfaceN(int id, String nom, String prenom, String email, String numtel, String adresse, int nbre_reclamation, int nbre_colis_total, int nbre_colis_courant, String password, int id_partenaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.adresse = adresse;
        this.nbre_reclamation = nbre_reclamation;
        this.nbre_colis_total = nbre_colis_total;
        this.nbre_colis_courant = nbre_colis_courant;
        this.password = password;
        this.id_partenaire = id_partenaire;
    }

    public LivreurInterfaceN(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }
    
     public LivreurInterfaceN(int  id ,String nom, String prenom, String email, String numtel,String adresse, int nbre_reclamation, int nbre_colis_total, int nbre_colis_courant, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
         this.adresse = adresse;
        this.nbre_reclamation = nbre_reclamation;
        this.nbre_colis_total = nbre_colis_total;
        this.nbre_colis_courant = nbre_colis_courant;     
        this.password=password;
        
    }
    public LivreurInterfaceN( int  id ,String nom, String prenom, String email, String numtel,String adresse) {
       this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.adresse = adresse;
           
        
        
    }

    public String getAdresse() {
        return adresse;
    }


    public int getNbre_reclamation() {
        return nbre_reclamation;
    }

      public String getPassword() {
        return password;
    }

    public int getId_partenaire() {
        return id_partenaire;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

 

  
    
    //toString

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId_partenaire(int id_partenaire) {
        this.id_partenaire = id_partenaire;
    }

   
  

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.nom);
        hash = 89 * hash + Objects.hashCode(this.prenom);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.numtel);
        hash = 89 * hash + this.nbre_reclamation;
        hash = 89 * hash + this.nbre_colis_total;
        hash = 89 * hash + this.nbre_colis_courant;
        hash = 89 * hash + Objects.hashCode(this.password);
        hash = 89 * hash + this.id_partenaire;
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
        final LivreurInterfaceN other = (LivreurInterfaceN) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nbre_reclamation != other.nbre_reclamation) {
            return false;
        }
        if (this.nbre_colis_total != other.nbre_colis_total) {
            return false;
        }
        if (this.nbre_colis_courant != other.nbre_colis_courant) {
            return false;
        }
        if (this.id_partenaire != other.id_partenaire) {
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
        return true;
    }
  
      @Override
    public String toString() {
        return "LivreurInterface{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", numtel=" + numtel + ", nbre_reclamation=" + nbre_reclamation + ", nbre_colis_total=" + nbre_colis_total + ", nbre_colis_courant=" + nbre_colis_courant + ", password=" + password + ", id_partenaire=" + id_partenaire + '}';
    }
    
    
    
}