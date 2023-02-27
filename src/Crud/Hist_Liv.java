/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

/**
 *
 * @author yasoulanda
 */
public class Hist_Liv {
        private String nom; 
        private String numtel;
        private String ref;
        private String prix;
        
        public Hist_Liv (String nom, String numtel, String refcolis,String prixcolis){
        this.nom = nom;
        this.numtel = numtel;
        this.ref = refcolis;
        this.prix = prixcolis;
      }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getRefcolis() {
        return ref;
    }

    public void setRefcolis(String refcolis) {
        this.ref = refcolis;
    }

    public String getPrixcolis() {
        return prix;
    }

    public void setPrixcolis(String prixcolis) {
        this.prix = prixcolis;
    }

    @Override
    public String toString() {
        return "Hist_Liv{" + "nom =" + nom + ", numero tel=" + numtel + ", reference du colis=" + ref + ", prix du colis=" + prix + '}';
    }
        
        
        

}
