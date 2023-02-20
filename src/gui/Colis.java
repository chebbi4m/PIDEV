/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author LENOVO THINKPAD E15
 */
public class Colis {
    private int id;
    private String ref;
    private int hauteur;
    private int largeur;
    private int poids;
    private int prix;
    private boolean fragile;
    private String depart;
    private String destination;
    private String nom_receveur;
    private int id_client;
    private int id_paiement;
    private int id_livreur;
    
    public Colis(){
        
    }
    public Colis(int id,String ref, int hauteur, int largeur, int poids, int prix, boolean fragile,String depart, String destination, String nom_receveur, int id_client, int id_paiement, int id_livreur) {
        this.id = id;
        this.ref = ref;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.poids = poids;
        this.prix = prix;
        this.fragile = fragile;
        this.depart = depart;
        this.destination = destination;
        this.nom_receveur = nom_receveur;
        this.id_client = id_client;
        this.id_paiement = id_paiement;
        this.id_livreur = id_livreur;
    }
    
    // getters
    
    public int getId(){
        return this.id;
    }
    
    public String getRef() {
        return this.ref;
    }

    public int getHauteur() {
        return this.hauteur;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public int getPoids() {
        return this.poids;
    }

    public int getPrix() {
        return this.prix;
    }

    public boolean getFragile() {
        return this.fragile;
    }
    
    public String getDepart() {
        return this.depart;
    }

    public String getDestination() {
        return this.destination;
    }
    
    public String getNom_receveur() {
        return this.nom_receveur;
    }

    public int getId_client() {
        return this.id_client;
    }

    public int getId_paiment() {
        return this.id_paiement;
    }
    
    public int getId_livreur() {
        return this.id_livreur;
    }
    
    //setters
    
    public void setId(int id) {
        this.id = id;
    }
    
     public void setRef(String ref) {
        this.ref = ref;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }
    
    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public void setNom_receveur(String nom_receveur) {
        this.nom_receveur = nom_receveur;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_paiement(int id_paiement) {
        this.id_paiement = id_paiement;
    }
    
    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
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
        final Colis other = (Colis) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Colis{" + "ref=" + ref + ", hauteur=" + hauteur + ", largeur=" + largeur + ", poids=" + poids + ", prix=" + prix + ", fragile=" + fragile + " depart=" + depart + ", destination=" + destination + " nom_receveur=" +nom_receveur + ", id_client=" + id_client + ", id_paiement=" + id_paiement + ", id_livreur=" + id_livreur + '}';
    }   
}

