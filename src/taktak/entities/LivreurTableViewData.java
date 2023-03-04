/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.entities;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Cheima
 */
public class LivreurTableViewData {
    private String ref;
    private String nom;
    private String destination;
    private String type;
    private String etat_colis;
    private ComboBox etat;
//    private Button button;

    public LivreurTableViewData(String ref,String nom, String destination, String type, String etat_colis) {
        this.ref = ref;
        this.nom = nom;
        this.destination = destination;
        this.type = type;
        this.etat_colis = etat_colis;
        this.etat=new ComboBox();
        this.etat.getItems().addAll("En cours", "Livrée", "Non livrée");
//        this.button= new Button("save");
    }


    public String getRef() {
        return ref;
    }

    public String getNom() {
        return nom;
    }
    
    public String getDestination() {
        return destination;
    }
     public String getType() {
        return type;
    }
       public String getEtat_colis() {
        return etat_colis;
    }
       
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEtat_colis(String etat_colis) {
        this.etat_colis = etat_colis;
    }
    public ComboBox getEtat(){
        return etat;
    }
    public void setEtat(ComboBox etat){
        this.etat=etat;
    }
//     public Button getButton(){
//        return button;
//    }
//    public void setButton(Button button){
//        this.button=button;
//    }
}


