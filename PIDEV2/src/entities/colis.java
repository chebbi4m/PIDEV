package entities;

public class colis {

    private int id;
    private String ref;
    private double hauteur;
    private double largeur;
    private double poids;
    private double prix;
    private boolean fragile;
    private String depart;
    private String destination;
    private int id_client;
    private int id_partenaire;

    public colis(){

    }

    public colis(int id, String ref, double hauteur, double largeur, double poids, double prix, boolean fragile,String depart, String destination, int id_client, int id_partenaire) {
        this.id = id;
        this.ref = ref;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.poids = poids;
        this.prix = prix;
        this.fragile = fragile;
        this.depart = depart;
        this.destination = destination;
        this.id_client = id_client;
        this.id_partenaire = id_partenaire;
    }



    // getters

    public int getId(){
        return this.id;
    }

    public String getRef() {
        return this.ref;
    }

    public double getHauteur() {
        return this.hauteur;
    }

    public double getLargeur() {
        return this.largeur;
    }

    public double getPoids() {
        return this.poids;
    }

    public double getPrix() {
        return this.prix;
    }

    public boolean getFragile() {
        return this.fragile;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return this.destination;
    }

    public int getId_client() {
        return this.id_client;
    }

    public int getId_partenaire() {
        return this.id_partenaire;
    }
    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_partenaire(int Id_partenaire) {
        this.id_partenaire= id_partenaire;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final colis other = (colis)  obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Colis{" + "ref=" + ref + ", hauteur=" + hauteur + ", largeur=" + largeur + ", poids=" + poids + ", prix=" + prix + ", fragile=" + fragile + ", destination=" + destination + ", id_client=" + id_client + ", id_partenaire=" + id_partenaire + '}';
    }





}