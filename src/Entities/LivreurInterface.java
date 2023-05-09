package Entities;

public class LivreurInterface {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private int numtel;
    private int nbre_reclamation;
    private int nbre_colis_total;
    private int nbre_colis_courant;
    private String password;
    private int user_id;
    private String adresse;
    private int id_partenaire;
   
    // Constructors
    public LivreurInterface(int id,String nom, String prenom, String email, int numtel, int nbre_reclamation, int nbre_colis_total, int nbre_colis_courant, String password, int user_id, String adresse, int id_partenaire) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.nbre_reclamation = nbre_reclamation;
        this.nbre_colis_total = nbre_colis_total;
        this.nbre_colis_courant = nbre_colis_courant;
        this.password = password;
        this.user_id = user_id;
        this.adresse = adresse;
        this.id_partenaire = id_partenaire;
    }

    public LivreurInterface() {
    }
    
    // Getters
     public int getId(){
        return this.id;
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

    public int getNbre_reclamation() {
        return nbre_reclamation;
    }

    public int getNbre_colis_total() {
        return nbre_colis_total;
    }

    public int getNbre_colis_courant() {
        return nbre_colis_courant;
    }

    public String getPassword() {
        return password;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getId_partenaire() {
        return id_partenaire;
    }
    
    // Setters
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

    public void setNbre_reclamation(int nbre_reclamation) {
        this.nbre_reclamation = nbre_reclamation;
    }

    public void setNbre_colis_total(int nbre_colis_total) {
        this.nbre_colis_total = nbre_colis_total;
    }

    public void setNbre_colis_courant(int nbre_colis_courant) {
        this.nbre_colis_courant = nbre_colis_courant;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setId_partenaire(int id_partenaire) {
        this.id_partenaire = id_partenaire;
    }
    
    // toString
    @Override
    public String toString() {
        return "LivreurInterface{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", numtel=" + numtel +
                ", nbre_reclamation=" + nbre_reclamation +
                ", nbre_colis_total=" + nbre_colis_total +
                ", nbre_colis_courant=" + nbre_colis_courant +
                ", password='" + password + '\'' +
                ", user_id=" + user_id +
                ", adresse='" + adresse + '\'' +
                ", id_partenaire=" + id_partenaire +
                '}';
    }
}