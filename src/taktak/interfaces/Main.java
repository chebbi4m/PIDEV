/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taktak.interfaces;

/**
 *
 * @author LENOVO THINKPAD E15
 */

import java.util.Random;
import taktak.services.ColisService;
import taktak.entities.Colis;
import taktak.entities.RandomGenerator;


public class Main {
    
    public static void main(String[] args) {
       
       RandomGenerator generator = new RandomGenerator();
       String randomString = generator.generateRandomString();
       
    
       Colis cls = new Colis();
       cls.setRef(randomString);
       cls.setHauteur(10);
       cls.setLargeur(15);
       cls.setPoids(10);
       cls.setFragile(true);
       cls.setInflammable(true);
       cls.setDepart("Sousse");
       cls.setDestination("Paris");
       cls.setEtat_colis(cls.getEtat_colis());
       cls.setZone("Internationale");
       cls.setUrgent(false);
       cls.setId_client(1);
       cls.setId_livreur(0);
       
       if(cls.getPoids()<= 5){
           int[] array = new int[] {20, 30, 45, 50};
           Random rand = new Random();
           cls.setPrix(array[rand.nextInt(array.length)]);
       }
       else{
           int[] array = new int[] {60, 70, 80, 90};
           Random rand = new Random();
           cls.setPrix(array[rand.nextInt(array.length)]);
       }
       cls.setPrix(cls.getPrix());
       System.out.println("Votre prix est de " +cls.getPrix());
       
       ColisService cs = new ColisService();
       cs.ajouterColis(cls);
       //cs.supprimerColis(cls);
        
       System.out.println(cs.afficherColis());
        
    }
}
