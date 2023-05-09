/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

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
    private boolean fragile;
    private boolean inflammable;
    private String depart;
    private String destination;
    private String etat_colis = "En cours";
    private String zone;
    private boolean urgent;
    private int prix;
    private int id_client;
    private int id_livreur;
    private int id_partenaire;
    
    public Colis(){
        
    }

    public Colis(String ref, int hauteur, int largeur, int poids, boolean fragile, boolean inflammable, String depart, String destination, String etat_colis, String zone, boolean urgent, int prix, int id_client, int id_livreur, int id_partenaire) {
        this.ref = ref;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.poids = poids;
        this.fragile = fragile;
        this.inflammable = inflammable;
        this.depart = depart;
        this.destination = destination;
        this.etat_colis = etat_colis;
        this.zone = zone;
        this.urgent = urgent;
        this.prix = prix;
        this.id_client = id_client;
        this.id_livreur = id_livreur;
        this.id_partenaire = id_partenaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public boolean getFragile() {
        return fragile;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    public boolean getInflammable() {
        return inflammable;
    }

    public void setInflammable(boolean inflammable) {
        this.inflammable = inflammable;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getEtat_colis() {
        return etat_colis;
    }

    public void setEtat_colis(String etat_colis) {
        this.etat_colis = etat_colis;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public boolean getUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

    public int getId_partenaire() {
        return id_partenaire;
    }

    public void setId_partenaire(int id_partenaire) {
        this.id_partenaire = id_partenaire;
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
        return "Colis{" + "ref=" + ref + ", hauteur=" + hauteur + ", largeur=" + largeur + ", poids=" + poids + ", prix=" + prix + ", fragile=" + fragile + ", destination=" + destination + ", id_client=" + id_client + '}';
    }
    
    
    
}


