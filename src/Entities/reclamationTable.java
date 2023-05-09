/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author nedia
 */
public class reclamationTable {
    private String text;
    private String type_reclamation;
    private Date date;
    private String ref_colis;
    private int stars;
    private String livreur_nom;
    private String livreur_prenom;

    public reclamationTable(){}

    public reclamationTable(String ref_colis, Date date_reclamation, String type_reclamation, String text, int stars, String livreur_nom, String livreur_prenom) {
        this.text = text;
        this.type_reclamation = type_reclamation;
        this.date = date_reclamation;
        this.ref_colis = ref_colis;
        this.stars = stars;
        this.livreur_nom = livreur_nom;
        this.livreur_prenom = livreur_prenom;
    }

    public String getText() {
        return text;
    }

    public String getType_reclamation() {
        return type_reclamation;
    }

    public Date getDate() {
        return date;
    }

    public String getRef_colis() {
        return ref_colis;
    }

    public int getStars() {
        return stars;
    }

    public String getLivreur_nom() {
        return livreur_nom;
    }

    public String getLivreur_prenom() {
        return livreur_prenom;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType_reclamation(String type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRef_colis(String ref_colis) {
        this.ref_colis = ref_colis;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setLivreur_nom(String livreur_nom) {
        this.livreur_nom = livreur_nom;
    }

    public void setLivreur_prenom(String livreur_prenom) {
        this.livreur_prenom = livreur_prenom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.text);
        hash = 97 * hash + Objects.hashCode(this.type_reclamation);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.ref_colis);
        hash = 97 * hash + this.stars;
        hash = 97 * hash + Objects.hashCode(this.livreur_nom);
        hash = 97 * hash + Objects.hashCode(this.livreur_prenom);
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
        final reclamationTable other = (reclamationTable) obj;
        if (this.stars != other.stars) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.type_reclamation, other.type_reclamation)) {
            return false;
        }
        if (!Objects.equals(this.ref_colis, other.ref_colis)) {
            return false;
        }
        if (!Objects.equals(this.livreur_nom, other.livreur_nom)) {
            return false;
        }
        if (!Objects.equals(this.livreur_prenom, other.livreur_prenom)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "reclamationTable{" + "text=" + text + ", type_reclamation=" + type_reclamation + ", date=" + date + ", ref_colis=" + ref_colis + ", stars=" + stars + ", livreur_nom=" + livreur_nom + ", livreur_prenom=" + livreur_prenom + '}';
    }

    

}
