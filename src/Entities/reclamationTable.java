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
    private String nom_du_reclame; 
    private String type_reclamation;
    private Date date;
    private String ref_colis;


    public reclamationTable(String ref_colis, Date date_reclamation, String type_reclamation, String text, String personne_reclame) {
        this.text = text;
        this.nom_du_reclame = personne_reclame;
        this.type_reclamation = type_reclamation;
        this.date = date_reclamation;
        this.ref_colis = ref_colis;
    }

    public String getText() {
        return text;
    }

    public String getNom_du_reclame() {
        return nom_du_reclame;
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

    public void setText(String text) {
        this.text = text;
    }

    public void setNom_du_reclame(String nom_du_reclame) {
        this.nom_du_reclame = nom_du_reclame;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.text);
        hash = 53 * hash + Objects.hashCode(this.nom_du_reclame);
        hash = 53 * hash + Objects.hashCode(this.type_reclamation);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.ref_colis);
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
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.nom_du_reclame, other.nom_du_reclame)) {
            return false;
        }
        if (!Objects.equals(this.type_reclamation, other.type_reclamation)) {
            return false;
        }
        if (!Objects.equals(this.ref_colis, other.ref_colis)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "reclamationTable{" + "text=" + text + ", nom_du_reclame=" + nom_du_reclame + ", type_reclamation=" + type_reclamation + ", date=" + date + ", ref_colis=" + ref_colis + '}';
    }
    
        
}
