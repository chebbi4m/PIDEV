/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.entities;

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
    private boolean inflammable;
    private String depart;
    private String destination;
    private String etat_colis;
    private String zone;
    private boolean urgent;
    private int id_client;
    private int id_livreur;
    
    public Colis(){
        
    }
    public Colis(String ref, int hauteur, int largeur, int poids, int prix, boolean fragile, boolean inflammable ,String depart, String destination, String etat_colis, String zone, boolean urgent, int id_client, int id_livreur) {
        
        this.ref = ref;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.poids = poids;
        this.prix = prix;
        this.fragile = fragile;
        this.inflammable = inflammable;
        this.depart = depart;
        this.destination = destination;
        this.etat_colis = etat_colis;
        this.zone = zone;
        this.urgent = urgent;
        this.id_client = id_client;
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
    
    public boolean getInflammable(){
        return this.inflammable;
    }
    
    public String getDepart() {
        return this.depart;
    }

    public String getDestination() {
        return this.destination;
    }
    
    public String getEtat_colis(){
        return this.etat_colis;
    }
    
    public String getZone(){
        return this.zone;
    }
    
    public boolean getUrgent(){
        return this.urgent;
    }

    public int getId_client() {
        return this.id_client;
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
    
    public void setInflammable(boolean inflammable) {
        this.inflammable = inflammable;
    }
    
    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public void setEtat_colis(String etat_colis) {
        this.etat_colis = etat_colis;
    }
    
    public void setZone(String zone) {
        this.zone = zone;
    }
    
    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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
        return "Colis{" + "ref=" + ref + ", destination=" + destination + " etat_colis=" +etat_colis +'}';
    }
    
}