package Entities;

import javax.persistence.*;

@Entity
@Table(name = "livreur")
public class Livreur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom")

    private String nom;
    @Column(name = "prenom")

    private String prenom;
    @Column(name = "email")

    private String email;
    @Column(name = "numtel")

    private int numtel;
    @Column(name = "nbre_reclamation")

    private Integer nbreReclamation;
    @Column(name = "nbre_colis_total")

    private Integer nbreColisTotal;
    @Column(name = "nbre_colis_courant")

    private Integer nbreColisCourant;
    @Column(name = "password")

    private String password;
    @Column(name = "user_id")

    private Integer userId;
    @Column(name = "adresse")

    private String adresse;
    @Column(name = "id_partenaire")

    private Integer idPartenaire;

    public Livreur(int id, String nom, String prenom, String email, int numtel, Integer nbreReclamation, Integer nbreColisTotal, Integer nbreColisCourant, String password, Integer userId, String adresse, Integer idPartenaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.nbreReclamation = nbreReclamation;
        this.nbreColisTotal = nbreColisTotal;
        this.nbreColisCourant = nbreColisCourant;
        this.password = password;
        this.userId = userId;
        this.adresse = adresse;
        this.idPartenaire = idPartenaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public Integer getNbreReclamation() {
        return nbreReclamation;
    }

    public void setNbreReclamation(Integer nbreReclamation) {
        this.nbreReclamation = nbreReclamation;
    }

    public Integer getNbreColisTotal() {
        return nbreColisTotal;
    }

    public void setNbreColisTotal(Integer nbreColisTotal) {
        this.nbreColisTotal = nbreColisTotal;
    }

    public Integer getNbreColisCourant() {
        return nbreColisCourant;
    }

    public void setNbreColisCourant(Integer nbreColisCourant) {
        this.nbreColisCourant = nbreColisCourant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getIdPartenaire() {
        return idPartenaire;
    }

    public void setIdPartenaire(Integer idPartenaire) {
        this.idPartenaire = idPartenaire;
    }

    public String toString() {
        return nom + " " + prenom;
    }
}