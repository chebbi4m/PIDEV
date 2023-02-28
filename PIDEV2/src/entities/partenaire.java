package entities;


public class partenaire {
    private Integer id ;
    private String nom;
    private String login;
    private String mdp;
    private int numtel;
    private String email;
    private String moyen_transport;
    private String zone;
    private Double prix_poids;
    private Double  prix_zone;
    private Boolean inflammable;
    private Boolean fragile;

    public partenaire(Integer id, String nom, String login, String mdp, int numtel, String email, String moyen_transport, String zone, Double prix_poids, Double prix_zone, Boolean inflammable, Boolean fragile) {
        this.id = id;
        this.nom = nom;
        this.login = login;
        this.mdp = mdp;
        this.numtel = numtel;
        this.email = email;
        this.moyen_transport = moyen_transport;
        this.zone = zone;
        this.prix_poids = prix_poids;
        this.prix_zone = prix_zone;
        this.inflammable = inflammable;
        this.fragile = fragile;
    }

    public partenaire() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoyen_transport() {
        return moyen_transport;
    }

    public void setMoyen_transport(String moyen_transport) {
        this.moyen_transport = moyen_transport;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Double getPrix_poids() {
        return prix_poids;
    }

    public void setPrix_poids(Double prix_poids) {
        this.prix_poids = prix_poids;
    }

    public Double getPrix_zone() {
        return prix_zone;
    }

    public void setPrix_zone(Double prix_zone) {
        this.prix_zone = prix_zone;
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

    @Override
    public String toString() {
        return "partenaire{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", login='" + login + '\'' +
                ", mdp='" + mdp + '\'' +
                ", numtel=" + numtel +
                ", email='" + email + '\'' +
                ", moyen_transport='" + moyen_transport + '\'' +
                ", zone='" + zone + '\'' +
                ", prix_poids=" + prix_poids +
                ", prix_zone=" + prix_zone +
                ", inflammable=" + inflammable +
                ", fragile=" + fragile +
                '}';
    }
}