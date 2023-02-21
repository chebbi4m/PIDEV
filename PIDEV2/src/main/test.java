package main;

import entities.colis;
import entities.livreur;
import services.colisService;
import services.livreurService;

public class test {
    public static void main (String[] args){
        colis cls = new colis (4,"chapeau", 50, 30, 50, 2, true,"Tunis", "Sousse", 2, 2);

        colisService cs = new colisService();
        livreur l = new livreur(1,"mootez","nasri","moe",20919999,"22KLM111");

        cs.ajoutercolis(cls);
        livreurService ls = new livreurService();
        ls.ajouterlivreur(l);
        ls.afficherleslivreurs();


        //Colis cl = new Colis (13,"chapeau", 100, 100, 50, 2, true,"Tunis", "Sousse", 2, 2);
        //cs.modifierColis(cl);
        //cs.supprimerColis(cls);

        // System.out.println(cs.afficherColis());


    }

    }

