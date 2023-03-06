/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author nedia
 */
public class devis {
    String nom;
    LocalDate date;
    String depart;
    String destination;
    int prix;
    
    public devis(){}

    public devis(String nom, LocalDate date, String depart, String destination, int prix) {
        this.nom = nom;
        this.date = date;
        this.depart = depart;
        this.destination = destination;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDepart() {
        return depart;
    }

    public String getDestination() {
        return destination;
    }

    public int getPrix() {
        return prix;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.nom);
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + Objects.hashCode(this.depart);
        hash = 79 * hash + Objects.hashCode(this.destination);
        hash = 79 * hash + this.prix;
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
        final devis other = (devis) obj;
        if (this.prix != other.prix) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.depart, other.depart)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "devis{" + "nom=" + nom + ", date=" + date + ", depart=" + depart + ", destination=" + destination + ", prix=" + prix + '}';
    }

    



    
    
    
    
    
}
