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
    private String personne_reclame;
    private String type_reclamation;
    private Date date;
    private int id_client;
    private String ref_colis;
    private int stars;
    
    public reclamation(){}

    public reclamation(int id, String text, String personne_reclame, String type_reclamation, Date date, int id_client, String ref_colis, int stars) {
        this.id = id;
        this.text = text;
        this.personne_reclame = personne_reclame;
        this.type_reclamation = type_reclamation;
        this.date = date;
        this.id_client = id_client;
        this.ref_colis = ref_colis;
        this.stars = stars;
    }

    public reclamation(String text, String type_reclamation, Date date_reclamation, String ref_colis) {
        this.text = text;
        this.type_reclamation = type_reclamation;
        this.date = date;
        this.ref_colis = ref_colis;
    }

    public reclamation(String text, String type_reclamation, Date date_reclamation, String ref_colis, int stars) {
        this.text = text;
        this.type_reclamation = type_reclamation;
        this.date = date_reclamation;
        this.ref_colis = ref_colis;
        this.stars = stars;
    }

    public reclamation(int id,String text, int stars) {
        this.id = id;
        this.text = text;
        this.stars = stars;
    }



    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getPersonne_reclame() {
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

    public int getStars() {
        return stars;
    }
    
    
    
}

