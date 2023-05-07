package Entities;

import javax.persistence.*;

@Entity
@Table(name = "partenaire")
public class Partenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "numtel")
    private String numtel;

    @Column(name = "Zone")
    private String zone;

    @Column(name = "Prix_poids")
    private Double prixPoids;

    @Column(name = "Prix_zone")
    private String prixZone;

    @Column(name = "inflammable")
    private Boolean inflammable;

    @Column(name = "fragile")
    private Boolean fragile;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "user_id")
    private Integer userId;

    public Partenaire(int id, String nom, String email, String numtel, String zone, Double prixPoids, String prixZone, Boolean inflammable, Boolean fragile, String login, String password, Integer userId) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
        this.zone = zone;
        this.prixPoids = prixPoids;
        this.prixZone = prixZone;
        this.inflammable = inflammable;
        this.fragile = fragile;
        this.login = login;
        this.password = password;
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Double getPrixPoids() {
        return prixPoids;
    }

    public void setPrixPoids(Double prixPoids) {
        this.prixPoids = prixPoids;
    }

    public String getPrixZone() {
        return prixZone;
    }

    public void setPrixZone(String prixZone) {
        this.prixZone = prixZone;
    }

    public Boolean getInflammable() {
        return inflammable;
    }

    public void setInflammable(Boolean inflammable) {
        this.inflammable = inflammable;
    }

    public Boolean getFragile() {
        return fragile;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
}