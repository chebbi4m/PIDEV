/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.entities;

import java.util.Objects;

/**
 *
 * @author chebbi4m
 */
public class reclamation {
    private int id;
    private String text;
    private String nomPersonne;
    private String refColis;

    public reclamation(int id, String text, String nomPersonne, String refColis) {
        this.id = id;
        this.text = text;
        this.nomPersonne = nomPersonne;
        this.refColis = refColis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNomPersonne() {
        return nomPersonne;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    public String getRefColis() {
        return refColis;
    }

    public void setRefColis(String refColis) {
        this.refColis = refColis;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        if (!Objects.equals(this.refColis, other.refColis)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.nomPersonne, other.nomPersonne)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", text=" + text + ", nomPersonne=" + nomPersonne + ", refColis=" + refColis + '}';
    }
    
    
}

