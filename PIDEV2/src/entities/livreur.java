package entities;

import java.util.Objects;

public class livreur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private int numtel;
    private String id_compte;

    public livreur(int id, String nom, String prenom, String email, int numtel, String id_compte) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.id_compte = id_compte;
    }

    public livreur() {

    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public int getNumtel() {
        return numtel;
    }

    public String getId_compte() {
        return id_compte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public void setId_compte(String id_compte) {
        this.id_compte = id_compte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        livreur livreur = (livreur) o;
        return id == livreur.id && numtel == livreur.numtel && nom.equals(livreur.nom) && prenom.equals(livreur.prenom) && email.equals(livreur.email) && id_compte.equals(livreur.id_compte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, email, numtel, id_compte);
    }

    @Override
    public String toString() {
        return "livreur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", numtel=" + numtel +
                ", id_compte='" + id_compte + '\'' +
                '}';
    }
}
