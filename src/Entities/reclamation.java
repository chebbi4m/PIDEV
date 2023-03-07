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
 * @author chebbi4m
 */
public class reclamation {
    private int id;
    private String text;
    private int personne_reclame;
    private String nom_du_reclame; 
    private String type_reclamation;
    private Date date;
    private int id_client;
    private String ref_colis;
    
    public reclamation(){}

    public reclamation(int id, String text, int personne_reclame, String type_reclamation, Date date, int id_client, String ref_colis) {
        this.id = id;
        this.text = text;
        this.personne_reclame = personne_reclame;
        this.type_reclamation = type_reclamation;
        this.date = date;
        this.id_client = id_client;
        this.ref_colis = ref_colis;
    }
    
    
    
    public reclamation(int id, String text, String nom_du_reclame, String type_reclamation, Date date, int id_client, String ref_colis) {
        this.id = id;
        this.text = text;
        this.nom_du_reclame = nom_du_reclame;
        this.type_reclamation = type_reclamation;
        this.date = date;
        this.id_client = id_client;
        this.ref_colis = ref_colis;
    }

    public reclamation(String text, String type_reclamation, Date date, String ref_colis) {
        this.text = text;
        this.type_reclamation = type_reclamation;
        this.date = date;
        this.ref_colis = ref_colis;
    }

    public reclamation(String ref_colis, Date date, String type_reclamation, String text, String nom_du_reclame) {
        this.text = text;
        this.nom_du_reclame = nom_du_reclame;
        this.type_reclamation = type_reclamation;
        this.date = date;
        this.ref_colis = ref_colis;
    }
    
    
    

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getPersonne_reclame() {
        return personne_reclame;
    }

    public String getType_reclamation() {
        return type_reclamation;
    }

    public Date getDate() {
        return date;
    }

    public int getId_client() {
        return id_client;
    }

    public String getRef_colis() {
        return ref_colis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPersonne_reclame(int personne_reclame) {
        this.personne_reclame = personne_reclame;
    }

    public void setType_reclamation(String type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setRef_colis(String ref_colis) {
        this.ref_colis = ref_colis;
    }

    public String getNom_du_reclame() {
        return nom_du_reclame;
    }

    public void setNom_du_reclame(String nom_du_reclame) {
        this.nom_du_reclame = nom_du_reclame;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.text);
        hash = 59 * hash + this.personne_reclame;
        hash = 59 * hash + Objects.hashCode(this.nom_du_reclame);
        hash = 59 * hash + Objects.hashCode(this.type_reclamation);
        hash = 59 * hash + Objects.hashCode(this.date);
        hash = 59 * hash + this.id_client;
        hash = 59 * hash + Objects.hashCode(this.ref_colis);
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
        final reclamation other = (reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.personne_reclame != other.personne_reclame) {
            return false;
        }
        if (this.id_client != other.id_client) {
            return false;
        }
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
        return "reclamation{" + "id=" + id + ", text=" + text + ", personne_reclame=" + personne_reclame + ", nom_du_reclame=" + nom_du_reclame + ", type_reclamation=" + type_reclamation + ", date=" + date + ", id_client=" + id_client + ", ref_colis=" + ref_colis + '}';
    }
    
    

    
    

    
}

